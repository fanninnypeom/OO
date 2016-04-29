import java.lang.Thread.UncaughtExceptionHandler;


public class handler implements UncaughtExceptionHandler{
	public void uncaughtException(Thread t,Throwable e){
		System.out.println("good job!");
	}
}
