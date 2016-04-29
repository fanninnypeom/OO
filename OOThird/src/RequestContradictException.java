
public class RequestContradictException extends Exception{
	private String detail="指令冲突！一楼向下和十楼向上的指令为冲突指令！";

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
