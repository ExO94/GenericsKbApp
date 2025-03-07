/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

public class Term extends Entry
{
    String term;
    
    public Term(String term)
    {
      this.term = term;
    }

    public String toString()
    {
      return this.term;
    }

    public boolean equals(Term t)
    {
      return this.toString().equalsIgnoreCase(t.toString());
    }

}
