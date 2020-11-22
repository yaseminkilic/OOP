
public class Users {
	
	private String userName;
	private int age;
	private String gender;
	
	// default constructor
	public Users() {
		this("Unknown", 18, "Unknown");
	}
	
	// With three-argument constructor, validate and store userName, age, gender
	public Users (String userName, int age, String gender) {
		setUserName(userName);
		setAge(age);
		setGender(gender);
	}
	
	// get/set age
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if( age>0 ){
			this.age = age;
		}
		else
			this.age = 18;
	}
	
	// get/set gender
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if( gender.equalsIgnoreCase("K") || gender.equalsIgnoreCase("E") ){
			this.gender = gender;
		}else
			this.gender = "Unknown";
	}
	
	// get/set userName
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override  // indicates that this method overrides a superclass method
	public String toString() {
		return getUserName() + " " + getAge() + " " + getGender();
	}
}
