import java.util.ArrayList;


public class threadElevator extends elevator implements Runnable{
	private volatile threadRequest threadReq;
	private volatile ArrayList<threadRequest> pickedThreadReq=null;
	private volatile int elevatorNum;
	private volatile int aim;
	private volatile int direction;
	private volatile ArrayList<Integer> picked=null;
	private volatile boolean start;
	private volatile int totalMovement=0;
	private volatile double currentTime;
	public threadElevator(int num){
		setElevatorNum(num);
		picked=new ArrayList<Integer>();
		pickedThreadReq=new ArrayList<threadRequest>();
		start=false;
	}
	public void run() {
		while(true){
			if(start==true){

				if(direction>0){
					setStatus(1);
					for(;Floor<aim;){
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setCurrentTime(getCurrentTime() + 3.0);
						Floor++;
						setTotalMovement(getTotalMovement() + 1);
						if(picked.contains(Floor)&&Floor!=aim){
							System.out.print("("+elevatorNum+","+Floor+","+"UP"+","+getTotalMovement()+",");
							System.out.printf("%.1f",getCurrentTime());
							System.out.println(")");
							while(picked.indexOf(Floor)!=-1){
								int temp=picked.indexOf(Floor);
								picked.remove(temp);
								System.out.println(pickedThreadReq.get(temp));
								pickedThreadReq.remove(temp);
								
							}
							try {
								Thread.sleep(6000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							setCurrentTime(getCurrentTime() + 6.0);
						}
					}
					System.out.print("("+elevatorNum+","+Floor+","+"UP"+","+getTotalMovement()+",");
					System.out.printf("%.1f",getCurrentTime());
					System.out.println(")");
					System.out.println(threadReq);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setCurrentTime(getCurrentTime() + 6.0);
					setStatus(-1);
					start=false;
				}
				else if(direction<0){
					setStatus(0);
//					System.out.println("aim"+aim);
					for(;Floor>aim;){
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setCurrentTime(getCurrentTime() + 3.0);
						Floor--;
						setTotalMovement(getTotalMovement() + 1);
						if(picked.contains(Floor)&&Floor!=aim){
							System.out.print("("+elevatorNum+","+Floor+","+"DOWN"+","+getTotalMovement()+",");
							System.out.printf("%.1f",getCurrentTime());
							System.out.println(")");
							while(picked.indexOf(Floor)!=-1){
								int temp=picked.indexOf(Floor);
								picked.remove(temp);
								System.out.println(pickedThreadReq.get(temp));
								pickedThreadReq.remove(temp);
								
							}
							try {
								Thread.sleep(6000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							setCurrentTime(getCurrentTime() + 6.0);
							
						}
					}
					System.out.print("("+elevatorNum+","+Floor+","+"DOWN"+","+getTotalMovement()+",");
					System.out.printf("%.1f",getCurrentTime());
					System.out.println(")");
					System.out.println(threadReq);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setCurrentTime(getCurrentTime() + 6.0);
					setStatus(-1);
					start=false;
				}
				else{
					setStatus(0);
				///	System.out.println("·½Ïò"+direction);
					System.out.print("("+elevatorNum+","+Floor+","+"stay"+","+getTotalMovement()+",");
					System.out.printf("%.1f",getCurrentTime());
					System.out.println(")");
					System.out.println(threadReq);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setCurrentTime(getCurrentTime() + 6.0);
					setStatus(-1);
					start=false;
				}
			}
		}
		
	}
	public int getElevatorNum() {
		return elevatorNum;
	}
	public void setElevatorNum(int elevatorNum) {
		this.elevatorNum = elevatorNum;
	}
	public int getAim() {
		return aim;
	}
	public void setAim(int aim) {
		this.aim = aim;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}

	public void setPicked(int picked) {
		if((picked>threadReq.getFinalFloor()&&direction>0)||
				(picked<threadReq.getFinalFloor()&&direction<0)){
			aim=picked;
			this.picked.add(threadReq.getFinalFloor());
			threadRequest temp=threadReq;
			threadReq=pickedThreadReq.get(pickedThreadReq.size()-1);
			pickedThreadReq.remove(pickedThreadReq.size()-1);
			pickedThreadReq.add(temp);
		}
		else
		this.picked.add(picked);
	}
	public int getTotalMovement() {
		return totalMovement;
	}
	public void setTotalMovement(int totalMovement) {
		this.totalMovement = totalMovement;
	}

	public void setPickedThreadReq(threadRequest pickedThreadReq) {
		this.pickedThreadReq.add(pickedThreadReq);
	}
	public threadRequest getThreadReq() {
		return threadReq;
	}
	public void setThreadReq(threadRequest threadReq) {
		this.threadReq = threadReq;
	}
	public double getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}

}
