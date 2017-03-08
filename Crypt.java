import  java.util.*;
import java.lang.Math;

public class Crypt
{
	public HashMap<Byte,Integer> createTable(byte[] args)
	{
		Scanner input = new Scanner(System.in);
		byte[] key = new byte[9];  //Array for key
		byte[][] table;
		HashMap<Byte,Integer> final_table=new HashMap<Byte,Integer>();  

		key = args;

		table=new byte[key[0]+1][];
		for(int i=0; i<=key[0]; i++)
		{
			table[i] = new byte[key[1]+1];
		}

		if(key[2]==0) //Start Header Number from ROW
		{
			int j=1;
			if(key[3]==0)
			{
				for(byte i=1; i<table.length; i++, j++)
				{
					table[i][0]=(byte)(j);
				}
			}
			if(key[3]==1)
			{
				for(byte i=(byte)(table.length-1); i>0; i--, j++)
				{
					table[i][0]=(byte)(j);
				}
			}
			if(key[4]==0)
			{
				for(byte i=1; i<=key[1]; i++, j++)
				{
					table[0][i]=(byte)(j);
				}
			}
			if(key[4]==1)
			{
				for(byte i=(byte)(key[1]); i>0; i--, j++)
				{
					table[0][i]=(byte)(j);
				}
			}
		}

		else if(key[2]==1) //Start Header Number from Column
		{
			int j=1;
			if(key[4]==0)
			{
				for(byte i=1; i<=key[1]; i++, j++)
				{
					table[0][i]=(byte)(j);
				}
			}
			if(key[4]==1)
			{
				for(byte i=(byte)(key[1]); i>0; i--, j++)
				{
					table[0][i]=(byte)(j);
				}
			}
			if(key[3]==0)
			{
				for(byte i=1; i<table.length; i++, j++)
				{
					table[i][0]=(byte)(j);
				}
			}
			if(key[3]==1)
			{
				for(byte i=(byte)(table.length-1); i>0; i--, j++)
				{
					table[i][0]=(byte)(j);
				}
			}
		}
	
		else
		{
			System.out.println("\nKey ERROR!!!!");
		}

		for(int i=0; i<table.length; i++) //Initialize table by -1
		{
			//System.out.println("");
			for(int j=0; j<table[i].length; j++)
			{  
				if(i==0 || j==0) 
				{
				}
				else 
				{
					table[i][j]=(byte)(-1);
				}
			}
		}
	
		if(key[5]==0) //Insert Only Small alphabet in table
		{
			byte symbol = 97;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 122)
					{
						break;
					}
	
				}
				if(symbol == 122)
				{
					break;
				}
			}
		}

		else if(key[5]==1) //Insert Only Capital alphabet in table
		{
			byte symbol = 65;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 90)
					{
						break;
					}
	
				}
				if(symbol == 90)
				{
					break;
				}
			}
		}

		else if(key[5]==2) //Insert Only Digit character in table
		{
			byte symbol = 48;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 57)
					{
						break;
					}
	
				}
				if(symbol == 57)
				{
					break;
				}
			}
		}

		else if(key[5]==3) //Insert Small Alphabet -> Capital Alphabet  in table
		{
			byte symbol = 97;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 122)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						break;
					}
	
				}
				if(symbol == 90)
				{
					break;
				}
			}
		}

		else if(key[5]==4) //Insert Capital Alphabet -> Small Alphabet  in table
		{
			byte symbol = 65;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 90)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						break;
					}
	
				}
				if(symbol == 122)
				{
					break;
				}
			}
		}

		else if(key[5]==5) //Insert Small Alphabet -> Digit  in table
		{
			byte symbol = 97;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 122)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						break;
					}
	
				}
				if(symbol == 57)
				{
					break;
				}
			}
		}

		else if(key[5]==6) //Insert Digit -> Small Alphabet  in table
		{
			byte symbol = 48;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 57)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						break;
					}
	
				}
				if(symbol == 122)
				{
					break;
				}
			}
		}

		else if(key[5]==7) //Insert Capital Alphabet -> Digit  in table
		{
			byte symbol = 65;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 90)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						break;
					}
	
				}
				if(symbol == 57)
				{
					break;
				}
			}
		}

		else if(key[5]==8) //Insert Digit -> Capital Alphabet  in table
		{
			byte symbol = 48;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 57)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						break;
					}
	
				}
				if(symbol == 90)
				{
					break;
				}
			}
		}

		else if(key[5]==9) //Insert Small Alphabet -> Capital Alphabet -> Digit  in table
		{
			byte symbol = 97;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 122)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						break;
					}
				}
				if(symbol == 57)
				{
					break;
				}
			}
		}

		else if(key[5]==10) //Insert Small Alphabet -> Digit -> Capital Alphabet in table
		{
			byte symbol = 97;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 122)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						break;
					}
				}
				if(symbol == 90)
				{
					break;
				}
			}
		}

		else if(key[5]==11) //Insert Capital Alphabet -> Small Alphabet -> Digit in table
		{
			byte symbol = 65;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 90)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						break;
					}
				}
				if(symbol == 57)
				{
					break;
				}
			}
		}

		else if(key[5]==12) //Insert Capital Alphabet -> Digit -> Small Alphabet in table
		{
			byte symbol = 65;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 90)
					{
						symbol=47;
					}
					if(symbol == 57)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						break;
					}
				}
				if(symbol == 122)
				{
					break;
				}
			}
		}

		else if(key[5]==13) //Insert Digit -> Small Alphabet -> Capital Alphabet in table
		{
			byte symbol = 48;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 57)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						break;
					}
				}
				if(symbol == 90)
				{
					break;
				}
			}
		}

		else if(key[5]==14) //Insert Digit -> Capital Alphabet -> Small Alphabet in table
		{
			byte symbol = 48;
			for(int i=1; i<table.length; i++)
			{
				for(int j=1; j<table[i].length; j++, symbol++)
				{  
					table[i][j]=symbol;
					if(symbol == 57)
					{
						symbol=64;
					}
					if(symbol == 90)
					{
						symbol=96;
					}
					if(symbol == 122)
					{
						break;
					}
				}
				if(symbol == 122)
				{
					break;
				}
			}
		}
		
		else
		{
			System.out.println("KEY 5 ERROR!!!!!");
		}
		
		if(key[7]==1)
		{
			int temp = key[6], k=1;
			int rows[]=new int[temp], r=0;
			int cols[]=new int[temp], c=0;
			Stack st = new Stack();
			for(int i=0; i<table.length; i++)
			{
				for(int j=0; j<table[i].length; j++)
				{  
					if(i==0 || j==0) //Print Header in number
					{
					}
					else
					{
						if(k%temp!=0)
						{
							rows[r]=i;
							cols[c]=j;
							if(table[i][j]!=-1)
							{
								st.push(new Byte(table[i][j]));
								r++;
								c++;
							}
						}
						else
						{
							rows[r]=i;
							cols[c]=j;
							if(table[i][j]!=-1)
							{
								st.push(new Byte(table[i][j])); //push if k%temp==0
							}
							r=0;
							c=0;
							while(!st.empty())
							{
								table[rows[r]][cols[c]]=(byte)st.pop();
								r++;
								c++;
							}
							r=0;
							c=0;
						}
						k++;
					}
				}
			}
		}

		System.out.println("");
		System.out.println("Temporary Table");
		for(int i=0; i<table.length; i++) //Store final table as MAP
		{
			System.out.println("");
			for(int j=0; j<table[i].length; j++)
			{  
				if(i==0 || j==0) //Print Header in number
				{
					System.out.print("\t"+table[i][j]);
				}
				else //Print content in character 
				{
					if(table[i][j]!=-1)
					{
						int sq=table[0][j]*table[0][j]+table[i][0]*table[i][0];	
						int digits=0, diff=0, entry=0;
						int num = sq;
						while(num!=0)
                				{
                        				digits++;
                        				num=num/10;
                				}
						
						diff=key[8]-digits;	
						sq=(int)((double)(sq)*(Math.pow(10, diff)));
						
						while(final_table.containsValue(sq))
						{
							sq=(sq+1);
						}
						final_table.put(table[i][j],sq);
						System.out.print("\t"+(char)((int)table[i][j]));
					}
				}
			}
		}
		
		return final_table;
	}
}
