/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

import com.sun.source.tree.Tree;

public class tree extends Entry
 {
    String tree;

    public tree(String tree)
    {
      this.tree = tree;
    }

    public String toString()
    {
      return this.tree;
    }

    public equals(Tree t)
    {
      return this.toString().equalsIgnoreCase(t.toString());
    }


 }