package process_test;

import java.io.File;
import java.io.IOException;

public class RunProcess {
	
	public static void main(String[] args) {
		
		String path = System.getProperty("user.dir");
		String fileName = "RunExecTest";
		String file = path + File.separator + fileName;
		System.out.println(file);
		
		try {
			Runtime r = Runtime.getRuntime();
//			Process p = r.exec("javac " + file + ".java");
//			p = r.exec("java " + file);
			Process p = r.exec("javac -version");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
