/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

 public class Confidence extends Statement
 {
    double confidenceValue;
    
    public Confidence(double confidence)
    {
        super();
        this.confidenceValue = confidence;
    }

    public double getValue()
    {
        return this.confidenceValue;
    }

    public boolean isHigherConfidence(Confidence con1, Confidence con2)
    {
        return (con1.getValue() > con2.getValue());
    }

    public String toString()
    {
        return this.getValue() + "";
    }


 }