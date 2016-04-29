import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;


public class main {
	public static void main(String argus[]) {
		detail d=new detail();
		summary s=new summary();
		
		Scanner scan = new Scanner(System.in);
		String[] task=new String[50];
		System.out.println("请输入任务");
		ArrayList<monitor> taskArray=new ArrayList<monitor>();
		ArrayList<String> taskPathArray=new ArrayList<String>();
		int i=0;
		Pattern p=Pattern.compile("^\\(([^,]+),(renamed|modified|pathChanged|sizeChanged),(recordSummary|recordDetail|recover)\\)$");
		while(true){
			String str;
			if(!(str=scan.nextLine()).equals("END")&&i<50){
				boolean a=p.matcher(str).matches();
				if(a==false)
					continue;
				task[i]=str;
				i++;
			}
			else
				break;
		}
		modifier reviewer=new modifier();
		for(int j=0;j<i;j++){
			int trigger=0,assignment=0;
			String[] temp=task[j].split("[(),]");
			switch(temp[2]){
			case "renamed" : trigger=1;break;
			case "modified" : trigger=2;break;
			case "pathChanged" : trigger=3;break;
			case "sizeChanged" : trigger=4;break;
			}
			switch(temp[3]){
			case "recordSummary" :assignment=1;break;
			case "recordDetail" :assignment=2;break;
			case "recover" :assignment=3;break;
			}
			if(!taskPathArray.contains(temp[1])){
			monitor m=null;
			try {
				m = new monitor(temp[1],trigger,assignment,reviewer);
			} catch (UnExistingException e) {
				// TODO Auto-generated catch block
				System.out.println("文件不存在！");
				System.exit(0);
			}
			taskArray.add(m);
			taskPathArray.add(temp[1]);
			}
			else{
				int index=taskPathArray.indexOf(temp[1]);
				taskArray.get(index).addTask(assignment);
				taskArray.get(index).addTrigger(trigger);
			}
		}
	//	Thread.setDefaultUncaughtExceptionHandler(new handler());
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int k=0;k<taskArray.size();k++){
			exec.execute(taskArray.get(k));
		}
		//在这里启动测试线程，请使用exec.execute(Runnable)的格式启动！
		//请将reviewer作为参数传入你的测试线程类中	
		//例如:
		test5 test1=new test5(reviewer);
		//TAT,你唯一要做的只是将test3改为你编写的测试类
		exec.execute(test1);
	}
}
