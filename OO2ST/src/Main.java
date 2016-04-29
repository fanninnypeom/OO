import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	public static void main(String argus[]){
		try{
		int i;
		Floor [] F=new Floor[10];
		for(i=0;i<10;i++){
			F[i]=new Floor(1,-1);
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入算式!");
		requestQueue WaitingHandle=new requestQueue();
		for(i=0;i<200;i++){
			String str=scan.nextLine();
			
			String str1=str.replaceAll(" ","");

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
		scheduler dispatch=new scheduler();
		elevator elevator1=new elevator();
		while(true){
			Request req1=new Request();
			try{
				req1=WaitingHandle.remove();
			}catch(NoSuchElementException e1){
				break;
			}
			dispatch.setFloor(elevator1.getFloor());//将电梯所处的楼层告诉给调度器
			dispatch.GiveOutInstr(req1);
			if(!(dispatch.getInstr()==0&&dispatch.getTime()<elevator1.getTime())){
			elevator1.GetInstruction(dispatch.getInstr(),dispatch.getTime());
			elevator1.PrintRunningRecord();
			}
		}
			}catch(Throwable e){
				System.out.println("格式错误！");
				System.exit(0);
			}
	}

}
