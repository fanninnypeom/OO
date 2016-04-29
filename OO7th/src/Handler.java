import java.lang.Thread.UncaughtExceptionHandler;


public class Handler implements UncaughtExceptionHandler{
	public void uncaughtException(Thread t,Throwable e){
		System.out.println("good job!");
	}
}
