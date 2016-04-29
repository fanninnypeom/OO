
public class TimeException extends Exception{
	private String detail="";
	public String getDetail	() {
		return detail;
	}
	public TimeException(int i){
		if(i==1)
		detail="输入序列的时间顺序有误！";
		else
		detail="第一个请求发出的时间必须为0!";
	}

	
}
