import java.io.File;
import java.util.ArrayList;


public class monitor implements Runnable{
	private ArrayList<Integer> trigger;
	private ArrayList<Integer> task;
	private String workSpace;
	private long result=0;
	private long pathChanged=0;
	private long size;
	private boolean IsDirectory;
	private modifier reviewer;
	private long modifiedTime;
	private File monitorFile;
	private File parentFile;
	private File dirRenamedFile;
	private String dirRenamedFilePath;
	private String fileName;
	private String filePath;
	private String recoverFilePath;
	private boolean monitorChanged;
	//private ArrayList<File> workSpaceFile=new ArrayList<File>();
	private ArrayList<File> nowWorkSpaceFileList;
	private ArrayList<File> workSpaceFileList;
	private ArrayList<File> changedFile;
	private ArrayList<sillyFile> changedSillyFile;
	
	private ArrayList<sillyFile> workSpaceSillyFileList;
	public static String TimeStamp2Date(String timestampString, String formats){    
		  Long timestamp = Long.parseLong(timestampString)*1000;    
		  String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
		  return date;    
		}  
	public monitor(String file,int trigger1,int task1,modifier reviewer1) throws UnExistingException{
		changedFile=new ArrayList<File>();
		changedSillyFile=new ArrayList<sillyFile>();
		monitorChanged=false;
		monitorFile=new File(file);
		if(!monitorFile.exists())
			throw new UnExistingException("文件路径错误！");
		fileName=monitorFile.getName();
		filePath=monitorFile.getAbsolutePath();
		recoverFilePath=monitorFile.getPath();
		parentFile=monitorFile.getParentFile();
		trigger=new ArrayList<Integer>();
		task=new ArrayList<Integer>();
		trigger.add(trigger1);
		task.add(task1);
		reviewer=reviewer1;
	
		if(monitorFile.isDirectory()){
			IsDirectory=true;
			modifiedTime=getLastModified(monitorFile);
			workSpace=monitorFile.getAbsolutePath();
		}
		else if(monitorFile.isFile()){
			IsDirectory=false;
			modifiedTime=monitorFile.lastModified();
			workSpace=monitorFile.getParent();
		}
		size=getDirSize(monitorFile);
		nowWorkSpaceFileList=new ArrayList<File>();
		workSpaceFileList=new ArrayList<File>();
		workSpaceSillyFileList=new ArrayList<sillyFile>();
		scanSillyFile();
	}
	public void run(){
		while(true){
		File f=new File(workSpace);
		if(!f.exists()){
			System.out.println("工作区没了，你看着办吧");
			System.exit(0);
		}
		synchronized(reviewer){
		System.out.println("--我-是-扫-描-分-割-线--------------------");
			try {
				reviewer.wait(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("文件不存在！");
				System.exit(0);
			}
			if(IsDirectory){
				if(!monitorFile.exists())
					try {
						throw new UnExistingException("文件路径错误！");
					} catch (UnExistingException e) {
						// TODO Auto-generated catch block
						System.out.println("文件不存在！");
						System.exit(0);
					}
				int renamedFlag=0,modifiedFlag=0,pathFlag=0,sizeFlag=0;
				for(int i=0;i<trigger.size();i++){
					switch(trigger.get(i)){
					case 1:try {
			//			System.out.println("trigger"+i);
							if(renamedFlag==0&&dirRenamed()){
								for(int j=0;j<trigger.size();j++){
									if(trigger.get(j)==1){
										switch(task.get(j)){
											case 1:
												for(int ii=0;ii<changedFile.size();ii++)
												summary.renamedPlus();
												break;
											case 2:
												for(int ii=0;ii<changedFile.size();ii++){
												String str=changedSillyFile.get(ii).getParent()+"\\"+changedSillyFile.get(ii).getName()+" to "+changedFile.get(ii).getName();
							//					System.out.println("1");
												System.out.println(str);
												detail.add(str);
												}
												break;
//									case 3:recover();System.out.println("yyy");break;
											}
									}
								}
						for(int j=0;j<trigger.size();j++){
							if(trigger.get(j)==1){
								if(task.get(j)==3){
									for(int ii=0;ii<changedFile.size();ii++){
										File temp=new File(changedSillyFile.get(ii).getPath());
										changedFile.get(ii).renameTo(temp);
									}
									break;
									}
							}
						}
								renamedFlag=1;
								break;
							}
						} catch (UnExistingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					case 2: 
		//				System.out.println("modifiedTime "+modifiedTime+
	//							"fileTime "+getLastModified(monitorFile));	
						if(modifiedFlag==0&&modifiedTime!=getLastModified(monitorFile)){
							for(int j=0;j<trigger.size();j++){
								if(trigger.get(j)==2){
									switch(task.get(j)){
										case 1:summary.modifiedPlus();break;
										case 2:recordDetail(2);break;
						//				case 3:recover();System.out.println("yyy");break;
										}
								}
							}
							restore(2);
							modifiedFlag=1;
							break;		
//							System.out.println("yes2");	
			//				switch(task.get(i)){
		//						case 1:summary.modifiedPlus();
		//						restore(2);
		//						break;
		//						case 2:recordDetail(2);restore(2);break;
		//						}
		//						break;
						}
					case 3:
						if(pathFlag==0&&dirPathChanged()){
							for(int j=0;j<trigger.size();j++){
								if(trigger.get(j)==3){
									switch(task.get(j)){
										case 1:
											for(int ii=0;ii<changedFile.size();ii++)
											summary.pathChangedPlus();
											break;
										case 2:
											for(int ii=0;ii<changedFile.size();ii++){
											String str=changedSillyFile.get(ii).getParent()+"\\"+changedSillyFile.get(ii).getName()+" to "+changedFile.get(ii).getAbsolutePath();
						//					System.out.println("3");
											System.out.println(str);
											detail.add(str);
											}
											break;											}
								}
							}
							for(int j=0;j<trigger.size();j++){
								if(trigger.get(j)==3){
									if(task.get(j)==3){
										for(int ii=0;ii<changedFile.size();ii++){
											File temp=new File(changedSillyFile.get(ii).getPath());
											changedFile.get(ii).renameTo(temp);
										}
										break;
										}
								}
							}
							pathFlag=1;
							break;
						}
					case 4: if(sizeFlag==0&&size!=getDirSize(monitorFile)){
						for(int j=0;j<trigger.size();j++){
							if(trigger.get(j)==4){
								switch(task.get(j)){
									case 1:summary.sizeChangedPlus();break;
									case 2:recordDetail(4);break;
			//						case 3:recover();System.out.println("yyy");break;
									}
							}
						}
						restore(4);	
						sizeFlag=1;
						break;
						//						System.out.println("yes4");	
	//					switch(task.get(i)){
	//						case 1:summary.sizeChangedPlus();
	//						restore(4);
	//						break;
	//						case 2:recordDetail(4);restore(4);break;
	//					}
	//						break;
					}
					}
				}
					try {
						scanSillyFile();
					} catch (UnExistingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			else{
				if(!parentFile.exists())
					try {
						throw new UnExistingException("文件路径错误！");
					} catch (UnExistingException e) {
						// TODO Auto-generated catch block
						System.out.println("文件不存在！");
						System.exit(0);
					}
				int renamedFlag=0,modifiedFlag=0,pathFlag=0,sizeFlag=0;
				for(int i=0;i<trigger.size();i++){
					switch(trigger.get(i)){
					case 1:if(renamed()&&renamedFlag==0){
						for(int j=0;j<trigger.size();j++){
							if(trigger.get(j)==1){
								switch(task.get(j)){
									case 1:summary.renamedPlus();break;
									case 2:recordDetail(1);break;
//									case 3:recover();System.out.println("yyy");break;
									}
							}
						}
						for(int j=0;j<trigger.size();j++){
							if(trigger.get(j)==1){
								if(task.get(j)==3){
									recover();
								//	System.out.println("yyy");
									break;
									}
							}
						}
						restore(1);
						renamedFlag=1;
	//					System.out.println("renamedFlag  "+renamedFlag);
						break;
			//				switch(task.get(i)){
			//				case 1:summary.renamedPlus();
			//				restore(1);
			//				break;
			//				case 2:recordDetail(1);restore(1);break;
			//				case 3:recover();System.out.println("yyy");break;
			//				}
			//				break;
					}
					
//					System.out.println("renamedFlag  "+renamedFlag);
//					break;
					case 2:						
					if(modifiedFlag==0&&modifiedTime!=monitorFile.lastModified()&&monitorFile.exists()){
						for(int j=0;j<trigger.size();j++){
							if(trigger.get(j)==2){
								switch(task.get(j)){
									case 1:summary.modifiedPlus();break;
									case 2:recordDetail(2);break;
					//				case 3:recover();System.out.println("yyy");break;
									}
							}
						}
						restore(2);
						modifiedFlag=1;
						break;
						//							switch(task.get(i)){
//							case 1:summary.modifiedPlus();
//							restore(2);
//							break;
//							case 2:recordDetail(2);restore(2);break;
//							}
//							break;
					}
					case 3:try {
							if(pathFlag==0&&pathChanged()){
								for(int j=0;j<trigger.size();j++){
									if(trigger.get(j)==3){
										switch(task.get(j)){
											case 1:summary.pathChangedPlus();break;
											case 2:recordDetail(3);break;
//											case 3:recover();System.out.println("yyy1");break;
											}
									}
								}
								for(int j=0;j<trigger.size();j++){
									if(trigger.get(j)==1){
										if(task.get(j)==3){
											recover();
											//System.out.println("yyy");
											break;
											}
									}
								}
								restore(3);
//									switch(task.get(i)){
//									case 1:summary.pathChangedPlus();restore(3);break;
//									case 2:recordDetail(3);restore(3);break;
//									case 3:recover();break;
//									}
//									break;
							}
						} catch (UnExistingException e) {
							// TODO Auto-generated catch block
							System.out.println("文件不存在！");
							System.exit(0);
						}
					pathFlag=1;
					break;
					case 4:
						if(sizeFlag==0&&size!=getDirSize(monitorFile)){
							for(int j=0;j<trigger.size();j++){
								if(trigger.get(j)==4){
									switch(task.get(j)){
										case 1:summary.sizeChangedPlus();break;
										case 2:recordDetail(4);break;
				//						case 3:recover();System.out.println("yyy");break;
										}
								}
							}
							restore(4);	
							//	System.out.println(trigger.get(i));
					//			switch(task.get(i)){
						//		case 1:summary.sizeChangedPlus();restore(4);break;
						//		case 2:recordDetail(4);restore(4);break;
						//		}
						//		break;
						}
						sizeFlag=1;
						break;
						default : break;
					}
				}
			}
		}
		summary.print();
		detail.print();
		}
	}

	public void addTrigger(int trigger1) {
		trigger.add(trigger1);
	}
	public void addTask(int task1) {
		task.add(task1);
	}
	public void restore(int i){
		switch(i){
		case 1:
			fileName=monitorFile.getName();
			recoverFilePath=filePath;
			filePath=monitorFile.getAbsolutePath();
			break;
		
		case 2:
			if(!IsDirectory){
			modifiedTime=monitorFile.lastModified();
			break;
			}
			else{
				modifiedTime=getLastModified(monitorFile);
			}
			break;
		case 3:
			parentFile=monitorFile.getParentFile();
			recoverFilePath=filePath;
			filePath=monitorFile.getAbsolutePath();
			break;
		
		case 4:
			if(!IsDirectory){
				size=monitorFile.length();
			}
			else{
				size=getDirSize(monitorFile);	
			}
			break;
		}
	}
	public void recordDetail(int i){
		String str;
		switch(i){
		case 1:
			str=filePath+" to "+monitorFile.getName();
			System.out.println(str);
			detail.add(str);
			
			break;
		
		case 2:
			if(!IsDirectory){
			str=monitorFile.getAbsolutePath()+" "+modifiedTime+" to "+monitorFile.lastModified();
			System.out.println(str);
			detail.add(str);
			break;
			}
			else{
				str=monitorFile.getAbsolutePath()+" "+modifiedTime+" to "+getLastModified(monitorFile);
				System.out.println(str);
				detail.add(str);
			}
		break;
		case 3:
			str=parentFile.getPath()+"\\"+fileName+" to "+monitorFile.getParent()+"\\"+monitorFile.getName();
			System.out.println(str);
			detail.add(str);
			break;
		
		case 4:
			if(!IsDirectory){
				str=monitorFile.getAbsolutePath()+" "+size+" to "+monitorFile.length();
				System.out.println(str);
				detail.add(str);
			}
			else{
				str=monitorFile.getAbsolutePath()+" "+size+" to "+getDirSize(monitorFile);
				System.out.println(str);
				detail.add(str);	
			}
			break;
		}
	}
	public long getDirSize(File file) {          
            //如果是目录则递归计算其内容的总大小    
        File file1=new File(filePath);
		if(!file.exists()&&!file1.exists())
        	return 0;
		if(file1.exists()&&!file.exists()){
			file=file1;
			monitorFile=file1;
		}   
			if(file.isDirectory()){
                File[] children = file.listFiles();
               int jj=0;
                for (File f : children)     
                    jj += f.length();
                return jj;     
			}
			else
				return file.length();
    }
	public boolean renamed(){
		monitorChanged=true;
		if(!monitorFile.exists()){
			File parent=monitorFile.getParentFile();
			File[] children=parent.listFiles();
			for(File f : children){
	//			System.out.println("time"+f.lastModified()+" "+modifiedTime);
//				System.out.println("size"+f.length()+" "+size);	
				if(f.lastModified()==modifiedTime&&
						f.isFile()&&f.length()==size){
						monitorFile=f;
						return true;
					}
					
			}
		}
		return false;
	}
	public boolean pathChanged() throws UnExistingException{
		monitorChanged=true;
		File workSpaceFile=new File(workSpace);
		if(!workSpaceFile.exists())
			throw new UnExistingException("文件路径错误！");
		traversalExternalWorkSpace(workSpaceFile);
//		System.out.println(pathChanged);
//		System.out.println(monitorFile.getAbsolutePath());
		if(!parentFile.equals(monitorFile.getParentFile())
				){
//			System.out.println("yes");
			pathChanged=0;
			return true;
		}
		return false;
	}
	public void traversalExternalWorkSpace(File file){
        if (file.isDirectory()) {     
            File[] children = file.listFiles();   
            for (File f : children)     
                traversalExternalWorkSpace(f);          
        } else {
  //      	System.out.println("file"+file.getName()+" "+file.lastModified()+" "+file.length());
  //      	System.out.println(fileName+" "+modifiedTime+" "+size);
  //      	System.out.println(file.getParentFile()+" "+parentFile);
            if(file.getName().equals(fileName)&&file.lastModified()==modifiedTime
            		&&file.length()==size&&!file.getParentFile().equals(parentFile)){
            	monitorFile=file;
            	pathChanged=1;
  //          	System.out.println("yes");
            }     
        } 
	}
	public void recover(){
//			System.out.println("yes");
			File temp=new File(recoverFilePath);
			monitorFile.renameTo(temp);
//			fileName=monitorFile.getName();
//			parentFile=monitorFile.getParentFile();
//			filePath=monitorFile.getAbsolutePath();
	}
	
	public void scanSillyFile() throws UnExistingException{
		
		workSpaceFileList.removeAll(workSpaceFileList);
		workSpaceSillyFileList.removeAll(workSpaceSillyFileList);
		File workSpaceFile=new File(workSpace);
		if(!workSpaceFile.exists())
			throw new UnExistingException("文件路径错误！");
		getWorkSpaceFile(workSpaceFile);
	}
	public void getWorkSpaceFile(File file) throws UnExistingException{
        if (file.isDirectory()) {     
            File[] children = file.listFiles();   
            for (File f : children)     
                getWorkSpaceFile(f);          
        } else {
        	sillyFile s=new sillyFile(file.getAbsolutePath(),file.getParent(),file.getName(),file.lastModified(),file.length(),file.hashCode());
            workSpaceSillyFileList.add(s);
            workSpaceFileList.add(file);
        } 
		
	}
	public void getNewFileList() throws UnExistingException{
		nowWorkSpaceFileList.clear();
		File workSpaceFile=new File(workSpace);
		if(!workSpaceFile.exists())
			throw new UnExistingException("文件路径错误！");
		getFileList(workSpaceFile);
	}
	public void getFileList(File file) throws UnExistingException{
        if (file.isDirectory()) {     
            File[] children = file.listFiles();   
            for (File f : children)     
                getFileList(f);          
        } else {
            nowWorkSpaceFileList.add(file);
        } 
		
	}
	
	public long getLastModified(File file){
        result=0;
        if(file.isFile())
        	return file.lastModified();
        else{
        	recurse(file);
        }
        return result;
	}
	public void recurse(File file){
		if(file.isDirectory()){
			if(result<file.lastModified())
				result=file.lastModified();
			File[] children=file.listFiles();
			for(File f : children)
				recurse(f);
		}
		else{
			if(result<file.lastModified())
				result=file.lastModified();
		}
	}
	public boolean dirRenamed() throws UnExistingException{
		changedFile.clear();
		changedSillyFile.clear();
		File workSpaceFile=new File(workSpace);
		if(!workSpaceFile.exists())
			throw new UnExistingException("文件路径错误！");
		getNewFileList();
		for(int i=0;i<workSpaceFileList.size();i++){
		//	System.out.println(workSpaceFileList.get(i).getName()+" "+workSpaceFileList.get(i).exists());
			if(workSpaceFileList.get(i).exists()==false){
	//			System.out.println("yes");
				for(int j=0;j<nowWorkSpaceFileList.size();j++){
					if((!workSpaceSillyFileList.get(i).getName().equals(nowWorkSpaceFileList.get(j).getName()))&&
					workSpaceSillyFileList.get(i).getSize()==nowWorkSpaceFileList.get(j).length()&&
					workSpaceSillyFileList.get(i).getLastModifiedTime()==nowWorkSpaceFileList.get(j).lastModified()&&
					workSpaceSillyFileList.get(i).getParent().equals(nowWorkSpaceFileList.get(j).getParent())){
//						System.out.println("yes");
						changedFile.add(nowWorkSpaceFileList.get(j));
						changedSillyFile.add(workSpaceSillyFileList.get(i));
					}
				}
			}
		}
		if(changedFile.size()!=0)
			return true;
		else
			return false;
	}
	public boolean dirPathChanged(){
		changedFile.clear();
		changedSillyFile.clear();
	//	System.out.println("pathChanged"+workSpaceFileList.size()+" "+workSpaceSillyFileList.size());
		for(int i=0;i<workSpaceFileList.size();i++){
			if(!workSpaceFileList.get(i).exists())
				for(int j=0;j<nowWorkSpaceFileList.size();j++)
					if(!nowWorkSpaceFileList.get(j).getParent().equals(workSpaceSillyFileList.get(i).getParent())&&
				nowWorkSpaceFileList.get(j).getName().equals(workSpaceSillyFileList.get(i).getName())&&
				nowWorkSpaceFileList.get(j).length()==	workSpaceSillyFileList.get(i).getSize()&&
				nowWorkSpaceFileList.get(j).lastModified()==workSpaceSillyFileList.get(i).getLastModifiedTime()){
				changedFile.add(nowWorkSpaceFileList.get(j));
				changedSillyFile.add(workSpaceSillyFileList.get(i));
			}
		}
		if(changedFile.size()!=0)
			return true;
		else
			return false;
	}
}
