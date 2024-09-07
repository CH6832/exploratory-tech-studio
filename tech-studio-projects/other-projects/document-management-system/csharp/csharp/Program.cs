using System;
using System.Collections.Generic;

namespace DocumentManagementSystem
{
    class Program
    {
        static void Main(string[] args)
        {
            AuthenticationService authService = new AuthenticationService();
            DocumentManager docManager = new DocumentManager();

            Console.WriteLine("Welcome to the Document Management System!");

            Console.Write("Enter username: ");
            string username = Console.ReadLine();
            Console.Write("Enter password: ");
            string password = Console.ReadLine();

            if (authService.Authenticate(username, password))
            {
                Console.WriteLine("Login successful!");

                while (true)
                {
                    Console.WriteLine("\n1. Add Document");
                    Console.WriteLine("2. Search Document");
                    Console.WriteLine("3. Exit");
                    Console.Write("Choose an option: ");
                    if (int.TryParse(Console.ReadLine(), out int choice))
                    {
                        switch (choice)
                        {
                            case 1:
                                Console.Write("Enter document title: ");
                                string title = Console.ReadLine();
                                Console.Write("Enter document content: ");
                                string content = Console.ReadLine();
                                docManager.AddDocument(new Document(title, content));
                                Console.WriteLine("Document added successfully!");
                                break;

                            case 2:
                                Console.Write("Enter document title to search: ");
                                string searchTitle = Console.ReadLine();
                                Document doc = docManager.GetDocumentByTitle(searchTitle);
                                if (doc != null)
                                {
                                    Console.WriteLine("Document Found: " + doc);
                                }
                                else
                                {
                                    Console.WriteLine("Document not found.");
                                }
                                break;

                            case 3:
                                Console.WriteLine("Exiting...");
                                return;

                            default:
                                Console.WriteLine("Invalid option. Try again.");
                                break;
                        }
                    }
                    else
                    {
                        Console.WriteLine("Invalid input. Please enter a number.");
                    }
                }
            }
            else
            {
                Console.WriteLine("Authentication failed. Exiting...");
            }
        }
    }

    // Placeholder classes to match Java code structure
    class AuthenticationService
    {
        public bool Authenticate(string username, string password)
        {
            // Implement authentication logic here
            return true; // Placeholder
        }
    }

    class DocumentManager
    {
        private readonly List<Document> documents = new List<Document>();

        public void AddDocument(Document document)
        {
            documents.Add(document);
        }

        public Document GetDocumentByTitle(string title)
        {
            return documents.Find(doc => doc.Title == title);
        }
    }

    class Document
    {
        public string Title { get; }
        public string Content { get; }

        public Document(string title, string content)
        {
            Title = title;
            Content = content;
        }

        public override string ToString()
        {
            return $"Title: {Title}, Content: {Content}";
        }
    }
}
