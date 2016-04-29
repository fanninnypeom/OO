
public class scheduler {
	private int instr;
	private int Floor;
	private int Status; //0代表下降到当前floor,1代表上升到当前floor
	private double Time;//当前时间
	public void GiveOutInstr(Request req){
		if(req.getRequestFlag()==0){
			setInstr(req.getRequestFloor()-Floor);
			setTime(req.getTime());
		}
		else if(req.getRequestFlag()==1){
			setInstr(req.getAimFloor()-Floor);
			setTime(req.getTime());
		}
		else{
			System.out.println("空请求！");
			System.exit(0);
		}
	}
	public int getInstr() {
		return instr;
	}
	public void setInstr(int instr) {
		this.instr = instr;
	}
	public double getTime() {
		return Time;
	}
	public void setTime(double time) {
		Time = time;
	}
	public int getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}

}

