package process_lastproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.seereal.dto.TestCaseVO;
import kr.or.seereal.dto.TestFactorVO;
import kr.or.seereal.function.codingtest.controller.CoteGradeCommand;

public class RunCode {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	private String compilePath = "C:\\seereal";
	private String runPath = compilePath;
	private File dir = new File(runPath);		// 실행할 경로
	private String fileName = "Solution";
	private String fileNameExt = "Solution.java";
	private File newFile;
	
	private static Map<String, String> resultMap = new HashMap<String, String>();
	private static String resultStr = "";
	private static boolean errStatus = false;
	
	
	public Map<String, String> runCode(CoteGradeCommand gradeCmd) {
		RunCode runCode = new RunCode();
		
		List<TestCaseVO> testcaseList = gradeCmd.getTestcaseList();	// testCaseList
		String userExp = gradeCmd.getAlgo_mem_exp();
		
		try {
			int i=1;
			for (TestCaseVO testcase : testcaseList) {
				runCode.getCodeToFile(userExp, testcase);
				String compileCommand = runCode.compileMethod();
				String runCommand = runCode.runMethod();
				
				String resultIn = runCode.execCommand(compileCommand, runCommand, i);
				if (resultIn == null) {			// 수행 실패했을 경우
					resultMap.put("resultStr", resultStr);
					throw new IOException();
				}
				resultStr  += runCode.compareResult(resultIn.trim(), testcase.getTestcase_answer(), i);
				i++;
			}
			if (bufferedReader!=null) bufferedReader.close();
			
			resultMap.put("resultStr", resultStr);
			if(errStatus) {
				resultMap.put("msg", "error");
				resultStr += "\n 아깝게 틀렸습니다! 조금더 분발해주세요! ୧༼◕ ᴥ ◕༽୨   ᕙ༼◕ ᴥ ◕༽ᕗ ୧༼◕ ᴥ ◕༽୨   ᕙ༼◕ ᴥ ◕༽ᕗ";
				resultMap.put("resultStr", resultStr);
				
			} else {
				resultMap.put("msg", "success");
				resultStr += "\n 정답입니다! ₍₍ (ง ˙ω˙)ว ⁾⁾ ₍₍ (ง ˙ω˙)ว ⁾⁾";
				resultMap.put("resultStr", resultStr);
			}
			return resultMap;
			
		} catch (IOException e) {
			//e.printStackTrace();
			resultMap.put("msg", "error");
			return resultMap;
		} finally {
			resultStr = "";
		}
		
	}
	
	
	// 사용자가 작성했던 코드를 바탕으로 .java 파일 작성
	public void getCodeToFile(String userExp, TestCaseVO testcase) {
		
		//System.out.println(". 기존 코드 : \n" + userExp);
		
		// 패키지 추가
		userExp = userExp.replace("public class", "package process;\npublic class");
		
		// 파라미터부분 가져오기
		int paramStart = userExp.indexOf("solution(") + 9;
		int paramEnd = userExp.indexOf(")", paramStart);
		String param = userExp.substring(paramStart, paramEnd);
		
		// main 메서드 만들기
		String[] paramArr = param.split(", ");
		List<TestFactorVO> testFactorList = testcase.getTestFactorList();

		String mainMethod = "\n\tpublic static void main(String[] args) {\n"
				+ "\t\tSolution sol = new Solution(); \n"
				+ "\t\tsol.solution(";
		for (int i=0; i<paramArr.length; i++) {
			if (i==0) {
				mainMethod += testFactorList.get(i).getFactor_value();
				continue;
			}
			mainMethod += ", "+ testFactorList.get(i).getFactor_value();
		}
		mainMethod += ");\n \t}\n";
		
		// 코드에 main 메서드 추가하기
		userExp = userExp.replace("Solution {", "Solution {" + mainMethod);
		//System.out.println(". 수정된 코드 : \n" + userExp);
		
		// 파일에 코드 작성
		String javaFile = compilePath + File.separator + fileNameExt;
		try {
			newFile = new File(javaFile);
			FileWriter fw = new FileWriter(newFile, false);
			fw.write(userExp);
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
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
	public String execCommand(String compileCommand, String runCommand, int cnt) {
		
		InputStream error = null;
		InputStreamReader iserror = null;
		BufferedReader bre = null;
		InputStream error2 = null;
		InputStreamReader iserror2 = null;
		BufferedReader bre2 = null;
		
		try {
			String line = null;
			String line2 = null;
			errStatus = false;
			
			process = Runtime.getRuntime().exec(compileCommand);
			error = process.getErrorStream();
			iserror = new InputStreamReader(error, "MS949");
			bre = new BufferedReader(iserror);
			while ( (line = bre.readLine()) != null ) {
				resultStr += "[compile error] " + line + "\n";
				errStatus = true;
			}
			if(bre!=null) bre.close();
			if(iserror!=null) iserror.close();
			if(error!=null) error.close();
			
			process = Runtime.getRuntime().exec(runCommand, null, dir);
			error2 = process.getErrorStream();
			iserror2 = new InputStreamReader(error2, "MS949");
			bre2 = new BufferedReader(iserror2);
			while ( (line2 = bre2.readLine()) != null ) {
				resultStr += "[Run error] " + line2;
				errStatus = true;
			}
			if(bre2!=null) bre2.close();
			if(iserror2!=null) iserror.close();
			if(error2!=null) error2.close();
			
			
			if (errStatus) {				// 컴파일 또는 run 에러 났을경우
				throw new Exception();
			}
			
			// 컴파일 또는 run 에러가 없었을 경우
			resultStr += "테스트케이스 " + cnt + " : 정상 실행 - ";
						
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			readBuffer = new StringBuffer();
			
			while ( (line = bufferedReader.readLine()) != null ) {
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			
			return readBuffer.toString();
			
		} catch(Exception e) {
			return null;
		} 
		
	}

	// 결과 비교
	public String compareResult(String result, String compareResult, int i) {
		
		if (result.equals(compareResult)) {
			return "수행 결과 일치!\n";
		} else {
			errStatus = true;
			return "수행 결과 불일치!\n";
		}
	}
	
	
}
