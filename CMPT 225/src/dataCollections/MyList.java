// Filename: MyList.java
// Author: Weihao Yang
// Std Number: 301159935
// Lab Number: D106
// Created on: June 2013


package dataCollections;

import app.Account;

//Class Description: Unsorted
//  <For you to fill in the rest.>

// Class Invariants:
//  <For you to fill in if there are any.> 

public class MyList {

    // static constants
	final static int INITIAL_SIZE_OF_COLLECTION = 25;  // To be used if no initial size is given

    // data members
	private Account[] theAccounts;  // collection of accounts
	private int numberOfAccounts;   // current number of accounts

    // method members
	
	// Default constructor
	public MyList( )
	{
		theAccounts = new Account[ INITIAL_SIZE_OF_COLLECTION ]; 
		numberOfAccounts = 0;

	} // end constructor
	
	
	/*
	 * Description: Returns the number of accounts in the data collection
	 */	
	public int getNumberOfAccounts( )
	{
		return numberOfAccounts;

	} // end of getNumberOfAccounts

	/*
	 * Description: Inserts an account into the data collection (collection of accounts)
	 */
	public void insert(Account anAccount) {

		// More logic is needed in this method.
		// For example, if the data collection is full, what should we do?
		
		// If there is space in the data collection, we insert the new account
		// and increment the number of accounts currently in the data collection by 1.
		theAccounts[numberOfAccounts++] = anAccount;

		return;
	}

	/*
	 * Description: This method finds and returns the desired account without deleting it
	 *              from the data collection (collection of accounts)
	 */
	public Account retrieve(int thisAccountID) {

		Account theAccount = null;
		boolean notFound = true;

		if (numberOfAccounts <= 0)
			// There are no accounts - May be better to throw an exception
			System.out.println("\nThere are no accounts!\n");
		else
			// Search for the account. When found, display it and stop searching.
			for (int index = 0; (index < numberOfAccounts && notFound); index++)
				if (theAccounts[index].getIDNumber() == thisAccountID) {
					// Get a handle to account we are searching for.
					theAccount = theAccounts[index];
                    notFound = false;
				}
	
		if (notFound)
			// If the account was not found - May be better to throw an exception
			System.out.println("\nAccount not found!\n");
		
		return theAccount;
		
	}
	/*
	 * Description: This method finds and deletes the desired account
	 *              from the data collection (collection of accounts)
	 */
	public void delete(int thisAccountID) {

		boolean notFound = true;

		if (numberOfAccounts <= 0)
			// There are no accounts - May be better to throw an exception
			System.out.println("\nThere are no accounts!");
		else {
			// Search for the account. When found, display it and delete it.
			for (int index = 0; (index < numberOfAccounts && notFound); index++)
				if (theAccounts[index].getIDNumber() == thisAccountID) {
					// Overwrite (i.e. delete account) this account with the
					// last account in data collection
					theAccounts[index] = theAccounts[--numberOfAccounts];
					// Does the above deletion scheme work if the account to be deleted
					// is the last one in our data collection?
                    notFound = false;				
				}
		}
		
		if (notFound)
			// If the account was not found - May be better to throw an exception
			System.out.println("\nAccount not found!\n");
			
        return;
	}

	/*
	 * Description: Transforms into a string all the account objects in the data collection
	 * Postcondition: The string containing all the account information is returned
	 */	
	public String toString( )
	{
		String theString = "";
		
		if ( numberOfAccounts <= 0 )
			// No accounts to display
			theString = "\nThere are no accounts!";        
		else {
			theString += "\nDisplaying all accounts:\n";			
			for ( int index = 0; index < numberOfAccounts; index++ )
				theString += "\n" + theAccounts[ index ].toString( ) + "\n"; 
		}

		return theString;

	} // end of toString method
	
	
} // end of MyList class