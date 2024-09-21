package com.dms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentManagerTest {

    private DocumentManager docManager;

    @BeforeEach
    public void setUp() {
        docManager = new DocumentManager();
    }

    @Test
    public void testAddDocument() {
        Document doc = new Document("Test Document", "This is a test document.");
        docManager.addDocument(doc);
        Document retrievedDoc = docManager.getDocumentByTitle("Test Document");
        assertNotNull(retrievedDoc, "Document should be successfully added and retrieved.");
        assertEquals(doc.getTitle(), retrievedDoc.getTitle(), "Document title should match.");
    }

    @Test
    public void testGetDocumentByNonExistentTitle() {
        Document retrievedDoc = docManager.getDocumentByTitle("Non-Existent Document");
        assertNull(retrievedDoc, "Document retrieval should return null for non-existent title.");
    }
}
