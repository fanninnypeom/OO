import java.lang.Thread.UncaughtExceptionHandler;


public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
	public void uncaughtException(Thread t,Throwable e){
		System.out.println("‘À––Ω· ¯~");
	}
}
