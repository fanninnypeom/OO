import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class betterScheduler extends scheduler{
	private ArrayList<Integer> instrArray=new ArrayList<Integer>();
	private ArrayList<Integer> instrTimeArray=new ArrayList<Integer>();
	private double[] timeArray=new double[200];
	private double nowTime;
	private int[] arriveFloor=new int[10];
	private ArrayList<Request> remainingReq=new ArrayList<Request>();
	private Request mainReq;
	private Request carriedReq;
	public ArrayList<Request> ALS_Schedule (Request mainReq,ArrayList<Request> list,double mainReqStartTime){
		System.out.print(mainReq);
		System.out.print("(");
		
		for(Iterator it = list.iterator();it.hasNext();){
             System.out.print(it.next()+" ");
        }
		 System.out.println(")");
		instrArray.removeAll(instrArray);
		int i=0;
		while(true){
			try{
				carriedReq=list.get(i);
				if((carriedReq.getFinalFloor()>mainReq.getFinalFloor()&&mainReq.getFinalFloor()>getFloor())||
						(carriedReq.getFinalFloor()<mainReq.getFinalFloor()&&mainReq.getFinalFloor()<getFloor())){
					list.remove(carriedReq);
					remainingReq.add(carriedReq);
					i--;
				}
				i++;
				}catch(IndexOutOfBoundsException e){
					break;
				}
			
			}
		if(list.size()!=0)
		Collections.sort(list,new requestFloorComparator());
		return list;
/*		for(i=0;i<list.size();i++){
			if(i==0)
				instrArray.add(list.get(i).getFinalFloor()-getFloor());
			else
				instrArray.add(list.get(i).getFinalFloor()-list.get(i-1).getFinalFloor());
			instrTimeArray.add(list.get(i).getTime());
		}
		Integer it0=Integer();
		if(i==0){
		it0=(Integer)(mainReq.getFinalFloor()-this.getFloor());
		}
		else{
		it0=(Integer)(mainReq.getFinalFloor()-list.get(i-1).getFinalFloor());
		}
		getInstrTimeArray().add(mainReq.getTime());
		instrArray.add(it0);
		nowTime=mainReqStartTime;
		timeArray[0]=mainReqStartTime;
		for(i=1;i<instrArray.size();i++){
			nowTime+=0.5*Math.abs(instrArray.get(i-1))+1;
			timeArray[i]=nowTime;
		}
*/
	

	}
	private Integer Integer() {
		// TODO Auto-generated method stub
		return null;
	}
	public Request getMainReq() {
		return mainReq;
	}
	public void setMainReq(Request mainReq) {
		this.mainReq = mainReq;
	}
	public ArrayList<Request> getRemainingReq(){
		return remainingReq;
	}
	public void removeRemainingReq(){
		remainingReq.removeAll(remainingReq);
	}
	public ArrayList<Integer> getInstrArray() {
		return instrArray;
	}
	public void setInstrArray(ArrayList<Integer> instrArray) {
		this.instrArray = instrArray;
	}
	public double[] getTimeArray() {
		return timeArray;
	}
	public void setTimeArray(double[] timeArray) {
		this.timeArray = timeArray;
	}
	public ArrayList<Integer> getInstrTimeArray() {
		return instrTimeArray;
	}
	public void setInstrTimeArray(ArrayList<Integer> instrTimeArray) {
		this.instrTimeArray = instrTimeArray;
	}
	public Request getCarriedReq() {
		return carriedReq;
	}
	public void setCarriedReq(Request carriedReq) {
		this.carriedReq = carriedReq;
	}

}
