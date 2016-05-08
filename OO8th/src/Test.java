import java.util.ArrayList;


public class Test implements Runnable{
	private requestArray reqArray;
	Test(requestArray r){
		//requires:nop
		//modify:nop 
		//effect:init reqArray
		reqArray=r;
	}
	public boolean existInMap(int start,int end){
		//requires:start>=0 && start<6400  end>=0 &&end<6400
				//modify:nop 
				//effect:renturn a boolean to judge if the two point is adjacent
		if(start-80==end){
			if(Map.mapDuplicate[end/80][end%80]==2||Map.mapDuplicate[end/80][end%80]==3){
				return true;
			}
		}
		else if(start+80==end){
			if(Map.mapDuplicate[start/80][start%80]==2||Map.mapDuplicate[start/80][start%80]==3){
				return true;
			}

		}
		else if(start+1==end){
			if(Map.mapDuplicate[start/80][start%80]==1||Map.mapDuplicate[start/80][start%80]==3){
				return true;
			}
		}
		else if(start-1==end){
			if(Map.mapDuplicate[end/80][end%80]==1||Map.mapDuplicate[end/80][end%80]==3){
				return true;
			}
		}
		return false;
	}
	public void modifyMap(int start,int end,boolean status){
		//requires:start>=0 &&start<6400 end>=0 &&end<6400
		//modify:nop
		//effect:map[] is changed because a new road is created or a road is destroyed
		if(!(start>=0&&start<6400&&end>=0&&end<6400)){
			System.out.println("请输入合法的点");
			return;
		}
		if(status==true&&existInMap(start,end)){
			if(start-80==end){
				if(Map.map[end/80][end%80]==0){
					Map.map[end/80][end%80]=2;
				}
				else if(Map.map[end/80][end%80]==1){
					Map.map[end/80][end%80]=3;
				}
			}
			else if(start+80==end){
				if(Map.map[start/80][start%80]==0){
					Map.map[start/80][start%80]=2;
				}
				else if(Map.map[start/80][start%80]==1){
					Map.map[start/80][start%80]=3;
				}

			}
			else if(start+1==end){
				if(Map.map[start/80][start%80]==0){
					Map.map[start/80][start%80]=1;
				}
				else if(Map.map[start/80][start%80]==2){
					Map.map[start/80][start%80]=3;
				}
			}
			else if(start-1==end){
				if(Map.map[end/80][end%80]==0){
					Map.map[end/80][end%80]=1;
				}
				else if(Map.map[end/80][end%80]==2){
					Map.map[end/80][end%80]=3;
				}
			}
		}
		else{
			if(start-80==end){
				if(Map.map[end/80][end%80]==2){
					Map.map[end/80][end%80]=0;
				}
				else if(Map.map[end/80][end%80]==3){
					Map.map[end/80][end%80]=1;
				}
			}
			else if(start+80==end){
				if(Map.map[start/80][start%80]==2){
					Map.map[start/80][start%80]=0;
				}
				else if(Map.map[start/80][start%80]==3){
					Map.map[start/80][start%80]=1;
				}

			}
			else if(start+1==end){
				if(Map.map[start/80][start%80]==1){
					Map.map[start/80][start%80]=0;
				}
				else if(Map.map[start/80][start%80]==3){
					Map.map[start/80][start%80]=2;
				}
			}
			else if(start-1==end){
				if(Map.map[end/80][end%80]==1){
					Map.map[end/80][end%80]=0;
				}
				else if(Map.map[end/80][end%80]==3){
					Map.map[end/80][end%80]=2;
				}
			}
		}
	}
	public void run() {
		//requires:nop
		//modify:nop 
		//effect:run the thread
		// TODO Auto-generated method stub
		int i=0;
//		while(true){
//		i++;
//		Request req=new Request(i,45,27,34,12);
//		Request req=new Request(i,45,45,34,12);
		modifyMap(0,80,false);
		modifyMap(1,81,false);
//		modifyMap(1,2,true);
		modifyMap(2,82,false);
		modifyMap(3,83,false);
		modifyMap(0,80,true);
		modifyMap(1,81,true);
//		modifyMap(1,2,true);
		modifyMap(2,82,true);
		modifyMap(3,83,true);
		//		Request req1=new Request(0,12,12,66,66);
//		Request req2=new Request(1,12,12,66,66);
//		Request req3=new Request(2,12,12,66,66);
//		reqArray.add(req1);
//		reqArray.add(req2);
//		reqArray.add(req3);
			while(true){
		Request req1=new Request(0,12,12,66,66);
		reqArray.add(req1);
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Request req2=new Request(1,66,66,12,12);
		reqArray.add(req2);
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

}
