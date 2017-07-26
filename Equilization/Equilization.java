import java.sql.*;
import java.io.*;
public class Equilization
{
	static Connection con;
	static ResultSet res;
	static PreparedStatement pst;
	
	public void run()throws IOException
	{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Hello!");
				try
				{
					int no=0,clstr=0,j=1,as=0,bs=0,clusterno=0,k,data;
					double dist=0.0;
					char flag='N';
					
					while(flag=='N' || flag=='n')
					{
						System.out.print("Enter no of Students- ");
						no=Integer.parseInt(br.readLine());
						System.out.print("Enter no of clusters- ");
						clstr=Integer.parseInt(br.readLine());
						
						System.out.print("Therefore you have "+(no/clstr)+" no of students for each cluster. Enter (Y/y) or (N/n)- ");
						flag=br.readLine().charAt(0);
						
						if(flag=='Y' || flag=='y')
							break;
					}
					
					LinkedList obj[]=new LinkedList[clstr+1];	
					double cluster[][]=new double[2][clstr+1];
					
					for(int i=1;i<=clstr;i++)
					{
						System.out.print("Enter average AScore of Cluster "+i+"- ");
						cluster[0][i]=Double.parseDouble(br.readLine());
						System.out.print("Enter average BScore of Cluster "+i+"- ");
						cluster[1][i]=Double.parseDouble(br.readLine());
					}
					
					for(int i=1;i<=clstr;i++)
					{
						obj[i]=new LinkedList();
					}
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","system");
					
					String strqry="select * from StudentData";
					
					pst=con.prepareStatement(strqry);
					res=pst.executeQuery();
					
					while(res.next())
					{			
						as=res.getInt(2);
						bs=res.getInt(3);
						
						if(j<=no)
						{
							System.out.print("Enter Cluster no. for Roll "+j+"- ");
							clusterno=Integer.parseInt(br.readLine());
							
							dist=Math.sqrt(Math.pow(cluster[0][clusterno]-as, 2)+Math.pow(cluster[1][clusterno]-bs, 2));
							
							obj[clusterno].add(j++,dist);
						}
					}
					
					for(int i=1;i<=clstr;i++)
					{
						System.out.print("\nCluster "+i+"- ");
						obj[i].display();
					}
					
					for(int i=1;i<clstr;i++)
						for(j=i+1;j<=clstr;j++)
						{
							double temp;
							LinkedList tmp;
							if(cluster[0][i]>cluster[0][j])
							{
								temp=cluster[0][i];
								cluster[0][i]=cluster[0][j];
								cluster[0][j]=temp;
								temp=cluster[1][i];
								cluster[1][i]=cluster[1][j];
								cluster[1][j]=temp;
								tmp=obj[i];
								obj[i]=obj[j];
								obj[j]=tmp;
							}
						}
										
					for(int i=1;i<clstr;i++)
					{
						if(obj[i].count()>=(no/clstr))
						{
							while(obj[i].count()!=(no/clstr))
							{
								data=obj[i].max();
								Node ptr=obj[i].remove(data);
								obj[i+1].add(ptr);
							}
						}
						else
						{
							k=i+1;
							
							while(obj[i].count()!=(no/clstr))
							{
								if(obj[k].count()==0)
									k++;
								else
								{
									data=obj[k].max();
									Node ptr=obj[k].remove(data);
									obj[i].add(ptr);
								}
							}
						}
					}
					
					System.out.println("\n\n");
					for(int i=1;i<=clstr;i++)
					{
						System.out.print("\nCluster "+(i)+" : ");
						obj[i].display();			
					}
				}
				catch(ClassNotFoundException es)
				{
					es.printStackTrace();
				}
				catch(SQLException ess)
				{
					ess.printStackTrace();
				}
	}
	
	public static void main(String args[])throws IOException
	{
		Equilization obj=new Equilization();
		
		obj.run();
	}
}
