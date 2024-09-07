package com.dms;

import java.util.ArrayList;
import java.util.List;

public class DocumentManager {
    private List<Document> documents;

    public DocumentManager() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public Document getDocumentByTitle(String title) {
        for (Document doc : documents) {
            if (doc.getTitle().equalsIgnoreCase(title)) {
                return doc;
            }
        }
        return null;
    }
}
