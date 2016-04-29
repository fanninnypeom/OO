import java.util.ArrayList;


public class newElevator extends elevator{
	public void getInstructionArray(Request mainReq,ArrayList<Request> list){
		for(int i=0;i<list.size();i++){
			if(invalidInstr(list.get(i))){
				GiveOutInstr(list.get(i));		
				WriteRunningRecord();
			}
		}
		
	}

	private void GiveOutInstr(Request request) {
		// TODO Auto-generated method stub
		
	}
}
