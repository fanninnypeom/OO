import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;




public class elevator implements runningMethod{
	private ArrayList<Output> OutputArray=new ArrayList<Output>();
	protected volatile int Floor;
	
	private  volatile int Status; //0代表正处于下降状态,1代表正处于上升状态,-1代表静止状态
//	private int inertance;//电梯的惯性,0为惯性向下,1为惯性向上
	private double Time;//当前时间
	public elevator(){
		Floor=1;
		Time=0;
		Status=-1;
	}
	public void WriteRunningRecord(){
		Output Result=new Output();
		Result.setFloor(Floor);
		Result.setStatus(getStatus());
		Result.setTime(Time);
		getOutputArray().add(Result);
	}
	public boolean invalidInstr(Request Req){
		int count=getOutputArray().size();
		for(int i=count-1;i>=0;i--){
			if(getOutputArray().get(i).getTime()<=Req.getTime())
				break;
			else{
				if(Req.getFinalFloor()==getOutputArray().get(i).getFloor())
					return false;
			}
		}
		return true;
	}
	
	
	public void PrintRunningRecord(){
		 for(Iterator it = getOutputArray().iterator();it.hasNext();){
             System.out.println(it.next());
        }
	}
	public void GetInstruction(int instr,double d){
		Floor=Floor+instr;
		if(Time<d)
			Time=d;
		if(instr<0)
			setStatus(0);
		else if(instr>0)
			setStatus(1);
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
	public ArrayList<Output> getOutputArray() {
		return OutputArray;
	}
	public void setOutputArray(ArrayList<Output> outputArray) {
		OutputArray = outputArray;
	}
	public synchronized int getStatus() {
		return Status;
	}
	public synchronized void setStatus(int status) {
		Status = status;
	}

}

