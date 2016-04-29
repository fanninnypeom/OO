import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Taxi {
	private int number;
	private ArrayList<Request> waitingReqArray;
	private Request nowRequest;
	private int status; //1为等待服务状态，2为即将服务状态，3为正在服务状态，4为停止状态
	private ArrayList<Integer> optimalPath;
	private ArrayList<Integer> optimalGuestPath;
	private int x;
	private int y;
	private int credit;
	private boolean receiveFlag;
	private int waitCount;
	private int stopCount;
	public Taxi(int num){
		receiveFlag=false;
		waitingReqArray=new ArrayList<Request>();
		optimalPath=new ArrayList<Integer>();
		optimalGuestPath=new ArrayList<Integer>();
		number=num;
		status=1;
		Random random=new Random();
		if(num<80){
		x=num;
		y=num;
		}
		else{
		x=num-80;
		y=num-80;	
		}
		//x=random.nextInt(80);
		//y=random.nextInt(80);
		credit=0;
		setWaitCount(200);
	}
	public String toString(){
		return "number"+number+"x"+x+"y"+y;
	}
	public int distanceToReq(Request req){
		Queue<Integer> Q=new LinkedList<Integer>();
		int[] color=new int[6400];
		int[] d=new int[6400];
		int[] father=new int[6400];
		int u;
		ArrayList<Integer> adj=new ArrayList<Integer>();
		for(int i=0;i<6400;i++){
			father[i]=-1;
		}
		int n=x*80+y;
		Q.add(n);
		color[n]=1;//gray
		d[n]=0;
		while(!Q.isEmpty()){
			adj.clear();
			u=Q.poll();
			getAdj(u,adj);
			for(int i=0;i<adj.size();i++){
				int t=adj.get(i);
				if(color[t]==0){
					color[t]=1;
					d[t]+=1;
					father[t]=u;
					Q.add(t);
				}
			}
			color[u]=2;
			if(d[req.getX()*80+req.getY()]!=0)
				break;
		}
		return d[req.getX()*80+req.getY()];
	}
	public void add(Request req){
		waitingReqArray.add(req);
	}
	public void getAdj(int u,ArrayList<Integer> adj){
		int xx=u/80;
		int yy=u%80;
		if(Map.map[xx][yy]==1||Map.map[xx][yy]==3)
			adj.add(u+1);
		if(Map.map[xx][yy]==2||Map.map[xx][yy]==3)
			adj.add(u+80);
		if(yy>=1&&(Map.map[xx][yy-1]==1||Map.map[xx][yy-1]==3))
			adj.add(u-1);
		if(xx>=1&&(Map.map[xx-1][yy]==2||Map.map[xx-1][yy]==3))
			adj.add(u-80);
	}
	public void calOptimalPath(){
		Queue<Integer> Q=new LinkedList<Integer>();
		int[] color=new int[6400];
		int[] d=new int[6400];
		int[] father=new int[6400];
		int u;
		ArrayList<Integer> adj=new ArrayList<Integer>();
		for(int i=0;i<6400;i++){
			father[i]=-1;
		}
		int n=nowRequest.getX()*80+nowRequest.getY();
		Q.add(n);
		color[n]=1;//gray
		d[n]=0;
		while(!Q.isEmpty()){
			adj.clear();
			u=Q.poll();
			getAdj(u,adj);
			for(int i=0;i<adj.size();i++){
				int t=adj.get(i);
				if(color[t]==0){
					color[t]=1;
					d[t]+=1;
					father[t]=u;
					Q.add(t);
				}
			}
			color[u]=2;
			if(d[nowRequest.getAim_x()*80+nowRequest.getAim_y()]!=0)
				break;
		}
		optimalPath.clear();
		getPath(father,nowRequest.getAim_x()*80+nowRequest.getAim_y());
	}
	
	
	public void getPath(int[] father,int aim){
		optimalPath.add(aim);
		while(father[aim]!=-1){
			optimalPath.add(father[aim]);
			aim=father[aim];
		}
	}
	public void getGuestPath(int[] father,int aim){
		optimalGuestPath.add(aim);
		while(father[aim]!=-1){
			optimalGuestPath.add(father[aim]);
			aim=father[aim];
		}
	}
	public void calOptimalGuestPath(){
		Queue<Integer> Q=new LinkedList<Integer>();
		int[] color=new int[6400];
		int[] d=new int[6400];
		int[] father=new int[6400];
		int u;
		ArrayList<Integer> adj=new ArrayList<Integer>();
		for(int i=0;i<6400;i++){
			father[i]=-1;
		}
		int n=x*80+y;
		Q.add(n);
		color[n]=1;//gray
		d[n]=0;
		while(!Q.isEmpty()){
			adj.clear();
			u=Q.poll();
			getAdj(u,adj);
			for(int i=0;i<adj.size();i++){
				int t=adj.get(i);
				if(color[t]==0){
					color[t]=1;
					d[t]+=1;
					father[t]=u;
					Q.add(t);
				}
			}
			color[u]=2;
			if(d[nowRequest.getX()*80+nowRequest.getY()]!=0)
				break;
		}
		optimalGuestPath.clear();
		getGuestPath(father,nowRequest.getX()*80+nowRequest.getY());
	}
	public void start(){
			nowRequest=waitingReqArray.get(0);
			waitingReqArray.remove(0);
			calOptimalGuestPath();
			for(int i=0;i<optimalGuestPath.size();i++){
			System.out.print("["+optimalGuestPath.get(i)/80+" "+optimalGuestPath.get(i)%80+"]"+" ");
			}
			System.out.println(" ");
			calOptimalPath();
			for(int i=0;i<optimalPath.size();i++){
				System.out.print("["+optimalPath.get(i)/80+" "+optimalPath.get(i)%80+"]"+" ");
				}
			System.out.println(" ");
			status=2;
	}
	public void move(){
		boolean doneFlag=false;
		while(!doneFlag)
		if(getWaitCount()==0&&status==1){
			status=4;
			setStopCount(10);
		}
		else if(status==1){
			setWaitCount(getWaitCount() - 1);
			doneFlag=true;
			int direct;
			while(true){
				Random random=new Random();
				direct=random.nextInt(4);
//				System.out.println("x "+x+"y "+y);
				if(direct==0){//东
					if(y<79&&Map.map[x][y]==1||Map.map[x][y]==3)
						break;
				}
				else if(direct==1){//南
					if(x<79&&Map.map[x][y]==2||Map.map[x][y]==3)
						break;
				}
				else if(direct==2){//西
					if(y>=1&&(Map.map[x][y-1]==1||Map.map[x][y-1]==3))
						break;
				}
				else{//北
					if(x>=1&&(Map.map[x-1][y]==2||Map.map[x-1][y]==3))
						break;
				}
			}
			//System.out.println("move");
			if(direct==0){
				y=y+1;
			}
			else if(direct==1){
				x=x+1;
			}
			else if(direct==2){
				y=y-1;
			}
			else{
				x=x-1;
			}
	
		}
		else if(status==2){
			doneFlag=true;
			if(optimalGuestPath.size()==0){
				status=4;
				receiveFlag=true;
				doneFlag=false;
				setStopCount(10);
			}
			else{
			int point=x*80+y;
			int aim=optimalGuestPath.get(optimalGuestPath.size()-1);
			optimalGuestPath.remove(new Integer(aim));
			
			if(point-1==aim){
				y--;
			}
			else if(point+1==aim){
				y++;
			}
			else if(point+80==aim){
				x++;
			}
			else if(point-80==aim){
				x--;
			}
			}
		}
		else if(status==3){
			doneFlag=true;
			if(optimalPath.size()==0){
				status=4;
				setStopCount(10);
				doneFlag=false;
			}
			else{
			int point=x*80+y;
			int aim=optimalPath.get(optimalPath.size()-1);
			optimalPath.remove(new Integer(aim));
			if(point-1==aim){
				y--;
			}
			else if(point+1==aim){
				y++;
			}
			else if(point+80==aim){
				x++;
			}
			else if(point-80==aim){
				x--;
			}
			}
		}
		else{
			doneFlag=true;
			if(getStopCount()==0&&!receiveFlag){
				status=1;
				doneFlag=false;
				setWaitCount(200);
			}
			else if(getStopCount()==0&&receiveFlag){
				status=3;
				receiveFlag=false;
				doneFlag=false;
			}
			else
				setStopCount(getStopCount() - 1);
		}
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Request getNowRequest() {
		return nowRequest;
	}

	public void setNowRequest(Request nowRequest) {
		this.nowRequest = nowRequest;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getX() {
		return x;
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
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean near(Request req) {
		if((x==req.getX()||x==req.getX()+1||x==req.getX()+2
		||x==req.getX()-1||x==req.getX()-2)&&
		(y==req.getY()||y==req.getY()+80||y==req.getY()+160	
		||x==req.getX()-80||x==req.getX()-160))
			return true;
		else
			return false;
	}
	public int getWaitCount() {
		return waitCount;
	}
	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}
	public int getStopCount() {
		return stopCount;
	}
	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}
}
