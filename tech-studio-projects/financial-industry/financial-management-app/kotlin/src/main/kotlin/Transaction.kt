import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val title: String,
    val amount: Double,
    val date: Date,
    val category: String
)
