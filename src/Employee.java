
public class Employee {
	private String name;
	private int age;
	private double salary;
	
	public Employee(){
		
	}
	
	public Employee(String aname,int aage,double asalary){
		this.age=aage;
		this.name=aname;
		this.salary=asalary;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	
}
