/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

 import java.io.File;
 import java.io.FileWriter;
 import java.util.Scanner;

 public class Database
 {
    File database;
    Scanner scanner;
    Statement[] databaseStatments = new Statement[100_000];
    

    public Database(String filePath)
    {
        int i = 0;
        database = new File(filePath);
        scanner = new scanner(database);

        while (scanner.hasNextLine())
        {
            Statement statement = new Statement(scanner.nextLine());
            databaseStatments[i] = statement;
            i++;
        }
    }

    public void updateDatabase()
    {
    }

    public void mergeDatabase(Database database)
    {
    }

    public void addStatement(Statement statement)
    {
    }

    public String searchStatement(Term term)
    {
        for (Statement statement: databaseStatments)
        {
            if (term.equals(statement.getTerm()))
            {
                String output = "Statement found: " + statement.getTree().toString() + "/n(Confidence score: "+ statement.getConfidence().toString() + ")";
                break;
            }
        }

        return output;
    }

    public String searchStatement(Term term, Tree tree)
    {
        for (Statement statement: databaseStatments)
        {
            if (term.equals(statement.getTerm()) && tree.equals(statement.getTree()))
            {
                String output = "The statement was found and has a confidence score of "+ statement.getConfidence().toString()+".";
                break;
            }
        }

        return output;

    }



}


 