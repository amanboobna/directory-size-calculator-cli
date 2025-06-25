
import java.util.*;

class Directory {
    String name;
    Directory parent;
    List<Directory> subdirectories = new ArrayList<>();
    List<FileItem> files = new ArrayList<>();

    Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    String getPath() {
        if (parent == null) return "/" + name;
        return parent.getPath() + "/" + name;
    }

    void addSubdirectory(Directory dir) {
        subdirectories.add(dir);
    }

    void addFile(FileItem file) {
        files.add(file);
    }
}

class FileItem {
    String name;
    long size; 

    FileItem(String name, long size) {
        this.name = name;
        this.size = size;
    }

    String getFormattedSize() {
        if (size < 1024) return size + " KB";
        if (size < 1024 * 1024) return String.format("%.1f MB", size / 1024.0);
        return String.format("%.1f GB", size / (1024.0 * 1024.0));
    }
}

public class FileSystemCLI {
    private static Directory root = new Directory("root", null);
    private static Directory current = root;

    public static void main(String[] args) {
        setupInitialData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the CLI File System. Type 'help' to see commands.");

        while (true) {
            System.out.print(current.getPath() + " > ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
            handleCommand(input);
        }
    }

    static void handleCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command) {
            case "current":
                System.out.println(current.getPath());
                break;
            case "cd":
                if (parts.length < 2) {
                    System.out.println("Usage: cd <dirname>");
                } else {
                    changeDirectory(parts[1]);
                }
                break;
            case "cd..":
                if (current.parent != null) {
                    current = current.parent;
                } else {
                    System.out.println("Already at root");
                }
                break;
            case "reset":
                current = root;
                break;
            case "ls":
                listDirectory();
                break;
            case "size":
                long size = calculateSize(current);
                System.out.println("Total size: " + formatSize(size) + " (" + size + " KB)");
                break;
            case "help":
                printHelp();
                break;
            default:
                System.out.println("Unknown command. Type 'help' to list available commands.");
        }
    }

    static void changeDirectory(String name) {
        for (Directory dir : current.subdirectories) {
            if (dir.name.equals(name)) {
                current = dir;
                return;
            }
        }
        System.out.println("Directory not found: " + name);
    }

    static void listDirectory() {
        if (!current.subdirectories.isEmpty()) {
            System.out.println("Directories:");
            for (Directory dir : current.subdirectories) {
                long size = calculateSize(dir);
                System.out.println("  " + dir.name + " (" + formatSize(size) + ")");
            }
        }

        if (!current.files.isEmpty()) {
            System.out.println("Files:");
            for (FileItem file : current.files) {
                System.out.println("  " + file.name + " (" + file.getFormattedSize() + ")");
            }
        }

        if (current.subdirectories.isEmpty() && current.files.isEmpty()) {
            System.out.println("Directory is empty");
        }
    }

    static long calculateSize(Directory dir) {
        long total = 0;
        for (FileItem file : dir.files) total += file.size;
        for (Directory sub : dir.subdirectories) total += calculateSize(sub);
        return total;
    }

    static String formatSize(long sizeInKB) {
        if (sizeInKB < 1024) return sizeInKB + " KB";
        if (sizeInKB < 1024 * 1024) return String.format("%.1f MB", sizeInKB / 1024.0);
        return String.format("%.1f GB", sizeInKB / (1024.0 * 1024.0));
    }

    static void setupInitialData() {
        Directory documents = new Directory("documents", root);
        Directory pictures = new Directory("pictures", root);
        Directory work = new Directory("work", documents);
        Directory personal = new Directory("personal", documents);
        Directory vacation = new Directory("vacation", pictures);

        root.addSubdirectory(documents);
        root.addSubdirectory(pictures);
        documents.addSubdirectory(work);
        documents.addSubdirectory(personal);
        pictures.addSubdirectory(vacation);

        root.addFile(new FileItem("readme.txt", 10));
        documents.addFile(new FileItem("doc1.pdf", 50));
        documents.addFile(new FileItem("doc2.pdf", 75));
        work.addFile(new FileItem("report.docx", 120));
        work.addFile(new FileItem("presentation.pptx", 250));
        personal.addFile(new FileItem("diary.txt", 1500));
        pictures.addFile(new FileItem("photo1.jpg", 200));
        pictures.addFile(new FileItem("photo2.jpg", 150));
        vacation.addFile(new FileItem("beach.jpg", 300));
        vacation.addFile(new FileItem("mountain.jpg", 280));
    }

    static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  current            - Show current path");
        System.out.println("  cd <dirname>   - Change into subdirectory");
        System.out.println("  cd..           - Move up to parent directory");
        System.out.println("  ls             - List contents of current directory");
        System.out.println("  size           - Calculate total size of current directory");
        System.out.println("  reset          - Go back to root directory");
        System.out.println("  help           - Show this help message");
        System.out.println("  exit           - Quit the program");
    }


}


