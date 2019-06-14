/*
 * File: ElementAlreadyInListException.java
 * Author: Max (Tsz Keung Wong)
 * Std Number: 301165131
 * Lab Number: D-106
 * Created on: 01/06/2013
 * Revised on: May 20, 2013
 */

package exceptions;

//Class Description:
// This exception is thrown when the element the user intended to add
// is already in the list when using the insert method 

public class ElementAlreadyInListException extends java.lang.Exception {
        
   	//Default constructor
    public ElementAlreadyInListException() 
    {
    } //end of constructor
    
  	// Parameterized constructor	
    public ElementAlreadyInListException(String message) 
    {
        super(message);
    }//end of constructor

}//end of ElementAlreadyInListException class