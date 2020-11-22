package hw3.main;

import java.util.ArrayList; 


public class Transcript {
	
	private int studentID;
	private double GPA; 
	int numOfcourse=0;
	private ArrayList<CourseGrade> courseGrade ;
	
	public Transcript(int studentId)
	{
		setStudentID(studentId);
		GPA = 0.0;
		courseGrade = new ArrayList<>();
	}
	
	public double getAverageGpa() 
	{
		double result = 0;
		int creditTotal = 0;
		
		for( int i=0 ; i<numOfcourse ; i++ ){
	           result += courseGrade.get(i).getGradeTaken().getNumericValue() * courseGrade.get(i).getCourseCredit();
	           creditTotal += courseGrade.get(i).getCourseCredit();
	    }
		
		if(creditTotal > 0)
			GPA = (result/creditTotal);
		else
			GPA = 0;
		
		
		return GPA;

	}
	
	public void addCourseTaken(CourseGrade newCourse)  // ??? (CourseGrade addGrade)
	{
		if(newCourse == null )
		{
			System.out.println("Error !!! CourseGrade is equal null.");
		}
		else {
			numOfcourse++;
			courseGrade.add( newCourse );
			GPA = getAverageGpa();
		}	
	}
	
    @Override
	public String toString() {
    	String abcd="";
    	
    	for(int i=0;i<numOfcourse;i++) {
        	abcd  +=   String.format( "%s \n",courseGrade.get(i).toString() );
        }
        
		return "StudentID : " + studentID + "\n" + abcd + "GPA : " +getAverageGpa();
	}
    
    public int getStudentID() { 
		return studentID; 
	} 
	
	public void setStudentID(int newStudentID) { 
		studentID = newStudentID;
	} 

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		if (gPA>=0.0 && gPA<=4.0) {
		    GPA = gPA;
		}
	}

	public ArrayList<CourseGrade> getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(ArrayList<CourseGrade> newCourseGrade) {
		courseGrade = newCourseGrade;
	}
	
}
