/*
 * File: ElementNotInListException.java
 * Author: Max (Tsz Keung Wong)
 * Std Number: 301165131
 * Lab Number: D-106
 * Created on: 01/06/2013
 * Revised on: May 20, 2013
 */

package exceptions;

//Class Description:
// This exception is thrown when the element is not found in the list when the user
// tries to use the delete, retrieve method 

public class ElementNotInListException extends java.lang.Exception {
        
	//default constructor  
    public ElementNotInListException() 
    {
    } //end of constructor
    
   // Parameterized constructor
    public ElementNotInListException(String message) 
    {
        super(message);
    }//end of constructor

}//end of ElementNotInListException class
