// File: Person.java
// Author: Max (Tsz Keung Wong)
// Std Number: 301165131
// Lab Number: D-106
// Created on: 18/06/2013
// Revised on: 19/06/2013


package app;

import interfaces.*;

// Class Description 
//   The Person class models a person with a first and last name.
//   Provides getters and setters to access and modify the first and last name of a person.

// Class Invariants:
//   Can you think of a class invariant for this class?

// Questions:
// Can you think of precondition(s) you would add to some/most/all of the methods of this class?
// Why did I not include "postconditions" to the methods of this class?

public class Person implements Listable
{
	// static constants
    final static String INITIAL_VALUE = "";  // Used to initialize theFirstName and/or
                                             // theLastName when no values are given
    // data members
    private String theFirstName; // the first name (A)
    private String theLastName;  // the last name  (B)
                                 // Since the name of each of these data members is very 
                                 // descriptive, we could skip the comments (A) and (B) 
                                 // describing the data members

    // method members
    
    // Default constructor
    public Person()
    // Description: Initializes theFirstName and theLastName to INITIAL_VALUE
    {
    	this( INITIAL_VALUE, INITIAL_VALUE );
        
    } // end constructor

    // Parameterized constructor
    public Person(String aFirstName, String aLastName)
    // Description: Initializes theFirstName and theLastName using the given parameters
    {
    	setName( aFirstName, aLastName );
         
    } // end constructor
 
    // Getters
    public String getFirstName( )
    // Description: Returns theFirstName
    {
        return theFirstName;
        
    } // end getFirstName

    public String getLastName()
    // Description: Returns theLastName
    {
        return theLastName;
        
    } // end getLastName
    
    // Setter
    public void setName( String aFirstName, String aLastName )
    // Description: Sets theFirstName and theLastName using the given parameters
    {
         theFirstName = aFirstName;
         theLastName = aLastName;
         
    } // end setName

    public String toString( )
    // Description: Returns a string containing the first and last name of this person
    {
    	return( getFirstName( ) + " " + getLastName( ) );

    } // end toString
    
    	public int compareTo(Listable otherObject)
	// Description: Compare  otherObject with this object
	// Precondition: The object otherObject should be an Account object
	// Postcondition: Returns 0 if this = otherObject (Same account ID number)
	//                Return -1 if this < otherObject (otherObject has ID number greater than this object)
	//                Return  1 if this > otherObject (otherObject has ID number less than this object) 
	{
		if(toString().equals(otherObject.toString()))
		{
			return 0; //Same account ID number
		}
		else
			return 1; //otherObject ID number is less than this object
	}//end compareTo
  
} // end of Person class