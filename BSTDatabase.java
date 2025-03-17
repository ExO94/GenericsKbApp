/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 17/03/25
 */

import java.io.*;
import java.util.*;

/**
 * Represents a simple database using a Binary Search Tree (BST) to store Statements.
 * Supports loading from a file, searching, adding, and merging databases.
 */
public class BSTDatabase 
{
    private BSTNode root;
    private String filePath;

    /**
     * Internal class representing a node in the BST.
     */
    private class BSTNode 
    {
        Statement statement;
        BSTNode left, right;

        BSTNode(Statement statement) 
        {
            this.statement = statement;
        }
    }

    /**
     * Constructs a new BSTDatabase and loads data from the given file.
     *
     * @param filePath Path to the database file.
     */
    public BSTDatabase(String filePath) 
    {
        this.filePath = filePath;
        loadDatabase();
    }

    /**
     * Loads the database from the specified file.
     */
    private void loadDatabase() 
    {
        try (Scanner scanner = new Scanner(new File(filePath))) 
        {
            while (scanner.hasNextLine()) 
            {
                addStatement(new Statement(scanner.nextLine()), false);
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found. Starting with an empty database.");
        }
    }

    /**
     * Updates the database file to reflect the current state of the BST.
     */
    private void updateDatabase() 
    {
        try (PrintWriter writer = new PrintWriter(filePath)) 
        {
            inorderTraversal(root, writer);
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Performs an inorder traversal of the BST and writes statements to the given writer.
     */
    private void inorderTraversal(BSTNode node, PrintWriter writer) {
        if (node != null) {
            inorderTraversal(node.left, writer);
            writer.println(node.statement.toString());
            inorderTraversal(node.right, writer);
        }
    }

    /**
     * Adds a new statement to the database. If a statement with the same term exists, it's updated only if the new one has higher confidence.
     */
    public void addStatement(Statement newStatement) {
        addStatement(newStatement, true);
    }

    /**
     * Adds a statement to the BST. Optionally updates the file.
     */
    private void addStatement(Statement newStatement, boolean update) {
        root = addStatementRecursive(root, newStatement);
        if (update) 
        {
            updateDatabase();
        }
    }

    /**
     * Recursively adds a statement to the BST.
     */
    private BSTNode addStatementRecursive(BSTNode node, Statement newStatement) {
        if (node == null) 
        {
            return new BSTNode(newStatement);
        }

        int comparison = newStatement.getTerm().toString().compareToIgnoreCase(node.statement.getTerm().toString());
        if (comparison == 0) 
        {
            if (newStatement.getConfidence().isHigherConfidence(node.statement.getConfidence())) 
            {
                node.statement = newStatement;
            }
        } 
        else if (comparison < 0) 
        {
            node.left = addStatementRecursive(node.left, newStatement);
        } 
        else 
        {
            node.right = addStatementRecursive(node.right, newStatement);
        }
        return node;
    }

    /**
     * Checks if a database term contains the search term.
     */
    private boolean containsSearchTerm(String dbTerm, String searchTerm) 
    {
        dbTerm = dbTerm.toLowerCase();
        searchTerm = searchTerm.toLowerCase();
        return dbTerm.contains(searchTerm);
    }

    /**
     * Searches for a statement in the database by its term.
     */
    public String searchStatement(Term term) 
    {
        if (term == null) {
            return "Search term cannot be null";
        }
    
        List<Statement> matchingStatements = new ArrayList<>();
        searchByTermRecursive(root, term, matchingStatements);
    
        if (matchingStatements.isEmpty()) 
        {
            return "No matches found for term: " + term;
        }
    
        StringBuilder results = new StringBuilder();
        for (Statement statement : matchingStatements) 
        {
            if (term.toString().equalsIgnoreCase(statement.getTerm().toString())) 
            {
                results.append("Exact match found: ").append(statement.getTree().toString())
                      .append("\n(Confidence score: ").append(statement.getConfidence().toString()).append(")\n");
            } 
            else 
            {
                results.append("Partial match found: Term '").append(statement.getTerm().toString())
                      .append("': ").append(statement.getTree().toString())
                      .append("\n(Confidence score: ").append(statement.getConfidence().toString()).append(")\n");
            }
        }
        return results.toString();
    }
    
    private void searchByTermRecursive(BSTNode node, Term term, List<Statement> results) 
    {
        if (node == null) 
        {
            return;
        }
    
        int comparison = term.toString().compareToIgnoreCase(node.statement.getTerm().toString());
        
        if (comparison == 0 || containsSearchTerm(node.statement.getTerm().toString(), term.toString())) 
        {
            results.add(node.statement);
        }
    
        if (comparison <= 0) 
        {
            searchByTermRecursive(node.left, term, results);
        }
        
        if (comparison >= 0 || containsSearchTerm(node.statement.getTerm().toString(), term.toString())) 
        {
            searchByTermRecursive(node.right, term, results);
        }
    }

    /**
     * Merges another BSTDatabase into this one. Keeps statements with the highest confidence when merging.
     */
    public void mergeDatabase(BSTDatabase otherDb) 
    {
        mergeDatabaseRecursive(otherDb.root);
        updateDatabase();
        System.out.println("Databases merged successfully.");
    }

    /**
     * Recursively merges another BST into this one.
     */
    private void mergeDatabaseRecursive(BSTNode node) 
    {
        if (node != null) 
        {
            addStatement(node.statement, false);
            mergeDatabaseRecursive(node.left);
            mergeDatabaseRecursive(node.right);
        }
    }
}