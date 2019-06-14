// File: ZeroOrNegativeAmountOfMoneyException.java
// Author: Max (Tsz Keung Wong)
// Std Number: 301165131
// Lab Number: D-106
// Created on: 18/06/2013
// Revised on: 19/06/2013

package exceptions;

// Class Description 
//   This exception is thrown when the amount of money to
//   be either deposited or withdrawn is 0.0 or negative

public class ZeroOrNegativeAmountOfMoneyException extends java.lang.Exception 
{  
    // Default constructor
    public ZeroOrNegativeAmountOfMoneyException( ) 
    {
    	// Question: why do we provide an empty default constructor?
    	
    } // end of constructor
    
    // Parameterized constructor
    public ZeroOrNegativeAmountOfMoneyException(String msg) 
    {
        super( msg );
        
    } // end of constructor

} // end of ZeroOrNegativeAmountOfMoneyException class