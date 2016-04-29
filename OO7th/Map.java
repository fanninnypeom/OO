import java.io.BufferedReader;
import java.io.FileReader;


public class Map {
	static public int[][] map;
	public Map(){
		map=new int[80][80];
		String str;
		BufferedReader bre = null;
		String file = "W:\\java-projects\\OO7th\\map.txt";
try{
		bre = new BufferedReader(new FileReader(file));
		int i=0;
		while ((str = bre.readLine())!= null) 
		{
			if((str.replace(" ","")).length()==0)
				continue;
		char[] c=str.toCharArray();
		for(int j=0;j<80;j++){
			map[i][j]=c[j]-'0';
			if(!(map[i][j]==0||map[i][j]==1||map[i][j]==2||map[i][j]==3))
				throw new Exception();
		}
		i++;
		if(i==80)
			break;
		}
		}
		catch(Throwable e){
			System.out.println("good game");
			e.printStackTrace();
			System.exit(0);
		}
		}
}
	

