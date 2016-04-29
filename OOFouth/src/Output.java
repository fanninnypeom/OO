
public class Output {
	private int Floor;
	private int Status;
	private double Time;
	public String toString(){
		String str="("+Floor+",";
		if(Status==1)
			str+="UP, ";
		else if(Status==0)
			str+="DOWN, ";
		str+=Time;
		str+=")";
		return str;
	}
	public int getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public double getTime() {
		return Time;
	}
	public void setTime(double time) {
		Time = time;
	}
}
