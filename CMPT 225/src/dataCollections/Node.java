
/**
* @(#)Node.java
*
*
* @author
* @version 1.00 2013/6/17
*/
 
package dataCollections;
 
import interfaces.*;
 
public class Node
{
 // data members
 
 private Queueable element;
 private Node next;
 
 // constructors
 public Node() {
 	 this(null, null);
 } // end constructor
 
 public Node(Queueable  newElement) {
 	 this(newElement, null);
 } // end constructor
 
 public Node(Queueable  newElement, Node nextNode) {
 	 element = newElement;
 	 next = nextNode;
 } // end constructor
 
 // getters
 public Queueable  getElement() {
 	 return element;
 } // end getElement
 
 public Node getNext() {
 	 return next;
 } // end getNext
 
 // setters
 public void setElement(Queueable  newElement) {
 	 element = newElement;
 } // end setElement
 
 public void setNext(Node nextNode) {
 	 next = nextNode;
 } // end setNext
 
} // end Node

