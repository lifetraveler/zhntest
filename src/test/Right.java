package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class Right {
	public static void main(String[] args) throws IOException {
		int n=123456789;
		float f=n;
		System.out.println(System.getProperty("user.dir"));
		PrintWriter out = new PrintWriter("myfiles.txt");
		out.write("hello monitor!");
		out.close();
		Scanner in=new Scanner(Paths.get("myfiles.txt"));
		System.out.println(in.nextLine());
		//System.out.printf("%ty",new Date());
		//System.out.println(f);
		String greeting="§ is hello";
		//System.out.println(greeting.charAt(0));
		//System.out.println(greeting.indexOf('a'));
		//System.out.println(greeting.codePointCount(0, 8));
		int m=greeting.length();
		int index=greeting.offsetByCodePoints(1, 1);
		char cp=greeting.charAt(index);
		//System.out.println(cp);
		String unicode = "\\u5f00\\u59cb\\u4efb\\u52a1";
	    //System.out.println(unicodeToCn(unicode));
	    //System.out.println("\u5f00");
	}
	private static String cnToUnicode(String cn) {
	    char[] chars = cn.toCharArray();
	    String returnStr = "";
	    for (int i = 0; i < chars.length; i++) {
	      returnStr += "\\u" + Integer.toString(chars[i], 16);
	      
	    }
	    //System.out.println(returnStr);
	    return returnStr;
	}
	 
	private static String unicodeToCn(String unicode) {
	    /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
	    String[] strs = unicode.split("\\\\u");
	    //System.out.println(strs[1].toString());
	    String returnStr = "";
	    // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
	    for (int i = 1; i < strs.length; i++) {
	      returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
	      //System.out.println(strs[i]);
	    }
	    return returnStr;
	}
	
}
