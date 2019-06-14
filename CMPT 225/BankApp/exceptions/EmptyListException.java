/*
 * File: EmptyListException.java
 * Author: Max (Tsz Keung Wong)
 * Std Number: 301165131
 * Lab Number: D-106
 * Created on: 01/06/2013
 * Revised on: May 20, 2013
 */

package exceptions;

//Class Description:
// This exception is thrown if the list is empty and the user tries to use 
// the delete, retrieve method 

public class EmptyListException extends java.lang.Exception {
        
   	//default constructor
    public EmptyListException() 
    {
    }//end of constructor
    
	// Parameterized constructor
    public EmptyListException(String message) 
    {
        super(message);
    }//end of constructor

}//end of EmptyListException class