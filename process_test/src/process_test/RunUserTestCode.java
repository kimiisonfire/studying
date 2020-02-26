package process_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunUserTestCode {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	private File file;
	private BufferedWriter bufferWriter;
	
	private String fileName = "Test2";
	private String fileNameExt = "Test2.java";	// 일단은 하나의 파일로 진행하고
											// 나중에는 코드마다 파일을 만들자

	/**
	 * Test.java에 파일에 담을 내용을 넘겨서 컴파일 - 실행
	 */
	public static void main(String[] args) {
		RunUserTestCode cmd = new RunUserTestCode();
		
//		File file = new File("src/process/UsersTestCode.java");
//		if (!file.exists()) {
//			System.out.println("미친");
//		} else {
//			System.out.println("파일있음");
//		}
		
		String pjPath = System.getProperty("user.dir");
		String javaPath = pjPath + File.separator + "src\\process";
		String fileName = "UsersTestCode.java";
		
		File file = new File(javaPath, fileName);
//		if (!file.exists()) {
//			System.out.println("그딴파일없음");
//		} else {
//			System.out.println("파일이 잘있네용");
//		}
		
		
		StringBuffer mainBuffer = new StringBuffer();
		
		try {
			FileReader in = new FileReader(file);
			
			int ch;
			while ( (ch = in.read()) != -1 ) {
				mainBuffer.append((char)ch);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mainBuffer.toString());
		
//		mainBuffer.append("public class Test");
//		mainBuffer.append("{ public static void main(String[] args) ");
//		mainBuffer.append("  { System.out.println(\"hi!\");   ");
//		mainBuffer.append("    System.out.println( System.getProperty(\"user.dir\")); }  }");
		
		String command = cmd.inputSource(mainBuffer.toString());
		String result = cmd.execCommand(command);
		System.out.println(result);
	}
	
	/**
	 * Test.java 파일에 실행할 내용을 담아주고,
	 * 컴파일 명령어를 buffer에 담아 return
	 * @param Test.java 파일에 담을 내용
	 * @return 컴파일 명령어
	 */
	public String inputSource(String source) {
		
		buffer = new StringBuffer();
		
		buffer.append("cmd.exe ");
		buffer.append("/c  ");
		buffer.append("javac " + fileNameExt);		// fileName을 컴파일하는 명령어
		
		createFileAsSource(source);		// Test.java에 담길 내용을 보내서 파일 생성
		
		return buffer.toString();
	}
	
	/**
	 * Test.java에 담길 내용을 파라미터로 받아서 실제 파일로 생성
	 * @param Test.java에 담길 내용
	 */
	private void createFileAsSource(String source) {
		
		System.out.println("여기는 잘오고있나용");
		
		try {
			file = new File(fileNameExt);		// Test.java에 write 할 예정
			bufferWriter = new BufferedWriter(new FileWriter(file, false));
			
			bufferWriter.write(source);
			bufferWriter.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				bufferWriter.close();
				file = null;
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	/**
	 * 입력받은 명령어를 process에 담고 bufferedReader -> readBuffer에 담아 반환
	 * 여기서 cmd는 inputSource()를 수행하고 반환된 컴파일 명령어를 파라미터로 받고
	 * 실제로 java Test를 실행하는 명령어를 추가로 담을 예정
	 * @param 입력받은 명령어
	 * @return 명령어 반환
	 */
	public String execCommand(String cmd) {
		try {
			process = Runtime.getRuntime().exec(cmd);
			process = Runtime.getRuntime().exec(runClass());
			
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line = null;
			readBuffer = new StringBuffer();
			
			while ( (line = bufferedReader.readLine()) != null ) {
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			return readBuffer.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 컴파일된 Test.java를 실행하는 명령어 반환 메서드
	 * @return Test 실행 명령어
	 */
	private String runClass() {
		
		buffer = new StringBuffer();
		
		buffer.append("cmd.exe ");
		buffer.append("/c  ");
		buffer.append("java " + fileName);
		
		return buffer.toString();
	}
	
	
	
}
