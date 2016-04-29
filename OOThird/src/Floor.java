
public class Floor {
	private int FloorID;
	private int Status;//0代表向下的按钮被按，1代表向上的按钮被按，-1代表没有被按
	public Floor(int ID,int status){
		FloorID=ID;
		Status=status;
	}
	public int GetStatus(int ID){
		if(ID==FloorID)
			return Status;
		else
			return -2;
	}
}
