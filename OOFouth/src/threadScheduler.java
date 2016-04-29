
public class threadScheduler extends betterScheduler implements Runnable{
//	private Timer timer;
	private threadRequestArray reqArray;
	private long lossTime=0;
	private long nowTime=0;
	private int waitFlag=0;
	private threadElevator[] elevatorArray;
	public threadScheduler (threadRequestArray reqArray1,threadElevator[] elevatorArray1){
		reqArray=reqArray1;
		elevatorArray=elevatorArray1;
	}
	
	public void run() {
		while(true){
			int temp=reqArray.length();
			
			synchronized(reqArray){
			for(int i=0;i<reqArray.length();i++){
				int which;
				if((which=canBeCarried(reqArray.get(i)))>=0){
					elevatorArray[which].setPickedThreadReq(reqArray.get(i));
					elevatorArray[which].setPicked(reqArray.get(i).getFinalFloor());
					reqArray.remove(i);
					temp=reqArray.length();
					i--;
				}
			}
			
		System.out.println("请求队列的长度"+temp);
			
			if(temp!=0){
			int j;
			lossTime+=System.currentTimeMillis()-nowTime;
				while(true)
				{
					synchronized(elevatorArray[0]){
						synchronized(elevatorArray[1]){
					
					synchronized(elevatorArray[2]){
					for(j=0;j<temp;j++)
					if(canBeExecuted(reqArray.get(j)))
						break;
					if(j!=temp)
						break;
					}
					}
						}
					Thread.yield();
					waitFlag=1;
				}
				nowTime=System.currentTimeMillis();
//				System.out.println("主请求"+reqArray.get(j));
			executeRequest(reqArray.get(j));
			reqArray.remove(j);
			}
			
			else{
				if(nowTime!=0)
				lossTime+=System.currentTimeMillis()-nowTime;
				while(reqArray.getChanged()==0);
				nowTime=System.currentTimeMillis();
			}

			if(reqArray.getChanged()==1)
				reqArray.setChanged(0);
			}
			Thread.yield();
		}
	}

	private int canBeCarried(threadRequest threadRequest) {//此函数返回的是可以捎带该请求的电梯 编号
		int[] distance=new int[3];
		int flag=-1;
		for(int i=0;i<3;i++){
//							System.out.println(i+" "+elevatorArray[i].isStart()+""+
//						elevatorArray[i].getFloor()+" "
//						+threadRequest.getFinalFloor()+" "+
//						elevatorArray[i].getAim());
			if(elevatorArray[i].isStart()&&((((elevatorArray[i].getFloor()<threadRequest.getFinalFloor()&&
					threadRequest.getFinalFloor()<=elevatorArray[i].getAim())
					||(elevatorArray[i].getFloor()>threadRequest.getFinalFloor()&&
							threadRequest.getFinalFloor()>=elevatorArray[i].getAim()))&&
							threadRequest.getRequestFlag()==0&&threadRequest.getDirection()==elevatorArray[i].getStatus())||
					(threadRequest.getRequestFlag()==1&&threadRequest.getElevatorNum()==i&&
					((elevatorArray[i].getFloor()<elevatorArray[i].getAim()&&elevatorArray[i].getFloor()<threadRequest.getFinalFloor())||
							(elevatorArray[i].getFloor()>elevatorArray[i].getAim()&&elevatorArray[i].getFloor()>threadRequest.getFinalFloor())))))
						{
				distance[i]=elevatorArray[i].getTotalMovement();
				flag++;
			}
			else
				distance[i]=10000000;
		}
		int min=1000,index=0;
		for(int i=0;i<3;i++){
			if(distance[i]<min){
				min=distance[i];
				index=i;
			}
		}
		if(threadRequest.getRequestFlag()==1)
			if(distance[threadRequest.getElevatorNum()]!=10000000)
				index=threadRequest.getElevatorNum();
		if(flag==-1)
			return -1;
		else
		return index;
	}
	private boolean canBeExecuted(threadRequest threadRequest){
		if((elevatorArray[0].getStatus()==-1||
				   elevatorArray[1].getStatus()==-1||
				   elevatorArray[2].getStatus()==-1)&&threadRequest.getRequestFlag()==0)
			return true;
		else if(threadRequest.getRequestFlag()==1&&elevatorArray[threadRequest.getElevatorNum()].getStatus()==-1)
			return true;
		return false;
	}
	private void executeRequest(threadRequest threadRequest){
		int[] distance=new int[3];

		lossTime+=System.currentTimeMillis()-nowTime;
		while(elevatorArray[0].getStatus()!=-1&&
		   elevatorArray[1].getStatus()!=-1&&
		   elevatorArray[2].getStatus()!=-1)
			waitFlag=1;
		nowTime=System.currentTimeMillis();
		for(int i=0;i<3;i++){
			if(elevatorArray[i].getStatus()==-1){
				distance[i]=elevatorArray[i].getTotalMovement();
			}
			else
				distance[i]=10000000;
		}
		int min=1000,index=0;
		for(int i=0;i<3;i++)
			if(distance[i]<min){
				min=distance[i];
				index=i;
			}
		if(threadRequest.getRequestFlag()==1){
			index=threadRequest.getElevatorNum();
			lossTime+=System.currentTimeMillis()-nowTime;
			while(elevatorArray[index].getStatus()!=-1)
				waitFlag=1;
			
			nowTime=System.currentTimeMillis();
		}
			
//		System.out.println("执行主请求的电梯编号"+index);
		elevatorArray[index].setAim(threadRequest.getFinalFloor());
		if(threadRequest.getFinalFloor()<elevatorArray[index].getFloor())
			elevatorArray[index].setDirection(-1);	
		else if(threadRequest.getFinalFloor()>elevatorArray[index].getFloor())
			elevatorArray[index].setDirection(1);
		else
			elevatorArray[index].setDirection(0);
		elevatorArray[index].setThreadReq(threadRequest);
//		System.out.println("当前时间和nowTime"+System.currentTimeMillis()+" "+" "+nowTime);
		lossTime+=System.currentTimeMillis()-nowTime;
//		System.out.println("lossTime"+lossTime);
		nowTime=System.currentTimeMillis();
		Timer.compensate(lossTime);
		lossTime=0;
		if(waitFlag==0){
			elevatorArray[index].setCurrentTime(Timer.getCurrentTime());
		}
			elevatorArray[index].setStart(true);
		
	
	}
}
