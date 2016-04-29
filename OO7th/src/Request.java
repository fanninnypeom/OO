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
		number=num;
		x=x1;
		y=y1;
		aim_x=aim_x1;
		aim_y=aim_y1;
		count=30;
		waitingTaxi=new ArrayList<Taxi>();
	}
	public String toString(){
		return ("num"+number+"x"+x+"y"+y);
	}
	public void sub(){
		count--;
//		System.out.println("count"+count);
	}
	public int getX() {
		return x;
	}
	public int size(){
		return waitingTaxi.size();
	}
	public Taxi get(int i){
		return waitingTaxi.get(i);
	}
	public void addTaxi(Taxi t){
		if(!waitingTaxi.contains(t)){
			waitingTaxi.add(t);
			System.out.println(t.getNumber()+"号出租车加入"+getNumber()+"号请求的抢单");
			System.out.println("出租车的位置为("+t.getX()+" "+t.getY()+")");
		}
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAim_x() {
		return aim_x;
	}
	public void setAim_x(int aim_x) {
		this.aim_x = aim_x;
	}
	public int getAim_y() {
		return aim_y;
	}
	public void setAim_y(int aim_y) {
		this.aim_y = aim_y;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
