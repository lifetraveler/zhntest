package test;
import java.text.DateFormatSymbols;
import java.util.*;


public class CalendarTest {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.CHINA);
		GregorianCalendar d=new GregorianCalendar();
		d.setFirstDayOfWeek(1);
		int today=d.get(Calendar.DAY_OF_MONTH);
		//System.out.println(today);
		int month=d.get(Calendar.MONTH);
		
		d.set(Calendar.DAY_OF_MONTH, 1);
		
		int weekday=d.get(Calendar.DAY_OF_WEEK);
		
		int firstDayOfWeek=d.getFirstDayOfWeek();
		System.out.println(firstDayOfWeek);
		int indent=0;
		while(weekday!=firstDayOfWeek)
		{
			indent++;
			d.add(Calendar.DAY_OF_WEEK, -1);
			weekday=d.get(Calendar.DAY_OF_WEEK);
			
			
		}
		System.out.println("indent="+indent);
		String[] weekdayNames=new DateFormatSymbols().getShortWeekdays();
		do
		{
			System.out.printf("%4s",weekdayNames[weekday]);
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday=d.get(Calendar.DAY_OF_WEEK);
			
		}while(weekday!=firstDayOfWeek);
		System.out.println();
		
		for(int i=1;i<indent+1;i++)
		{
			System.out.print(" 星期三");
			if(i%7==0) System.out.println();
		}
		d.set(Calendar.DAY_OF_MONTH, 1);
		
		do
		{
			int day=d.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d",day);
			
			if(day==today) System.out.print("* ");
			else System.out.print("  ");
			
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday=d.get(Calendar.DAY_OF_WEEK);
			
			if(weekday==firstDayOfWeek) System.out.println();
			
		}while(d.get(Calendar.MONTH)==month);
		
		if(weekday!=firstDayOfWeek)	System.out.println();
	}
	
}
