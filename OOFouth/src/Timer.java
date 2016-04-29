import java.util.Calendar;


public class Timer {
	private static long initTime;
	private static long currentTime;
	public Timer(){
	//Calendar c = Calendar.getInstance();
	//int mm = c.get(Calendar.MINUTE);
	//int SS = c.get(Calendar.SECOND);
	//int MI = c.get(Calendar.MILLISECOND);
//	int HH = c.get(Calendar.HOUR_OF_DAY);
	
	//initTime=HH*3600+mm*60+SS+MI/1000.0;
//	System.out.println("init"+HH+" "+MI+" "+SS+" "+mm+" "+initTime);
	initTime=System.currentTimeMillis();
	}
	static public void compensate(long lossTime){
		initTime+=lossTime;
	}
	static public double getCurrentTime(){
	double result;
	//Calendar c = Calendar.getInstance();
	//int mm = c.get(Calendar.MINUTE);
	//int SS = c.get(Calendar.SECOND);
	//int MI = c.get(Calendar.MILLISECOND);
	//int HH = c.get(Calendar.HOUR_OF_DAY);

//	currentTime=HH*3600+mm*60+SS+MI/1000.0;
//	result=currentTime-initTime;
//	System.out.println(result);
//	result=result*10;
//	result=Math.round(result);
//	result=result/10.0;
//	System.out.println(result+" "+HH+" "+MI+" "+SS+" "+mm);
	currentTime=System.currentTimeMillis();
//	System.out.println(currentTime-initTime+"ms");
	result=(currentTime-initTime)/100.0;
	result=Math.round(result);
	result=result/10.0;
	return result;
	}
}
