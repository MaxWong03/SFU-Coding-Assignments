/**
 * @(#)EmptyQueueException.java
 *
 *
 * @author 
 * @version 1.00 2013/7/7
 */
 

package exceptions; 

//Class Description:
//This exception is thrown when the user trying to dequeue from an empty queue

public class EmptyQueueException extends java.lang.Exception {
        
   	//Default constructor
    public EmptyQueueException() 
    {
    }//end of constructor
    
	// Parameterized constructor
    public EmptyQueueException(String message) 
    {
        super(message);
    }//end of constructor

}//end of EmptyQueueExcpetion class