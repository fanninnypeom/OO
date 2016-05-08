import java.util.ArrayList;


public class TaxiArray {
	private ArrayList<Taxi> taxiArray;
	public TaxiArray(){
		//requires:nop
		//modify:nop
		//effect:init taxiArray
	taxiArray=new ArrayList<Taxi>();
	for(int i=0;i<100;i++){
		taxiArray.add(new Taxi(i));
	}
	}
	public Taxi get(int i){
		//requires:nop
		//modify:nop
		//effect:return a taxi objective
		return taxiArray.get(i);
	}
}
