/*
  class: CityNode
  This class creates nodes specifically for this assignment called CityNodes, 
  and these nodes contain three fields: a next pointer field, a City object,
  and an integer value for distance. The class has several constructors for 
  many possible declarations. There is a no-arg constructor. There is a 
  constructor that only takes in a City object. There is a constructor 
  that takes in both a City object and an integer value for distance. 
  Finally there is a constructor that takes in arguments for all three fields.  
  The rest of the code contains setters and getters.
*/

public class CityNode 
{
	CityNode next; 
	City city; 
	int distance; 
		
	CityNode()
	{
	  this(null, null, 0); 
	}
	CityNode(City city)
	{
	  this(null, city, 0); 
	}
	
	CityNode(City city, int distance) 
	{
	  this(null, city, distance); 
	}
		
	CityNode(CityNode next, City city, int distance) 
	{
	  this.next = next; 
	  this.city = city; 
	  this.distance = distance; 
	}
		
	//setters
		
	public void setNext(CityNode next)
	{
	  this.next = next; 
	}
		
	public void setCity(City city) 
	{
	  this.city = city; 
	}
		
	public void setDistance(int distance) 
	{
	  this.distance = distance; 
	}
		
	//getters
		
	public CityNode getNext()
	{
	  return next; 
	}
		
	public City getCity()
	{
	  return city; 
	}
		
	public int getDistance() 
	{
	  return distance; 
	}
}
