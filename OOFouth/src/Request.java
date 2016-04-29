import java.util.ArrayList;


public class Request {
	protected int RequestFlag;//0代表是楼层请求1代表电梯内请求-1代表空请求
	protected int Direction;//楼层请求的运行方向，0代表向下，1代表向上，-1代表为电梯内请求
	protected int AimFloor;//目标楼层,若为楼层请求则为-1
	protected int RequestFloor;//请求发出所在的楼层，若为电梯内请求，则为-1
	protected double Time;
	protected ArrayList<Integer> ArriveFloor=new ArrayList<Integer>();
	protected String RequestStr;
//	private int RunningDirection;//电梯的运行方向-1为向下,0为不懂,1为向上。
	protected int finalFloor;//指令的目标楼层，方便排序
	private double[] arriveTime=new double[11];
	public Request(){
		RequestFlag=-1;
		
	}
	public void initArriveTime(int floor,double d){
		for(int i=1;i<=10;i++){
			arriveTime[i]=0.5*Math.abs(i-floor)+d;
		}
	}

	public Request(int a[]){//楼层请求的构造 函数，该死的楼层类，害我不得不多写了一个构造函数
		try{
		setRequestFlag(0);
		setDirection(a[0]);
		setAimFloor(-1);
		setRequestFloor(a[1]);
		setTime(a[2]);
		}catch(Exception e){
			System.out.println("格式错误！");
			System.exit(0);
		}
	}
	public Request(String str){//电梯请求的构造函数
		RequestStr=str;
		String[] str1=str.split("[(,)]");
		if(str1[1].equals("FR")){
			try{
			RequestFlag=0;
			AimFloor=-1;
			RequestFloor=Integer.parseInt(str1[2]);
			if(str1[3].equals("UP"))
				Direction=1;
			else if(str1[3].equals("DOWN"))
				Direction=0;
			else{
				System.out.println("格式错误！");
				System.exit(0);
			}
			Time=Integer.parseInt(str1[4]);
			} catch(NumberFormatException e){
				
			}
		}
		else if(str1[1].equals("ER")){
			try{
			setRequestFlag(1);	
			setRequestFloor(-1);
			setAimFloor(Integer.parseInt(str1[2]));
			setDirection(-1);
			setTime(Integer.parseInt(str1[3]));
			} catch(NumberFormatException e){
				System.out.println("格式错误！");
				System.exit(0);
			}
		}
		else{
			System.out.println("格式错误！");
			System.exit(0);
		}
		if(RequestFlag==0){
			setFinalFloor(RequestFloor);
		}
		else{
			setFinalFloor(AimFloor);
		}
		ArriveFloor.add(finalFloor);
	}
	public int getRequestFlag() {
		return RequestFlag;
	}
	public void setRequestFlag(int requestFlag) {
		RequestFlag = requestFlag;
	}
	public int getDirection() {
		return Direction;
	}
	public void setDirection(int direction) {
		Direction = direction;
	}
	public int getAimFloor() {
		return AimFloor;
	}
	public void setAimFloor(int aimFloor) {
		AimFloor = aimFloor;
	}
	public int getRequestFloor() {
		return RequestFloor;
	}
	public void setRequestFloor(int requestFloor) {
		RequestFloor = requestFloor;
	}
	public double getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	public boolean canBeCarried(Request req,int Floor,double elevatorTime){
		ArriveFloor.add(Floor);
		int i;
		if(RequestFlag==0){
			if(req.getRequestFlag()==0){
				if(((RequestFloor<=req.getRequestFloor()&&req.getRequestFloor()<=Floor)||
				(RequestFloor>=req.getRequestFloor()&&req.getRequestFloor()>=Floor))&&
				((req.getDirection()==1&&finalFloor>Floor)||
					(req.getDirection()==0&&finalFloor<Floor)||(finalFloor==Floor)	)){
					if((req.getTime()<arriveTime[req.getRequestFloor()])||
						req.getTime()==arriveTime[req.getRequestFloor()]&&ArriveFloor.contains(req.getRequestFloor())	){
						if(req.getDirection()==1&&finalFloor>Floor){
							for(i=req.getRequestFloor()+1;i<=10;i++)
								arriveTime[i]+=1;
						}
						else if(req.getDirection()==0&&finalFloor<Floor){
							for(i=req.getRequestFloor()-1;i>=1;i--)
								arriveTime[i]+=1;
						}
						ArriveFloor.add(req.getRequestFloor());
						return true;
					}
					
				}
			}
			else{
				if((finalFloor<Floor||finalFloor==Floor)&&req.getAimFloor()<=Floor&&(req.getTime()<arriveTime[req.getAimFloor()]||
						(req.getTime()==arriveTime[req.getAimFloor()]&&ArriveFloor.contains(req.getAimFloor())))){
					if(finalFloor<Floor)
					for(i=req.getAimFloor()-1;i>=1;i--)
						arriveTime[i]+=1;
					ArriveFloor.add(req.getAimFloor());
					return true;
				}
				else if((finalFloor>Floor||finalFloor==Floor)&&req.getAimFloor()>=Floor&&(req.getTime()<arriveTime[req.getAimFloor()]||
						(req.getTime()==arriveTime[req.getAimFloor()]&&ArriveFloor.contains(req.getAimFloor())))){
					if(finalFloor>Floor)
					for(i=req.getAimFloor()+1;i<=10;i++)
						arriveTime[i]+=1;
					ArriveFloor.add(req.getAimFloor());
					return true;
				}
			}
		}
		else{
			if(req.getRequestFlag()==0){
				if(((AimFloor<=req.getRequestFloor()&&req.getRequestFloor()<=Floor)||
				(AimFloor>=req.getRequestFloor()&&req.getRequestFloor()>=Floor))&&
				((req.getDirection()==0&&(AimFloor-Floor)<0)||
				(req.getDirection()==1&&(AimFloor-Floor)>0)||(AimFloor==Floor))&&
				((req.getTime()<arriveTime[req.getRequestFloor()])||
			req.getTime()==arriveTime[req.getRequestFloor()]&&ArriveFloor.contains(req.getRequestFloor())	)){
					if(req.getDirection()==1&&(AimFloor-Floor)>0){
						for(i=req.getRequestFloor()+1;i<=10;i++)
							arriveTime[i]+=1;
					}
					else if(req.getDirection()==0&&(AimFloor-Floor)<0){
						for(i=req.getRequestFloor()-1;i>=1;i--)
							arriveTime[i]+=1;
					}
					ArriveFloor.add(req.getAimFloor());
					return true;
				}
		}
			else{
				if(((AimFloor-Floor)<0||AimFloor==Floor)&&req.getAimFloor()<=Floor&&(req.getTime()<arriveTime[req.getAimFloor()]||
						(req.getTime()==arriveTime[req.getAimFloor()]&&ArriveFloor.contains(req.getAimFloor())))){
					if(AimFloor-Floor<0)
					for(i=req.getAimFloor()-1;i>=1;i--)
						arriveTime[i]+=1;
					ArriveFloor.add(req.getAimFloor());
					return true;
				}
				else if(((AimFloor-Floor)>0)&&req.getAimFloor()>=Floor&&(req.getTime()<arriveTime[req.getAimFloor()]||
						(req.getTime()==arriveTime[req.getAimFloor()]&&ArriveFloor.contains(req.getAimFloor())))){
					if(AimFloor-Floor>0)
					for(i=req.getAimFloor()+1;i<=10;i++)
						arriveTime[i]+=1;	
					ArriveFloor.add(req.getAimFloor());
					return true;
				}
				}
		}
		return false;
	}
	public int getFinalFloor() {
		return finalFloor;
	}
	public void setFinalFloor(int finalFloor) {
		this.finalFloor = finalFloor;
	}
	public double[] getArriveTime(){
		return arriveTime;
	}
	public String toString(){
		return RequestStr;
	}
}

