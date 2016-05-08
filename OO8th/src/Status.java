import java.util.ArrayList;
import java.util.Calendar;


public class Status{
	private TaxiArray taxiArray;
	Status(TaxiArray t){
		//requires:nop
		//modify:nop
		//effect:init status
		taxiArray=t;
	}
	public String  getCurrentTime(){
		//requires:nop
		//modify:nop
		//effect:return now time
		Calendar c = Calendar.getInstance();	
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int temp=c.get(Calendar.MILLISECOND);
		int millSec=Math.round(temp);
		int r=millSec/100;
		return "hour:"+hour + "minute:" +minute + "second:" + second+"."+r;
	}
	public void getStatus(int i) {
		//requires:nop
		//modify:nop
		//effect:print all status of i-th taxi
				System.out.println(taxiArray.get(i).getNumber()+"号出租车处于"+
			"("+taxiArray.get(i).getX()+","+taxiArray.get(i).getY()+")位置"+"状态为"+taxiArray.get(i).getStatus()+"时间为"+getCurrentTime());
	}
	
	public int getTaxiStatus(int i){
		//requires:nop
		//modify:nop
		//effect:return the i-th taxi's status
		return taxiArray.get(i).getStatus();
	}
	public int getTaxiWaitCount(int i){
		//requires:nop
		//modify:nop
		//effect:return the i-th taxi's waitcount
		return taxiArray.get(i).getWaitCount();
	}
	public int getTaxiStopCount(int i){
		//requires:nop
		//modify:nop
		//effect:return the i-th taxi's stopcount
		return taxiArray.get(i).getStopCount();
	}
}
