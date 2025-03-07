/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

 public class Statement
 {
    public Term term;
    public Tree tree;
    public Confidence confidence;

    public Statement()
    {
      this.term = null;
      this.tree = null;
      this.confidence = null;
    }
   
    public Statement(String statement)
    {
      for (int i = 0; i < 3; i ++)
      {
        int posTab = statement.indexOf("/t");

        if (i == 0)
        {
          term = new Term(statement(0,posTab));
          this.Term = term;
          statement = statement(posTab+1,statement.length()-1);
        }
        else if (i == 1)
        {
          tree = new Tree(statement(0,posTab));
          this.Tree = tree;
          statement = statement(posTab+1,statement.length()-1);
        }
        else 
        {
          confidence = new Confidence(Double.parseDouble(statement));
          this.Confidence = confidence;
        }
      }
    }

    public Term getTerm()
    {
      return this.term;
    }

    public Tree getTree()
    {
      return this.tree;
    }

    public Confidence getConfidence()
    {
      return this.confidence;
    }

   public String toString()
   {
      return this.term.toString() + "\t" + this.tree.toString() + "\t" + this.confidence.toString();
   }
    

 }