/**
 * @(#)QueueException.java
 *
 *
 * @author 
 * @version 1.00 2013/7/7
 */

package exceptions;

//Class Description:
//This exception is thrown when the operation on the queue is not succesful 

public class QueueException extends java.lang.Exception 
{
        
    //Default constructor
    public QueueException() 
    {
    }//end of constructor
    
   	// Parameterized constructor
    public QueueException(String message) 
    {
        super(message);
    }//end of constructor
}//end of QueueExcpetion class