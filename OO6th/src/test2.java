
public class test2 implements Runnable{
	private modifier reviewer;
	public test2(modifier m){
		reviewer=m;
	}
	public void run(){
		reviewer.deleteFile("D:\\sillybOO\\1\\aaa");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.buildFile("D:\\sillybOO\\1\\aaa");
		reviewer.modify("D:\\sillybOO\\1\\aaa", "43656436");
		
	}
}