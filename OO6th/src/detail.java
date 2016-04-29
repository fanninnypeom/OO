import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class detail {
	static ArrayList<String> content;
	static File ff;
	static int count;
	public detail(){
		content=new ArrayList<String>();
		File directory=new File(".");
		String path=null;
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path+="\\detail.txt";
		ff=new File(path);
		if(ff.exists())
			ff.delete();
		try {
			ff.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在！");
			System.exit(0);
		}
		count=0;
	}
	public synchronized static void add(String str){
		content.add(str);
	}
	public synchronized static void print(){
		if(count!=content.size()){
		 try {
		FileWriter fileWritter = new FileWriter(ff.getAbsolutePath(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		for(int i=count;i<content.size();i++){
			bufferWritter.write(content.get(i));
			bufferWritter.write("\r\n");
		}
		 
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在！");
			System.exit(0);
		}
		count=content.size();
		}
	}
}
