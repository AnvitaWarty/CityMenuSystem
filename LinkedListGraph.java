/*
 class: LinkedListGraph 
 This class contains code for implementing a Graph data structure that
 uses an adjacency list (instead of a matrix). There are three fields: 
 a String array that contains labels for the vertices, a CityNode array that 
 contains the vertices of the Graph, and a size variable that defines the length of
 both arrays. There will be a comment to explain each individual method/constructor.  
*/
public class LinkedListGraph 
{
	private String[] labels;
	private CityNode[] vertices; 
	private int size; 
	
	//method: constructor 
	//Initializes arrays with size argument
	LinkedListGraph(int size)
	{
	  this.size = size; 
	  labels = new String[size]; 
	  vertices = new CityNode[size];
	}
	
	//method: setVerticesAndLabels 
	//Uses an array containing City objects 
	//and stores each City object in the vertices array 
	//then stores each City object's city_code field in the labels 
	//array
	public void setVerticesAndLabels(City[] cities)
	{
	  for(int i = 0; i < cities.length; i++)
	  {
		vertices[i] = new CityNode(cities[i]);
	  }
	  
	  for(int i = 0; i < cities.length; i++) 
	  {
		labels[i] = cities[i].getCityCode(); 
	  }
	}
	
	
	//method: findIndex 
	//Takes a String argument which is a city_code 
	//and searches for the index position of the city 
	//by performing a for loop on the labels array 
	//Finally, the method will either return a valid index position (>=0) 
	//or it will return -1 to signify that no such city was found
	public int findIndex(String city_code)	
	{
	  int i = 0;	  
	  while(i < labels.length)
	  {
		if(labels[i].equalsIgnoreCase(city_code))
	       return i; 
	    else
			i++; 
	  }
	  
	  return (-1);
	}
	
	//method: getVertex
	//This method simply uses an integer argument (an index position) to 
	//find and return the appropriate CityNode in the vertices array
	public CityNode getVertex(int i)
	{
	  return vertices[i];
	}
	
	//method: edgeExists 
	//This method has two arguments: code1 and code 2 
	//These String arguments signify the city_codes 
	//The method will check whether a road exists between the two cities 
	//by traversing starting city's linked list. The method returns as true if a connection exists otherwise false.
	public boolean edgeExists(String code1, String code2)
	{
	  int from_city = findIndex(code1); 
	  int to_city = findIndex(code2); 
	  
      CityNode temp = vertices[from_city].getNext(); 
      while(temp!=null) 
      {
		if(temp.getCity().getCityCode().equalsIgnoreCase(code2))
          return true; 			
		else
		  temp = temp.getNext(); 
      }
	  
      return false; 	  
	}
	
	
	//method: addEge 
	//This method takes in three arguments: a city code, a City object, and an integer value for distance. 
	//First and foremost, the method obtains the index position of the starting city (from the city code). 
	//Then if an edge exists between the cities already, an exception is thrown.
	//Otherwise, the linked list of the starting city is traversed until their last connection's next pointer is null,
	//then we will set the last connection's next pointer to the new City object. 
	public void addEdge(String code1, City city, int distance)
	{
	  int from_city = findIndex(code1); 
      
      if(edgeExists(code1, city.getCityCode()))
		throw new IllegalArgumentException();
	  
	  CityNode temp = vertices[from_city]; 
	  while(temp.getNext()!=null) 
      {
		temp = temp.getNext(); 
	  }
	  
      temp.setNext(new CityNode(city, distance));	   
	}

	
	//method: deleteEdge 
	//This method takes in two String arguments which are two city codes (a starting point and an ending point). 
	//First and foremost, the method obtains the index position of the starting point. 
	//Then if an edge does not exist between the cities, an exception is thrown.
	//Otherwise, the linked list of the starting city is traversed until a succeeding connection is discovered with the same city code as the 
	//ending point specified by the second argument. Then we will set the current CityNode's next pointer to the succeeding connection's next pointer.
	public void deleteEdge(String code1, String code2) 
	{
	  int from_city = findIndex(code1); 
	  
	  if(!edgeExists(code1, code2))
		throw new IllegalArgumentException(); 
	
      CityNode temp = vertices[from_city];   
	  while(temp.getNext()!=null) 
	  {
	    if(temp.getNext().getCity().getCityCode().equalsIgnoreCase(code2))
        {			
           temp.setNext(temp.getNext().getNext());
		   break;
        }		   
        else 
		  temp = temp.getNext(); 	
      }
    }
	
	//method: searchDestination
	//This method takes in a String value for the city code and a City object that is called destination. 
	//First, the index position of the city code is obtained. Then the method
	//traverses through the linked list of the starting city until we reach a CityNode that contains destination. 
	//We will then return that CityNode. 
    public CityNode searchDestination(String code1, City destination)
	{
	  int from_city = findIndex(code1); 
	  
	  CityNode temp = vertices[from_city];
	  while(temp!=null) 
	  {
	    if(temp.getCity().getCityCode().equalsIgnoreCase(destination.getCityCode()))
        {			
           return temp;
        }		   
        else 
		  temp = temp.getNext(); 	
      }

      return null; 	  
	}  
}
