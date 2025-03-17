/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 15/03/25
 */

 import java.util.Scanner;

 public class Main
 {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String defaultFilePath = "GenericsKB.txt";
        Database database = new Database(defaultFilePath);

        while (true) 
        {
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Add a new statement to the knowledge base");
            System.out.println("2. Load and merge a new knowledge base from another file");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Search for a statement in the knowledge base by term and sentence");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            String action = scanner.nextLine();

            switch (action) 
            {
                case "1":
                    try 
                    {
                        System.out.print("Enter the term: ");
                        String term = scanner.nextLine();
                        System.out.print("Enter the tree (sentence structure): ");
                        String tree = scanner.nextLine();
                        System.out.print("Enter the confidence value: ");
                        double confidence = Double.parseDouble(scanner.nextLine());

                        Statement newStatement = new Statement(term + "\t" + tree + "\t" + confidence);
                        database.addStatement(newStatement);
                        System.out.println("Statement added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid confidence value. Please enter a valid number.");
                    }
                    break;

                case "2":
                    System.out.print("Enter the file path of the new knowledge base: ");
                    String newFilePath = scanner.nextLine();
                    Database newDatabase = new Database(newFilePath);
                    database.mergeDatabase(newDatabase);
                    System.out.println("New knowledge base loaded and merged successfully.");
                    break;

                case "3":
                    System.out.print("Enter the term to search: ");
                    String searchTerm = scanner.nextLine();
                    String result = database.searchStatement(new Term(searchTerm));
                    System.out.println(result);
                    break;

                case "4":
                    System.out.print("Enter the term to search: ");
                    String searchTerm2 = scanner.nextLine();
                    System.out.print("Enter the tree (sentence structure) to search: ");
                    String searchTree = scanner.nextLine();
                    String result2 = database.searchStatement(new Term(searchTerm2), new Tree(searchTree));
                    System.out.println(result2);
                    break;

                case "5":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default: 
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
 }