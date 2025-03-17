/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 17/03/25
 */

/**
 * Represents the tree of a statement in the database.
 * A tree is a component of a statement which describes the statements term.
 */
public class Tree 
{
    String tree;
    
    /**
     * Constructs a new Tree object with the specified tree.
     */
    public Tree(String tree)
    {
      this.tree = tree;
    }
    
    /**
     * Returns a string representation of the tree.
     */
    public String toString()
    {
      return this.tree;
    }
    
    /**
     * Compares this tree with another tree for equality.
     * The comparison is case-insensitive.
     */
    public boolean equals(Tree t)
    {
      if (t == null) 
      {
        return false;
      }
      return this.toString().equalsIgnoreCase(t.toString());
    }
    
    /**
     * Checks if this tree contains the specified tree or vice versa.
     * The comparison is case-insensitive.
     */
    public boolean contains(Tree t)
    {
      if (t == null)
      {
        return false;
      }
      return this.toString().toLowerCase().contains(t.toString().toLowerCase()) || 
             t.toString().toLowerCase().contains(this.toString().toLowerCase());
    }
}