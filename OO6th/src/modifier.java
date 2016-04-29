import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class modifier {
	public synchronized void  modify(String str,String sth){//此方法仅给一个文件增加一个字符串
		File file=new File(str);
		try{
		FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(sth);
        bufferWritter.close();
		}catch(Throwable e){
			System.out.println("文件不存在！");
			System.exit(0);
		}
	}
	public synchronized void rename(String file1,String dest1){//此方法用于重命名和移动文件
		File file=new File(file1);//第一个参数为原文件absolutePath，第二个参数为移动(重命名)之后文件的absolutePath
		File dest=new File(dest1);
		file.renameTo(dest);
	}
	public synchronized void buildFile(String str){//输入文件路径和文件名
		File myFile=new File(str);
		try {
			myFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件已存在！");
			System.exit(0);
		}
	}
	public synchronized void buildDirectory(String str){
		File dir=new File(str);
		dir.mkdirs();
	}
	public synchronized boolean deleteFile(String sPath) {  
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	public synchronized boolean deleteDirectory(String sPath) {  
		Boolean flag=true;
	    File dirFile = new File(sPath);    
	    //删除文件夹下的所有文件(包括子目录)
	    if(!dirFile.isDirectory())
	    	return deleteFile(sPath);
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
}
