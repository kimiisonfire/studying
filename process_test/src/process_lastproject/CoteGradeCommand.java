package process_lastproject;

import java.util.List;

public class CoteGradeCommand {
	
	private String algo_mem_exp;
	private List<TestCaseVO> testcaseList;
	
	public CoteGradeCommand() {}
	
	public CoteGradeCommand(String algo_mem_exp, List<TestCaseVO> testcaseList) {
		super();
		this.algo_mem_exp = algo_mem_exp;
		this.testcaseList = testcaseList;
	}
	
	public String getAlgo_mem_exp() {
		return algo_mem_exp;
	}
	public void setAlgo_mem_exp(String algo_mem_exp) {
		this.algo_mem_exp = algo_mem_exp;
	}
	public List<TestCaseVO> getTestcaseList() {
		return testcaseList;
	}
	public void setTestcaseList(List<TestCaseVO> testcaseList) {
		this.testcaseList = testcaseList;
	}
	
	@Override
	public String toString() {
		return "CoteGradeCommand [algo_mem_exp=" + algo_mem_exp + ", testcaseList=" + testcaseList + "]";
	}
}
