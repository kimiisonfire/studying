package process_lastproject;

import java.util.Date;
/**
 * 코딩테스트에 대한 사용자의 풀이 VO 
 * @author 김희영
 */
public class AlgorithmExplanVO {
	
	private String algo_exp_num;
	private String mem_id;
	private String algo_ts_num;
	private Date algo_exp_regdate;
	private int algo_exp_status;
	private String algo_mem_exp;
	

	public String getAlgo_exp_num() {
		return algo_exp_num;
	}

	public void setAlgo_exp_num(String algo_exp_num) {
		this.algo_exp_num = algo_exp_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getAlgo_ts_num() {
		return algo_ts_num;
	}

	public void setAlgo_ts_num(String algo_ts_num) {
		this.algo_ts_num = algo_ts_num;
	}

	public Date getAlgo_exp_regdate() {
		return algo_exp_regdate;
	}

	public void setAlgo_exp_regdate(Date algo_exp_regdate) {
		this.algo_exp_regdate = algo_exp_regdate;
	}

	public int getAlgo_exp_status() {
		return algo_exp_status;
	}

	public void setAlgo_exp_status(int algo_exp_status) {
		this.algo_exp_status = algo_exp_status;
	}

	public String getAlgo_mem_exp() {
		return algo_mem_exp;
	}

	public void setAlgo_mem_exp(String algo_mem_exp) {
		this.algo_mem_exp = algo_mem_exp;
	}


	@Override
	public String toString() {
		return "AlgorithmExplanVO [algo_exp_num=" + algo_exp_num + ", mem_id=" + mem_id + ", algo_ts_num=" + algo_ts_num
				+ ", algo_exp_regdate=" + algo_exp_regdate + ", algo_exp_status=" + algo_exp_status + ", algo_mem_exp="
				+ algo_mem_exp + "]";
	}

}
