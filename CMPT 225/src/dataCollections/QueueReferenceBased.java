/**
 * @(#)QueueReferenceBased.java
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
//The data collection uses a reference based implementation to implement the Queue
//The data collection has the First in First out characteristic
//It allows user to enqueue (add an element) to the queue (only at the end of the queue)
//It allows user to dequeue (retrieve and remove an element) from the queue  (only at the front of the queue)
//It allows user to look at (peek) the element that is at the top of the queue (only at the front of the queue)
//It allows user to remove all elements from the queue
//It allows user to see whether the queue is empty

//Class Invariants:
//The data collection uses a reference based implementation
//First in First out / Last in Last out 

package dataCollections;

import interfaces.*;
import exceptions.*;

public class QueueReferenceBased implements MyQueueInterface{
	
	private Node lastNode;
	
	//Default constructor
    public QueueReferenceBased() 
    {
    	lastNode = null;
    }//end of default constructor
    
    public boolean isEmpty()
    //Description: Determine whether the Queue is empty, return true if the queue is empty
    {
    	return lastNode == null;
    }//end of isEmpty
    
    public void dequeueAll()
    //Description: Remove all the element in the Queue
    //Postcondition: The queue is empty
    {
    	lastNode = null;
    }//end of dequeueAll
    
    public void enqueue(Queueable Element) throws QueueException
    //Description: Add an Element to the Queue
    //Precondition: The element is "Queueable"
    //Postcondition: The element is added to the end of the Queue
    //Exceptions: Throws QueueExcpetion when the operation is not succesful
    {
    	Node newNode = new Node(Element);
    	//insert the new node
    	if(isEmpty())
    	{
    		//insertion into empty queue
    		newNode.setNext(newNode);
    	}
    	else
    	{
    		//insertion into nonempty queue
    		newNode.setNext(lastNode.getNext());
    		lastNode.setNext(newNode);
    	}//end if
    	lastNode = newNode;
    }//end enqueue
    
    public Queueable dequeue() throws EmptyQueueException,QueueException
    //Description: Retrieve and delete the element that is at the front of the queue
    //Precondition: The queue is not empty
    //Postcondition: The size of the queue shrinks by one, the elemenet at the front of the queue is deleted and returned to the user.
    //Exceptions: Throws EmptyQueueException if the queue is empty
    //            Throws QueueException if the operation is not succesful
    {
    	if(!isEmpty())
    	{
    		//the queue is not empty; remove the element at front
    		Node firstNode = lastNode.getNext();
    		if(firstNode == lastNode) //special case, only one node in the queue
    			lastNode = null;
    		else
    			lastNode.setNext(firstNode.getNext());
    	return firstNode.getElement();
    	}
    	else //the queue is empty, throws an excpetion
    		throw new EmptyQueueException ("The queue is Empty.");
    }//end dequeue 
    
    public Queueable peek() throws EmptyQueueException
    //Description: Retrieve the element that is at the front of the queue without changing the queue
    //Precondition:The queue is not empty.
    //Postcondition: The element at the front of the queue is returned, the queue is unchanged
    //Exception: Throws EmptyQueueException if the Queue is emtpy
    //           Throws QueueException if the operation fails
    {
    	if(!isEmpty())
    	{
    		//the queue is not empty, return the first element in the queue
    		Node firstNode = lastNode.getNext();
    		return firstNode.getElement();
    	}
    	else
    		throw new EmptyQueueException("The Queue is empty.");
    }//end peek

}//end QueueReferenceBased