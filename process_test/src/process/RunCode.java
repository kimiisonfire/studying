package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCode {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	private String compilePath = System.getProperty("user.dir") + File.separator + "src\\process";
	private String runPath = System.getProperty("user.dir") + File.separator + "bin";
	private File dir = new File(runPath);		// 실행할 경로
	private String fileName = "process.Solution";
	private String fileNameExt = "Solution.java";
	private File newFile;
	
	private CodeService service = new CodeService();
	private Code code;
	private String userExp;
	
	public static void main(String[] args) {
		RunCode runCode = new RunCode();
		
		runCode.getCodeToFile();
		
		String compileCommand = runCode.compileMethod();
		String runCommand = runCode.runMethod();
		
		String result = runCode.execCommand(compileCommand, runCommand);
		String compareResult = runCode.compareResult(result.trim());
		System.out.println(compareResult);
	}
	
	// db에서 해당 pk의 코드 가져오기, 파일로 작성
	public void getCodeToFile() {
		// pk에 해당하는 코드 가져옴
		code = service.selectUserCode("at001");
		userExp = code.getUserExp();
		System.out.println(". 기존 코드 : \n" + userExp);
		
		// 패키지 추가
		userExp = userExp.replace("public class", "package process;\npublic class");
		//System.out.println(". 패키지 추가 : \n" + userExp);
		
		// 파라미터부분 가져오기
		int paramStart = userExp.indexOf("solution(") + 9;
		int paramEnd = userExp.indexOf(")", paramStart);
		String param = userExp.substring(paramStart, paramEnd);
		//System.out.println(". 파라미터 : '" +param+"'");
		
		// main 메서드 만들기
		String[] paramArr = param.split(", ");
		String[] testcase = {"2", "3"};				// db에서 테스트케이스 가져오기

		String mainMethod = "\n\tpublic static void main(String[] args) {\n"
				+ "\t\tSolution sol = new Solution(); \n"
				+ "\t\tsol.solution(";
		for (int i=0; i<paramArr.length; i++) {
			if (i==0) {
				mainMethod += testcase[i];
				continue;
			}
			mainMethod += ", "+testcase[i];
		}
		mainMethod += ");\n \t}\n";
		//System.out.println(". main 메서드 : \n" + mainMethod);
		
		// 코드에 main 메서드 추가하기
		userExp = userExp.replace("Solution {", "Solution {" + mainMethod);
		System.out.println(". 수정된 코드 : \n" + userExp);
		
		// 파일에 코드 작성
		String javaFile = compilePath + File.separator + "Solution.java";
		try {
			newFile = new File(javaFile);
			FileWriter fw = new FileWriter(newFile, false);
			fw.write(userExp);
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (newFile.exists()) {
			System.out.println("파일 만들어졌음!");
		} else {
			System.out.println("파일이 없는데요? Solution.java파일말여");
		}
	}
	
	// 컴파일 명령어
	public String compileMethod() {
		buffer = new StringBuffer();
		buffer.append("cmd.exe ");
		buffer.append(" /c  javac " + compilePath + File.separator + fileNameExt +" -encoding UTF-8");
		return buffer.toString();
	}
	
	// 실행 명령어
	private String runMethod() {
		buffer = new StringBuffer();
		buffer.append("cmd.exe ");
		buffer.append(" /c java  -Dfile.encoding=\"UTF-8\" " + fileName );
		return buffer.toString();
	}	
	
	// process 실행, 결과 반환
	public String execCommand(String compileCommand, String runCommand) {
		
		InputStream error = null;
		InputStreamReader iserror = null;
		BufferedReader bre = null;
		InputStream error2 = null;
		InputStreamReader iserror2 = null;
		BufferedReader bre2 = null;
		
		try {
			String line = null;
			String line2 = null;
			
			process = Runtime.getRuntime().exec(compileCommand);
			error = process.getErrorStream();
			iserror = new InputStreamReader(error);
			bre = new BufferedReader(iserror);
			while ( (line = bre.readLine()) != null ) {
				System.out.println("[compile error] " + line);
			}
			
			process = Runtime.getRuntime().exec(runCommand, null, dir);
			error2 = process.getErrorStream();
			iserror2 = new InputStreamReader(error2);
			bre2 = new BufferedReader(iserror2);
			while ( (line2 = bre2.readLine()) != null ) {
				System.out.println("[Run error] " + line2);
			}
			
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			readBuffer = new StringBuffer();
			
			while ( (line = bufferedReader.readLine()) != null ) {
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			return readBuffer.toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			if (bre!=null)     try{ bre.close(); } catch(IOException e) {}
			if (iserror!=null) try{ iserror.close(); } catch(IOException e) {}
			if (error!=null)   try{ error.close(); } catch(IOException e) {}
			if (bre2!=null)    try{ bre2.close(); } catch(IOException e) {}
			if (iserror2!=null) try{ iserror2.close(); } catch(IOException e) {}
			if (error2!=null)  try{ error2.close(); } catch(IOException e) {}
		}
		
		return null;
	}

	// 결과 비교
	public String compareResult(String result) {
		String compareResult = "5";
		
		System.out.println(". 코드실행결과 : '" + result + "'");
		System.out.println(". 테스트케이스 답 : '" + compareResult + "'");
		
		if (result.equals(compareResult)) {
			return "결과가 일치합니다.";
		} else {
			return "수행 결과가 일치하지 않습니다.";
		}
	}
	
	
}
