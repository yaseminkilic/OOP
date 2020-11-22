package hw3.main;

import java.util.Scanner;
import hw3.util.Grade;

public class CourseGrade  {
	
	private String courseDepartment; // CENG, COMP, ECE, ME and MATH.
	private int courseCode; // three digit code between 100 and 599.
	private int courseCredit; //only valid values are 3 and 4.
	private Grade gradeTaken; //a Grade value.
	
	Scanner input= new Scanner(System.in);
	
	public CourseGrade() {
		this("CENG",100,4,Grade.F);
	}
	
	public CourseGrade(String aCourseDepartment) {
		this(aCourseDepartment,100,4,Grade.F);
	}
	
	public CourseGrade(String bCourseDepartment, int bCourseCode) {
		this(bCourseDepartment,bCourseCode,4,Grade.F);
	}
	
	public CourseGrade(String cCourseDepartment, int cCourseCode, int cCourseCredit) {
		this(cCourseDepartment,cCourseCode,cCourseCredit,Grade.F);
	}
	
	public CourseGrade(String dCourseDepartment, int dCourseCode, int dCourseCredit, Grade dGradeTaken) {
		setCourseGrade(dCourseDepartment, dCourseCode, dCourseCredit, dGradeTaken);
	}
	
	public void setCourseGrade(String dCourseDepartment, int dCourseCode, int dCourseCredit, Grade dGradeTaken) {
		setCourseDepartment( dCourseDepartment ) ;
		setCourseCode( dCourseCode );
		setCourseCredit( dCourseCredit );
		setGradeTaken( dGradeTaken );
	}
	
	public String getCourseDepartment() {
		return courseDepartment;
	}
	
	public void setCourseDepartment(String newCourseDepartment) {
		if(newCourseDepartment.equalsIgnoreCase("COMP") 
				|| newCourseDepartment.equalsIgnoreCase("MATH") || newCourseDepartment.equalsIgnoreCase("ECE") 
				|| newCourseDepartment.equalsIgnoreCase("ME") || newCourseDepartment.equalsIgnoreCase("CENG") )
			courseDepartment = newCourseDepartment;
		else {
			System.out.println("CourseDepartment must be one of CENG, COMP, ECE, ME or MATH.");
			System.out.print("\nEnter A New Course Department : ");
			setCourseDepartment( input.next() );
		}
			
	}
	
	public int getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(int newCourseCode) {
		if( (newCourseCode>=100) && (newCourseCode<=599) )
			courseCode = newCourseCode;
		else {
			System.out.println("CourseCode must be three digit code between 100 and 599.");
			System.out.print("\nEnter A New Course Code : ");
			setCourseCode( input.nextInt() );
		}
	}
	
	public int getCourseCredit() {
		return courseCredit;
	}
	
	public void setCourseCredit(int newCourseCredit) {
		if( (newCourseCredit == 3) || (newCourseCredit == 4) )
			courseCredit = newCourseCredit;
		else {
			System.out.println("CourseCredit must be only 3 or 4.");
		    System.out.print("\nEnter A New Course Credit : ");
		    setCourseCredit( input.nextInt() );
		}
	}
	
	public Grade getGradeTaken() {
		return gradeTaken;
	}
	
	public void setGradeTaken(double val) {
		if( (val>3.0) && (val<=4.0) )
			gradeTaken = Grade.A;
		else if( (val>2.0) && (val<=3.0) )
			gradeTaken = Grade.B;	
		else if( (val>1.0) && (val<=2.0) )
			gradeTaken = Grade.C;
		else if( (val>0.0) && (val<=1.0) )
			gradeTaken = Grade.D;
		else if( val==0 )
			gradeTaken = Grade.F;  
		else {
			System.out.println("Grade must be only between 0.0 and 4.0 .");
			System.out.print("\nEnter A New Grade : ");
		    setGradeTaken( input.nextDouble() );
		}
	}
	
	public void setGradeTaken (Grade g) {
		gradeTaken = g;
	}
	
	@Override
	public String toString() { //Department: CENG CourseCode: 201 Credit: 5 Grade: F
		return "Department: " + courseDepartment
				+ " CourseCode: " + courseCode + " Credit: "
				+ courseCredit + " " + gradeTaken.getStringValue();
	}
	
}
