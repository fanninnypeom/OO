import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	public static void main(String argus[]){
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		Timer timer=new Timer();
		threadElevator[] threadElevatorArray =new threadElevator[3];
		for(int i=0;i<3;i++)
			threadElevatorArray[i]=new threadElevator(i+1);
		threadRequestArray reqArray=new threadRequestArray();
		requestSimulation reqSimulation=new requestSimulation(reqArray);
		threadScheduler threadScheduler1=new threadScheduler(reqArray,threadElevatorArray);
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<3;i++){
			exec.execute(threadElevatorArray[i]);
		}
		exec.execute(threadScheduler1);
		exec.execute(reqSimulation);
	}
/*	public static void main1(String argus[]){
		try{
		int i;
		Floor [] F=new Floor[10];
		for(i=0;i<10;i++){
			F[i]=new Floor(1,-1);
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入算式!");
		requestQueue WaitingHandle=new requestQueue();
		ArrayList<Request> HigherWaitingHandle=new ArrayList<Request>();
		ArrayList<Request> remainingReq=new ArrayList<Request>();
		ArrayList<Request> carriedReq=new ArrayList<Request>();
		ArrayList<Request> list=new ArrayList<Request>();
		Request mainReq=new Request();
		double mainReqStartTime=0;
		for(i=0;i<200;i++){
			String str=scan.nextLine();
			
			String str2=str.replaceAll(" ","");
			String str1=str2.replaceAll("\t","");
			if(!str1.equals("run")){
			Pattern pattern1 = Pattern.compile("^\\(FR,[+-]?\\d{1,8},(UP|DOWN),[+-]?\\d{1,8}\\)$");
			Pattern pattern2 =Pattern.compile("^\\(ER,[+-]?\\d{1,8},[+-]?\\d{1,8}\\)$");
			Matcher matcher1=pattern1.matcher(str1);
			Matcher matcher2=pattern2.matcher(str1);
			boolean b1=matcher1.matches();
			boolean b2=matcher2.matches();
			if(!(b1||b2)){
				System.out.println("格式错误");
				System.exit(0);
			}
				Request req=new Request(str1);
				try{
				WaitingHandle.add(req);
				}
			catch(TimeException e){
				System.out.println(e.getDetail());
				System.exit(0);
			}
			}
				
			else
				break;
		}
		if(i==200){
			System.out.println("指令数量过多!");
			System.exit(0);
		}
		betterScheduler dispatch=new betterScheduler();
		newElevator elevator1=new newElevator();
		Request req1=new Request();
		while(true){
			if(carriedReq.size()!=0)
			carriedReq.removeAll(carriedReq);
			if(mainReq.getRequestFlag()==-1){
				mainReq=WaitingHandle.remove();
				mainReq.initArriveTime(elevator1.getFloor(), elevator1.getTime() );
						}
			for(i=0;i<remainingReq.size();i++)
				if(mainReq.canBeCarried(remainingReq.get(i),elevator1.getFloor() , elevator1.getTime())){
					carriedReq.add(remainingReq.get(i));
					remainingReq.remove(i);
					i--;
				}
			for(i=0;i<HigherWaitingHandle.size();i++)
				if(mainReq.canBeCarried(HigherWaitingHandle.get(i),elevator1.getFloor() , elevator1.getTime())){
					carriedReq.add(HigherWaitingHandle.get(i));
					HigherWaitingHandle.remove(i);
					i--;
				}
			
			while(true){
			req1=WaitingHandle.peek();
			if(req1==null)
				break;
			if((int)mainReq.getArriveTime()[mainReq.getFinalFloor()]<req1.getTime())
				break;
			WaitingHandle.remove();
			if(mainReq.canBeCarried(req1,elevator1.getFloor() , elevator1.getTime()))
				carriedReq.add(req1);
			else
			HigherWaitingHandle.add(req1);
			}
			dispatch.setFloor(elevator1.getFloor());
			if(elevator1.getOutputArray().size()==0)
				mainReqStartTime=mainReq.getTime();
			else
			mainReqStartTime=Math.max(mainReq.getTime(), elevator1.getOutputArray().get(elevator1.getOutputArray().size()-1).getTime());
			
			list=dispatch.ALS_Schedule(mainReq,carriedReq,mainReqStartTime);
			for(i=0;i<list.size();i++){
				if(elevator1.invalidInstr(list.get(i))){
					dispatch.GiveOutInstr(list.get(i));
					elevator1.GetInstruction(dispatch.getInstr(), mainReq.getTime());
					elevator1.WriteRunningRecord();
					dispatch.setFloor(elevator1.getFloor());
				}
			}
			if(elevator1.invalidInstr(mainReq)){
				dispatch.GiveOutInstr(mainReq);
				elevator1.GetInstruction(dispatch.getInstr(), mainReq.getTime());
				elevator1.WriteRunningRecord();
				dispatch.setFloor(elevator1.getFloor());
			}

			
			if(dispatch.getRemainingReq().size()!=0){
				remainingReq.addAll(dispatch.getRemainingReq());
				dispatch.removeRemainingReq();
			}
			if(remainingReq.size()!=0){
				mainReq=remainingReq.get(0);
				remainingReq.remove(0);
							}
			else if(HigherWaitingHandle.size()!=0){
				mainReq=HigherWaitingHandle.get(0);
				HigherWaitingHandle.remove(0);	
			}
			else 
			mainReq= WaitingHandle.poll();
			if(mainReq==null)
				break;
			mainReq.initArriveTime(elevator1.getFloor(), Math.max(elevator1.getTime(),mainReq.getTime()) );
			}
		
		
/*		while(true){
			Request req1=new Request();
			try{
				req1=WaitingHandle.remove();
			}catch(NoSuchElementException e1){
				break;
			}
			if(mainReq.getRequestFlag()==-1){
				mainReq=req1;
				mainReq.initArriveTime(elevator1.getFloor(), elevator1.getTime() );
			}
			else{	
				if(mainReq.canBeCarried(req1,elevator1.getFloor() , elevator1.getTime()))
					carriedReq.add(req1);
				else
					HigherWaitingHandle.add(req1);
			}
			if(mainReq.getArriveTime()[mainReq.getFinalFloor()]<req1.getTime()){
				dispatch.setFloor(elevator1.getFloor());
				dispatch.ALS_Schedule(carriedReq);
				elevator1.getInstructionArray(instr, d);
				if((remainingReq=dispatch.getRemainingReq()).size()!=0){
					mainReq=remainingReq.get(0);
					remainingReq.remove(mainReq);
				}
			}*/
//将电梯所处的楼层告诉给调度器
//			dispatch.GiveOutInstr(req1);
//			if(!(dispatch.getInstr()==0&&dispatch.getTime()<elevator1.getTime())){
//			elevator1.GetInstruction(dispatch.getInstr(),dispatch.getTime());
//			elevator1.WriteRunningRecord();
//			}
//			if(elevator1.invalidInstr(req1)){
//				elevator1.GetInstruction(dispatch.getInstr(),dispatch.getTime());
//				elevator1.WriteRunningRecord();
//			}
//		}
//		elevator1.PrintRunningRecord();
//			}catch(Throwable e){
//				System.out.println("格式错误~");
//				System.exit(0);
//			}
//	}

}
