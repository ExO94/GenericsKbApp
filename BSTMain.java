/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 17/03/25
 */

import java.util.Scanner;

public class BSTMain 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String defaultFilePath = "GenericsKB.txt";
        BSTDatabase database = new BSTDatabase(defaultFilePath);

        while (true) 
        {
            System.out.println("=== Knowledge Base Menu ===");
            System.out.println("1. Add a new statement to the knowledge base");
            System.out.println("2. Update an existing statement in the knowledge base");
            System.out.println("3. Load and merge a new knowledge base from another file");
            System.out.println("4. Search for a statement in the knowledge base by term");
            System.out.println("5. Search for a statement in the knowledge base by term and tree");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            String action = scanner.nextLine();

            switch (action) 
            {
                case "1":
                    try 
                    {   
                        System.out.print("Enter term: ");
                        String term = scanner.nextLine();
                        System.out.print("Enter tree structure: ");
                        String tree = scanner.nextLine();
                        System.out.print("Enter confidence (0.0 to 1.0): ");
                        double confidence = Double.parseDouble(scanner.nextLine());
                        Statement newStatement = new Statement(term + "\t" + tree + "\t" + confidence);
                        database.addStatement(newStatement);
                        System.out.println("Statement added successfully.");
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Invalid confidence value. Please enter a valid number.");
                    }
                    break;

                case "2":
                    try 
                    {
                        System.out.print("Enter term: ");
                        String term = scanner.nextLine();
                        System.out.print("Enter tree structure: ");
                        String tree = scanner.nextLine();
                        System.out.print("Enter confidence (0.0 to 1.0): ");
                        double confidence = Double.parseDouble(scanner.nextLine());

                        Statement newStatement = new Statement(term + "\t" + tree + "\t" + confidence);
                        database.addStatement(newStatement);
                        System.out.println("Statement updated successfully.");
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Invalid confidence value. Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.print("Enter file path for new knowledge base: ");
                    String newFilePath = scanner.nextLine();
                    BSTDatabase newDatabase = new BSTDatabase(newFilePath);
                    database.mergeDatabase(newDatabase);
                    System.out.println("Databases merged successfully.");
                    break;

                case "4":
                    System.out.print("Enter term to search: ");
                    String searchTerm = scanner.nextLine();
                    String result = database.searchStatement(new Term(searchTerm));
                    System.out.println("Search result: " + result);
                    break;

                case "5":
                    System.out.print("Enter term to search: ");
                    String searchTerm2 = scanner.nextLine();
                    System.out.print("Enter tree structure to search: ");
                    String searchTree = scanner.nextLine();
                    String result2 = database.searchStatement(new Term(searchTerm2));
                    if (result2.equals("No matches found for term: " + searchTerm2)) {
                        System.out.println(result2);
                    } else {
                        System.out.println("Search result: " + result2);
                    }
                    break;

                case "6":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 6.");
                    break;
            }
        }
    }
}