import java.util.ArrayList;


public class TaxiArray {
	private ArrayList<Taxi> taxiArray;
	public TaxiArray(){
	taxiArray=new ArrayList<Taxi>();
	for(int i=0;i<100;i++){
		taxiArray.add(new Taxi(i));
	}
	}
	public Taxi get(int i){
		return taxiArray.get(i);
	}
}
