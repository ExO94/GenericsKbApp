/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

  /**
   * Represents a statement in the database.
   * A statement consists of a term, a tree, and a confidence value.
   */
 public class Statement
 {
    public Term term;
    public Tree tree;
    public Confidence confidence;

    /**
     * Constructs a new Statement object with default values (null for all fields).
     */
    public Statement()
    {
      this.term = null;
      this.tree = null;
      this.confidence = null;
    }
   
    /**
     * Constructs a new Statement object by parsing a string representation of the statement.
     * The string is expected to be in the format: "term\ttree\tconfidence".
     */
    public Statement(String statement)
    {
      String[] parts = statement.split("\t"); 
      this.term = new Term(parts[0]); 
      this.tree = new Tree(parts[1]);
      this.confidence = new Confidence(Double.parseDouble(parts[2]));
    }


    /**
     * Returns the term associated with this statement.
     */
    public Term getTerm()
    {
      return this.term;
    }

    /**
     * Returns the tree associated with this statement.
     */
    public Tree getTree()
    {
      return this.tree;
    }

    /**
     * Returns the confidence value associated with this statement.
     */
    public Confidence getConfidence()
    {
      return this.confidence;
    }

    /**
     * Returns a string representation of the statement in the format: "term\ttree\tconfidence".
     */
   public String toString()
   {
      return this.term.toString() + "\t" + this.tree.toString() + "\t" + this.confidence.toString();
   }
    

 }