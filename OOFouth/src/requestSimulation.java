import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class requestSimulation implements Runnable{
	private threadRequestArray reqArray;
//	private Timer timer;
	private threadRequest currentRequest;
	public requestSimulation(threadRequestArray reqArray1){
		reqArray=reqArray1;
//		timer=timer1;
	}
/*		public static void main(String[] args){
			Calendar c = Calendar.getInstance();
			int mm = c.get(Calendar.MINUTE);
			int SS = c.get(Calendar.SECOND);
			int MI = c.get(Calendar.MILLISECOND);
			int HH = c.get(Calendar.HOUR_OF_DAY);
//			System.out.println(mm);
//			System.out.println(SS);
			System.out.println(MI);
			int temp=MI;
			if(temp%100<50)
				MI=(temp/100)*100;
			else
				MI=(temp/100+1)*100;
			System.out.println(MI);
			
		}
*/
		
		public void run() {
			System.out.println("ÇëÊäÈëËãÊ½!");
			Scanner scan = new Scanner(System.in);
			while(true){
				String str=scan.nextLine();
				String str2=str.replaceAll(" ","");
				String str1=str2.replaceAll("\t","");
				if(!str1.equals("run")){
					Pattern pattern1 = Pattern.compile("^\\(FR,[+-]?\\d{1,8},(UP|DOWN)\\)$");
					Pattern pattern2 =Pattern.compile("^\\(ER,#[1|2|3],[+-]?\\d{1,8}\\)$");
					Matcher matcher1=pattern1.matcher(str1);
					Matcher matcher2=pattern2.matcher(str1);
					boolean b1=matcher1.matches();
					boolean b2=matcher2.matches();
					if(!(b1||b2)){
						continue;
					}
			}
				str1=str1.replaceAll("#","");
				currentRequest=new threadRequest(str1);
//				System.out.println("lovelive");
				if(currentRequest.getRequestFlag()==0&&(currentRequest.getRequestFloor()>20||
						currentRequest.getRequestFloor()<1))
					;
				else if(currentRequest.getRequestFlag()==1&&(currentRequest.getAimFloor()>20||
						currentRequest.getAimFloor()<1))
					;
				else if(currentRequest.getRequestFlag()==0&&
						((currentRequest.getRequestFloor()==20&&currentRequest.getDirection()==1)||
						(currentRequest.getRequestFloor()==1&&currentRequest.getDirection()==0)))
					;
				else{
					reqArray.setChanged(1);
				reqArray.add(currentRequest);
					
					
				}
				
		//		notifyAll();

		}
}
}
