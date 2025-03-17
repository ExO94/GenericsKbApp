/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

/**
 * Represents the confidence level of a statement in the database.
 * The confidence level is a numeric value that indicates the reliability or certainty of a statement.
 */
public class Confidence
{
   double confidenceValue;
   
   /**
    * Constructs a new Confidence object with the specified confidence value.
    */
   public Confidence(double confidence)
   {
       this.confidenceValue = confidence;
   }

   /**
    * Returns the confidence value of this object.
    */
   public double getValue()
   {
       return this.confidenceValue;
   }

   /**
    * Compares this confidence value with another confidence value to determine if this one is higher.
    */
   public boolean isHigherConfidence(Confidence con)
   {
       return (this.getValue() > con.getValue());
   }

   /**
    * Returns a string representation of the confidence value.
    */
   public String toString()
   {
       return this.getValue() + "";
   }
}