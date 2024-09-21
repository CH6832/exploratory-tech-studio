package com.dms;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();
        DocumentManager docManager = new DocumentManager();

        System.out.println("Welcome to the Document Management System!");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.authenticate(username, password)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("\n1. Add Document");
                System.out.println("2. Search Document");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter document title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter document content: ");
                        String content = scanner.nextLine();
                        docManager.addDocument(new Document(title, content));
                        System.out.println("Document added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter document title to search: ");
                        String searchTitle = scanner.nextLine();
                        Document doc = docManager.getDocumentByTitle(searchTitle);
                        if (doc != null) {
                            System.out.println("Document Found: " + doc);
                        } else {
                            System.out.println("Document not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
        scanner.close();
    }
}
