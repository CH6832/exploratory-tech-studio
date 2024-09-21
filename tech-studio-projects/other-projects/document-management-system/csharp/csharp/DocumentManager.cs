using System.Collections.Generic;
using System.Linq;

namespace DocumentManagementSystem
{
    public class DocumentManager
    {
        private readonly List<Document> documents;

        public DocumentManager()
        {
            documents = new List<Document>();
        }

        public void AddDocument(Document document)
        {
            documents.Add(document);
        }

        public Document GetDocumentByTitle(string title)
        {
            return documents.FirstOrDefault(doc => doc.Title.Equals(title, System.StringComparison.OrdinalIgnoreCase));
        }
    }
}
