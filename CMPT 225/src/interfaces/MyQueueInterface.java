/**
 * @(#)MyQueueInterface.java
 *
 *
 * @author 
 * @version 1.00 2013/7/7
 */


//Class invariants : FIFO or LILO

package interfaces;

import exceptions.EmptyQueueException;
import exceptions.*;

public interface MyQueueInterface {
    
    public void enqueue(Queueable Element) throws QueueException;
    	//Description: Add an Element to the queue
    	//Exceptions: Throws QueueException when the operation is not successful
    
    public Queueable dequeue() throws EmptyQueueException, QueueException;
    	//Description: Removes and return an element from the queue.
    	//Precondition: Queue is not empty
    	//Exceptions: Throws EmptyQueueException if the queue is empty
    	//            Throws QueueException when the operation is not successful
    
    public void dequeueAll();
    	//Description: Removes all elements from the queue
    	//Postcondition: The queue is empty, isEmpty() returns ture
    
    public Queueable peek() throws EmptyQueueException;
    	//Description: Retrieves an element from the queue and returns it
    	//Precondition: The queue is not emtpy
    	//Postcondition: The element is returned, the queue is unchanged
    	//Exception: Throws EmptyQueueException if the queue is empty
    	//           Throws QueueException when the operation is not succesful 
    
    public boolean isEmpty();
    	//Description: Returns true if the queue is empty, otherwise return false    
   	
    
}