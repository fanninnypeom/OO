
public class threadRequest extends Request{
		private int elevatorNum;
		public threadRequest(String str){
			RequestStr=str;
			String[] str1=str.split("[(,)]");
			if(str1[1].equals("FR")){
				try{
				RequestFlag=0;
				AimFloor=-1;
				RequestFloor=Integer.parseInt(str1[2]);
				if(str1[3].equals("UP"))
					Direction=1;
				else if(str1[3].equals("DOWN"))
					Direction=0;
				else{
					System.out.println("格式错误！");
					System.exit(0);
				}
				Time=Timer.getCurrentTime();
				} catch(NumberFormatException e){
					System.out.println("格式错误！");
					System.exit(0);
				}
//				RequestStr=RequestStr+String.valueOf(Time);
			}
			else if(str1[1].equals("ER")){
				try{
				setRequestFlag(1);	
				setRequestFloor(-1);
				elevatorNum=(Integer.parseInt(str1[2])-1);
				setAimFloor(Integer.parseInt(str1[3]));
				setDirection(-1);
				Time=Timer.getCurrentTime();
				} catch(NumberFormatException e){
					System.out.println("格式错误！");
					System.exit(0);
				}
//				RequestStr=RequestStr+String.valueOf(Time);
			}
			else{
				System.out.println("格式错误！");
				System.exit(0);
			}
			if(RequestFlag==0){
				setFinalFloor(RequestFloor);
			}
			else{
				setFinalFloor(AimFloor);
			}
			ArriveFloor.add(finalFloor);
		}
		public int getElevatorNum() {
			return elevatorNum;
		}

		public void setElevatorNum(int elevatorNum) {
			this.elevatorNum = elevatorNum;
		}
}
