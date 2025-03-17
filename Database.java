/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 17/03/25
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 import java.io.PrintWriter;
 
 /**
  * Represents a database of statements stored in a file.
  * Each statement contains a term, a tree, and a confidence score.
  */
 public class Database
 {
    File database;
    Scanner scanner;
    String filePath;
    Statement[] databaseStatments = new Statement[100_000];
    
    /**
     * Constructs a Database object by reading statements from the specified file.
     * The file must contain 100,000 lines or less, each representing a statement.
     */
    public Database(String fp)
    {
        filePath = fp;
        database = new File(filePath);
        try 
        {
            scanner = new Scanner(database);
            int i = 0;
            while (scanner.hasNextLine() && i < databaseStatments.length) 
            {
                Statement statement = new Statement(scanner.nextLine());
                databaseStatments[i] = statement;
                i++;
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + e.getMessage());
        }
    }
 
    /**
     * Updates the database file with the current state of the statements array.
     * Overwrites the existing file with the updated statements.
     */
    public void updateDatabase()
    {
        try (PrintWriter writer = new PrintWriter(filePath)) 
        {
            for (Statement statement : databaseStatments) 
            {
                if (statement != null)
                {
                    writer.println(statement.toString());
                }
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
 
    /**
     * Merges the current database with another database.
     * For statements with the same term, the one with the higher confidence is retained.
     */
    public void mergeDatabase(Database database)
    {
        for (int i = 0; i < this.databaseStatments.length; i++)
        {
            if (this.databaseStatments[i] == null) continue;
            
            for (int k = 0; k < database.databaseStatments.length; k++)
            {
                if (database.databaseStatments[k] == null) continue;
                
                if (this.databaseStatments[i].getTerm().equals(database.databaseStatments[k].getTerm()))
                {
                    if (database.databaseStatments[k].getConfidence().isHigherConfidence(this.databaseStatments[i].getConfidence()))
                    {
                        this.databaseStatments[i] = database.databaseStatments[k];
                    }
                }
            }
        }
        updateDatabase();
    }
 
    /**
     * Adds a new statement to the database.
     * If a statement with the same term already exists, it is updated if the new statement has higher confidence.
     * If no matching term exists, the statement is not added.
     */
    public void addStatement(Statement newStatement)
    {
        boolean termExists = false;
        
        for (int i = 0; i < this.databaseStatments.length; i++)
        {
            if (this.databaseStatments[i] == null) continue;
            
            if (this.databaseStatments[i].getTerm().equals(newStatement.getTerm()))
            {
                termExists = true;
                if (newStatement.getConfidence().isHigherConfidence(this.databaseStatments[i].getConfidence()))
                {
                    databaseStatments[i] = newStatement;
                    System.out.println("Statement updated with higher confidence value.");
                }
                else
                {
                    System.out.println("Existing statement has higher or equal confidence. No update made.");
                }
                break;
            }
        }
        
        if (!termExists)
        {
            System.out.println("Term does not exist in database. No new statement added.");
        }
        
        updateDatabase();
    }
 
    /**
     * Searches for a statement in the database by its term.
     * Supports partial matching.
     */
    public String searchStatement(Term term)
    {
       StringBuilder results = new StringBuilder();
       boolean foundMatch = false;
       
       for (Statement statement: this.databaseStatments)
       {
         if (statement == null) continue;
         

         if (term.equals(statement.getTerm()))
         {
             results.append("Exact match found: ").append(statement.getTree().toString())
                   .append("\n(Confidence score: ").append(statement.getConfidence().toString()).append(")\n");
             foundMatch = true;
         }
         else if (statement.getTerm().toString().toLowerCase().contains(term.toString().toLowerCase()) || 
                  term.toString().toLowerCase().contains(statement.getTerm().toString().toLowerCase()))
         {
             results.append("Partial match found: Term '").append(statement.getTerm().toString())
                   .append("': ").append(statement.getTree().toString())
                   .append("\n(Confidence score: ").append(statement.getConfidence().toString()).append(")\n");
             foundMatch = true;
         }
       }
       
       if (foundMatch) 
       {
           return results.toString();
       } else {
           return "No matches found for term: " + term.toString();
       }
    }
 
    /**
     * Searches for a statement in the database by its term and tree.
     * Supports partial matching.
     */
    public String searchStatement(Term term, Tree tree)
    {
        StringBuilder results = new StringBuilder();
        boolean foundMatch = false;
        
        for (Statement statement: this.databaseStatments)
        {
            if (statement == null) continue;
            
            boolean termMatch = false;
            boolean treeMatch = false;
            
    
            if (term.equals(statement.getTerm())) 
            {
                termMatch = true;
            } else if (statement.getTerm().toString().toLowerCase().contains(term.toString().toLowerCase()) || 
                       term.toString().toLowerCase().contains(statement.getTerm().toString().toLowerCase())) {
                termMatch = true;
            }
            
        
            if (tree.equals(statement.getTree())) 
            {
                treeMatch = true;
            } else if (statement.getTree().toString().toLowerCase().contains(tree.toString().toLowerCase()) || 
                       tree.toString().toLowerCase().contains(statement.getTree().toString().toLowerCase())) {
                treeMatch = true;
            }
            
        
            if (termMatch && treeMatch) 
            {
                results.append("Match found: Term '").append(statement.getTerm().toString())
                      .append("', Tree '").append(statement.getTree().toString())
                      .append("'\n(Confidence score: ").append(statement.getConfidence().toString()).append(")\n");
                foundMatch = true;
            }
        }
        
        if (foundMatch)
        {
            return results.toString();
        } else 
        {
            return "No matches found for the given term and tree.";
        }
    }
 }