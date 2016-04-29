
public class Request {
	private int RequestFlag;//0代表是楼层请求1代表电梯内请求-1代表空请求
	private int Direction;//楼层请求的运行方向，0代表向下，1代表向上，-1代表为电梯内请求
	private int AimFloor;//目标楼层,若为楼层请求则为-1
	private int RequestFloor;//请求发出所在的楼层，若为电梯内请求，则为-1
	private int Time;
	public Request(){
		RequestFlag=-1;
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
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
}
