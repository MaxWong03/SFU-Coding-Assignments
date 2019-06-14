// File: Listable.java
// Author: AL
// Std Number: blahblahblah
// Lab Number: blahblahblah
// Created on: September 2002
// Revised on: May 290, 2013

package interfaces;

public interface Listable {  
   
   public String toString( );
   // Postcondition: concatenate the value of the object's 
   //                data members into a string and return this string.
  
   public int compareTo( Listable otherObject );
   // Description: decides whether this object is equal to, > than or < than otherObject
   // Postcondition: returns 0 if both objects match (are equal)
   //                returns 1 if this object > otherObject
   //                returns -1 if this object < otherObject

} // end of interface Listable