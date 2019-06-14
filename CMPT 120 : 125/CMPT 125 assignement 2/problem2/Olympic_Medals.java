/**
 * @(#)Olympic_Medals.java
 *
 *
 * @author 
 * @version 1.00 2013/4/4
 */



public class Olympic_Medals {
        


    public static void main(String[] args) 
    {	
    	
    	OlympicMedals medals = new OlympicMedals(); 
    	String [] results = medals.OlympicResults(5); 
    	OlympicMedals table = new OlympicMedals();
    	String [] medal_table = table.Olympic_Medals(results);
    	
        
        
    }
}
