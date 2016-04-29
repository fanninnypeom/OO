
public class TestStatus implements Runnable{
	private Status status;
	public TestStatus(Status s){
		status=s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			status.getStatus(i);
		}
		while(true){
			for(int i=0;i<100;i++){
				if(status.getTaxiStatus(i)==2||status.getTaxiStatus(i)==3||status.getTaxiStatus(i)==4)
				{
				status.getStatus(i);
				System.out.println(i+" stopCount"+status.getTaxiStopCount(i));
				}
				//System.out.println(i+" waitCount"+status.getTaxiWaitCount(i));
				//System.out.println(i+"status" +status.getTaxiStatus(i));
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
