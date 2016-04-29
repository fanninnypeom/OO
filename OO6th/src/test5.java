public class test5 implements Runnable{
	private modifier reviewer;
	public test5(modifier m){
		reviewer=m;
	}
	public void run(){
//		reviewer.rename("D:\\sillybOO\\111.txt", "D:\\sillybOO\\1\\111.txt");
//		reviewer.rename("D:\\sillybOO\\222.txt", "D:\\sillybOO\\2\\222.txt");
//		reviewer.rename("D:\\sillybOO\\333.txt", "D:\\sillybOO\\3\\333.txt");
//		reviewer.buildFile("D:\\sillybOO\\4\\444.txt");
//		reviewer.modify("D:\\sillybOO\\4\\444.txt","sillybOO");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//reviewer.rename("D:\\sillybOO\\2\\3\\111","D:\\sillybOO\\2\\111");
  reviewer.modify("D:\\sillybOO\\222.txt", "124");
  
		/*	reviewer.modi	
 * try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewer.deleteFile("D:\\sillybOO\\4\\444.txt");
	//	reviewer.rename("D:\\sillybOO\\1\\111.txt", "D:\\sillybOO\\111.txt");
		reviewer.rename("D:\\sillybOO\\2\\222.txt", "D:\\sillybOO\\222.txt");
		reviewer.rename("D:\\sillybOO\\3\\333.txt", "D:\\sillybOO\\333.txt");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		reviewer.deleteDirectory("D:\\sillybOO\\1");
	*/
	}
}