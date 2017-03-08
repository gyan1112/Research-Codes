import java.util.*;

public class Parser
{
	static String encMsg="";
	static String encMsg1="";
	static HashMap<String, Integer> tag_table=new HashMap<String, Integer>();
	static HashMap<Byte, Integer> final_table=new HashMap<Byte,Integer>();
	static int i=0;
	static Crypt c=new Crypt();
	public static void enc(String args, int a)
	{
		byte[] key={6, 12, 1, 0, 1, 14, 1, 1, 3};
		
		if(i==0)
		{
			final_table=c.createTable(key);
			i++;
		}
		if(a==0)
		{
                	System.out.println("");
                	System.out.println("");
                	System.out.println("Symbol Table:");
			for(Map.Entry m:final_table.entrySet())
                	{
                       		  System.out.println((char)((byte)m.getKey())+"\t"+m.getValue());
                	}	
                	System.out.println("");
                	System.out.println("Symbol Table based encryption:");
                	System.out.println(encMsg);
                	System.out.println("");
                	System.out.print("Size of symbol table based encrypted message: ");
                	System.out.println(encMsg.length());

                	System.out.println("");
                	System.out.println("Tag Table:");
			for(Map.Entry m:tag_table.entrySet())
                	{
                       		  System.out.println(m.getKey()+"\t"+m.getValue());
                	}	
		}
		else if(a==1)
		{
			XMLHandler.noOfTags+=1;
			XMLHandler.noOfCharInTags+=args.length()+2;
			encMsg+=" "+"0";
			for(int i=0; i<args.length(); i++)
			{
                       		int value=final_table.get((byte)args.charAt(i));
				encMsg=encMsg+value;
			}
			if(tag_table.containsKey(args)!=true)
			{
				int sum=0;
				int digits=0, diff=0, entry=0;
				for(int i=0; i<args.length(); i++)
				{
                       			int value=final_table.get((byte)args.charAt(i));
					sum=sum+(int)(value);
				}
				int num=sum;
				while(num!=0)
				{
					digits++;
					num=num/10;
				}
				diff=key[8]-digits-2;
				//System.out.println("Tag diff "+args+" ="+diff);
				sum=(int)((double)(sum)*(Math.pow(10, diff)));
				//System.out.println("Tag sum "+args+" ="+sum);
				//System.out.println("Tag="+args+" "+"Enc="+sum+" "+"Digits="+digits+" "+"Diff="+diff);
				while(tag_table.containsValue(sum)||sum==0)
                        	{
				//	System.out.println("Tag sum "+args+" ="+sum);
                        		sum=(sum+1)%100;
                        	}
				System.out.println("Tag sum "+args+" ="+sum);
				tag_table.put(args, sum);
			}
		}
		else if(a==2)
		{
			encMsg=encMsg+" "+"0";
			XMLHandler.noOfCharInTags+=args.length()+3;
		}
		else if(a==3)
		{
			XMLHandler.noOfVariable+=1;
			XMLHandler.noOfCharInVariable+=args.length();
			encMsg+=" ";
			for(int i=0; i<args.length(); i++)
			{
                       		int value=final_table.get((byte)args.charAt(i));
				encMsg=encMsg+value;
			}
		}
		else if(a==4)
		{
			XMLHandler.noOfAttrs+=1;
			XMLHandler.noOfCharInAttrs+=args.length()+1;
			encMsg+=" "+"00";
			for(int i=0; i<args.length(); i++)
			{
                       		int value=final_table.get((byte)args.charAt(i));
				encMsg=encMsg+value;
			}
			if(tag_table.containsKey(args)!=true)
			{
				int sum=0;
                        	int digits=0, diff=0, entry=0;
				for(int i=0; i<args.length(); i++)
				{
                       			int value=final_table.get((byte)args.charAt(i));
					sum=sum+(int)(value);
				}
				int num=sum;
				while(num!=0)
				{
					digits++;
					num=num/10;
				}
				diff=key[8]-digits-2;
				sum=(int)((double)(sum)*(Math.pow(10, diff)));
				//System.out.println("Tag="+args+" "+"Enc="+sum+" "+"Digits="+digits+" "+"Diff="+diff);
				while(tag_table.containsValue(sum)||sum==0)
                        	{
                        		sum=(sum+1)%100;
                        	}
				System.out.println("Args sum "+args+" ="+sum);
				tag_table.put(args, sum);
			}
		}
		else if(a==5)
		{
			XMLHandler.noOfAttrVal+=1;
			XMLHandler.noOfCharInAttrVal+=args.length()+2;
			encMsg+=" "+"000";
			for(int i=0; i<args.length(); i++)
			{
                       		int value=final_table.get((byte)args.charAt(i));
				encMsg=encMsg+value;
			}
			if(tag_table.containsKey(args)!=true)
			{
				int sum=0;
                        	int digits=0, diff=0, entry=0;
				for(int i=0; i<args.length(); i++)
				{
                       			int value=final_table.get((byte)args.charAt(i));
					sum=sum+(int)(value);
				}
				int num=sum;
				while(num!=0)
				{
					digits++;
					num=num/10;
				}
				diff=key[8]-digits-2;
				sum=(int)((double)(sum)*(Math.pow(10, diff)));
				//System.out.println("Tag="+args+" "+"Enc="+sum+" "+"Digits="+digits+" "+"Diff="+diff);
				while(tag_table.containsValue(sum)||sum==0)
                        	{
                        		sum=(sum+1)%100;
                        	}
				System.out.println("Args value sum "+args+" ="+sum);
				tag_table.put(args, sum);
			}
		}
		else if(a==6)
		{
                	System.out.println(" ");
                	System.out.println("Tag Table Based Encryption: ");
                	System.out.println(encMsg1);
                	System.out.println("");
                	System.out.print("Size of tag table based encrypted message: ");
                	System.out.println(encMsg1.length());
                	System.out.println("Number of tags:"+XMLHandler.noOfTags);
                	System.out.println("Number of chars in opening and closing tags:"+XMLHandler.noOfCharInTags);
                	System.out.println("Number of Attrs:"+XMLHandler.noOfAttrs);
                	System.out.println("Number of char Attrs including =:"+XMLHandler.noOfCharInAttrs);
                	System.out.println("Number of Attr Values:"+XMLHandler.noOfAttrVal);
                	System.out.println("Number of char Attr value including quote:"+XMLHandler.noOfCharInAttrVal);
                	System.out.println("Number of Variable Values:"+XMLHandler.noOfVariable);
                	System.out.println("Number of char Variable value:"+XMLHandler.noOfCharInVariable);
		}
		else if(a==7)
		{
			encMsg1+=" "+"0";
                       	int value=tag_table.get(args);
			encMsg1=encMsg1+value;
		}
		else if(a==8)
		{
			encMsg1=encMsg1+" "+"0";
		}
		else if(a==9)
		{
			encMsg1+=" ";
			for(int i=0; i<args.length(); i++)
			{
                       		int value=final_table.get((byte)args.charAt(i));
				encMsg1=encMsg1+value;
			}
		}
		else if(a==10)
		{
			encMsg1+=" "+"00";
                       	int value=tag_table.get(args);
			encMsg1=encMsg1+value;
		}
		else if(a==11)
		{
			encMsg1+=" "+"000";
                       	int value=tag_table.get(args);
			encMsg1=encMsg1+value;
		}
		else
		{
			System.out.println("a = "+a);
		}

	}
}
