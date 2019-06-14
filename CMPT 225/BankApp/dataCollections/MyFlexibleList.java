/**
 * Filename: MyFlexibleList.java
 * author: Max (Tsz Keung) Wong
 * StdNumber: 301165131
 * Lab Number: D-106
 * Date: 01/06/2013
 * Revised on: 04/06/2013
 */

//Class Description: 
//Implements: MyListInterface
//Import: interfaces, exceptions
//package: dataCollections 
//Takes in numbers of Listable objects and provides varieties of methods for user to manipulate the data collection. 
//It allows user to insert a Listable object in the data collection.
//It allows user to delete a Listable object from the data collection.
//It allows user to retrieve a Listable object from the data collection.
//It allows user to know the number of data in the data collection and display them.
//It allows user to delete all the datas in the data collection.
//Since there were no requirements stating that the Listable objects needed 
//to be kept in a certain sort order, the data collection (collection of Listable objects) is therefore unsorted.
 

// Class Invariants:
// The data collection is never full (i.e user can always add a Listable object to the list)
// The data collection only holds Listable objects.
// Number of data is never less than 0. 

/**
 * Filename: MyFlexiblelist.java
 * Author: AL
 * Date of creation: May 21, 2007
 * Revised: June 2013
 * Description: Value-oriented data collection List class (ADT) containing Listable elements.
 * Class invariant: Elements are unsorted and unique (no duplication allowed).
 */

package dataCollections;

import interfaces.*;
import exceptions.*;
	
public class MyFlexibleList implements MyListInterface {

	// data members
	private Node theCollection;       // collection of Listable elements - "head"
	private int numberOfElements;     // current number of elements
	
	// Default constructor
	public MyFlexibleList( )
	{
		theCollection = null;
		numberOfElements = 0;

	} // end default constructor

	// methods members
	public int size( )
	// Description: Returns the number of elements currently stored in the list.
	// Postcondition: Returns 0 if empty otherwise returns the number of elements.
	{
		return numberOfElements;

	} // end of size

	
	public void deleteAll( )
	// Description: Deletes all the elements from the list.
	// Postcondition: List is empty.
	{ 
		numberOfElements = 0;
		theCollection = null;
		    
	} // end of deleteAll
	
	public void insert( Listable element ) throws ElementAlreadyInListException
	// Description: Inserts an element into the list.
	// Precondition: "element" is not already in the list. This is to say: no duplication allowed.
	// Postcondition: "element" has been added to the list.
	// Exception: Throws an ElementAlreadyInListException if "element" is already in the list.
	{	
		Node current = theCollection;
		Node previous = null;
		
		// if the list empty
		if ( numberOfElements <= 0 )
			theCollection = new Node( element, null );			
		else {
			// Verify precondition: If "element" already in list -> throw exception
			while ( current != null ) {
		       if ( current.getElement().compareTo(element) == 0 ) {
				   throw new ElementAlreadyInListException( "This element is already in the list." );			
	           } else // step forward one Node
				   previous = current;
				   current = current.getNext( );		   
			}

			// Insert the new element at the end of the List
			// Note: element can be inserted anywhere in the List
			previous.setNext(new Node( element, null ));
		}
		
		// and increment the number of elements currently in the data structure by 1.
		numberOfElements++;		

		return;
				
	} // end of insert

	public Listable retrieve( Listable element ) throws EmptyListException, ElementNotInListException	
	// Description: Returns this element.
	// Precondition: List is not empty.
	// Postcondition: If "element" is found in the list, it is returned but not deleted from the list.
	//	              If "element" is not found in the list, an exception is thrown.
	// Exception: Throws an EmptyListException if list is empty.
	// Exception: Throws an ElementNotInListException if "element" is not found in the list.
	{
		if (size()<= 0) //the list is empty, throw an exception
		{	
			throw new EmptyListException("The list is empty.");
		}
		Node current = theCollection;
		Node previous = null;
		Listable returnElement = null;
		boolean elementNotFound = true;
		while (current!= null && elementNotFound)
		{
			
			if (current.getElement().compareTo(element) == 0)
			{
				elementNotFound = false;
				returnElement = current.getElement();
			}
			else			
				previous = current;
				current = current.getNext();
		}
		if(elementNotFound)//the element is not in the data collection, throw an exception
		{	
			throw new ElementNotInListException("The element is not in the list.");
		}
		
		else //return the element if it is in the list and the list is not empty
		{	
			
			return returnElement;
		}
		
	} // end of retrieve

	
	public void delete( Listable element ) throws EmptyListException, ElementNotInListException
	// Description: Deletes this element from the list.
	// Precondition: List is not empty.
	// Postcondition: If "element" is found in the list, it is deleted from the list. 
	//	               If "element" is not found in the list, an exception is thrown.
	// Exception: Throws an EmptyListException if list is empty.
	// Exception: Throws an ElementNotInListException if "element" is not found in the list.
    {
    	if (size()<= 0) //the list is empty, throw an exception
		{	
			throw new EmptyListException("The list is empty.");
		}
		Node current = theCollection;
		Node previous = null;
		boolean elementNotFound = true;
		while (current!= null)
		{
			
			if (current.getElement().compareTo(element) == 0)
			{
				elementNotFound = false;
			}
			else			
				previous = current;
				current = current.getNext();
		}
		if(elementNotFound)//the element is not in the data collection, throw an exception
		{	
			throw new ElementNotInListException("The element is not in the list.");
		}
		
		if(elementNotFound == false)
		{
			current = theCollection;
			previous = null;
			while (current != null ) 
			{
				//we can delete by setting the node that is previous to the node we are trying to delete
				//reference to the node that is next toe the node we are trying to delete
		       if  ((current.getElement().compareTo(element) == 0)&&(previous!=null)) 
		       {
					previous.setNext(current.getNext());
					numberOfElements--;
	           } 
	           //the above deletion scheme does not work if the node to be deleted is the first node in the list
	           //we need to implement a different scheme for this special case
	           //we can delete by making the head reference bypasses the old first node
	           else if((current.getElement().compareTo(element) == 0)&&(previous == null))
	           {
	           	    theCollection = theCollection.getNext();
	           	    numberOfElements--;
	           } 
	           else // step forward one Node     		
	           		previous = current;
				  	current = current.getNext( );
	           		   
			
			}
		}
	} // end of delete
	

	

	
	public String toString( )
	// Description: Outputs all elements.
	// Postcondition: A string containing all elements (their information) is returned.
	{
		Node current = theCollection;
		
		String theString = "";
		if ( numberOfElements <= 0 )
			theString = "\nThere are no elements!";        
		else {
			while ( current != null ) {
				theString += "\n Element => " + current.getElement().toString( );
			    // Go to next node
				current = current.getNext();
			}
		}
		return theString;

	} // end of toString 
	

	
} // end of MyFlexibleList class