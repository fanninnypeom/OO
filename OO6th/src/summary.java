import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class summary {
	static int renamed;
	static int modified;
	static int pathChanged;
	static int sizeChanged;
	static int preRenamed;
	static int preModified;
	static int prePathChanged;
	static int preSizeChanged;
	static File ff;
	public summary(){
		renamed=0;
		modified=0;
		pathChanged=0;
		sizeChanged=0;
		preRenamed=0;
		preModified=0;
		prePathChanged=0;
		preSizeChanged=0;
		File directory=new File(".");
		String path=null;
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path+="\\summary.txt";
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
	}
	public synchronized static void modifiedPlus(){
		modified=modified+1;
		System.out.println("modified"+modified);
	}
	public synchronized static void renamedPlus(){
		renamed=renamed+1;
		System.out.println("renamed"+renamed);
	}
	public synchronized static void pathChangedPlus(){
		pathChanged=pathChanged+1;
		System.out.println("pathChanged"+pathChanged);
	}
	public synchronized static void sizeChangedPlus(){
		sizeChanged=sizeChanged+1;
		System.out.println("sizeChanged"+sizeChanged);
	}
	public synchronized static void print(){
		 try {
			 	System.out.println(ff.exists());
				FileWriter fileWritter = new FileWriter(ff.getAbsolutePath(),true);
		        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				if(preModified!=modified){	
		        bufferWritter.write("modified"+modified);
				bufferWritter.write("\r\n");
		        preModified=modified;
				}
				if(renamed!=preRenamed){
					bufferWritter.write("renamed"+renamed);
					bufferWritter.write("\r\n");
					preRenamed=renamed;
				}
				if(pathChanged!=prePathChanged){
					bufferWritter.write("pathChanged"+pathChanged);
					bufferWritter.write("\r\n");
					prePathChanged=pathChanged;
				}
				if(sizeChanged!=preSizeChanged){
					bufferWritter.write("sizeChanged"+sizeChanged);
					bufferWritter.write("\r\n");
					preSizeChanged=sizeChanged;
				}
					bufferWritter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("文件不存在！");
					System.exit(0);
				}
	}
}
