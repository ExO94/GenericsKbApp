/*
  Student Number: FRTETH003
  Name : Ethan Fortuin
  Date : 07/03/25
 */

 public class Confidence
 {
    double confidenceValue;
    
    Confidence(double confidence)
    {
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


 }