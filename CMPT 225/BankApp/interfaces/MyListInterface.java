//File: MyListInterface.java
//Author: Max (Tsz Keung) Wong
//Std Number : 301165131
//Lab Number : D-106
//Date of creation: 28/05/2013

package interfaces;

import exceptions.*;

public interface MyListInterface {

	public int size();
		//Description: Returns the number of elements currently stored in the list.
		//Postcondition: Returns 0 if empty otherwise returns the number of elements.
	
	public void insert(Listable element) throws ElementAlreadyInListException;
		//Description: Inserts an element into the list.
		//Precondition: "element" is not already in the list. This is to say: no duplication allowed.
		//Postcondition: "element" has been added to the list.
		//Exception: Throws an ElementAlreadyInListException if "element" is already in the list.
	
	public void delete(Listable element) throws EmptyListException, ElementNotInListException;
		//Description: Deletes this element from the list.
		//Precondition: List is not empty.
		//Postcondition: If "element" is found in the list, it is deleted from the list. 
	    //		         If "element" is not found in the list, an exception is thrown.
		//Exception: Throws an EmptyListException if list is empty.
		//Exception: Throws an ElementNotInListException if "element" is not found in the list.
		
	public Listable retrieve(Listable element) throws EmptyListException, ElementNotInListException ;
		//Description: Returns this element.
		//Precondition: List is not empty.
		//Postcondition: If "element" is found in the list, it is returned but not deleted from the list.
		//               If "element" is not found in the list, an exception is thrown.
		//Exception: Throws an EmptyListException if list is empty.
		//Exception: Throws an ElementNotInListException if "element" is not found in the list.
	
	public void deleteAll();
		//Description: Deletes all the elements from the list. 
        //Postcondition: List is empty.
    
    public String toString();
    //Description: Outputs all elements.
	//Postcondition: A string containing all elements (their information) is returned.	   

}//end of interface Listable