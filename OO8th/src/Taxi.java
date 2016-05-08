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
	private ArrayList<Integer> optimalPath1;
	private ArrayList<Integer> optimalGuestPath;
	private ArrayList<Integer> optimalGuestPath1;
	private int pre_direction;
	//private int pre_y;
	private int x;
	private int y;
	private int credit;
	private boolean receiveFlag;
	private int waitCount;
	private int stopCount;
	public Taxi(int num){
		//requires:num must >=0
				//modify:nop 
				//effect:init Taxi
		pre_direction=-1;
		receiveFlag=false;
		waitingReqArray=new ArrayList<Request>();
		optimalPath=new ArrayList<Integer>();
		optimalGuestPath=new ArrayList<Integer>();
		optimalPath1=new ArrayList<Integer>();
		optimalGuestPath1=new ArrayList<Integer>();
		number=num;
		status=1;
		Random random=new Random();
		x=random.nextInt(80);
		y=random.nextInt(80);
//		x=12;
//		y=12;
		credit=0;
		setWaitCount(200);
	}
	public String toString(){
		//requires:nop
				//modify:nop 
				//effect:override toString method
		return "number"+number+"x"+x+"y"+y;
	}
	public boolean adj(int i,int j){
		//requires:i>=0&&i<6400 j>=0&&j<6400
		//modify: nop
		//effect:judge if the i and j is adjant
		ArrayList<Integer> adj=new ArrayList<Integer>();
		getAdj(i,adj);
		if(adj.contains(j)){
			return true;
		}
		return false;
	}
	public int distanceToReq(int u1,Request req){
		//requires:req must be a Request objective
		//modify:nop 
		//effect:return how distance between the taxi and the place that the req come from
		int x=u1/80;
		int y=u1%80;
		if(x==req.getX()&&y==req.getY())
			return 0;

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
					d[t]=d[u]+1;
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
	public int distanceToAim(int u1,Request req){
		//requires:req must be a Request objective
				//modify:nop 
				//effect:return how distance between the taxi and the place that the req come from
		int x=u1/80;
		int y=u1%80;
		if(x==req.getAim_x()&&y==req.getAim_y())
			return 0;
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
					d[t]=d[u]+1;
					father[t]=u;
					Q.add(t);
				}
			}
			color[u]=2;
			if(d[req.getAim_x()*80+req.getAim_y()]!=0)
				break;
		}
		return d[req.getAim_x()*80+req.getAim_y()];
	}
	public void add(Request req){
		//requires:req can't be null
				//modify:nop 
				//effect:add req to waitingReqArray
		waitingReqArray.add(req);
	}
	public void getAdj(int u,ArrayList<Integer> adj){
		//requires:adj can't have any element and u>=0&&u<6400
				//modify:adj
				//effect:add the point nearby the u point to the adj arraylist 
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
		//requires:nowRequest can't be null
		//modify:optimalPath and optimalPath1
		//effect:calculate the shortest path to the aim and fill it in optimalPath and optimalPath1
		if(x==nowRequest.getAim_x()&&y==nowRequest.getAim_y()){
			optimalPath.clear();
			return;
		}
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
			if(u==-1){
				Q.add(-1);
				continue;
			}
			if(u==-1&&Q.isEmpty()){
				break;
			}
			if(Q.isEmpty())
				Q.add(-1);
			getAdj(u,adj);
			for(int i=0;i<adj.size();i++){
				int t=adj.get(i);
				try{
				if(color[t]==0){
					color[t]=1;
					d[t]+=1;
					father[t]=u;
					Q.add(t);
				}
				}catch(ArrayIndexOutOfBoundsException e){
					
					}
			}
			color[u]=2;
			if(d[nowRequest.getAim_x()*80+nowRequest.getAim_y()]!=0)
				break;
		}
		int aim=nowRequest.getAim_x()*80+nowRequest.getAim_y();
		optimalPath.clear();
		getPath(father,nowRequest.getAim_x()*80+nowRequest.getAim_y());
		while(true){
			if((u=Q.poll())==-1){
				break;
			}
			if((u-80==aim||u+80==aim||u+1==aim||u-1==aim)&&adj(u,aim)){
					father[aim]=u;
				getPath1(father,nowRequest.getAim_x()*80+nowRequest.getAim_y());
				if(optimalPath1.get(optimalPath1.size()-2)!=optimalPath.get(optimalPath.size()-2)&&
						smallerFlow(optimalPath1.get(optimalPath1.size()-2),optimalPath.get(optimalPath.size()-2))){
					try{
						optimalPath.clear();
						optimalPath.addAll(optimalPath1);
				//		System.arraycopy(optimalPath1, 0, optimalPath, 0, optimalPath.size());
						}catch(ArrayStoreException e){
							System.out.println("Path1"+optimalPath1);
							System.out.println("Path"+optimalPath);
							System.exit(0);
						}
				}
				optimalPath1.clear();
			}
		}
	}
	public boolean smallerFlow(int a,int b){
		//requires:a and b must be point in the map 
		//modify:nop 
		//effect:judge the flow of a and b
		int r1=0,r2=0;
		int a_x=a/80;
		int a_y=a%80;
		int b_x=b/80;
		int b_y=b%80;
		if(x==a_x+1){
			r1=Map.flowMap[a_x][y][1];
		}
		else if(x==a_x-1){
			r1=Map.flowMap[x][y][1];
		}
		else if(y==a_y+1){
			r1=Map.flowMap[x][a_y][0];
		}
		else if(y==a_y-1){
			r1=Map.flowMap[x][y][0];
		}
		if(x==b_x+1){
			r2=Map.flowMap[b_x][y][1];
		}
		else if(x==b_x-1){
			r2=Map.flowMap[x][y][1];
		}
		else if(y==b_y+1){
			r2=Map.flowMap[x][b_y][0];
		}
		else if(y==b_y-1){
			r2=Map.flowMap[x][y][0];
		}
		if(r1<r2)
			return true;
		else
			return false;
	}
	
	public int Flow(int a){
		//requires:a and b must be point in the map 
		//modify:nop 
		//effect:judge the flow of a and b
		int r1=0,r2=0;
		int a_x=a/80;
		int a_y=a%80;
		if(x==a_x+1){
			r1=Map.flowMap[a_x][y][1];
		}
		else if(x==a_x-1){
			r1=Map.flowMap[x][y][1];
		}
		else if(y==a_y+1){
			r1=Map.flowMap[x][a_y][0];
		}
		else if(y==a_y-1){
			r1=Map.flowMap[x][y][0];
		}
		
		return r1;
	}
	
	public void getPath1(int[] father,int aim){
		//requires: have found the aim point
		//modify: nop
		//effect:fill the optimalPath1 with the shortest path
		optimalPath1.add(aim);
		while(father[aim]!=-1){
			optimalPath1.add(father[aim]);
			aim=father[aim];
		}
	}
	public void getPath(int[] father,int aim){
		//requires: have found the aim point
		//modify: nop
		//effect:fill the optimalPath with the shortest path
		optimalPath.add(aim);
		while(father[aim]!=-1){
			optimalPath.add(father[aim]);
			aim=father[aim];
		}
	}
	public void getGuestPath(int[] father,int aim){
		//requires: have found the aim point
		//modify: nop
		//effect:fill the optimalGuestPath with the shortest path
		optimalGuestPath.add(aim);
		while(father[aim]!=-1){
			optimalGuestPath.add(father[aim]);
			aim=father[aim];
		}
	}
	public void getGuestPath1(int[] father,int aim){
		//requires: have found the aim point
		//modify: nop
		//effect:fill the optimalGuestPath1 with the shortest path
		optimalGuestPath1.add(aim);
		while(father[aim]!=-1){
			optimalGuestPath1.add(father[aim]);
			aim=father[aim];
		}
	}
	public void calOptimalGuestPath(){
		//requires:nowRequest can't be null
		//modify:optimalGuestPath and optimalGuestPath1
		//effect:calculate the shortest path to the aim and fill it in optimalGuestPath and optimalGuestPath1
		if(x==nowRequest.getX()&&y==nowRequest.getY()){
			optimalGuestPath.clear();
			return;
		}
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
			if(u==-1){
				Q.add(-1);
				continue;
			}
			if(u==-1&&Q.isEmpty()){
				break;
			}
			if(Q.isEmpty())
				Q.add(-1);
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
//		optimalGuestPath.clear();
//		getGuestPath(father,nowRequest.getX()*80+nowRequest.getY());
		int aim=nowRequest.getX()*80+nowRequest.getY();
		optimalGuestPath.clear();
		getGuestPath(father,nowRequest.getX()*80+nowRequest.getY());
		while(true){
			if((u=Q.poll())==-1){
				break;
			}
			if((u-80==aim||u+80==aim||u+1==aim||u-1==aim)&&adj(u,aim)){
				father[aim]=u;
				getGuestPath1(father,nowRequest.getX()*80+nowRequest.getX());
				if(optimalGuestPath1.get(optimalGuestPath1.size()-2)!=optimalGuestPath.get(optimalGuestPath.size()-2)&&
						smallerFlow(optimalGuestPath1.get(optimalGuestPath1.size()-2),optimalGuestPath.get(optimalGuestPath.size()-2))){
					try{
						optimalGuestPath.clear();
						optimalGuestPath.addAll(optimalGuestPath1);
//					System.arraycopy(optimalGuestPath1, 0, optimalGuestPath, 0, optimalGuestPath.size());
					}catch(ArrayStoreException e){
						System.out.println("Path1"+optimalGuestPath1);
						System.out.println("Path"+optimalGuestPath);
						System.exit(0);
					}
				}
				optimalGuestPath1.clear();
			}
		}
	}
	public void start(){
		//requires:waitingReqarray should not be empty
				//modify:waitingReqarray
				//effect:set the status of taxi to 2
		nowRequest=waitingReqArray.get(0);
			waitingReqArray.remove(0);
//			calOptimalGuestPath();
//			for(int i=0;i<optimalGuestPath.size();i++){
//			System.out.print("["+optimalGuestPath.get(i)/80+" "+optimalGuestPath.get(i)%80+"]"+" ");
//			}
//			System.out.println(" ");
//			calOptimalPath();
//			for(int i=0;i<optimalPath.size();i++){
//				System.out.print("["+optimalPath.get(i)/80+" "+optimalPath.get(i)%80+"]"+" ");
//				}
//			System.out.println(" ");
			status=2;
	}
	public void move(){
		//requires:nowRequest can't be null
				//modify:optimalGuestPath and optimalGuestPath1
				//effect:change the position of taxi 
		boolean doneFlag=false;
		while(!doneFlag)
		if(getWaitCount()==0&&status==1){
			status=4;
			setStopCount(10);
		}
		else if(status==1){
			setWaitCount(getWaitCount() - 1);
			doneFlag=true;
			int minimum=101;
			int direct=0;
			if((y<79&&Map.map[x][y]==1||Map.map[x][y]==3)){
				if(Map.flowMap[x][y][0]<minimum)
					minimum=Map.flowMap[x][y][0];
			}
			if(x<79&&Map.map[x][y]==2||Map.map[x][y]==3){
				if(Map.flowMap[x][y][1]<minimum)
					minimum=Map.flowMap[x][y][1];
			}
			if(y>=1&&(Map.map[x][y-1]==1||Map.map[x][y-1]==3)){
				if(Map.flowMap[x][y-1][0]<minimum)
					minimum=Map.flowMap[x][y-1][0];
			}
			if(x>=1&&(Map.map[x-1][y]==2||Map.map[x-1][y]==3)){
				if(Map.flowMap[x-1][y][1]<minimum)
					minimum=Map.flowMap[x-1][y][1];
			}
		while(true){
				Random random=new Random();
				direct=random.nextInt(4);
//				System.out.println("x "+x+"y "+y);
				if(direct==0){//东
					if((y<79&&Map.map[x][y]==1||Map.map[x][y]==3)&&Map.flowMap[x][y][0]==minimum)
						break;
				}
				else if(direct==1){//南
					if((x<79&&Map.map[x][y]==2||Map.map[x][y]==3)&&Map.flowMap[x][y][1]==minimum)
						break;
				}
				else if(direct==2){//西
					if(y>=1&&(Map.map[x][y-1]==1||Map.map[x][y-1]==3)&&Map.flowMap[x][y-1][0]==minimum)
						break;
				}
				else{//北
					if(x>=1&&(Map.map[x-1][y]==2||Map.map[x-1][y]==3)&&Map.flowMap[x-1][y][1]==minimum)
						break;
				}
			}
			//System.out.println("move");
			if(pre_direction==-1){
				
			}
			else if(pre_direction==0){
				Map.flowMap[x][y-1][0]--;
			}
			else if(pre_direction==1){
				Map.flowMap[x-1][y][1]--;
			}
			else if(pre_direction==2){
				Map.flowMap[x][y][0]--;
			}
			else{
				Map.flowMap[x][y][1]--;
			}
			pre_direction=direct;
			if(direct==0){
				Map.flowMap[x][y][0]++;
				y=y+1;
			}
			else if(direct==1){
				Map.flowMap[x][y][1]++;
				x=x+1;
			}
			else if(direct==2){
				Map.flowMap[x][y-1][0]++;
				y=y-1;
			}
			else{
				Map.flowMap[x-1][y][1]++;
				x=x-1;
			}
	
		}
		else if(status==2){
			doneFlag=true;
//			calOptimalGuestPath();
			ArrayList<Integer> t=new ArrayList<Integer>();
			getAdj(x*80+y,t);
			int nearest=10000;
			int index=0;
			if(distanceToReq(x*80+y,nowRequest)==0){
				status=4;
				receiveFlag=true;
				doneFlag=false;
				setStopCount(10);
			}
			else{				
			for(int i=0;i<t.size();i++){
				if(nearest>distanceToReq(t.get(i),nowRequest)){
					nearest=distanceToReq(t.get(i),nowRequest);
					index=i;
				}
				else if(nearest==distanceToReq(t.get(i),nowRequest)){
					if(smallerFlow(t.get(i),t.get(index))){
						index=i;
					}
				}
			}
/*			if(optimalGuestPath.size()<=1){
				status=4;
				receiveFlag=true;
				doneFlag=false;
				setStopCount(10);
			}*/
			
			int point=x*80+y;
			int aim=t.get(index);
			//			int aim=optimalGuestPath.get(optimalGuestPath.size()-2);
//			optimalGuestPath.remove(new Integer(aim));
if(pre_direction==-1){
				
			}
			else if(pre_direction==0){
				Map.flowMap[x][y-1][0]--;
			}
			else if(pre_direction==1){
				Map.flowMap[x-1][y][1]--;
			}
			else if(pre_direction==2){
				Map.flowMap[x][y][0]--;
			}
			else{
				Map.flowMap[x][y][1]--;
			}
			if(point-1==aim){
				pre_direction=2;
				Map.flowMap[x][y-1][0]++;
				y--;
			}
			else if(point+1==aim){
				pre_direction=0;
				Map.flowMap[x][y][0]++;
				y++;
			}
			else if(point+80==aim){
				pre_direction=1;
				Map.flowMap[x][y][1]++;
				x++;
			}
			else if(point-80==aim){
				pre_direction=3;
				Map.flowMap[x-1][y][1]++;
				x--;
			}
			}
		}
		else if(status==3){
			doneFlag=true;
			ArrayList<Integer> t=new ArrayList<Integer>();
			getAdj(x*80+y,t);
			int nearest=10000;
			int index=0;
			if(distanceToAim(x*80+y,nowRequest)==0){
				status=4;
				doneFlag=false;
				setStopCount(10);
			}
			else{
			for(int i=0;i<t.size();i++){
				if(nearest>distanceToAim(t.get(i),nowRequest)){
					nearest=distanceToAim(t.get(i),nowRequest);
					index=i;
				}
				else if(nearest==distanceToAim(t.get(i),nowRequest)){
					if(smallerFlow(t.get(i),t.get(index))){
						index=i;
					}
				}
			}
//			calOptimalPath();

//			if(optimalPath.size()<=1){
//				status=4;
//				setStopCount(10);
//				doneFlag=false;
//			}
//			else{
			int point=x*80+y;
			int aim=t.get(index);
			//			int aim=optimalPath.get(optimalPath.size()-2);
//			optimalPath.remove(new Integer(aim));
			if(pre_direction==-1){
				
			}
			else if(pre_direction==0){
				Map.flowMap[x][y-1][0]--;
			}
			else if(pre_direction==1){
				Map.flowMap[x-1][y][1]--;
			}
			else if(pre_direction==2){
				Map.flowMap[x][y][0]--;
			}
			else{
				Map.flowMap[x][y][1]--;
			}
			if(point-1==aim){
				pre_direction=2;
				Map.flowMap[x][y-1][0]++;
				y--;
			}
			else if(point+1==aim){
				pre_direction=0;
				Map.flowMap[x][y][0]++;
				y++;
			}
			else if(point+80==aim){
				pre_direction=1;
				Map.flowMap[x][y][1]++;
				x++;
			}
			else if(point-80==aim){
				pre_direction=3;
				Map.flowMap[x-1][y][1]++;
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
		//requires:nop
				//modify:nop
				//effect:return number
		return number;
	}

	public void setNumber(int number) {
		//requires:nop
				//modify:nop
				//effect:assign this.number
		this.number = number;
	}

	public Request getNowRequest() {
		//requires:nop
		//modify:nop
		//effect:return nowRequest
		return nowRequest;
	}

	public void setNowRequest(Request nowRequest) {
		//requires:nop
		//modify:nop
		//effect:assign this.nowRequest
		this.nowRequest = nowRequest;
	}

	public int getStatus() {
		//requires:nop
		//modify:nop
		//effect:return status
		return status;
	}

	public void setStatus(int status) {
		//requires:nop
		//modify:nop
		//effect:assign this.status
		this.status = status;
	}

	public int getX() {
		//requires:nop
		//modify:nop
		//effect:return x
		return x;
	}

	public void setX(int x) {
		//requires:nop
		//modify:nop
		//effect:assign this.x
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
		//effect:assign this.y
		this.y = y;
	}
	public int getCredit() {
		//requires:nop
		//modify:nop
		//effect:return credit
		return credit;
	}
	
	public void setCredit(int credit) {
		//requires:nop
		//modify:nop
		//effect:assign this.credit
		this.credit = credit;
	}
	public boolean near(Request req) {
		//requires:req can not be null
		//modify:nop
		//effect:judge if the req is nearby the taxi
		if((x==req.getX()||x==req.getX()+1||x==req.getX()+2
		||x==req.getX()-1||x==req.getX()-2)&&
		(y==req.getY()||y==req.getY()+80||y==req.getY()+160	
		||x==req.getX()-80||x==req.getX()-160))
			return true;
		else
			return false;
	}
	public int getWaitCount() {
		//requires:nop
		//modify:nop
		//effect:return waitCount
		return waitCount;
	}
	public void setWaitCount(int waitCount) {
		//requires:nop
		//modify:nop
		//effect:assign this.waitCount
		this.waitCount = waitCount;
	}
	public int getStopCount() {
		//requires:nop
		//modify:nop
		//effect:return stopCount
		return stopCount;
	}
	public void setStopCount(int stopCount) {
		//requires:nop
		//modify:nop
		//effect:assign this.stopCount
		this.stopCount = stopCount;
	}
}
