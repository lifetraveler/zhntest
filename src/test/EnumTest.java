package test;
import java.util.*;	

public class EnumTest {
	public static void main(String[] args) 
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter a size: (SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
		String input=in.next().toUpperCase();
		Size size=Enum.valueOf(Size.class, input);
		System.out.println("size="+size);
		System.out.println("abbrevition="+size.getAbbrevition());
		if(size==Size.EXTRA_LARGE)
		{
			System.out.println("Well done!");
		}
	}
	
	enum Size
	{
		SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
		
		private Size(String abbreviation)
		{
			this.abbreviation=abbreviation;
		}
		
		private String abbreviation;
		
		public String getAbbrevition() 
		{
			return abbreviation;
		}
	}
}
