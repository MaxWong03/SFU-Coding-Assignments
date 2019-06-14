// File: BankApp.java - version 4
// Author: Max (Tsz Keung Wong)
// Std Number: 301165131
// Lab Number: D-106
// Created on: 18/06/2013
// Revised on: 19/06/2013

package app;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import exceptions.*;
import dataCollections.*;

// Class Description: This class models various bank operations.
//                    It allows for customer operations and bank staff operations via menus.
//                    It allows a customer to deposit money, to withdraw money and 
//                    display the balance of an account.
//                    It allows a bank staff to open and close accounts and display all accounts.
//                    Since there were no requirements stating that the accounts needed 
//                    to be kept in a certain sort order, the data collection 
//                    (collection of accounts) is therefore unsorted.

// Class Invariant: none

public class BankApp {
	
	// static constants
	final static Account ACCOUNT_NOT_FOUND = null;
	final static int QUIT = 0;

    // data members	
    private MyFlexibleList accounts;			// data collection -> my list of accounts
	private Scanner theKbd;             // to help us read keyboard input from user

    // method members
	
	// Default constructor
	public BankApp() {
		accounts = new MyFlexibleList();
		theKbd = new Scanner(System.in);
		System.out.println("Welcome to the Bank Application - version4 !");

	}
	
    // Getter method
	private MyFlexibleList getAccounts( ) {
		return accounts;
	}
	
/*
 * Menu methods
 */
	/*
	 * Description: Prints the main menu
	*/
	private void mainMenu() {
		System.out.println("\nCustomer operations\t- Enter 1");
		System.out.println("Bank operations\t\t- Enter 2");
		System.out.println("Quit\t\t\t- Enter 0");
		System.out.print("\nYour selection is: ");
		return;
	}

	/*
	 * Description: Prints the customer operations menu
	*/
	 private void customerOperations() {
		System.out.println("\nDeposit money\t- Enter 3");
		System.out.println("Withdraw money\t- Enter 4");
		System.out.println("Display balance\t- Enter 5");
		System.out.println("Previous menu\t- Enter 0" );
		System.out.print("\nYour selection is: ");
		return;
	}

	/*
	 * Description: Prints the bank staff operations menu
	*/
	 private void bankOperations() {
		System.out.println("\nOpen a new account\t  - Enter 6");
		System.out.println("Close an existing account - Enter 7");
		System.out.println("Display all accounts\t  - Enter 8" );
		// Would be nice to have ... (perhaps next version):
		// System.out.println( "Search for an account - Enter ?" );
		System.out.println("Previous menu\t\t  - Enter 0" );
		System.out.print("\nYour selection is: ");
		return;
	}
	 
 /*
  * IO methods
  */

 	/*
 	 * Description: Wrapper method that calls readInt( ) since a selection is an int
 	*/
 	private int readSelection() {

 		int userSelection = QUIT;

 		userSelection = this.readInt("");
 		// Echo user selection:
 		System.out.println("Your selection entered is: " + userSelection);
 		return userSelection;
 	}

 	/*
 	 * Description: Reads a String value entered by user and deals with possible exceptions
 	 */
 	private String readString(String userInstruction) {

 		String aString = null;

 		try {
 			System.out.print(userInstruction);
 			aString = theKbd.nextLine();
 		} catch (NoSuchElementException e) {
 			//if no line was found
 			System.out.println("\nNoSuchElementException error occurred (no line was found) " + e);
 		} catch (IllegalStateException e) {
 			// if this scanner is closed
 			System.out.println("\nIllegalStateException error occurred (scanner is closed)" + e);
 		}

 		return aString;
 	}

 	/*
 	 * Description: Reads an int value entered by user and deals with possible exceptions
 	 */
 	private int readInt(String userInstruction) {

 		int anInt = 0;
		boolean correctRun = false;
		while (correctRun == false)
		{
 			try 
 			{
 				System.out.print(userInstruction);
 				anInt = theKbd.nextInt();
 				theKbd.nextLine();
 				correctRun = true;
 			}	 
 			catch (InputMismatchException e) 
 			{
 				System.out.println("You must enter one of the numbers listed in the menu above.");
         		theKbd.nextLine();
         		correctRun = false;
 			} 
 			catch (NoSuchElementException e) 
 			{
 				System.out.println("\nNoSuchElementException error occurred (input is exhausted)" + e);
 				correctRun = true;
 			} 
 			catch (IllegalStateException e) 
 			{
 				System.out.println("\nIllegalStateException error occurred (scanner is closed)" + e);
 				correctRun = true;
 			}
		}
 		return anInt;

 	}

 	/*
 	 * Description: Reads a double value entered by user and deals with possible exceptions
 	 */
 	private double readDouble(String userInstruction) {

 		double aDouble = 0;
		boolean correctRun = false;
		while(correctRun == false)
		{
 			try 
 			{
 				System.out.print(userInstruction);
 				aDouble = theKbd.nextDouble();
 				theKbd.nextLine();
 				correctRun = true;
 			} 
 			catch (InputMismatchException e) 
 			{
 				System.out.println("You must enter one of the numbers listed in the menu above.");
         		theKbd.nextLine();
         		correctRun = false;
 			} 
 			catch (NoSuchElementException e) 
 			{
 				System.out.println("\nNoSuchElementException error occurred (input is exhausted)" + e);
 				correctRun = true;
 			} 
 			catch (IllegalStateException e) 
 			{
 				System.out.println("\nIllegalStateException error occurred (scanner is closed)" + e);
 				correctRun = true;
 			}
		}
 		return aDouble;

 	}

 	/*
 	 * Description: Reads customer's last and first name from user and
 	 *              create a Person object to represent the customer              
 	 */
 	private Person readAndCreateCustomer() {

 		// Deal with customer's last name
 		String theCustomerLastName = this.readString("\nEnter customer last name: ");
 		// Echo user input:
 		System.out.println("The customer's last name entered is: " + theCustomerLastName);

 		// Deal with customer's first name
 		String theCustomerFirstName = this.readString("\nEnter customer first name: ");
 		// Echo user input:
 		System.out.println("The customer's first name entered is: " + theCustomerFirstName);

 		return new Person(theCustomerFirstName, theCustomerLastName);
 	}

 	/*
 	 * Description: Reads a balance from user and 
 	 *              ensures that the balance is >= 0 
 	 */
 	private double readBalance() {

 		double theBalance = Account.INITIAL_BALANCE;
 		theBalance = this.readDouble("\nEnter a balance (must be >= 0): ");
 		while (theBalance < 0)
 		{
 			System.out.println("You can not enter a negative balance.");
 			theBalance = this.readDouble("\nEnter a balance (must be >= 0): ");
 		}
 		System.out.println("The balance entered is: " + theBalance);
 		return theBalance;
 	}

 	/*
 	 * Description: Reads an account ID number from user
 	 */
 	private int readAccountNumber() {

 		int theAccountNumber;
 		
 		theAccountNumber = this.readInt("\nEnter the account ID number: ");
 		// Echo user input:
 		System.out.println("The account ID number entered is: " + theAccountNumber);
 		return theAccountNumber;
 	}

/*
 * Methods implementing customer and bank staff functions
 *
 */
 		
	/*
	 * Description: Creates an account object
	*/
 	private void openAccount() throws ElementAlreadyInListException 
 	{
		Account anAccount = new Account(this.readAndCreateCustomer(), this.readBalance());
		this.getAccounts().insert(anAccount);
		System.out.println("\nAccount created successfully:\n" + anAccount);
	}

	/*
	 * Description: Closes an account object by deleting it
	*/
	private void closeAccount() throws EmptyListException, ElementNotInListException 
	{
		Account anAccount = new Account(this.readAccountNumber());
		this.getAccounts().delete(anAccount);
		System.out.println("You have succesfully delete the account in the system.\nSystem now returning back to the main menu.");
	}

	/*
	 *Description: Lets user deposit money into their account
	 */
	private void deposit()  throws ZeroOrNegativeAmountOfMoneyException,EmptyListException, ElementNotInListException
	{
		Account anAccount = new Account(this.readAccountNumber());
		((Account)this.getAccounts().retrieve(anAccount)).deposit(this.readDouble("Enter the amount you wish to deposit:"));
		System.out.println("You have sucessfully deposit money into your account. Your new balance is $ "+ ((Account)this.getAccounts().retrieve(anAccount)).getBalance() + ".");
	}
	
	/*
	 *Description: Lets user withdraw money from their account
	 */
	private void withdraw() throws EmptyListException,ElementNotInListException,InsufficientAmountOfMoneyException,ZeroOrNegativeAmountOfMoneyException
	{
		Account anAccount = new Account(this.readAccountNumber());
		((Account)this.getAccounts().retrieve(anAccount)).withdraw(this.readDouble("Enter the amount you wish to withdraw:"));
		System.out.println("You have succesfully withdrwe money from your account. Your new balance is $ " + ((Account)this.getAccounts().retrieve(anAccount)).getBalance() +".");
	}
	
	/*
	 *Description: shows user the balance in their account
	 */
	private void showBalance() throws EmptyListException, ElementNotInListException
	{
		Account anAccount = new Account(this.readAccountNumber());
		System.out.println("You have $ "+((Account)this.getAccounts().retrieve(anAccount)).getBalance() + "in your account.");
	}
	
	/*
	 * Description: Transforms into a string all the account objects in the data collection
	 * Postcondition: The string containing all the account information is returned
	 */	
	public String toString() 
	{
		String theString = "";
		
		if (this.getAccounts().size() <= 0)
			// No accounts to display
			theString += "\nThere are no accounts yet! :-)";
		else 
			theString += "\n" + this.getAccounts();
		
		return theString;
	} // end of toString 

	
	/*
	 * Description: Gets the ball rolling
	 */	
	public static void main(String[] args) {
		
		// Instantiate an object of the BankApp class
		BankApp theBankApp = new BankApp();
		int userSelection = QUIT;

		// Display initial menu
		theBankApp.mainMenu();
		
		// Read in user selection
		userSelection = theBankApp.readSelection();

		// While user has not selected QUIT
		while (userSelection != QUIT) {
			switch (userSelection) {
			// If user selected Customer Operations
			case 1:
				// Display Customer Operations menu
				theBankApp.customerOperations();
				// Read in customer selection
				userSelection = theBankApp.readSelection();

				switch (userSelection) {
				case 3:
				{	//If user chooses to deposit money into their account
					boolean correctRun = false;
					while (correctRun == false)
					{	
						try
						{
							theBankApp.deposit();
							correctRun = true;
						}
						catch (EmptyListException e)//the list is empty, throws an exception
						{
							System.out.println("There is no account to be deposited .\nSystem now goes back to the main menu.");
							correctRun =true;
						}
						catch (ElementNotInListException e)//the element is not in the list, throws an exception
						{
							System.out.println("The account you wish to deposit money into is not in the system.\n Enter 1 to enter another accountID or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if (selection ==1)//let user enter another accountID
								correctRun =false;
							if (selection ==2)// return to the main menu
								correctRun = true; 
						}
						catch (ZeroOrNegativeAmountOfMoneyException e)//the amount the user trying to deposit is zero or negative, throws an exception
						{
							System.out.println("You can not deposit 0 or negative amount of money.\n Enter 1 to re-enter your accountId and deposit a different amount or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if(selection ==1)//let user enter a didferent deposit amount
								correctRun =false;
							if(selection==2)//let user return to the main menu
								correctRun = true;
						}
					}
				}	
					break;
				
				case 4:
				{	
					//If user chooses to withdraw money from their account
					boolean correctRun = false;
					while(correctRun ==false)
					{
						try
						{
							theBankApp.withdraw();
							correctRun = true;
						}
						catch (EmptyListException e)//the list is emtpy, throws an exception
						{
							System.out.println("There is no account to be withdrawn.\nSystem now goes back to the main menu.");
							correctRun =true;
						}
						catch (ElementNotInListException e)//the element is not in the list, throws an exception
						{
							System.out.println("The account you wish to withdraw money from is not in the system.\nEnter 1 to enter another accountID or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if (selection ==1)//let user enter another accountID
								correctRun =false;
							if (selection ==2)// return to the main menu
								correctRun = true; 
						}
						catch(InsufficientAmountOfMoneyException e)//the amount of money the user trying to withdraw exceeds the account balance, throws an exception
						{
							System.out.println("The amount you are trying to withdraw exceeds your balance.\nEnter 1 to re-enter your accoundID and withdraw a different amount or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if(selection==1)
								correctRun=false;//let the user enter a different amount
							if(selection==2)
								correctRun=true;//return to the main menu
						}
						catch(ZeroOrNegativeAmountOfMoneyException e)//the amount the user trying to deposit is zero or negative, throws an exception
						{
							System.out.println("You can not withdraw 0 or negative amount of money.\nEnter 1 to re-enter your accoutnID and withdraw a different amount or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if(selection==1)
								correctRun=false;//let the user enter a different amount
							if(selection==2)
								correctRun=true;//return to the main menu
						}
					}
				}
					break;
				
				case 5:
				{	
					//If user chooses to display balance in their account
					boolean correctRun = false;
					while(correctRun==false)
					{
						try
						{
							theBankApp.showBalance();
							correctRun = true;
						}
						catch (EmptyListException e)//the list is empty, throws an exception
						{
							System.out.println("There is no account to be displayed.\nSystem now goes back to the main menu.");
							correctRun =true;
						}
						catch (ElementNotInListException e)//the element is not in the list , throws an exception
						{
							System.out.println("The account you wish to display into is not in the system.\n Enter 1 to enter another accountID or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if (selection ==1)//let user enter another accountID
								correctRun =false;
							if (selection ==2)// return to the main menu
								correctRun = true; 
						}
					}
					break;
				}
		}

				break;


			// If user has selected Bank Operations
			case 2:
				// Display Bank Operations menu
				theBankApp.bankOperations();
				// Read in banker selection
				userSelection = theBankApp.readSelection();

				switch (userSelection) {
				case 6:
					// If user has selected "Open a new account"
					try
					{
						theBankApp.openAccount();
					}
					catch (ElementAlreadyInListException e)//the element is already in the list, throws an exception
					{
						System.out.println("The account you are trying to insert is already in the System. Duplication is not allowed.\nSystem now return to the main menu.");	
					}
					break;
				case 7:
					// If user has selected "Close an existing account"
					boolean correctRun = false;
					while (correctRun ==false)
					{	
						try
						{	
							theBankApp.closeAccount();
							correctRun = true;
						}	
						catch (EmptyListException e)//the list is empty, throws an exception
						{
							System.out.println("There is no account to be deleted.\nSystem now goes back to the main menu.");
							correctRun =true;
						}
						catch (ElementNotInListException e)//the element is not in the list, throws an exception
						{
							System.out.println("The account you wish to delete is not in the system.\n Enter 1 to enter another accountID or Enter 2 to return to the main menu.");
							int selection = theBankApp.readSelection();
							if (selection ==1)//let user enter another accountID
								correctRun =false;
							if (selection ==2)// return to the main menu
								correctRun = true; 
						}
					}//end while
					break;
				case 8:
					// If user has selected "Display all accounts"
					System.out.println(theBankApp);
					break;
				}
				break;
			}

			// Display initial menu
			theBankApp.mainMenu();
			// Read in user selection
			userSelection = theBankApp.readSelection();
		}
		// Exit
		System.out.println("Bye!");
	}
} // end of BankApp class