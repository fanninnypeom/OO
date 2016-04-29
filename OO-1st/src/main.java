import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class poly{
	public int[] coeffs;
	public poly(){
		coeffs=new int[1000000];
	}
	public poly sub(poly q){
		int i=0;
		for(i=0;i<1000000;i++){
			this.coeffs[i]=this.coeffs[i]-q.coeffs[i];
		}
		return this;
	}
	public poly add(poly q){
		int i=0;
		for(i=0;i<1000000;i++){
			this.coeffs[i]=this.coeffs[i]+q.coeffs[i];
		}
		return this;
	}
	public void clear(){
		java.util.Arrays.fill(this.coeffs,0);
	}
}
public class main {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入算式!");
		String str=scan.nextLine();
		scan.close();
		if(str.length()>500){
			System.out.println("算式过长！");
			return ;
		}
		String str1=str.replaceAll(" ","");
//		Pattern pattern1 = Pattern.compile("^([+-]?\\{\\(-?(\\d+),{1}(\\d+)\\){1}(,{1}\\(-?(\\d+),{1}(\\d+)\\))*\\}){1}([+-](\\{(\\(-?(\\d+),{1}(\\d+)\\)(,{1}\\(-?(\\d+),{1}(\\d+)\\))*)+\\}){1})*$");
		Pattern pattern1 = Pattern.compile("^([+-]?\\{(\\((-?(\\d+),{1}(\\d+))?\\){1}(,{1}\\((-?(\\d+),{1}(\\d+))?\\))*)?\\}){1}"
				+ "([+-](\\{(\\((-?(\\d+),{1}(\\d+))?\\)(,{1}\\((-?(\\d+),{1}(\\d+))?\\))*)?\\}){1})*$");
		Matcher matcher1=pattern1.matcher(str1);
		boolean b1=matcher1.matches();
		if(b1==false){
			System.out.println("格式错误！请按照标准格式输入");
			return ;
		}
		int i,j,coeffs,exps,sym_flag=0;
		poly polyFirst=new poly();
	    poly polySecond=new poly();
	    Pattern p=Pattern.compile("(\\{[^{}]*\\})");
//	    Pattern p=Pattern.compile("[^+-]");
	    Matcher m=p.matcher(str1);
	    String operators=m.replaceAll("").trim();
	    char [] op=operators.toCharArray();
		String[] polyArray=str1.split("(\\}\\+\\{)|(\\}\\-\\{)");
		if(op.length==polyArray.length){
			if(op[0]=='-')
				sym_flag=1;
			else if(op[0]=='+')
				sym_flag=0;
			for(i=0;i<op.length-1;i++){
				op[i]=op[i+1];
			}
		}
		for(i=0;i<polyArray.length;i++){
			if(i==0){
				String[] polyFirstArray=polyArray[i].split("\\+\\{|\\-\\{|\\{|\\}|\\,|\\(|\\)");
				for(j=0;j<polyFirstArray.length;){
					if(polyFirstArray[j].length()==0){
						j=j+1;
						continue;
					}
					if(polyFirstArray[j].length()>8||polyFirstArray[j+1].length()>6){
						System.out.println("数字过长！");
						return;
					}
					if(polyFirstArray[j].length()==0||polyFirstArray[j+1].length()==0){
						System.out.println("格式错误！");
						return;
					}
					coeffs=Integer.parseInt(polyFirstArray[j]);
					exps=Integer.parseInt(polyFirstArray[j+1]);
					if(sym_flag==1)
						polyFirst.coeffs[exps]=-coeffs;
					else if(sym_flag==0)
						polyFirst.coeffs[exps]=coeffs;
					j=j+2;
				}
				continue;
			}
			String[] polySecondArray=polyArray[i].split("[{}(),]");
			polySecond.clear();
			for(j=0;j<polySecondArray.length;){
				if(polySecondArray[j].length()==0){
					j=j+1;
					continue;
				}
				if(polySecondArray[j].length()>8||polySecondArray[j+1].length()>8){
					System.out.println("数字过长！");
					return;
				}
				if(polySecondArray[j].length()==0||polySecondArray[j+1].length()==0){
					System.out.println("格式错误！");
					return;
				}
				coeffs=Integer.parseInt(polySecondArray[j]);
				exps=Integer.parseInt(polySecondArray[j+1]);
				polySecond.coeffs[exps]=coeffs;
				j=j+2;
			}
			if(op[i-1]=='+')
				polyFirst.add(polySecond);
			else if(op[i-1]=='-')
				polyFirst.sub(polySecond);
		}
		System.out.print("{");
		for(i=0;i<1000000;i++){
			if(polyFirst.coeffs[i]!=0){
			System.out.print("(");	
			System.out.print(polyFirst.coeffs[i]);
			System.out.print(",");
			System.out.print(i);
			System.out.print(") ");
			}
		}
		System.out.println("}");
	    return ;
	}
	
}

/*		char [] StringArr=str1.toCharArray();
		int i=0,CalcFlag=1,j;
		char [] ChArr=str1.toCharArray();
		poly polyFirst=new poly();
	    poly polySecond=new poly();
	    for(i=0;i<500;i++){
	        if(StringArr[i]=='\n')
	            break;
	            if(StringArr[i]=='{'&&i==0){
	                while(true){
	                    i++;
	                    if(StringArr[i]=='('){
	                        j=0;
	                        while(StringArr[i]!=')'){
	                                ChArr[j]=StringArr[i];
	                                j++;
	                                i++;
	                           }
	                            ChArr[j]='\0';
	                            String strTemp=new String(ChArr);
	                            String[] strArray=strTemp.split(",");
	                            int coeffs=Integer.parseInt(strArray[0]);
	                            int exps=Integer.parseInt(strArray[1]);
	                            polyFirst.coeffs[exps]=coeffs;
	                        }
	                        if(StringArr[i]==',')
	                            continue;
	                        if(StringArr[i]=='}')
	                            break;              
	                }
	            }
	            if(StringArr[i]=='+')
	                CalcFlag=1;
	            else if(StringArr[i]=='-')
	                CalcFlag=0;
	            if(StringArr[i]=='{'){
	            	polySecond.clear();
	                while(true){
	                    i++;
	                        if(StringArr[i]=='('){
	                            j=0;
	                            while(StringArr[i]!=')'){
	                                i++;
	                                ChArr[j]=StringArr[i];
	                                j++;
	                           }
	                            ChArr[j]='\0';
	                            String strTemp1=new String(ChArr);
	                            String[] strArray1=strTemp1.split(",");
	                            int coeffs=Integer.parseInt(strArray1[0]);
	                            int exps=Integer.parseInt(strArray1[1]);
	                            polySecond.coeffs[exps]=coeffs;
	                        }
	                        if(StringArr[i]==',')
	                            continue;
	                        if(StringArr[i]=='}')
	                            break;
	                }

	            }
	            if(StringArr[i]=='}'){
	                if(CalcFlag==1)
	                	polyFirst.add(polySecond);
	                else if(CalcFlag==0)
	                	polyFirst.sub(polySecond);
	            }
	        

	    }
	    */
