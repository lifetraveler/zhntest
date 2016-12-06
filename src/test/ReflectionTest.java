package test;

import java.lang.reflect.*;
import java.lang.String;
/**
 * <pre> 
* 任务：
* 描述：(这里用一句话描述这个类的作用)
* 作者：zhangnan 
* 时间：2016年10月21日下午3:43:35
* 类名: ReflectionTest
 * </pre>
 */
public class ReflectionTest {

	public static void main(String[] arg) {
		
		try {
			   Class clazz = Class.forName("test.ReflectionTest");//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
			   Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
			   for(Field f : fields) {
			    System.out.println(f.getName()+":\t\t"+f.getType().getName());//打印每个属性的类型名字
			   }
			   
			   hehe he=new ReflectionTest().new hehe();
			   he.salary=2f;
			   Object obj=he.getProperty(he, "salary");
			   
			  System.out.println("1:"+obj.getClass()+":"+obj);
			  ReflectionTest ho=new ReflectionTest();
			  ho.salary=3.05f;
			  Object obj2=ho.getStaticProperty("test.ReflectionTest", "salary");
			  System.out.println("2:"+obj2.toString());
			  } catch(Exception e) {
			   e.printStackTrace();
			  }
	}
	

	public String name;
	public Integer snumber;
	public int age;
	public static Float salary;
	
	public Object getStaticProperty(String className, String fieldName) throws Exception {
		Class ownerClass = Class.forName(className);

		Field field = ownerClass.getField(fieldName);

		Object property = field.get(ownerClass);

		return property;
	}
	static class hoho{
		public String name;
		public Integer snumber;
		public int age;
		public static Float salary;



	}
	class hehe{
		public String name;
		public Integer snumber;
		public int age;
		public Float salary;
		
		public Object getProperty(Object owner, String fieldName) throws Exception {
			//salary=1.00f;
			Class ownerClass = owner.getClass();
			System.out.println(ownerClass.getName());
			Field field = ownerClass.getField(fieldName);
			System.out.println(field.getName());
			Object property = field.get(owner);
			return property;
		}

		public Object getStaticProperty(String className, String fieldName) throws Exception {
			Class ownerClass = Class.forName(className);

			Field field = ownerClass.getField(fieldName);

			Object property = field.get(ownerClass);

			return property;
		}

		public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {

			Class ownerClass = owner.getClass();

			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Method method = ownerClass.getMethod(methodName, argsClass);

			return method.invoke(owner, args);
		}

		public Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
			Class ownerClass = Class.forName(className);

			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Method method = ownerClass.getMethod(methodName, argsClass);

			return method.invoke(null, args);
		}

		public Object newInstance(String className, Object[] args) throws Exception {
			Class newoneClass = Class.forName(className);

			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Constructor cons = newoneClass.getConstructor(argsClass);

			return cons.newInstance(args);

		}
	}

}
