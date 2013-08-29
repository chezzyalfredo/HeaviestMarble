
public class Marble{
	private int weight = 0;
	private int marbleNumber = -1;
	
	public Marble(int w, int mn)
	{
		this.weight = w;
		this.marbleNumber = mn;
	}
	
	public void setWeight(int w)
	{
		this.weight = w;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
	
	public int getMarbleNumber()
	{
		return marbleNumber;
	}
}
