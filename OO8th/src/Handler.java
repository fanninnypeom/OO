import java.lang.Thread.UncaughtExceptionHandler;


public class Handler implements UncaughtExceptionHandler{
	public void uncaughtException(Thread t,Throwable e){
		//requires:nop
				//modify:nop
				//effect:print info
		System.out.println("good job!");
		System.exit(0);
	}
}
