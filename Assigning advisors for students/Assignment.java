
import java.util.Calendar;
public class Assignment {
	
	public static void assignStudentToAdvisor (Student student, Advisor advisor) 
	{
		advisor.incNumberOfStudentAssigned();
		student.setAdvisor(advisor);
		if( Advisor.getNumberOfStudentAssigned() == Advisor.giveStudentToAdvisor ) {
			
			System.out.println("ERROR: Cannot assign more students to advisor "+advisor.toStrAdvisorDetail() );
		}
		
		
	}
	
	public void whichSemester ()
	{
		Calendar cal = Calendar.getInstance();
		switch(cal.get(Calendar.MONTH)) {
                      case 9:
                      case 10:
                      case 11:
                      case 12:
                      case 1: 
        	        System.out.println("Current Semester is FALL");
        	        break;
                      case 2:
                      case 3:
                      case 4:
                      case 5:
                      case 6:
        	        System.out.println("Current Semester is SPRING");
        	        break;
                      case 7:
                      case 8:
        	        System.out.println("Current Semester is SUMMER");
        	        break;
                 }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Advisor a1 = new Advisor("Ali","Can");
		Advisor a2 = new Advisor("Veli","Can");
		Advisor a3 = new Advisor("Ahmet","Can");
		
		Student stu1 = new Student("Ayse","Can");
		assignStudentToAdvisor(stu1,a1);
		Student stu2 = new Student("Ahmet","Can",20,5,-1.0);
		assignStudentToAdvisor(stu2,a1);
		Student stu3 = new Student("Arda","Can");
		assignStudentToAdvisor(stu3,a1);
		Student stu4 = new Student("Ada","Can");
		assignStudentToAdvisor(stu4,a1);
		Student stu5 = new Student("Aylin","Can");
		assignStudentToAdvisor(stu5,a2);
		Student stu6 = new Student("Ayca","Can");
		assignStudentToAdvisor(stu6,a2);
		Student stu7 = new Student("Akasya","Can");
		assignStudentToAdvisor(stu7,a2);
		
		stu1.toStrStudentDetails();
		System.out.println("************************************************");
	        stu2.currentStatus();
	    
	        System.out.println("************************************************");
		stu2.setYear(5);
		System.out.println("************************************************");
		stu2.setGPA(-1.0);
		System.out.println("************************************************");
		
		stu1.setGPA(4.0);
		stu1.currentStatus();
		System.out.println("************************************************");
		
		Assignment month = new Assignment();
		month.whichSemester();
		System.out.println("************************************************");
		System.out.println(a1.toStrAdvisorDetail());
		System.out.println("************************************************");
		System.out.println(a2.toStrAdvisorDetail());
		System.out.println("************************************************");
		System.out.println(a3.toStrAdvisorDetail());
		System.out.println("************************************************");
	}

}

