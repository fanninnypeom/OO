

public class TestStatus implements Runnable{
	private Status status;
	public TestStatus(Status s){
		//requires:nop
				//modify:nop 
				//effect:init status
		status=s;
	}

	public void run() {
		//requires:nop
				//modify:nop 
				//effect:run thread
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			status.getStatus(i);
		}
		/*
		while(true){
			int count=0;
			for(int i=0;i<80;i++){
				for(int j=0;j<80;j++){
					for(int k=0;k<2;k++){
						if(Map.flowMap[i][j][k]>0){
							System.out.println("i"+i+" j"+j+" k"+k);
							count++;
						}
					}
				}
			}
			System.out.println("count"+count);
			System.out.println("------------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		while(true){
			for(int i=0;i<100;i++){
				if(status.getTaxiStatus(i)==1)
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
