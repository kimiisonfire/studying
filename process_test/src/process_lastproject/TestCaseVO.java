package process_lastproject;

import java.util.List;

/**
 * 코딩테스트 문제에 해당하는 테스트케이스 VO 
 * @author 김희영
 */
public class TestCaseVO {

	private int testcase_num;
	private String algo_ts_num;
	private String testcase_answer;
	
	private List<TestFactorVO> testFactorList; 
	
	public int getTestcase_num() {
		return testcase_num;
	}

	public void setTestcase_num(int testcase_num) {
		this.testcase_num = testcase_num;
	}

	public String getAlgo_ts_num() {
		return algo_ts_num;
	}

	public void setAlgo_ts_num(String algo_ts_num) {
		this.algo_ts_num = algo_ts_num;
	}

	public String getTestcase_answer() {
		return testcase_answer;
	}

	public void setTestcase_answer(String testcase_answer) {
		this.testcase_answer = testcase_answer;
	}

	public List<TestFactorVO> getTestFactorList() {
		return testFactorList;
	}

	public void setTestFactorList(List<TestFactorVO> testFactorList) {
		this.testFactorList = testFactorList;
	}

	@Override
	public String toString() {
		return "TestCaseVO [testcase_num=" + testcase_num + ", algo_ts_num=" + algo_ts_num + ", testcase_answer="
				+ testcase_answer + ", testFactorList=" + testFactorList + "]";
	}

}
