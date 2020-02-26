package process_lastproject;

public class CoteGradeCommand {
	
	private String algo_mem_exp;
	
	
	public String getAlgo_mem_exp() {
		return algo_mem_exp;
	}
	public void setAlgo_mem_exp(String algo_mem_exp) {
		this.algo_mem_exp = algo_mem_exp;
	}
	
	@Override
	public String toString() {
		return "CoteGradeCommand [algo_mem_exp=" + algo_mem_exp + "]";
	}
}
