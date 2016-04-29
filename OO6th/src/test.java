
public class test implements Runnable{
	private modifier reviewer;
	public test(modifier m){
		reviewer=m;
	}
	public void run(){
		reviewer.buildDirectory("D:\\sillybOO");
		reviewer.buildFile("D:\\sillybOO\\111.txt");
		reviewer.buildFile("D:\\sillybOO\\222.txt");
		reviewer.buildFile("D:\\sillybOO\\333");
		reviewer.buildDirectory("D:\\sillybOO\\1");
		reviewer.buildDirectory("D:\\sillybOO\\1\\ss");
		reviewer.buildDirectory("D:\\sillybOO\\2");
		reviewer.buildDirectory("D:\\sillybOO\\3");
		reviewer.buildDirectory("D:\\sillybOO\\4");
		reviewer.buildFile("D:\\sillybOO\\1\\aaa");
		reviewer.buildFile("D:\\sillybOO\\2\\ccc.txt");
		reviewer.buildFile("D:\\sillybOO\\2\\ddd");
		reviewer.buildFile("D:\\sillybOO\\3\\qqq.txt");
		reviewer.buildFile("D:\\sillybOO\\4\\ggg.txt");
		reviewer.buildFile("D:\\sillybOO\\4\\kkk");
		reviewer.modify("D:\\sillybOO\\222.txt","123");
		reviewer.modify("D:\\sillybOO\\222.txt","12434324");
		reviewer.modify("D:\\sillybOO\\222.txt","14243213");
		reviewer.modify("D:\\sillybOO\\111.txt","1243543awd");
		reviewer.modify("D:\\sillybOO\\111.txt","78864664awdwd");
		reviewer.modify("D:\\sillybOO\\333","256567864efsfesfwefafds");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\111.txt", "D:\\sillybOO\\xxx.txt");
		reviewer.rename("D:\\sillybOO\\222.txt", "D:\\sillybOO\\yyy.txt");
		reviewer.rename("D:\\sillybOO\\333", "D:\\sillybOO\\zzz");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\xxx.txt", "D:\\sillybOO\\1\\ss\\xxx.txt");
		reviewer.rename("D:\\sillybOO\\yyy.txt", "D:\\sillybOO\\2\\yyy.txt");
		reviewer.rename("D:\\sillybOO\\zzz", "D:\\sillybOO\\3\\zzz");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.modify("D:\\sillybOO\\1\\ss\\xxx.txt","a");
		reviewer.modify("D:\\sillybOO\\2\\yyy.txt","ppp");
		reviewer.modify("D:\\sillybOO\\3\\zzz","bbb");
		
		
	}
}
