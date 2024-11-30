package db

import (
	"database/sql"
	"fmt"
	"log"
)

// Migration represents a single database migration.
type Migration struct {
	ID       string
	Up       string // SQL for migrating "up"
	Down     string // SQL for rolling back
	Applied  bool
}

// MigrationManager manages the execution and tracking of database migrations.
type MigrationManager struct {
	db         *sql.DB
	migrations []Migration
}

// NewMigrationManager initializes a MigrationManager with a database connection.
func NewMigrationManager(db *sql.DB) *MigrationManager {
	return &MigrationManager{
		db:         db,
		migrations: []Migration{},
	}
}

// RegisterMigration registers a new migration.
func (mm *MigrationManager) RegisterMigration(id, up, down string) {
	mm.migrations = append(mm.migrations, Migration{
		ID:      id,
		Up:      up,
		Down:    down,
		Applied: false,
	})
}

// ApplyMigrations applies all pending migrations.
func (mm *MigrationManager) ApplyMigrations() error {
	if err := mm.ensureMigrationTable(); err != nil {
		return err
	}

	for _, migration := range mm.migrations {
		applied, err := mm.isMigrationApplied(migration.ID)
		if err != nil {
			return err
		}

		if !applied {
			log.Printf("Applying migration: %s\n", migration.ID)
			if _, err := mm.db.Exec(migration.Up); err != nil {
				return fmt.Errorf("failed to apply migration %s: %v", migration.ID, err)
			}

			if err := mm.markMigrationAsApplied(migration.ID); err != nil {
				return err
			}
		}
	}
	log.Println("All migrations applied successfully.")
	return nil
}

// RollbackMigration rolls back the last applied migration.
func (mm *MigrationManager) RollbackMigration() error {
	if err := mm.ensureMigrationTable(); err != nil {
		return err
	}

	var lastMigration *Migration
	for i := len(mm.migrations) - 1; i >= 0; i-- {
		applied, err := mm.isMigrationApplied(mm.migrations[i].ID)
		if err != nil {
			return err
		}

		if applied {
			lastMigration = &mm.migrations[i]
			break
		}
	}

	if lastMigration == nil {
		log.Println("No migrations to roll back.")
		return nil
	}

	log.Printf("Rolling back migration: %s\n", lastMigration.ID)
	if _, err := mm.db.Exec(lastMigration.Down); err != nil {
		return fmt.Errorf("failed to rollback migration %s: %v", lastMigration.ID, err)
	}

	if err := mm.markMigrationAsUnapplied(lastMigration.ID); err != nil {
		return err
	}

	log.Println("Migration rolled back successfully.")
	return nil
}

// ensureMigrationTable creates a migrations tracking table if it doesn't exist.
func (mm *MigrationManager) ensureMigrationTable() error {
	const query = `
	CREATE TABLE IF NOT EXISTS migrations (
		id TEXT PRIMARY KEY,
		applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	)`
	_, err := mm.db.Exec(query)
	return err
}

// isMigrationApplied checks if a migration has already been applied.
func (mm *MigrationManager) isMigrationApplied(id string) (bool, error) {
	const query = `SELECT COUNT(*) FROM migrations WHERE id = ?`
	var count int
	if err := mm.db.Query
