
public class test1 implements Runnable{
	private modifier reviewer;
	public test1(modifier m){
		reviewer=m;
	}
	public void run(){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\ss\\ssss\\AAA.txt", "D:\\sillybOO\\1\\ss\\ssss\\AAA1.txt");
		reviewer.rename("D:\\sillybOO\\1\\ss\\xxx.txt", "D:\\sillybOO\\1\\ss\\hhh.txt");
		reviewer.rename("D:\\sillybOO\\1\\aaa","D:\\sillybOO\\1\\aaa1");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		reviewer.rename( "D:\\sillybOO\\1\\ss\\hhh.txt","D:\\sillybOO\\1\\ss\\xxx.txt");
		reviewer.rename("D:\\sillybOO\\1\\ss\\ssss\\AAA1.txt","D:\\sillybOO\\1\\DDD\\AAA1.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\DDD\\AAA1.txt","D:\\sillybOO\\1\\ss\\ssss\\AAA1.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\ss\\ssss\\AAA1.txt", "D:\\sillybOO\\1\\ss\\ssss\\AAA.txt");
		reviewer.rename("D:\\sillybOO\\1\\ss\\hhh.txt", "D:\\sillybOO\\1\\ss\\xxx.txt");
		reviewer.rename("D:\\sillybOO\\1\\aaa1","D:\\sillybOO\\1\\aaa");
	
	}
	}