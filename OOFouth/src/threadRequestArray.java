import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class threadRequestArray {
	private volatile ArrayList<threadRequest> threadRequestArray;
	private volatile int changed=0; //0代表未被改变，1代表已被改变
	public threadRequestArray(){
		threadRequestArray=new ArrayList<threadRequest>();
		setChanged(0);
	}
	public synchronized ArrayList<threadRequest> add(threadRequest req){

			threadRequestArray.add(req);
			return threadRequestArray;
		
	}
/*	public threadRequest remove(){
		return threadRequestArray.remove();
	}
	public threadRequest peek() {
		// TODO Auto-generated method stub
		return threadRequestArray.peek();
	}
	public threadRequest poll() {
		// TODO Auto-generated method stub
		return threadRequestArray.poll();
	}
	*/
	public synchronized int length(){
		return (int)threadRequestArray.size();
	}
	public synchronized threadRequest get(int i){
		return threadRequestArray.get(i);
	} 
	public synchronized void remove(int i){
		threadRequestArray.remove(i);
	}
	public int getChanged() {
		return changed;
	}
	public void setChanged(int changed) {
		this.changed = changed;
	}
}
