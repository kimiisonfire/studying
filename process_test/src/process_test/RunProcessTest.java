package process_test;

public class RunProcessTest {
	
	public static void main(String[] args) {
		try {
			System.out.println("Executing notepad.exe");

			Process process = Runtime.getRuntime().exec("notepad.exe");
			
			System.out.println("Notepad should now open");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
