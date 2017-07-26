public class LinkedList
{
	protected Node start;
	
	public LinkedList()
	{
		start=null;
	}
	
	public LinkedList(Node start)
	{
		this.start=start;
	}
	
	public void add(int roll, double dist)
	{
		Node temp=new Node(roll,dist,null),ptr=start;
		
		if(ptr==null)
			start=temp;
		else
		{
			while(ptr.getLink()!=null)
				ptr=ptr.getLink();
			ptr.setLink(temp);
		}
	}
	
	public void add(Node temp)
	{
		Node ptr=start;
		
		while(ptr.getLink()!=null)
			ptr=ptr.getLink();
		ptr.setLink(temp);
	}
	
	public Node remove(int x)
	{
		Node ptr1=start,ptr2;
		
		if(ptr1==null)
			return null;
		else
		{
			if(ptr1.getRoll()==x)
			{
				start=ptr1.getLink();
				ptr1.setLink(null);
				return ptr1;
			}
			else
			{
				ptr2=ptr1.getLink();
				
				while(ptr2.getLink()!=null)
				{
					if(ptr2.getRoll()==x)
					{
						ptr1.setLink(ptr2.getLink());
						ptr2.setLink(null);
						return ptr2;
					}
					ptr1=ptr1.getLink();
					ptr2=ptr2.getLink();
				}
				
				ptr1.setLink(null);
				ptr2.setLink(null);
				return ptr2;
			}
		}	
	}
	
	public int count()
	{
		Node ptr=start;
		int c=0;
		
		while(ptr!=null)
		{
			c++;
			ptr=ptr.getLink();
		}
		
		return c;
	}
	
	void display()
	{
		Node ptr=start;
		
		while(ptr!=null)
		{
			System.out.print(ptr.getRoll()+"   ");
			ptr=ptr.getLink();
		}
	}
	
	public int max()
	{
		Node ptr=start;
		double max=0.0;
		int data=-1;
		
		while(ptr!=null)
		{
			if(ptr.getDist()>max)
			{
				max=ptr.getDist();
				data=ptr.getRoll();
			}
			ptr=ptr.getLink();
		}
				
		return data;
	}
}
