
public class Advisor {
	
	private String firstName;
	private String lastName;
	private int ID;
        private static int numberOfStudentAssigned;
        public static int giveStudentToAdvisor = 4;
        private static int countAdvisor=0;
    
   
    
	public Advisor (String advFirstName, String advLastName) {
		firstName = advFirstName;
		lastName = advLastName;
		countAdvisor++;
		ID = countAdvisor;
		numberOfStudentAssigned = 0;
	}
        public void incNumberOfStudentAssigned() {
		numberOfStudentAssigned++;
	}
        public static int getCountAdvisor() {
		return countAdvisor;
	}
	public static void setCountAdvisor(int countAdvisorP) {
		countAdvisor = countAdvisorP;
	}
    
        public String getAdvName()
   	{
   		return firstName;
   	}
        public void setAdvName (String advFirstName)
   	{
   		firstName=advFirstName;
   	}
    
   	public String getAdvLastName()
   	{
   		return lastName;
   	}
   	public void setAdvLastName(String advLastName)
   	{
   		lastName=advLastName;
   	}
   	public static int getNumberOfStudentAssigned() {
		return numberOfStudentAssigned;
	}
   	public  static void setNumberOfStudentAssigned(int numberOfStudent_Assigned) {
		numberOfStudentAssigned=numberOfStudent_Assigned;
	}
   	public String toStrAdvisorDetail() {
		return "ADVISOR ID: " + ID + " NAME: " + firstName + " " + lastName;
	}
	
	
}