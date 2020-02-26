package process_lastproject;
/**
 * 코딩테스트 service
 * @author 김희영
 */

import java.sql.SQLException;
import java.util.Map;

public interface AlgorithmTestService {
	
	/**
	 * 사용자가 "채점하고 제출" 버튼 선택하여 저장 및 채점
	 * @param userExp	: mem_id, algo_ts_num, algo_mem_exp가 담겨있음
	 * @return 채점한 결과("resultStr")와 성공/에러여부("msg") 등을 출력
	 * @throws SQLException
	 */
	void codeGrade(AlgorithmExplanVO userExp) throws SQLException;
	
	
}
