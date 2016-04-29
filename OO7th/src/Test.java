import java.util.ArrayList;


public class Test implements Runnable{
	private requestArray reqArray;
	Test(requestArray r){
		reqArray=r;
	}
	public void run() {
		// TODO Auto-generated method stub
		int i=0;
//		while(true){
//		i++;
//		Request req=new Request(i,45,27,34,12);
//		Request req=new Request(i,45,45,34,12);
		while(true){
		Request req1=new Request(0,12,12,66,66);
		reqArray.add(req1);
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Request req2=new Request(0,66,66,12,12);
		reqArray.add(req2);
	}
	}

}
