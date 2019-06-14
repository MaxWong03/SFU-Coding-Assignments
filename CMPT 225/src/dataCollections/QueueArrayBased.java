/**
 * @(#)QueueArrayBased.java
 *
 *
 * @author 
 * @version 1.00 2013/7/7
 */
 
//Class Description: 
//Implements: MyQueueInterface
//Import: interfaces, exceptions
//Package: dataCollection
//Stores number of Queueable elements in the data collection(Queue) and provide varities of method for users to manipulate the queue
//The data collection uses a index based implementation to implement the Queue
//The data collection has the First in First out characteristic
//It allows user to enqueue (add an element) to the queue (only at the end of the queue)
//It allows user to dequeue (retrieve and remove an element) from the queue  (only at the front of the queue)
//It allows user to look at (peek) the element that is at the top of the queue (only at the front of the queue)
//It allows user to remove all elements from the queue
//It allows user to see whether the queue is empty

//Class Invariants:
//The data collection uses a index based implementation
//First in First out / Last in Last out 

package dataCollections;

import interfaces.*;
import exceptions.*;

public class QueueArrayBased implements MyQueueInterface{
	private final int Max_Queue = 50; //max size of queue
	private Queueable[] items;
	private int front, back, count;
	
	//Default constructor
    public QueueArrayBased()
    {
    	items = new Queueable[Max_Queue];
    	front =0;
    	back = Max_Queue-1;
    	count =0;
    }//end of default constructor
    
   	public boolean isEmpty()
   	//Description: Determine whether the Queue is empty, return true if the queue is empty
   	{
   		return count ==0;
   	}//end of isEmpty
   	
   	public boolean isFull()
   	//Description: Determine whether the Queue is full, return true if the queue is full
   	{
   		return count == Max_Queue;
   	}//end isFull
   	
   	public void enqueue(Queueable Element) throws QueueException
    //Description: Add an Element to the Queue
    //Precondition: The element is "Queueable"
    //Postcondition: The element is added to the end of the Queue
    //Exceptions: Throws QueueExcpetion when the operation is not succesful
   	{
   		if(!isFull())
   		{
   			back = (back+1) % (Max_Queue);
   			items[back] = Element;
   			++count;
   		}
   		else
   			throw new QueueException("The queue is full.");
   	}//end enqueue
   	
   	public Queueable dequeue() throws EmptyQueueException,QueueException
   	//Description: Retrieve and delete the element that is at the front of the queue
    //Precondition: The queue is not empty
    //Postcondition: The size of the queue shrinks by one, the elemenet at the front of the queue is deleted and returned to the user.
    //Exceptions: Throws EmptyQueueException if the queue is empty
    //            Throws QueueException if the operation is not succesful
   	{
   		if(!isEmpty())
   		//the queue is not empty, retireve and delete the element at the front
   		{
   			Queueable queueFront = items[front];
   			front = (front+1) % (Max_Queue);
   			--count;
   			return queueFront;
   		}
   		
   		else
   			throw new EmptyQueueException("The queue is empty.");
    }//end dequeue
    
    public void dequeueAll()
    //Description: Remove all the element in the Queue
    //Postcondition: The queue is empty
    {
    	items = new Queueable[Max_Queue];
    	front =0;
    	back = Max_Queue-1;
    	count =0;
    }//end dequeueAll
    
    public Queueable peek() throws EmptyQueueException
    //Description: Retrieve the element that is at the front of the queue without changing the queue
    //Precondition:The queue is not empty.
    //Postcondition: The element at the front of the queue is returned, the queue is unchanged
    //Exception: Throws EmptyQueueException if the Queue is emtpy
    //           Throws QueueException if the operation fails
    {
    	if(!isEmpty())
    	{
    		//queue is not empty, retrieve the element at the front
    		return items[front];
    	}
    	else
    		throw new EmptyQueueException("The queue is empty.");
    }//end peek
}//end QueueArrayBased