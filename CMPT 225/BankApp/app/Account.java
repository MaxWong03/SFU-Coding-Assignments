// File: Account.java
// Author: Max (Tsz Keung Wong)
// Std Number: 301165131
// Lab Number: D-106
// Created on: 18/06/2013
// Revised on: 19/06/2013

package app;

import exceptions.InsufficientAmountOfMoneyException;
import exceptions.ZeroOrNegativeAmountOfMoneyException;
import interfaces.Listable;

// Class Description
   //   The Account class models a simple bank account that contains the
   //   Account ID number, the Account owner's (customer) information and the Account balance.
   //   The class provides getters and setters to access and modify the account ID number, 
   //   the customera£á?s information and the balance.
   //   The class also provides application-related methods deposit and withdraw 
   //   that modify the state of an account object by increasing/decreasing its balance.

   // Class Invariants:
   // - Account balance is always >= 0
   // - Account ID numbers are unique and cannot be modified.

public class Account extends Object implements Listable
{
    // static constants
    final static double INITIAL_BALANCE = 0.0;     // Used to initialize balance when no balance 
                                                   // value is given or when it is erroneous

    // static data members
    static int uniqueAccountID = 0;                // mechanism used to create unique account ID number

    // data members
    protected int theIDNumber;                     // account ID number
    protected Person theCustomer;                  // customer of the account
    protected double theBalance;                   // account balance


    // method members

    // Default constructor
    public Account( )
    // Description: Create an object Account.
    // Precondition: anInitialBalance is always >= 0.
    // Precondition: anAccountID is unique.
    // Postcondition: A new account is created with the next unused accountID,
    //                a default person, and a balance of INITIAL_BALANCE.
    //                Note that uniqueAccountID is incremented for the next account to be created.
    {
        // Question: What does this call to "this(...)" do?
    	this( uniqueAccountID++, new Person( ), INITIAL_BALANCE );

    } // end constructor

    // Parameterized constructor
    public Account( int anAccountID )
    // Description: Create an object Account.
    // Precondition: anInitialBalance is always >= 0.
    // REMOVED: Precondition: anAccountID is unique.
    // NOTE: Even though anAccountID is unique, we are able to construct Account objects with similar
	//       accountID for various purposes. We may not, however, be able to insert them in a
	//       collection of Account objects.
    // Postcondition: A new account is created with the given ID number, a default person,
    //                and a balance of INITIAL_BALANCE.
    {
       this( anAccountID, new Person( ), INITIAL_BALANCE );
       
    } // end constructor

    // Parameterized constructor
    public Account( Person aPerson )
    // Description: Create an object Account.
    // Precondition: anInitialBalance is always >= 0.
    // Precondition: anAccountID is unique.
    // Postcondition: A new account is created with the next unused accountID,
    //                the given person, and a balance of INITIAL_BALANCE.
    //                Note that uniqueAccountID is incremented for the next account to be created.
    {
       this( uniqueAccountID++, aPerson, INITIAL_BALANCE );
       
    } // end constructor

    // Parameterized constructor
    public Account( Person aPerson, double anInitialBalance )
    // Description: Create an object Account.
    // Precondition: anInitialBalance is always >= 0.
    // Precondition: anAccountID is unique.
    // Postcondition: A new account is created with the next unused accountID,
    //                the given person, and the given balance.
    //                Note that uniqueAccountID is incremented for the next account to be created.
    {
       this( uniqueAccountID++, aPerson, anInitialBalance );

    } // end constructor

    public Account( int anAccountID, Person aPerson, double anInitialBalance )
    // Description: Create an object Account.
    // Precondition: anInitialBalance is always >= 0.
    // REMOVED: Precondition: anAccountID is unique.
    // NOTE: Even though anAccountID is unique, we are able to construct Account objects with similar
	//       accountID for various purposes. We may not, however, be able to insert them in a
	//       collection of Account objects.
    // Postcondition: A new account is created with the given ID number, the given person,
    //                and the given balance (if this balance is positive or zero),
    //                otherwise INITIAL_BALANCE is used.
    {
       setIDNumber( anAccountID );
       setCustomer( aPerson );
       setBalance( anInitialBalance );

    } // end constructor

    // Getters
    public Person getCustomer( )
    // Description: Returns the customer information.
    {
    	return theCustomer;
    	
    } // end getCustomer

    public int getIDNumber( )
    // Description: Returns the account ID number.
    {
    	return theIDNumber;
    	
    } // end getID

    public double getBalance( )
    // Description: Returns the current balance of the account.
    {
    	return theBalance;
    	
    } // end getBalance

    // Setters
    public void setCustomer( Person newPerson )
    // Description: Sets the customer information.
    {
    	theCustomer = newPerson;
        return;
        
    } // end setCustomer

    public void setIDNumber( int newIDNumber )
    // Description: Sets the accountIDNumber.
    {
    	theIDNumber = newIDNumber;
        return;
        
    } // end setIDNumber

    public void setBalance( double newBalance )
    // Description: Sets the current balance to the newBalance if newBalance is positive 
    //              or zero, otherwise sets the current balance to INITIAL_BALANCE.
    // Precondition: newBalance is always >= 0.
    {
        if ( newBalance >= 0.0 )
            theBalance = newBalance;
         else
            theBalance = INITIAL_BALANCE;

        return;
        
    } // end setBalance

    public double deposit( double anAmount ) throws ZeroOrNegativeAmountOfMoneyException
    // Description: Deposits money into the account by incrementing the balance.
    // Precondition:  anAmount > $0.00.
    // Postcondition: If the amount to deposit is not negative or 0.00,
    //                the account's balance is increased by that amount;
    //                otherwise, the exception ZeroOrNegativeAmountOfMoneyException is thrown
    //                and the balance remains unchanged.
    {
        if ( anAmount <= 0.0 )
           throw new ZeroOrNegativeAmountOfMoneyException( "Depositing a negative amount or $0.00: " + anAmount );

        return  theBalance += anAmount;

    } // end deposit

    public void withdraw( double anAmount ) throws InsufficientAmountOfMoneyException, ZeroOrNegativeAmountOfMoneyException
    // Description: Withdraws money from the account by decrementing the balance.
    // Precondition: Balance of account >= anAmount and anAmount > $0.00.
    // Postcondition: If balance of account >= anAmount and anAmount > $0.00,
    //                then the account's balance is decremented by anAmount;
    //                otherwise, if balance of account < anAmount, then the exception
    //                InsufficientAmountOfMoneyException is thrown,
    //                if anAmount <= $0.00 ZeroOrNegativeAmountOfMoneyException is thrown,
    //                When an exception is thrown, the balance remains unchanged.
    {
        if ( anAmount <= 0.0 )
           throw new ZeroOrNegativeAmountOfMoneyException( "Withdrawing a negative amount or $0.00: " + anAmount );

        if ( enoughMoney( anAmount ) )
            theBalance = theBalance - anAmount;
        else
            throw new InsufficientAmountOfMoneyException( "Insufficient amount of $ in balance." +
                                                          "Requested amount: " + anAmount +
                                                          "Balance: " + theBalance);
        return;

    } // end withdraw

    public boolean enoughMoney( double anAmount ) throws ZeroOrNegativeAmountOfMoneyException
    // Description: Verifies whether there is sufficient $ in this account.
    // Postcondition: Returns true is the account contains at least anAmount or more,
    //                returns false otherwise.
    {
        return ( theBalance >= anAmount );

    } // end enoughMoney

	// Method from the Listable interface.

    public String toString( )    
    // Postcondition: concatenate the value of the object's 
    //                data members into a string and return this string.
    {
        return( "\tAccount ID Number: " + theIDNumber + "\n\tAccount Customer: "
        		+ theCustomer +"\n\tAccount Blance: " + "$" + theBalance );

    } // end toString

   
    public int compareTo( Listable otherObject )
    // Description: decides whether this object is equal to, > than or < than otherObject
    // Postcondition: returns 0 if both objects match (are equal)
    //                returns 1 if this object > otherObject
    //                returns -1 if this object < otherObject
    {
    	int answer = -1;

    	if (this.getIDNumber( ) == ((Account) otherObject).getIDNumber( ))
    		answer = 0;
    	else if (this.getIDNumber( ) > ((Account) otherObject).getIDNumber( ))
    		answer = 1;

    	return answer;

    } // end compareTo

} // end of Account class