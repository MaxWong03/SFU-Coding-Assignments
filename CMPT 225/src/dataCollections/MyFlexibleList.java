/**
 * Filename: MyFlexibleList.java
 * author: AL
 * StdNumber: blahblahblah
 * Lab Number: blahbahblah
 * Date: May 13, 2007
 * Revision History: June 2013
 * Description: Value-oriented data collection List class (ADT) containing Listable elements.
 * Class invariant: Elements are unsorted and unique (no duplication allowed).
 */

package dataCollections;

import interfaces.*;
import exceptions.*;
import app.newEvent;


public class MyFlexibleList implements MyListInterface {

    // static constants
	final static int INITIAL_SIZE_OF_COLLECTION = 3;  // To be used if no initial size is given

    // data members
	private Listable[] theCollection; // collection of Listable elements
	private int numberOfElements;     // current number of elements
	private int currentArraySize;     // used by the expansion process
	
	// Default constructor
	public MyFlexibleList( )
	{
		theCollection = new Listable[INITIAL_SIZE_OF_COLLECTION]; 
		numberOfElements = 0;
		currentArraySize = INITIAL_SIZE_OF_COLLECTION;

	} // end default constructor

	public MyFlexibleList( int initialSize )
	{
		theCollection = new Listable[initialSize]; 
		numberOfElements = 0;
		currentArraySize = initialSize;

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
		    
	} // end of deleteAll
	
	
	public void insert( Listable element ) throws ElementAlreadyInListException
	// Description: Inserts an element into the list.
	// Precondition: "element" is not already in the list. This is to say: no duplication allowed.
	// Postcondition: "element" has been added to the list.
	// Exception: Throws an ElementAlreadyInListException if "element" is already in the list.
	{
		// Verify precondition: If "element" already in list -> throw exception
		if ( isInTheList( element ) > -1 ) {	
			throw new ElementAlreadyInListException( "This element is already in the list." );			
		}
		
		// Is the list full -> expand it
		if ( theCollection.length == numberOfElements ) {
			expandArray( );
		}
		
		// Insert the new element at the end of the concrete data structure
		// and increment the number of elements currently in the data structure by 1.
		theCollection[numberOfElements++] = element;
		// or could insert last element then expand		
                //sort the element in the list if the list has more than one element
                for (int i =1; i < numberOfElements && numberOfElements > 1; i++)
                {
                    //if the previous event has a greater time than the event after it
                   if ((((newEvent)theCollection[i-1]).getTimeOfEvent()) > ((newEvent)theCollection[i]).getTimeOfEvent())
                   {
                       Listable temp = theCollection[i];
                       theCollection[i] = theCollection[i-1];
                       theCollection[i-1] = temp;
                   }
                   //if two events has same time, but the previous one is departure, we swap
                   if ((((newEvent)theCollection[i-1]).getTimeOfEvent()) == ((newEvent)theCollection[i]).getTimeOfEvent())
                   {
                       if (((newEvent)theCollection[i-1]).isDepature())
                        {
                       Listable temp = theCollection[i];
                       theCollection[i] = theCollection[i-1];
                       theCollection[i-1] = temp;
                        }
                       
                   }   
                 }
		return;
		
	} // end of insert
	
	// utility method
	private int isInTheList( Listable element ){
		boolean found = false;
		int result = -1;

		for ( int index = 0; index < numberOfElements && !found; index++ ) {
           if ( ((Listable)theCollection[index]).compareTo(element) == 0 ) {
        	   result = index;
        	   found = true;
           }
	    }
		return result;	
		
	} // end of isInTheList


	// utility method
	private void expandArray( ) {
		
		Listable[] theNewCollection = null;
		
		// For testing purposes: 
		System.out.println( "\nBefore expanding array, current length is " + this.theCollection.length );
		System.out.println( "Before expanding array, current length is " + this.currentArraySize );
	
		// Get a new array of Listable objects twice as big as previous one
		this.currentArraySize *= 2;			
		theNewCollection = new Listable[this.currentArraySize];
		
		// Copy all elements (in their order) into new array
		for ( int index = 0; index < numberOfElements; index++ ) {
			theNewCollection[index] = this.theCollection[index];
		}
		
		this.theCollection = theNewCollection;
	
		// For testing purposes: 
		System.out.println( "\nAfter expanding array, current length is " + this.theCollection.length );
		System.out.println( "After expanding array, current length is " + this.currentArraySize );
	
		return;
		
	} // end of expandArray


	public Listable retrieve( Listable element ) throws EmptyListException, ElementNotInListException	
	// Description: Returns this element.
	// Precondition: List is not empty.
	// Postcondition: If "element" is found in the list, it is returned but not deleted from the list.
	//	              If "element" is not found in the list, an exception is thrown.
	// Exception: Throws an EmptyListException if list is empty.
	// Exception: Throws an ElementNotInListException if "element" is not found in the list.
	{
		Listable theElement = null;
		int position;

		// Verify precondition: If the list empty -> throw an exception
		if ( numberOfElements == 0 )
			throw new EmptyListException( "This list is empty." );

		// Search for the element. When found, get its reference and stop searching.
		if ( ( position = isInTheList( element ) ) > -1 ) {	
 		   // Get a handle to the element we are searching for.
		   theElement = theCollection[position];
		} else 		// if (!found) -> -> throw an exception
		    throw new ElementNotInListException( "This element is not the list." );			

		return theElement;
		
	} // end of retrieve
        
        public Listable retrieveFirstElement()
        //Description: retrieve the first element of the list
        //Precondition:The list is not empty
        //Postcondition:The first element is now retrieved to the user, the list is unchanged
        {
            return theCollection[0];
        }
        


	public void delete( Listable Element ) throws EmptyListException, ElementNotInListException
	/*
	 * Description:  This method finds and deletes the desired element
	 *               from the list (collection of Listable Objects)
	 * Parameter: Listable Element
	 * Precondition: The data collection is not empty, the element is in the data collection
	 * Postcondition: The item indicated by the parameter in the data collection is deleted
	 *                and other items are renumbered accordingly. 
	 *                Size of list decreased by 1
	 * Throws: EmptyListException if the data collection is empty.
	 *         ElementNotInListException if "element" is not found in the data collection.
	 */
        {
			if (size() <= 0) //empty list
			{
				throw new EmptyListException ("The data collection is empty.");
			}//end if
		
			if (findElement(Element)==false) //element is not found in the list 
			{
				throw new ElementNotInListException ("The element is not in the list.");
			}//end if
		
			//Element is found, and the deletion takes place
			if (findElement(Element))
			{	
				int position = 0;
				boolean search = true; //this variable can help us terminate the search once we found the position
				//determining the element's position
				for(int i = 0; i < numberOfElements && search; i++)
				{
					if (compareElement(theCollection[i],Element)) //found the element and its position
					{	
						position = i;
						search = false;
					}
					
				}//end for	
				if ( position< numberOfElements-1)
				{	
					// Delete by shifting each element on the right side (of the element we wish to delete) to the left by 1
					for (int i = position; position<numberOfElements-1; position++)
					{
						theCollection[position] = theCollection[position+1];
					}
					// Decrease the size of the data collection by 1
  	         		 for (int i =0; i < numberOfElements-1;i++)
  	         		 {
  	         	 		theCollection[i] = theCollection[i];
  	         		 }
                                    numberOfElements -= 1; 
				}
				
				//The above deletion scheme does not work if the element to be deleted
				//is the last one in our data collection, so we need to implement a new 
				//scheme to do so. We can simply make the data collection copy itself till
				//the 2nd last element.
				else if(position == numberOfElements-1)
				{
					for (int i =0; i < numberOfElements-1;i++)
					{
					theCollection[i] = theCollection[i];
					}
                                         numberOfElements -=1;
				}
  	          				
			}//end if
	
        }	
	

	public String toString( )
	// Description: Outputs all elements.
	// Postcondition: A string containing all elements (their information) is returned.
	{
		String theString = "";
		if ( numberOfElements <= 0 )
			theString = "\nThere are no elements!";        
		else
			for ( int index = 0; index < numberOfElements; index++ )
				theString += "\n" + theCollection[ index ].toString( );    

		return theString;

	} // end of toString 	
        
        private boolean compareElement(Listable Element_1, Listable Element_2)
 	{
 		//Listable object has a compareTo method which returns 0 if the two objects are the same,
 		//-1 or 1 if the two objects are not the same (less than or greater than is not important
 		//in this case, since all we want to find out is whether two Listable obejects are the same
 		//or not)
 		return  (Element_1.compareTo(Element_2) == 0);
 		
 	}//end compareElement
        
        /*
	 *Description: Find an element in the data collection
	 *Parameter: Listable Element
	 *Retrun Type boolean
	 *Postcondition:Return true if the element is found in the data collection
	 *              return false if the element is not found in the data collection
	 */
	 private boolean findElement(Listable Element)
	 {
	 	boolean elementNotFound = true;
	 	//attempting to find the element in the data collection
	 	for (int i = 0; i < numberOfElements&& elementNotFound ; i++)
		{
			if (compareElement(theCollection[i],Element)) //found the element in the data collection
			{
				elementNotFound = false;
			}//end if
	 	
	 	}//end for
	 	return (elementNotFound ==false);
	 }//end findElement;
	
} // end of MyFlexibleList class