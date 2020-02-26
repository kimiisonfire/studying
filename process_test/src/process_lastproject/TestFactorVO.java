package process_lastproject;
/**
 * 코딩테스트 문제에 해당하는 테스트케이스의 인자 VO 
 * @author 김희영
 */
public class TestFactorVO {

	private String factor_num;
	private String factor_value;
	private int factor_order;
	private int testcase_num;
	
	public TestFactorVO() {}

	public TestFactorVO(String factor_num, String factor_value, int factor_order, int testcase_num) {
		super();
		this.factor_num = factor_num;
		this.factor_value = factor_value;
		this.factor_order = factor_order;
		this.testcase_num = testcase_num;
	}

	public String getFactor_num() {
		return factor_num;
	}

	public void setFactor_num(String factor_num) {
		this.factor_num = factor_num;
	}

	public String getFactor_value() {
		return factor_value;
	}

	public void setFactor_value(String factor_value) {
		this.factor_value = factor_value;
	}

	public int getFactor_order() {
		return factor_order;
	}

	public void setFactor_order(int factor_order) {
		this.factor_order = factor_order;
	}

	public int getTestcase_num() {
		return testcase_num;
	}

	public void setTestcase_num(int testcase_num) {
		this.testcase_num = testcase_num;
	}

	@Override
	public String toString() {
		return "TestFactorVO [factor_num=" + factor_num + ", factor_value=" + factor_value + ", factor_order="
				+ factor_order + ", testcase_num=" + testcase_num + "]";
	}
	
	
}
