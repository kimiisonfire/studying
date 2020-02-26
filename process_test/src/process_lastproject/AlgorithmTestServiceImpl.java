package process_lastproject;

import java.sql.SQLException;



public class AlgorithmTestServiceImpl implements AlgorithmTestService{


	// 채점하기 버튼 선택
	@Override
	public void codeGrade(AlgorithmExplanVO userExpuserExpNew) throws SQLException {
		
		CoteGradeCommand gradeCmd = new CoteGradeCommand();
		new RunCode().runCode(gradeCmd);
		
	}

	
}
