package hw3.util;


public enum Grade 
{
	A ("Grade.A", 4),
	B ("Grade.B", 3),
	C ("Grade.C", 2),
	D ("Grade.D", 1),
	F ("Grade.F", 0);

	private double numericValue;
    private String stringValue;
    
    Grade (String aStringValue , int aNumericValue ) 
    {
    	numericValue = aNumericValue;
    	stringValue = aStringValue;
    }
    
	public String toString() {
		return stringValue + " corresponds to numeric grade " + numericValue;
	} 
	
    public double getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(double numericValue) {
		this.numericValue = numericValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
}

