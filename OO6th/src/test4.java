public class test4 implements Runnable{
	private modifier reviewer;
	public test4(modifier m){
		reviewer=m;
	}
	public void run(){
		reviewer.modify("D:\\sillybOO\\111.txt", "436436");
		reviewer.modify("D:\\sillybOO\\222.txt", "436436");
		reviewer.modify("D:\\sillybOO\\333.txt", "436436");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\111.txt", "D:\\sillybOO\\1111.txt");
		reviewer.rename("D:\\sillybOO\\222.txt", "D:\\sillybOO\\2222.txt");
		reviewer.rename("D:\\sillybOO\\333.txt", "D:\\sillybOO\\3333.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.modify("D:\\sillybOO\\1111.txt", "436");
		reviewer.modify("D:\\sillybOO\\2222.txt", "436");
		reviewer.modify("D:\\sillybOO\\3333.txt", "436");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1111.txt", "D:\\sillybOO\\1\\1111.txt");
		reviewer.rename("D:\\sillybOO\\2222.txt", "D:\\sillybOO\\1\\2222.txt");
		reviewer.rename("D:\\sillybOO\\3333.txt", "D:\\sillybOO\\1\\3333.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.modify("D:\\sillybOO\\1\\1111.txt", "4");
		reviewer.modify("D:\\sillybOO\\1\\2222.txt", "4");
		reviewer.modify("D:\\sillybOO\\1\\3333.txt", "4");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\1111.txt", "D:\\sillybOO\\1\\111.txt");
		reviewer.rename("D:\\sillybOO\\1\\2222.txt", "D:\\sillybOO\\1\\222.txt");
		reviewer.rename("D:\\sillybOO\\1\\3333.txt", "D:\\sillybOO\\1\\333.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.rename("D:\\sillybOO\\1\\111.txt", "D:\\sillybOO\\111.txt");
		reviewer.rename("D:\\sillybOO\\1\\222.txt", "D:\\sillybOO\\222.txt");
		reviewer.rename("D:\\sillybOO\\1\\333.txt", "D:\\sillybOO\\333.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.modify("D:\\sillybOO\\111.txt", "43628394897324");
		reviewer.modify("D:\\sillybOO\\222.txt", "43628394897324");
		reviewer.modify("D:\\sillybOO\\333.txt", "43628394897324");
		
	}
}