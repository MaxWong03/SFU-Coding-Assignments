/**
 * @(#)OlympicMedals.java
 *
 *
 * @author 
 * @version 1.00 2013/4/5
 */

import java.util.ArrayList;
import java.util.Random;


public class OlympicMedals {

    static ArrayList<String> country_name_used = new ArrayList<String>();
    String [] medal_table;
    
    public String[] Olympic_Medals(String [] result) 
  	{	
  		
  		for (int i = 0; i < result.length; i++)  //generate an arraylist that takes in all the country name that will be on the table (non repeated)
  		{	
  			int name_repeated = 0;
  			for(int j = 0; j<country_name_used.size(); j++)
  			{
  				if(result[i].substring(0,3).equals(country_name_used.get(j)))
					name_repeated +=1;
				else
					name_repeated +=0;					  				
  			}
  			if(name_repeated ==0)
  			{
  				country_name_used.add(result[i].substring(0,3));
  			}
  			
  			name_repeated =0;
  			for(int j = 0; j<country_name_used.size(); j++)
  			{
  				if(result[i].substring(4,7).equals(country_name_used.get(j)))
					name_repeated +=1;
				else
					name_repeated +=0;					  				
  			}
  			if(name_repeated ==0)
  			{
  				country_name_used.add(result[i].substring(4,7));
  			}
  			
  			name_repeated =0;
  			for(int j = 0; j<country_name_used.size(); j++)
  			{
  				if(result[i].substring(8,11).equals(country_name_used.get(j)))
					name_repeated +=1;
				else
					name_repeated +=0;					  				
  			}
  			if(name_repeated ==0)
  			{
  				country_name_used.add(result[i].substring(8,11));
  			}
  		}
  		
  		
  		medal_table = new String [country_name_used.size()]; //medal_table`s size will = how many countries are there
  	
  		for (int i =0; i < medal_table.length; i ++) //generate an array of countries name  (non repeated) 
  		{
  			medal_table[i] = country_name_used.get(i);
  		}
  		
  		
  		country_name_used.clear();
  	
  		String country_name = "";
  		String gold = "" ;
  		String silver = "";
  		String brown = "";
  	
  		for (int i =0; i < result.length; i++)     //count the number of times an country`s name have appear and calculate how many golds, silver, and bornze it has according to the position in which the name appears
  			{	
  				int repeat = 0;
  				country_name = result[i].substring(0,3);
  				for (int j = 0; j < country_name_used.size(); j ++)
  				{
  					if (country_name.equals(country_name_used.get(j)))
  						repeat +=1;
  					else
  						repeat +=0;
  				}
  				
  				if (repeat ==0)
  				{
  					country_name_used.add(country_name);
  					int g = 0;
  					int s = 0;
  					int b = 0;
  					for (int k =0; k < result.length; k++)
  					{
  						if(country_name.equals(result[k].substring(0,3)))
  							g +=1;
  						if(country_name.equals(result[k].substring(4,7)))
  							s +=1;
  						if(country_name.equals(result[k].substring(8,11)))
  							b +=1;	
  					
  					}
  						 gold = Integer.toString(g);
  						 silver = Integer.toString(s);
  						 brown = Integer.toString(b);
  					String olympic_result = " " + gold + " " + silver + " " + brown + " " ;
  					for (int x = 0; x < medal_table.length; x++)
  					{
  						if(country_name.equals(medal_table[x]))
  							medal_table[x] += olympic_result;
  					}
  				}
  				
  				
  				country_name = result[i].substring(4,7);
  				
  				repeat = 0;
  			
  				for (int j = 0; j < country_name_used.size(); j ++)
  				{
  					if (country_name.equals(country_name_used.get(j)))
  						repeat +=1;
  					else
  						repeat +=0;
  				}
  				
  				if (repeat ==0)
  				{
  					country_name_used.add(country_name);
  					int g = 0;
  					int s = 0;
  					int b = 0;
  					for (int k =0; k < result.length; k++)
  					{
  						if(country_name.equals(result[k].substring(0,3)))
  							g +=1;
  						if(country_name.equals(result[k].substring(4,7)))
  							s +=1;
  						if(country_name.equals(result[k].substring(8,11)))
  							b +=1;
  						
  					}
  						 gold = Integer.toString(g);
  						 silver = Integer.toString(s);
  						 brown = Integer.toString(b);
  						String olympic_result = " " + gold + " " + silver + " " + brown + " " ;
  					for (int x = 0; x < medal_table.length; x++)
  					{
  						if(country_name.equals(medal_table[x]))
  							medal_table[x] += olympic_result;
  					}
  				}
  					
  				
  				country_name = result[i].substring(8,11);
  				
  				repeat = 0;
  				
  				for (int j = 0; j < country_name_used.size() ;j ++)
  				{
  					
  					if (country_name.equals(country_name_used.get(j)))
  						repeat +=1;
  					else
  						repeat +=0;
  				}
  				if (repeat ==0)
  				{
  					country_name_used.add(country_name);
  					int g = 0;
  					int s = 0;
  					int b = 0;
  					for (int k =0; k < result.length; k++)
  					{
  						if(country_name.equals(result[k].substring(0,3)))
  							g +=1;
  						if(country_name.equals(result[k].substring(4,7)))
  							s +=1;
  						if(country_name.equals(result[k].substring(8,11)))
  							b +=1;
  						
  					}
  						 gold = Integer.toString(g);
  						 silver = Integer.toString(s);
  						 brown = Integer.toString(b);
  						String olympic_result = " " + gold + " " + silver + " " + brown + " " ;
  					for (int x = 0; x < medal_table.length; x++)
  					{
  						if(country_name.equals(medal_table[x]))
  							medal_table[x] += olympic_result;
  					}
  				}
  					
  				

  				}
  		
  		
  		quicksort_bygold(medal_table,0, medal_table.length-1); //use quick sort to sort by gold
  		bubblesort_bysilver(medal_table);                      //then use bubble sort to sort by silver 
  		mergesort_bybronze(medal_table,0, medal_table.length-1); //then use merge sort to sort by bronze 
  		insertionsort_bycode(medal_table);                       // then use insertion sort to sort by coutry code 
  	
  		for (String medal : medal_table)                         //out put the medal table
  		{
  			System.out.println(medal);
  		}
  		
  		
  		
  		return medal_table;
  	}  
    
    private String EventResult() //returns a String representing the result of a discipline
 
	{
		 String [] country_code = {"AGO", "ARG", "AUS", "HKG", "HTI", "HUN", "CAN", "CHN", "USA", "JPN","KOR"};
		 Random generator = new Random();
		 int y = generator.nextInt(10)+1; 
		 String result = country_code[y];
		 return result;
	
	}
	
	public String[] OlympicResults(int n) //return an array of n Strings where each String represents the result of an event 
	{	
		String [] result = new String[n];
		String medal;
		for(int i =0; i< result.length; i++)
		{
			medal = EventResult() + " " + EventResult() + " " + EventResult();
			result[i] = medal;
			
		}
		for (String country : result)
			System.out.println(country);
		return result;	
		
	}
	
	private static int  partition (String [] table, int min, int max) //partition method 
	{                                                  
		String parition_element = table[min];
		int left = min;
		int right = max;
		
		while(left < right)
		{
		while ((table[right].substring(4,5)).compareTo(parition_element.substring(4,5)) <= 0 && left<right)
			right--;
		while ((table[left].substring(4,5)).compareTo(parition_element.substring(4,5)) > 0 )
			left++;
	
	
		
		if(left<right)
		{
			swap(table,left,right);
		
		}
		
	
		}
		
		swap(table,min,right);
		return right;
	}
	private static void quicksort_bygold(String [] table , int min, int max) //quick sorting
	{
		int pivot;
		if(min<max)
		{
			pivot = partition(table, min, max);
			quicksort_bygold(table,min,pivot-1);
			quicksort_bygold(table,pivot+1,max);
		}
	}
	
   private static void swap (String[] data, int index1, int index2) //swap method
   {
       String temp = data[index1];
       data[index1] = data[index2];
       data[index2] = temp;
   }
	
	private static void bubblesort_bysilver(String [] table) //bubble sorting
	{
      int position, scan;
      for (position = table.length - 1; position >= 0; position--)
      {
         for (scan = 0; scan <= position - 1; scan++)
         {
         	if((table[scan].substring(4,5).compareTo((table[scan+1]).substring(4,5)) ==0)) //(given that golds are the same)
         	{
         		if ((table[scan].substring(6,7)).compareTo((table[scan+1]).substring(6,7)) <= 0)
             		  swap (table, scan, scan+1);
         	}
         }
      }
   }
   
   private static void mergesort_bybronze(String [] table, int min, int max) // merge sorting 
  	{
      if (min < max)
      {
         int mid = (min + max) / 2;
         mergesort_bybronze (table, min, mid);
         mergesort_bybronze (table, mid+1, max);
         merge (table, min, mid, max);
      }
   }

private static void merge (String[] data, int first, int mid, int last) //merge method needed for merge sorting
   {
      String[] temp = new String[data.length];

      int first1 = first, last1 = mid;  
      int first2 = mid+1, last2 = last;  
      int index = first1;  
      while (first1 <= last1 && first2 <= last2)
      {
      	if(((data[first1].substring(4,5)).compareTo(data[first2].substring(4,5))==0)&&((data[first1].substring(6,7)).compareTo(data[first2].substring(6,7))==0))//given that golds and silver are the same
      	{
      	 if ((data[first1].substring(8,9)).compareTo(data[first2].substring(8,9)) >= 0)
         {
            temp[index] = data[first1];
            first1++;
          }
          else
          {
             temp[index] = data[first2];
             first2++;
          }
      	}
      	else 
      	{
      		temp[index] = data[first1];
      		first1++;
      	}
          index++;
 
      	
      }
      while (first1 <= last1)
      {
         temp[index] = data[first1];
         first1++;
         index++;
      }

      while (first2 <= last2)
      {
         temp[index] = data[first2];
         first2++;
         index++;
      }

     
      for (index = first; index <= last; index++)
         data[index] = temp[index];
     
     
     }
     
     private static void insertionsort_bycode(String [] table) //insertion sorting 
     {
     	for (int index = 1; index < table.length; index++)
      {
         String key = table[index];
         int position = index;

         while(position >0 && ((table[position-1].substring(0,3)).compareTo(key.substring(0,3)) > 0)
         	&& (((table[position-1].substring(4,5)).compareTo(key.substring(4,5)) ==0) && ((table[position-1].substring(6,7)).compareTo(key.substring(6,7))==0) && 
         		((table[position-1].substring(8,9)).compareTo(key.substring(8,9))==0))) //given that golds and silver and bronze are the same
         {
         	
         		table[position] = table[position-1];
         		position--;
         
         }
         
         table[position] = key;
      }

     }
     
}