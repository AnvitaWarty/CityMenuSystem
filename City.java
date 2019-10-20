/*
  class: City 
  The City class has five String objects as private fields. 
  The City constructor takes in a String array and extracts 
  important information for the array and stores them into 
  the proper data fields. To differentiate the full name 
  of the city from the following fields of population and elevation, 
  a for loop is used to step through the String array. If the next 
  item contains a letter as its first character, it is included in the
  full name field. There are only two getters that are required for the rest of the program.
  The toString method is used to display the full record for the city.
*/

public class City 
{
	private String city_number, city_code, city_full_name, population, elevation; 
	
	//constructor
	City(String[] fields)
	{
	 int i = 2; 
	 
	 city_number = fields[0]; 
	 city_code = fields[1]; 
	 
	 city_full_name = "";
	 for(;i<fields.length && Character.isLetter(fields[i].charAt(0)); i++)
	 { 
		city_full_name+=fields[i] + " ";
	 }
	 
	 city_full_name = city_full_name.trim(); 
	 population = fields[fields.length - 2]; 
	 elevation = fields[fields.length - 1]; 
	 
	}
	
	//getters
	public String getCityCode()
	{
	 return city_code;
	}
	
	public String getCityFullName()
	{
	 return city_full_name.toUpperCase();
	}

	public String toString()
	{
	 String str = city_number + " " + city_code + " " + city_full_name.toUpperCase() + " " + population + " " + elevation; 
	 return str; 
	}
	
	
	
	
}