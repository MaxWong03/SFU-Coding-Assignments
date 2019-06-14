// File: EmptyListException.java
// Author: Weihao Yang
// Std Number: 301159935
// Lab Number: D106
// Created on: June 2013

package exceptions;

/**
 *
 * @author weihaoy
 */
public class EmptyListException extends Exception 
{ 
    // Default constructor
    public EmptyListException( ) 
    {
    } // end of constructor
    
    // Parameterized constructor
    public EmptyListException( String msg )
    {
    	// Question: where is msg stored? Check out Exception class in Java API
        super( msg );
        
    } // end of constructor
    
} // end of InsufficientAmountOfMoneyException class 
