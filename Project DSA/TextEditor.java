
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class TextEditor {

    static LinkedList<Character> text = new LinkedList<>();

    static Stack<String> undo = new Stack<>();
    static Stack<String> redo = new Stack<>();

    // Convert LinkedList into String
    static String getText() {
        StringBuilder sb = new StringBuilder();

        for (char ch : text) {
            sb.append(ch);
        }

        return sb.toString();
    }

    // Restore text into LinkedList
    static void setText(String s) {
        text.clear();

        for (char ch : s.toCharArray()) {
            text.addLast(ch);
        }
    }

    // Insert text
    static void insert(String s) {
        if (!s.isEmpty()) {
            undo.push(getText());

            for (char ch : s.toCharArray()) {
                text.addLast(ch);
            }

            redo.clear();
            System.out.println("Text inserted successfully!");
        }
    }

    // Delete last character
    static void delete() {
        if (!text.isEmpty()) {
            undo.push(getText());
            text.removeLast();
            redo.clear();

            System.out.println("Last character deleted!");
        } else {
            System.out.println("Nothing to delete!");
        }
    }

    // Undo operation
    static void undo() {
        if (!undo.isEmpty()) {
            redo.push(getText());
            setText(undo.pop());

            System.out.println("Undo successful!");
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    // Redo operation
    static void redo() {
        if (!redo.isEmpty()) {
            undo.push(getText());
            setText(redo.pop());

            System.out.println("Redo successful!");
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    // Display text
    static void display() {
        System.out.println("Text: " + getText());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- TEXT EDITOR ---");
            System.out.println("1. Insert Text");
            System.out.println("2. Delete Last Character");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Display Text");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter text: ");
                    insert(sc.nextLine());
                    break;

                case 2:
                    delete();
                    break;

                case 3:
                    undo();
                    break;

                case 4:
                    redo();
                    break;

                case 5:
                    display();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
