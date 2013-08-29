import java.util.ArrayList;
import java.util.Random;


public class FindHeaviest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Marble> bagOfMarbles;
		Random randomizer = new Random();
		
		// note you can change the value of x and the max size to preference
		// and it will still work, as long as x != 0 because it only handles 
		// I placed the default case to 999 to 1050 so that it would be a big size.
		
		for(int x = 999; x <= 1050; x++) // does cases: size 999 to 1050
		{
			bagOfMarbles = new ArrayList<Marble>();
			for(int i = 0; i < x; i++) 
			{
				bagOfMarbles.add(new Marble(1, i));
			}
			int y = randomizer.nextInt(x);
			bagOfMarbles.get(y).setWeight(2);
			System.out.println("[INIT] Set Heaviest Marble to:   " + y);
			int z = findHeaviest(bagOfMarbles);
			System.out.println("[ANSW] Found Heaviest Marble at: " + z);
		}		
	}

	public static int findHeaviest(ArrayList<Marble> a)
	{
		int position = -1; // returns -1 if no heaviest marble
		if(a.size() <= 3) // handles smallest case
		{
			if(a.size() == 1) // will occur if heaviest marble is in size 1 from size 4 or 5 bag
			{
				return a.get(0).getMarbleNumber();
			}
			else
			{
				if(a.size() == 2) 
				{
					if(a.get(0).getWeight() > a.get(1).getWeight())
					{
						return a.get(0).getMarbleNumber();
					}
					else
					{
						return a.get(1).getMarbleNumber();
					}
				}
				else
				{
					return a.get(compareThree(a.get(0).getWeight(), a.get(1).getWeight(), a.get(2).getWeight())).getMarbleNumber();
				
				}
			}
		}
		else
		{
			ArrayList<ArrayList<Marble>> bags = new ArrayList<ArrayList<Marble>>(); // an object to hold 3 bags
			for(int i = 0; i < 3; i++)
			{
				bags.add(new ArrayList<Marble>());
			}
			for(int i = 0; i < a.size(); i++)
			{
				bags.get(i%3).add(a.get(i)); // mod so that marbles will be added evenly (favoring the first bag to have more)
			}
			position = findHeaviest(bags.get(compareBags(bags))); // returns the bag with the heaviest marble (use of recursion to do the smaller cases)
		}
		return position; 
	}
	
	public static int compareBags(ArrayList<ArrayList<Marble>> bags)
	{
		int x = -1;
		if(bags.get(0).size() > bags.get(1).size())
		{
			if(bags.get(1).size() == bags.get(2).size())
			{
				x = 0; // if the bag sizes differences are [1,0,0]
			}
			else
			{
				x = 1; // if the bag sizes differences are [1,1,0]
			}
		}
		else
		{
			x = 2; // if the bag sizes differences are [0,0,0]
		}
		
		int bag0weight = getBagWeight(bags.get(0));
		int bag1weight = getBagWeight(bags.get(1));
		int bag2weight = getBagWeight(bags.get(2));
		
		switch (x) 
		{ // we are dividing bags in an order that favors bag 0 being heaviest so only 3 cases:
	        case 0: // bag size difference: [1,0,0]
	        	if(bag1weight > bag2weight) // weigh the two even bags (bags 1 and 2)
	        	{
	        		return 1;
	        	}
	        	else
	        	{
	        		if(bag1weight < bag2weight)
	        		{
	        			return 2;
	        		}
	        		else
	        		{
	        			return 0; // return the biggest bag if the equal sized bags are the same
	        		}
	        	}
	        case 1: // bag size difference: [1,1,0]
	        	if(bag0weight > bag1weight) // weigh the two even bags (bags 0 and 1)
	        	{
	        		return 0;
	        	}
	        	else
	        	{
	        		if(bag0weight < bag1weight)
	        		{
	        			return 1;
	        		}
	        		else
	        		{
	        			return 2; // return the smallest bag if the equal sized bags are the same
	        		}
	        	}
	        case 2: // bag size difference: [0,0,0]
	        	return compareThree(bag0weight, bag1weight, bag2weight); // same logic as only 3 marbles
	        default:
	        	break;
		}
		
		return -1;
	}
	
	public static int getBagWeight(ArrayList<Marble> bag)
	{
		int weight = 0;
		for(Marble m : bag)
		{
			weight += m.getWeight(); // add all the marble weights
		}
		return weight;
	}
	
	public static int compareThree(int a, int b, int c)
	{
		if(a > b)
		{
			return 0; // largest is a
		}
		else
		{
			if(a < b)
			{
				return 1; // largest is b
			}
			else
			{
				return 2; // largest is c
			}
		}
	}
}
