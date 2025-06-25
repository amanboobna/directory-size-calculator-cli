# 🗂️ Java CLI File System Simulator

This project is a command-line Java application that simulates a file system. It enables navigation between directories, listing contents, checking sizes, and more — all without requiring a server or external tools like Postman or curl.

---

## 🚀 Features

- **Command-line navigation**
  - `cd <dirname>` – Move into a subdirectory
  - `cd..` – Move one level up to the parent directory
  - `reset` – Reset to the root directory
  - `current` – Display the current path
- **File and Directory Listing**
  - `ls` – Lists subdirectories and files with readable sizes
- **Size Calculation**
  - `size` – Recursively calculates the total size of the current directory
- **Help & Exit**
  - `help` – Displays available commands
  - `exit` – Exits the program
- **Readable Size Formatting**
  - Converts file sizes from KB to MB/GB based on their total

---

## 🧠 Sample Directory Structure

```
/root
  ├── readme.txt (10 KB)
  ├── documents/
  │   ├── doc1.pdf (50 KB)
  │   ├── doc2.pdf (75 KB)
  │   ├── work/
  │   │   ├── report.docx (120 KB)
  │   │   └── presentation.pptx (250 KB)
  │   └── personal/
  │       └── diary.txt (1500 KB)
  └── pictures/
      ├── photo1.jpg (200 KB)
      ├── photo2.jpg (150 KB)
      └── vacation/
          ├── beach.jpg (300 KB)
          └── mountain.jpg (280 KB)
```

---

## 🛠️ How to Run

### ✅ Prerequisites

- Java 8 or above installed on your system

### ▶️ Steps

1. **Save the file**  
   Save the code in a file called `FileSystemCLI.java`.

2. **Compile the program**
   ```bash
   javac FileSystemCLI.java
   ```

3. **Run the program**
   ```bash
   java FileSystemCLI
   ```

4. **Use commands**
   Start typing commands like `ls`, `cd documents`, `size`, etc.

---

## 📄 Sample Commands

```bash
current           # Show current path
ls                # List contents
cd documents      # Move into 'documents' directory
cd..              # Move to parent
size              # Calculate size
reset             # Reset to root
exit              # Quit program
```

---

## 👤 Author

**Aman Boobna**  
NYU Computer Science Graduate  
📧 ab10465@nyu.edu  
🔗 [LinkedIn](https://linkedin.com/in/aman-boobna/) | [GitHub](https://github.com/amanboobna)
