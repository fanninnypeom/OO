
public class test3 implements Runnable{
	private modifier reviewer;
	public test3(modifier m){
		reviewer=m;
	}
	public void run(){
		reviewer.rename("D:\\sillybOO\\111.txt", "D:\\sillybOO\\1\\111.txt");
		reviewer.rename("D:\\sillybOO\\222.txt", "D:\\sillybOO\\1\\222.txt");	
	//	reviewer.rename("D:\\sillybOO\\333", "D:\\sillybOO\\1\\333");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\111.txt","D:\\sillybOO\\111.txt");
		reviewer.rename("D:\\sillybOO\\1\\222.txt","D:\\sillybOO\\222.txt");	
		//reviewer.rename("D:\\sillybOO\\1\\333","D:\\sillybOO\\333");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.deleteDirectory("D:\\sillybOO\\333");
	}	
}