/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

public class Tree extends Statement
 {
    String tree;

    public Tree(String tree)
    {
      super();
      this.tree = tree;
    }

    public String toString()
    {
      return this.tree;
    }

    public booelan equals(Tree t)
    {
      return this.toString().equalsIgnoreCase(t.toString());
    }


 }