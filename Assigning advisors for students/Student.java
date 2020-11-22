
public class Student {
	

	private String firstName;
	private String lastName;
	private int age;
	private int year;
	private double GPA;
	private Advisor advisor;
	
	public Student (String stuFirstName, String stuLastName, int stuAge, int stuYear, double stuGPA) 
	{
		firstName = stuFirstName;
		lastName = stuLastName;
		age = stuAge;
		year = stuYear;
		GPA = stuGPA;
	}

	public Student (String stuFirstName, String stuLastName, int stuAge) 
	{
		firstName = stuFirstName;
		lastName = stuLastName;
		age = stuAge;
		year = 1;
		GPA = 0.0;
	}
	public Student (String stuFirstName, String stuLastName) 
	{
		firstName = stuFirstName;
		lastName = stuLastName;
		age = 18;
		year = 1;
		GPA = 0.0;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String stuFirstName) {
	    firstName = stuFirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String stuLastName) {
		lastName = stuLastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge (int stuAge) {
		if( stuAge < 18 ) {
			System.out.println("************************************************");
			System.out.println("ERROR: Wrong age value for student: " + stuAge);
		}
		else {
			age = stuAge;
		}
	}
	public int getYear() {
		return year;
	}
	public void setYear(int stuYear) {
		if( (stuYear < 1) || (stuYear > 4) ) {
			System.out.println("ERROR: Wrong year value for student: " + stuYear);
		}
		else {
			year = stuYear;
		}
	}
	public double getGPA()
	{
		return GPA;
	}
	public void setGPA (double stuGPA)
        {
		if( (stuGPA>4) || (stuGPA<0) ) {
			System.out.println("ERROR: Wrong GPA value for student: " + stuGPA);
		}
		else {
			GPA = stuGPA;
		}
		
        }
	public Advisor getAdvisor() {
		return advisor;
	}
	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
	
	public void toStrStudentDetails ()
	{ 
		    System.out.println("************************************************");
		    System.out.println("STUDENT NAME: " + firstName + " " + lastName + "\n" + "AGE: "+age+"\n" + "YEAR: " + year +"\n"+ "GPA: "+ GPA);
	}
	public String isSuccessful()
	{
		if( (GPA<=4) && (GPA>=2.5) ) {
		    return "true";
		}
		else {
			return "false";
		}
	}
	public void currentStatus()
	{
		if ( isSuccessful().equals("true") ) {
			System.out.println("STUDENT:"+" "+firstName+" "+lastName+" "+"SUCCESSFUL");
		}
		else {
			System.out.println("STUDENT:"+" "+firstName+" "+lastName+" "+"NOT SUCCESSFUL");
		}
	}
	
}
