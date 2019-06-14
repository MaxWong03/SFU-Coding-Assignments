/**
 * @(#)customer.java
 *
 *
 * @author 
 * @version 1.00 2013/7/10
 */

package app;
import interfaces.*;

public class customer implements Queueable{

        //data members
        private String arrivalTime;
	private String transcationTime;
    
    //method members    
        
    //default constructor
    public customer() 
    {
    }//end of default constructor
    
    //parametrized constructor
    public customer(String arrivalFile)
    {
    	arrivalTime = "";
    	transcationTime ="";
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
    		transcationTime += arrivalFile.substring(j);
    	}
    }//end of constructor
    
    //Description:get the arrival time of an event
    public int getArrivalTime()
    {
    	int arrival = Integer.parseInt(arrivalTime);
    	return arrival;
    }//end getArrivalTime   
    
    
    //Description;get the transactiontime of an event 
    public int getTransactiontime()
    {
    	int transaction = Integer.parseInt(transcationTime);
    	return transaction;
    }//end getTransactionTime
}//end of class customer