
public class elevator {
	private int Floor;
	private int Status; //0代表下降到当前floor,1代表上升到当前floor
	private int inertance;//电梯的惯性,0为惯性向下,1为惯性向上
	private double Time;//当前时间
	public elevator(){
		Floor=1;
		Time=0;
	}
	public void PrintRunningRecord(){
		System.out.print("(");
		System.out.print(Floor+", ");
		if(Status==1)
			System.out.print("UP, ");
		else if(Status==0)
			System.out.print("DOWN, ");
		System.out.print(Time);
		System.out.println(")");
	}
	public void GetInstruction(int instr,double d){
		Floor=Floor+instr;
		if(d>Time)
			Time=d;
		if(instr<0)
			Status=0;
		else if(instr>0)
			Status=1;
		Time=Time+0.5*Math.abs(instr)+1;

	}
	public int getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}
	public double getTime() {
		return Time;
	}
	public void setTime(double time) {
		Time = time;
	}

}
