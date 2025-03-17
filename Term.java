/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 17/03/25
 */

/**
 * Represents a term in the database.
 * A term is a component of a statement and is used for searching and comparison.
 */
public class Term {
  String term;
  
  /**
   * Constructs a new Term object with the specified term.
   */
  public Term(String term)
  {
    this.term = term;
  }
  
  /**
   * Returns a string representation of the term.
   */
  public String toString()
  {
    return this.term;
  }
  
  /**
   * Compares this term with another term for equality.
   * The comparison is case-insensitive.
   */
  public boolean equals(Term t)
  {
    if (t == null) 
    {
      return false;
    }
    return this.toString().equalsIgnoreCase(t.toString());
  }
  
  /**
   * Checks if this term contains the specified term or vice versa.
   * The comparison is case-insensitive.
   */
  public boolean contains(Term t)
  {
    if (t == null)
    {
      return false;
    }
    return this.toString().toLowerCase().contains(t.toString().toLowerCase()) || 
           t.toString().toLowerCase().contains(this.toString().toLowerCase());
  }
}