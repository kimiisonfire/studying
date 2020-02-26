package process;

public class CodeService {
	
	public Code code;
	
	public Code selectUserCode(String code_pk ) { 
		
		String userExp = "public class Solution {\n"
				+ "\tpublic void solution(int a, int b) {\n"
				+ "\t\tSystem.out.println(a+b);\n"
				+ "\t}\n"
				+ "}";
		
		this.code = new Code();
		code.setCode_pk(code_pk);
		code.setCode_title("k번째 수");
		code.setMem_id("mama");
		code.setUserExp(userExp);
		return code;
	}
}

class Code {
	private String code_pk;
	private String code_title;
	private String mem_id;
	private String userExp;
	public String getCode_pk() {
		return code_pk;
	}
	public void setCode_pk(String code_pk) {
		this.code_pk = code_pk;
	}
	public String getCode_title() {
		return code_title;
	}
	public void setCode_title(String code_title) {
		this.code_title = code_title;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getUserExp() {
		return userExp;
	}
	public void setUserExp(String userExp) {
		this.userExp = userExp;
	}
	@Override
	public String toString() {
		return "Code [code_pk=" + code_pk + ", code_title=" + code_title + ", mem_id=" + mem_id + ", userExp=" + userExp
				+ "]";
	}
	
}