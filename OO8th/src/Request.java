import java.util.ArrayList;


public class Request {
	
	private int number;
	private int x;
	private int y;
	private int aim_x;
	private int aim_y;
	private int count;
	private ArrayList<Taxi> waitingTaxi;
	public Request(int num,int x1,int y1,int aim_x1,int aim_y1){
		//requires:nop
		//modify:nop
		//effect:init Request
		number=num;
		x=x1;
		y=y1;
		aim_x=aim_x1;
		aim_y=aim_y1;
		count=30;
		waitingTaxi=new ArrayList<Taxi>();
	}
	public String toString(){
		//requires:nop
		//modify:nop
		//effect:override the toString method
		return ("num"+number+"x"+x+"y"+y);
	}
	public void sub(){
		//requires:nop
		//modify:nop
		//effect:count--
		count--;
//		System.out.println("count"+count);
	}
	public int getX() {
		//requires:nop
		//modify:nop
		//effect:return x
		return x;
	}
	public int size(){
		//requires:waitingTaxi must be inited
		//modify:nop
		//effect:size of waitingTaxi
		return waitingTaxi.size();
	}
	public Taxi get(int i){
		//requires:waitingTaxi must have i+1 elements
		//modify:nop
		//effect:return the i-th taxi in waitingTaxi
		return waitingTaxi.get(i);
	}
	public void addTaxi(Taxi t){
		//requires:waitingTaxi must be inited
		//modify:nop
		//effect:add Taxi to waitingTaxi
		if(!waitingTaxi.contains(t)){
			waitingTaxi.add(t);
	//		System.out.println(t.getNumber()+"号出租车加入"+getNumber()+"号请求的抢单");
	//		System.out.println("出租车的位置为("+t.getX()+" "+t.getY()+")");
		}
	}
	public void setX(int x) {
		//requires:nop
		//modify:nop
		//effect:assign this.x to x
		this.x = x;
	}
	public int getY() {
		//requires:nop
				//modify:nop
				//effect:return y
		return y;
	}
	public void setY(int y) {
		//requires:nop
				//modify:nop
				//effect:assign this.y to y
		this.y = y;
	}
	public int getAim_x() {
		//requires:nop
				//modify:nop
				//effect:return aim_x
		return aim_x;
	}
	public void setAim_x(int aim_x) {
		//requires:nop
				//modify:nop
				//effect:assign this.aim_x to aim_x
		this.aim_x = aim_x;
	}
	public int getAim_y() {
		//requires:nop
				//modify:nop
				//effect:return aim_y
		return aim_y;
	}
	public void setAim_y(int aim_y) {
		//requires:nop
				//modify:nop
				//effect:assign this.aim_y to y
		this.aim_y = aim_y;
	}
	public int getCount() {
		//requires:nop
				//modify:nop
				//return count
		return count;
	}
	public void setCount(int count) {
		//requires:nop
				//modify:nop
				//effect:assign this.count to count
		this.count = count;
	}
	public int getNumber() {
		//requires:nop
				//modify:nop
				//effect:return number
		return number;
	}
	public void setNumber(int number) {
		//requires:nop
				//modify:nop
				//effect:assign this.number to number
		this.number = number;
	}
	
}
