import java.util.ArrayList;


public class requestArray {
	private ArrayList<Request> reqArray;
	requestArray(){
		reqArray=new ArrayList<Request>();
	}
	public synchronized void add(Request req){
		if(req.getX()>=0&&req.getX()<80&&
				req.getY()>=0&&req.getY()<80&&
				req.getAim_x()>=0&&req.getAim_x()<80&&
				req.getAim_y()>=0&&req.getAim_y()<80	)
		reqArray.add(req);
		else
			System.out.println(req.getNumber()+"号请求无效");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public synchronized Request take(){
		Request temp=reqArray.get(0);
		reqArray.remove(0);
		return temp;
	}
	public synchronized int size(){
		return reqArray.size();
	}
	public  synchronized Request get(int i){
		return reqArray.get(i);
	}
	public synchronized void remove(int i){
		reqArray.remove(i);
	}
}
