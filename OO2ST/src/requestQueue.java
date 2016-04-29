import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class requestQueue {
	private Queue<Request> RequestQueue;
	private Request lastOne;
	public requestQueue(){
		RequestQueue=new LinkedList<Request>();
		lastOne=new Request();
	}
	/*
		try{
		RequestQueue.add(req);
		}catch(IllegalStateException e){
			System.out.println("格式错误！");
			System.exit(0);
		}catch(ClassCastException e){
			System.out.println("格式错误！");
			System.exit(0);
		}catch(NullPointerException e){
			System.out.println("空指针错误！");
			System.exit(0);
		}catch(IllegalArgumentException e){
			System.out.println("非法字符错误！");
			System.exit(0);
		}//我也不知道catch这么多有没有用   
	}*/
	public Queue<Request> add(Request req) throws TimeException {
		if(req.getRequestFlag()==0&&(req.getRequestFloor()>10||
				req.getRequestFloor()<1))
			return RequestQueue;
		else if(req.getRequestFlag()==1&&(req.getAimFloor()>10||
				req.getAimFloor()<1))
			return RequestQueue;
		else if(req.getRequestFlag()==0&&
				((req.getRequestFloor()==10&&req.getDirection()==1)||
				(req.getRequestFloor()==1&&req.getDirection()==0)))
			return RequestQueue;
		else if(req.getTime()!=0&&lastOne.getRequestFlag()==-1)
			throw new TimeException(2);
		else if(lastOne.getRequestFlag()==-1||lastOne.getTime()<req.getTime()){
			RequestQueue.offer(req);
			lastOne=req;
			return RequestQueue;
		}
		else{
			throw new TimeException(1);
		}
		
	}
	public Request remove(){
		return RequestQueue.remove();
	}
	public Request getLastOne() {
		return lastOne;
	}
	public void setLastOne(Request lastOne) {
		this.lastOne = lastOne;
	}

}
