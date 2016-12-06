public class Manager extends Employee
{
	private double bonus;
	{
		bonus=20000.00;
		
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public double getSalary(){
		return super.getSalary()+this.bonus;
	}
	
	public Manager(String name,int age,double salary){
		super(name,age,salary);
		
		
	}
	public static void main(String[] args) {
		Manager manager=new Manager("Bengi",45,90000);
		Employee[] employees=new Employee[3];
		employees[0]=manager;
		employees[1]=new Employee("Tom",15,6000);
		employees[2]=new Employee("Donypee",22,89000);
		//manager=(Manager)employees[1];
		for (Employee e : employees) {
			System.out.println(e instanceof Manager);
			System.out.println(e instanceof Employee);
			System.out.println("name is "+e.getName()+",salary="+e.getSalary());
		}
		
	}
}
