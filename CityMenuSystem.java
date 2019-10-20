/*
class: CityMenuSystem
This is a class that creates a menu system for the user to explore 
and interact with. First the program reads information from two files:
city.dat and road.dat. A graph is generated based on the information provided
in road.dat and city.dat. The menu system is then created and allows the user multiple options:
query a city record, insert a road between two cities, and delete a road between two cities.
Each method will have their own comments.
*/

import java.io.*;
import java.util.Scanner; 

public class CityMenuSystem 
{

//method: main 
//The main method creates two Scanner objects that read from different files. 
//The first scanner reads from the city.dat file and stores city objects into a cities array.
//The second scanner object reads from the road.dat file and stores city to city road information 
//in a graph that was initialized beforehand. Finally, the method calls the menu method which will 
//be how the user interacts with the program.
 public static void main(String[] args) throws IOException
 {   
   //Reading and initializing data for city.dat 
   File file = new File("city.dat"); 
   Scanner inputReader = new Scanner(file);
   int numberOfCities = getNumberOfLines(file);	 
   City[] cities = new City[numberOfCities];  
   int i = 0;

   while(inputReader.hasNext()) 
   {
	String[] fields = inputReader.nextLine().split(" "); 
	cities[i] = new City(fields); 
	i++; 
   }
   
   //Reading and initializing data for road.dat
   File roadFile = new File("road.dat"); 
   Scanner roadFileReader = new Scanner(roadFile);    

   LinkedListGraph graph = new LinkedListGraph(numberOfCities); 
   graph.setVerticesAndLabels(cities);
   
   while(roadFileReader.hasNext())
   {
	String[] roadFields = roadFileReader.nextLine().split(" ");
    graph.addEdge(roadFields[0], findCity(roadFields[1], cities), Integer.parseInt(roadFields[2]));	
   }
   
   menu(cities, graph);
   
 }
 
 //method: menu
 //This method takes in the cities array and graph object created in the main method.
 //The menu prints out a prompt asking the user to enter a letter for the option they desire. 
 //The first option (Q) asks for a city code, finds the City object with that city code, determines if it is an object that exists,  
 //and then prints out the entire city record using the City class's toString method
 //The second option (I) asks for two city codes and an integer value for the distance. It checks for the existence of the cities 
 //and it handles an exception where a road exists from the source city to the target city. Finally, it uses the graph object's addEdge method
 //to insert the road. A message is displayed informing the user of their action. The third option (R) is similar to the second option (I) except that
 //the exception occurs when a road doesn't exist and it uses the graph object's deleteEdge method to delete a road. Finally, the fourth and fifth option
 //are self-explanatory. 
 public static void menu(City[] cities, LinkedListGraph graph)
 {
   //variables for menu 
   String userIn = ""; 
   Scanner kb = new Scanner(System.in); 
   
   //menu driven system 
   //prompt
   System.out.println("\nCommand?\n" +
        "Q Query the city information by entering the city code.\n" +
		"I Insert a road by entering two city codes and distance.\n" +
		"R Remove an existing road by entering two city codes.\n" +
		"H Display this message.\n" +
		"E Exit.");
		
   userIn = kb.nextLine(); 
   while(!userIn.equalsIgnoreCase("E")) 
   {
	if(userIn.equalsIgnoreCase("Q"))
	{
	  System.out.print("City code: "); 
	  userIn = kb.nextLine(); 
	  
	  City record = findCity(userIn, cities); 
	  
	  if(record == null) 
		System.out.println("Sorry, this city does not exist."); 
	  else 
		System.out.println(record);
	}
	
	else if(userIn.equalsIgnoreCase("I"))
	{
	  System.out.print("City codes and distance: "); 
      userIn = kb.nextLine(); 
	  
      String[] roadFields = userIn.split(" "); 
	   
	  //check for existence of cities
	  City from = findCity(roadFields[0], cities); 
	  City to = findCity(roadFields[1], cities);  
	  
	  if(from==null||to==null)
      {		  
		System.out.println("Sorry, these city codes are not valid.");
      }		
	  else
	  {
		try
		{
		  graph.addEdge(roadFields[0], to, Integer.parseInt(roadFields[2])); 
		  CityNode destination = graph.searchDestination(roadFields[0], to); 
		  System.out.println("You have inserted a road from " + from.getCityFullName() + " to " + to.getCityFullName() +  " with a distance of " + destination.getDistance() + ".");
		}
		catch(IllegalArgumentException e)
		{
		  System.out.println("The road from " +  from.getCityFullName() + " to " +  to.getCityFullName() + " already exists."); 
		}
		finally
		{
		  System.out.print("Command? ");
		  userIn = kb.nextLine(); 
		  continue;
		}
      }	  
	}
	
	else if(userIn.equalsIgnoreCase("R"))
	{
	  System.out.print("City codes: "); 
	  userIn = kb.nextLine(); 
	  
	  String[] codes = userIn.split(" "); 
	  
	  //check for existence of cities
	  City from = findCity(codes[0], cities); 
	  City to = findCity(codes[1], cities);  
	  
	  if(from==null||to==null)
      {		  
		System.out.println("Sorry, these city codes are not valid.");
      }
	  else
	  {
	   try
	   {
		  graph.deleteEdge(codes[0], codes[1]);
		  System.out.println("You have deleted a road from " + from.getCityFullName() + " to " + to.getCityFullName() + "."); 	
	   }
	   catch(IllegalArgumentException e) 
	   {
		  System.out.println("The road from " +  from.getCityFullName() + " to " +  to.getCityFullName() + " doesn\'t exist.");
	   }
	   finally
	   {
		  System.out.print("Command? ");
		  userIn = kb.nextLine(); 
		  continue;
	   }
	  }
	}
	else if(userIn.equalsIgnoreCase("H"))
	{
     System.out.println("\nCommand?\n" +
        "Q Query the city information by entering the city code.\n" +
		"I Insert a road by entering two city codes and distance.\n" +
		"R Remove an existing road by entering two city codes.\n" +
		"H Display this message.\n" +
		"E Exit.");
		
     userIn = kb.nextLine();
     continue;	 
	}
	
	System.out.print("Command? "); 
	userIn = kb.nextLine();

   }
   
   if(userIn.equalsIgnoreCase("E"))
	System.exit(0); 
 }
 
 //method: getNumberOfLines
 //This method simply counts the number of lines in a file. 
 //It is used in order to initialize the cities array in the main method
 public static int getNumberOfLines(File file) throws IOException
 {
	Scanner counter = new Scanner(file); 
	int count = 0; 
	
	while(counter.hasNext())
    {
	  counter.nextLine(); 
	  count++; 
    }
	
	return count; 
 }
 
 //method: findCity
 //This method has two arguments: cityCode and the cities array 
 //The method will search for the cityCode in the cities array and return 
 //the appropriate City object. 
 public static City findCity(String cityCode, City[] cities)
 {
	for(int i = 0; i < cities.length; i++) 
    {
	  if(cities[i].getCityCode().equalsIgnoreCase(cityCode))
		return cities[i];
	}
	
	return null; 
 }


}