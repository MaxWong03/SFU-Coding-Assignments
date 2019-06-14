// File: Node.java
// Author: Max (Tsz Keung Wong)
// Std Number: 301165131
// Lab Number: D-106
// Created on: 18/06/2013
// Revised on: 19/06/2013

package dataCollections;

import interfaces.*;

public class Node 
{
	// data members
	
	private Listable element;
	private Node next;

	// constructors
	public Node() {
		this(null, null);
	} // end constructor

	public Node(Listable newElement) {
		this(newElement, null);
	} // end constructor
	
	public Node(Listable newElement, Node nextNode) {
		element = newElement;
		next = nextNode;
	} // end constructor

	// getters
	public Listable getElement() {
		return element;
	} // end getElement

	public Node getNext() {
		return next;
	} // end getNext
	
	// setters
	public void setElement(Listable newElement) {
		element = newElement;
	} // end setElement

	public void setNext(Node nextNode) {
		next = nextNode;
	} // end setNext

}  // end Node