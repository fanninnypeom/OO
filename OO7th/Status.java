import java.util.ArrayList;


public class Status{
	private TaxiArray taxiArray;
	Status(TaxiArray t){
		taxiArray=t;
	}
	
	public void getStatus(int i) {
				System.out.println(taxiArray.get(i).getNumber()+"号出租车处于"+
			"("+taxiArray.get(i).getX()+","+taxiArray.get(i).getY()+")位置"+"状态为"+taxiArray.get(i).getStatus()+"时间为"+System.currentTimeMillis());
	}
	
	public int getTaxiStatus(int i){
		return taxiArray.get(i).getStatus();
	}
	public int getTaxiWaitCount(int i){
		return taxiArray.get(i).getWaitCount();
	}
	public int getTaxiStopCount(int i){
		return taxiArray.get(i).getStopCount();
	}
}
