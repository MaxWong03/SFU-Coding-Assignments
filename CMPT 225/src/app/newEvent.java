
/**
 * @(#)newEvent.java
 *
 *
 * @author 
 * @version 1.00 2013/7/9
 */
package app;

import interfaces.*;

public class newEvent implements Listable {
        //data members
	private String arrivalTime;
	private String transactionTime;
	private int departureTime =0;
	private String arrivalData;
    
    //method members    
        
    //default constructor
    public newEvent() 
    {
    	
    }//end of default constructor
    
    //parameterized constructor
     public newEvent(String arrivalFile)
    {
        arrivalData = arrivalFile; 
    	arrivalTime = "";
    	transactionTime ="";
    	int length = arrivalFile.length();
    	int space_position =0;
    	boolean spaceNotMet = true;
    	//get the arrivalTime and transaction time, using the fact that there is a space between the two value
    	for (int i =1; i <= length && spaceNotMet; i++)
    	{
    		if(((arrivalFile.substring(i-1,i)).equals(" ")) == false)
    		{	
    			arrivalTime += arrivalFile.substring(i-1,i);
    		}
    		else
    		{
    			spaceNotMet = false;
    			space_position = i-1;
    		}
    	}
    	for (int j = space_position+1; j <=length; j++)
    	{
    		transactionTime += arrivalFile.substring(j);
    	}
    
    }//end of constructor
    
     //parameterized constructor
    public newEvent(int dpTime)
    {
    	departureTime = dpTime;
    }//end of constructor
    
    
    //Description: indicate whether the event is an arrival event or not
    public boolean isArrival()
    {
    	return (arrivalTime != null);
    }//end isArrival
    
    //Description: indicate whether the event is a departure event or not
    public boolean isDepature()
    {
    	return (departureTime != 0);
    }//end isDeparture
    
    //Description: toString method that tells the user what kind of event it is
     public String toString ()
    {   String result = "";
        if(isArrival())
            result = "This is an arrival event";
    	else
            result = "This is a departure event";
        return result;
    	
    }//end toString
    
     
     //Description: compare whether this event is same as the listable object
     //             return 0 if two object is the same
     //             return 1 otherwise
    public int compareTo(Listable otherObject)
    {
    	if (getTimeOfEvent() == ((newEvent)otherObject).getTimeOfEvent())
    	{
    		if(isArrival()== ((newEvent)otherObject).isArrival())
    			return 0;
    		else if(isDepature()==((newEvent)otherObject).isDepature())
    			return 0;
    		else 
    			return 1;
    	}
    	else
    		return 1;
    }//end of compareTo
    
    //Description: return the time of a specific event
    public int getTimeOfEvent ()
    {	
    	if(isArrival())
    	{	
    		int arrival = Integer.parseInt(arrivalTime);
    		return arrival;
    	}
    	else 
    		return departureTime;
    }//end getTimeOfEvent
    
    
    //Description: get the transaction time of a event
    public int getTransactionTime()
    {
    	int transaction = Integer.parseInt(transactionTime);
    	return transaction;
    }//end of getTransactionTime
    
    //Description: get the arrival data
	public String getArrivalData()
	{
		return arrivalData;
	}//end of getArrivalData
}//end of class newEvent