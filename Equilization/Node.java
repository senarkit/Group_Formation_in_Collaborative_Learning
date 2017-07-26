public class Node 
{
	protected int roll;
	protected double dist;
	protected Node link;
	
	public Node()
	{
		roll=0;
		dist=0.0;
		link=null;
	}
	
	public Node(int roll, double dist, Node link)
	{
		this.roll=roll;
		this.dist=dist;
		this.link=link;
	}
	
	public void setRoll(int roll)
	{
		this.roll=roll;
	}
	
	public void setDist(double dist)
	{
		this.dist=dist;
	}
	
	public void setLink(Node link)
	{
		this.link=link;
	}
	
	public int getRoll()
	{
		return roll;
	}
	
	public double getDist()
	{
		return dist;
	}
	
	public Node getLink()
	{
		return link;
	}
}
