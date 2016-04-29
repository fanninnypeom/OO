import java.util.ArrayList;


public class Monitor implements Runnable{
	private TaxiArray taxiArray;
	private requestArray reqArray;
	public Monitor(requestArray r,TaxiArray a){
		reqArray=r;
		taxiArray=a;
	}
	public Taxi getBestTaxi(Request req){
		if(req.size()==0)
			return null;
		int i=0;
		Taxi result=null;
		for(;i<req.size();){
		if(req.get(i).getStatus()==1){
		result=req.get(i);
		i++;
		break;
		}
		i++;
//		System.out.println("1");
		}
		for(;i<req.size();i++){
//			System.out.println("2");
			if(req.get(i).getStatus()==1&&req.get(i).getCredit()>result.getCredit())
				result=req.get(i);
			else if(req.get(i).getStatus()==1&&req.get(i).getCredit()==result.getCredit()&&
					req.get(i).distanceToReq(req)>result.distanceToReq(req)	)
		        result=req.get(i);
		        }
		return result;
	}
	public void run() {
		while(true){
//			Thread.yield();
			synchronized(reqArray){
//				System.out.println("yes");
			for(int i=0;i<reqArray.size();i++){
//				System.out.println("size"+reqArray.size());
				if(reqArray.get(i)!=null&&reqArray.get(i).getCount()==0){
					Taxi t=getBestTaxi(reqArray.get(i));
			//		System.out.println("1");
					if(t!=null){
					try{
					t.add(reqArray.get(i));
					}
					catch(NullPointerException e){
						System.out.println(t);
						System.out.println(reqArray.get(i));
					}
					
					reqArray.remove(i);
					t.start();
					int c=t.getCredit();
					c+=3;
					t.setCredit(c);
					i--;
					}
					else{
						System.out.println(reqArray.get(i).getNumber()+"号请求没有可响应的车辆");
						reqArray.remove(i);
						i--;
					}
				}
			}
			for(int i=0;i<100;i++){
				for(int j=0;j<reqArray.size();j++)
					if(taxiArray.get(i).near(reqArray.get(j))){
						reqArray.get(j).addTaxi(taxiArray.get(i));
						int t=taxiArray.get(i).getCredit();
						t++;
						taxiArray.get(i).setCredit(t);
					}
//				System.out.println("move"+i);
				taxiArray.get(i).move();
			}
			
			for(int i=0;i<reqArray.size();i++){
				reqArray.get(i).sub();
			//	System.out.println("2");
			}
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

