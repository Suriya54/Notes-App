import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Write a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Write notes to file
    private static void writeNote(Scanner sc) {
        System.out.println("Enter your note (type 'END' on a new line to finish):");

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String line;
            while (true) {
                line = sc.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Note saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read notes from file
    private static void readNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found! Please add some first.");
            return;
        }

        System.out.println("\n--- Saved Notes ---");
        try (FileReader fr = new FileReader(FILE_NAME);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
