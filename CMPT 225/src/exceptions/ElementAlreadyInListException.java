// Filename: ElementAlreadyInListException.java
// Author: Weihao Yang
// Std Number: 301159935
// Lab Number: D106
// Created on: June 2013

package exceptions;

/**
 *
 * @author weihaoy
 */
public class ElementAlreadyInListException extends Exception 
{ 
    // Default constructor
    public ElementAlreadyInListException( ) 
    {
    } // end of constructor
    
    // Parameterized constructor
    public ElementAlreadyInListException( String msg )
    {
    	// Question: where is msg stored? Check out Exception class in Java API
        super( msg );
        
    } // end of constructor
    
} // end of InsufficientAmountOfMoneyException class 