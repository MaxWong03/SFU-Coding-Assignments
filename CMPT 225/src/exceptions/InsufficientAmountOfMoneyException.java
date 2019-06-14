// File: InsufficientAmountOfMoneyException.java
// Author: Weihao Yang
// Std Number: 301159935
// Lab Number: D106
// Created on: June 2013


package exceptions;

// Class Description 
//   This exception is thrown when there is insufficient
//   amount of money to carry on an operation such as withdraw

public class InsufficientAmountOfMoneyException extends Exception 
{ 
    // Default constructor
    public InsufficientAmountOfMoneyException( ) 
    {
    } // end of constructor
    
    // Parameterized constructor
    public InsufficientAmountOfMoneyException( String msg )
    {
    	// Question: where is msg stored? Check out Exception class in Java API
        super( msg );
        
    } // end of constructor
    
} // end of InsufficientAmountOfMoneyException class 