import java.io.File;

public class DirectoryTreeGenerator {

    public static void main(String[] args) {
        // Check if the directory path is provided
        if (args.length < 1) {
            System.out.println("Usage: java DirectoryTreeGenerator <directory-path>");
            System.exit(1);
        }

        String path = args[0];
        File rootDir = new File(path);

        // Check if the provided path is a directory
        if (!rootDir.isDirectory()) {
            System.out.println("The provided path is not a directory: " + path);
            System.exit(1);
        }

        // Generate the directory tree
        printDirectoryTree(rootDir, 0);
    }

    private static void printDirectoryTree(File dir, int level) {
        // Indentation based on the level in the directory tree
        String indent = " ".repeat(level * 4);
        System.out.println(indent + "+-- " + dir.getName());

        // Get all files and directories in the current directory
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursively print the directory tree for subdirectories
                    printDirectoryTree(file, level + 1);
                } else {
                    // Print the file name
                    System.out.println(indent + "    +-- " + file.getName());
                }
            }
        }
    }
}
