import java.util.Comparator;


public class requestFloorComparator  implements Comparator{
	public int compare(Object o1, Object o2) {
		int i1 = ((Request)o1).getFinalFloor();
		int i2 = ((Request)o2).getFinalFloor();
		if (i1 < i2){
		return -1;
		}
		if (i1 > i2){
		return 1;
		}
		return 0;
		}
	/*public int compareTime(Request req1, Request req2) {
		int i1 = req1.getTime();
		int i2 = req2.getTime();
		if (i1 < i2){
		return -1;
		}
		if (i1 > i2){
		return 1;
		}
		return 0;
		}*/





	
}
