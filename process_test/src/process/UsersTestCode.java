package process;

import java.lang.reflect.Method;

public class UsersTestCode {
	
	public static void main(String[] args) {
		
		UsersTestCode test = new UsersTestCode();
		int[] dataInt;
		char[] dataChar;
		String[] dataStr;

		int size = args.length;
		
		Method[] methodArr = UsersTestCode.class.getDeclaredMethods();
		
		for (Method method : methodArr) {
			String methodName = method.getName();
			if (methodName.equals("main")) continue;
			
			System.out.println("메서드명 : "+method.getName());
			
			
			
			System.out.print("파라미터 : ");
			Class<?>[] argTypes = method.getParameterTypes();
			if (argTypes[0].getName().equals("int")) {
				dataInt = new int[size];
				for (int i=0; i<args.length; i++) {
					int strToInt = Integer.parseInt(args[i]);
					dataInt[i] = strToInt;
				}
				
				for (Class<?> types : argTypes) {
					
				}
			} else if (argTypes[0].getName().equals("char")) {
				dataChar = new char[size];
			} else if (argTypes[0].getName().equals("String")) {
				dataStr = new String[size];
			} else {
				System.out.println("[error] 일치하는 데이터 타입이 없습니다.");
			}
			
			for (Class<?> types : argTypes) {
				
				String argName = types.getName();
				
				System.out.print(argName + " , ");
			}
			System.out.println();
			
			Class<?> returnType = method.getReturnType();
			System.out.println("반환타입 : " + returnType.getName());
		}
		
	}
	
	public int doMath(int a, int b) {
		int result = a+b;
		return result;
	}
	
	
	
}
