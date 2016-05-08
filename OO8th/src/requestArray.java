import java.util.ArrayList;


public class requestArray {
	private ArrayList<Request> reqArray;
	requestArray(){
		//requires:nop
				//modify:nop
				//effect:init reqArray
		reqArray=new ArrayList<Request>();
	}
	public synchronized void add(Request req){
		//requires:nop
				//modify:nop
				//effect:add req to reqArray
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
		//requires:reqArray must have elements
				//modify:nop
				//effect:return the first req in reqArray
		Request temp=reqArray.get(0);
		reqArray.remove(0);
		return temp;
	}
	public synchronized int size(){
		//requires:nop
				//modify:nop
				//effect:return the size of reqArray
		return reqArray.size();
	}
	public  synchronized Request get(int i){
		//requires:nop
				//modify:nop
				//effect:return the i-th req in reqArray
		return reqArray.get(i);
	}
	public synchronized void remove(int i){
		//requires:nop
				//modify:nop
				//effect:remove the i-th req in reqArray 
		reqArray.remove(i);
	}
}
