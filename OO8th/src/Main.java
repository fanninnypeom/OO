import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;


public class Main {
	public static void main(String argus[]) {
		//requires:nop
		//modify:nop
		//effect:run the program
		Map map=new Map();
		requestArray reqArr=new requestArray();
		TaxiArray taxiArray=new TaxiArray();
//		System.out.println("0 start"+taxiArray.get(0).getX()+" "+taxiArray.get(0).getY());
		Monitor m=new Monitor(reqArr,taxiArray);	
		Status s=new Status(taxiArray);
		TestStatus ts=new TestStatus(s);//这是实时获得状态的线程，请编写好测试类之后将TestStatus修改为你编写的测试类
		Test t=new Test(reqArr);//产生请求的类，请 编写好测试类之后将Test改为你编写的测试类
		Thread.setDefaultUncaughtExceptionHandler(new Handler());
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(m);
		exec.execute(ts);//执行实时获得出租车状态的线程
		exec.execute(t);//执行测试类
	}
}
