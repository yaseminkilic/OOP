package hw3.main;

import hw3.util.Grade;
import java.util.*;

public class GenerateTranscript {
	
	Scanner input1 = new Scanner(System.in);
	private Formatter output;
	
	
	public static void main(String Args[]){
		
		GenerateTranscript gn = new GenerateTranscript();
		
		gn.takeInputFromUser();
		
	}
	
	public void takeInputFromUser() 
	{
		int x=0;
		int b=100,c=4;      //Default value
		String a = "CENG";  //Default value
		Grade e = Grade.F;  //Default value
		double d=0;
		
		System.out.printf("\n%s\n%s\n%s\n","To determine input, type the end-of-file indicator",
				"when you are prompted to enter input.","On Windows type <ctrl>z then press Enter\n");
		
		System.out.println("\nEnter Student Id : ");
		int id = input1.nextInt() ;
		Transcript tr = new Transcript(id);
		tr.setStudentID(id);
		

		CourseGrade cr = new CourseGrade();
		CourseGrade cr1 = new CourseGrade();
		CourseGrade cr2 = new CourseGrade();
		CourseGrade cr3 = new CourseGrade();
		CourseGrade cr4 = new CourseGrade ("CENG",100,4,Grade.F);   // Initialization with default value.
		
		
		
		while ( x==0 ) {
			
			try  {
				
				System.out.print("\nEnter Department : ");
				a = myNextString(input1);
				cr.setCourseDepartment(a);
				
				System.out.print("\nEnter Course Code : ");
				b = myNextInt(input1);
				cr1.setCourseCode(b);
				
				System.out.print("\nEnter Credit : ");
				c = myNextInt(input1);
				cr2.setCourseCredit(c);
				
				System.out.print("\nEnter Grade : ");
				d = myNextDouble(input1);
				cr3.setGradeTaken(d);
				e = cr3.getGradeTaken();
				
				System.out.println();
				
				cr4 = new CourseGrade(a, b, c, e);
				tr.addCourseTaken(cr4);
			
			} catch (Exception f) { 
				break;
			}
			
		}
		
		System.out.println();
		
		takeInputFromFile(tr,cr4,id);
		
		System.out.println();
		
		System.out.println(tr);
		
		CloseFile();
	}
	
	int myNextInt (Scanner s) throws Exception
	{
		if (s.hasNext())
			return s.nextInt();
		else 
			throw new Exception();
		
	}
	
	double myNextDouble (Scanner s) throws Exception
	{
		if (s.hasNext())
			return s.nextDouble();
		else 
			throw new Exception();
		
	}
	
	String myNextString (Scanner s) throws Exception
	{
		if(s.hasNext())
			return s.next();
		else
			throw new Exception();
	}
	
	public void takeInputFromFile(Transcript tr1,CourseGrade courseGrade1,int id) {
		
		String a = toString(tr1,courseGrade1,id);
		String fileName;
		
		try{
			System.out.println("\nEnter filename <It must be a string> : ");
			fileName = myNextString(input1);
		} catch(Exception g) { 
			fileName = ""+id;     // Default value for fileName
		}
		
		CreateFile(fileName);
		
    	output.format("\nFileName: %s\n%s", fileName, a);
	}
	
	
	public String toString(Transcript tr,CourseGrade cr,int id) {
		
		String abcde="";
		for(int i=0;i<tr.numOfcourse;i++) {
        	abcde += String.format("%s %s %s %s\n",tr.getCourseGrade().get(i).getCourseDepartment(),
        			tr.getCourseGrade().get(i).getCourseCode(), 
        			tr.getCourseGrade().get(i).getCourseCredit(),
        			tr.getCourseGrade().get(i).getGradeTaken().getNumericValue());
        }
		
		return ("\n" + id + "\n" +abcde);
	}

	public void CreateFile(String filename) {
		
		try {
			output = new Formatter( filename+ ".txt");
			System.out.println( "\nYou created a file that its name is " + filename );
		} catch (Exception e) 
		{
			System.out.println("\nYou got an error !!!");
		}
		
	}
	
	public void CloseFile() {
		output.close();
	}
	
	
}


