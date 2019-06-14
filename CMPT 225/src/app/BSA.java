

/**
 * @(#)BSA.java
 *
 *
 * @author 
 * @version 1.00 2013/7/8
 */
package app;
import exceptions.*;
import dataCollections.*;
import interfaces.*;
import java.util.*;
import java.io.*;
import app.customer;
import app.newEvent;

public class BSA {
    
    //data members
    private QueueReferenceBased bankQueue; //bank queue 
    private MyFlexibleList eventList; //event list 
    private int currentTime; //clock
    private Scanner theKBD; //to help us read keyboard input from user
    private Reader fileIn;  //file to be read
    private boolean done;   //indicate when we are done reading the file
    private Scanner scanfile; //Scanner file to be read      
    private String testInput; //test input
    private int numberOfPeople;
    private double arrivalTime;
    private double departureTime;
    private double transactionTime;
    
    //method memebers  
    
    //Default constructor  
	public BSA()
	{
		bankQueue = new QueueReferenceBased(); //create an empty queue to represent the bank line 
		eventList = new MyFlexibleList(); //create an empty event list
		currentTime =0; //initialize the clock 
		theKBD = null; //initialize the Scanner KBD
		boolean done = false;
                numberOfPeople = 0;
                arrivalTime = 0;
                transactionTime = 0;
                departureTime = 0;
               
    	scanfile =null; //Scanner file to be read
    	testInput = "";       
	}//end of default constructor
	
	
	
	private void procressArrivalEvent(newEvent arrivalEvent,Scanner data,MyListInterface eventList,MyQueueInterface bankQueue)
	//Description: procress an Arrival Event in the event list
	//Precondition: the top element of the event list is an arrival event
	//Postcondition: the bankQueue is updated, the event list is updated
	{	
		try
		{
		//update the bankQueue by inserting the customer
		boolean atFront = bankQueue.isEmpty(); //present queue status
		customer c = new customer(arrivalEvent.getArrivalData());
		bankQueue.enqueue(c);
		//update the event list
		eventList.delete(arrivalEvent);
                System.out.println("Processing an arrival event at time: " + currentTime);
                if(atFront)
		{       
                        
			//the line was empty, so new customer is at front of the line and begins transaction immediately
			//so we insert a departure event into the event list
			//also calculate the overall departure time and transaction time
                        int nextDeparture = currentTime + arrivalEvent.getTransactionTime();
			newEvent dpEvent = new newEvent(nextDeparture);
			eventList.insert(dpEvent);
                        departureTime += nextDeparture;
                        transactionTime += arrivalEvent.getTransactionTime();
		}
		
		if(data.hasNextLine()) //if not at the end of input file (i.e. there are still data in the test input file to be read)
		{       
			String arrivalData = data.nextLine(); //get the next arrival event from the arrivalFile
			newEvent nextArrival = new newEvent(arrivalData); 
			eventList.insert(nextArrival);//add the event to the eventlist
                        arrivalTime += nextArrival.getTimeOfEvent();//calculate the over all arrival time
                }
		}
		catch(QueueException q)
		{
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch (EmptyListException e)
		{	
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch (ElementAlreadyInListException i)
		{	
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch(ElementNotInListException e)
		{
			System.out.println("Error in simulation, simulation now ends.");
		}
	}//end of processArrivalEvent
	
	private void procressDepartureEvent(newEvent departureEvent,MyListInterface eventList,MyQueueInterface bankQueue)
	//Description: process a departure Event in the event list
	//Precondition:the top element of the event list is a departure event
	//Postcondition:the bankQueue is updated, the event list is updated. 
	{
		try
		{
		//Update the line by deleting the front customer
		bankQueue.dequeue();
		//Update the event list by deleting the departureEvent from the event list
		eventList.delete(departureEvent);
                System.out.println("Processing a departure event at time: " + currentTime);
		if(!bankQueue.isEmpty())
		{
			//customer at front of line begins transaction 
			//insert into event list a depature event that corresponds to the customer now at the front of the line
			//also calculate the overall departure time and overall transaction time
                        Queueable customerInfo = bankQueue.peek();
			int nextDepartureTime = currentTime + ((customer)customerInfo).getTransactiontime(); 
			newEvent nextDeparture = new newEvent(nextDepartureTime);
			eventList.insert(nextDeparture);
                        departureTime += nextDepartureTime;
                        transactionTime += ((customer)customerInfo).getTransactiontime();
		}
		}

		catch (EmptyListException e)
		{	
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch(EmptyQueueException e)
		{
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch(ElementAlreadyInListException e)
		{
			System.out.println("Error in simulation, simulation now ends.");
		}
		
		catch(QueueException e)
		{	
			System.out.println("Error in simulation, simulation now ends.");
		}
		catch(ElementNotInListException e)
		{
			System.out.println("Error in simulation, simulation now ends.");
		}
		
	}//end of processDepartureEvent

	
	//Description: Asks the user for the input file and performs the simulation
	//Postcondition: the simulation output is presented             
	public void simulate()
	{
		// Ask the user for a file name:
		theKBD = new Scanner(System.in);		
		System.out.print("Please, enter the nameof a file + path: ");		
		// Read file name in		
		String aFileName = theKBD.nextLine();		
		// Opening file for reading		
		try
		{ 
			fileIn = new FileReader(aFileName); 
		}
		
		catch(FileNotFoundException caughtException) 
		{
			System.out.println("Couldn��t open the file : " + aFileName );
                        System.out.println("Error in simulation, simulation now ends.");
			return; // exit main and stop program
		}
		scanfile = new Scanner(fileIn);
                 System.out.println("Simulation Begins");
                // Reading file
		while(!done) //while events are remained to be processed 
		{
			// Reading next line in the inputfile
			try
			{	
                     
				testInput = scanfile.nextLine(); //get the first arrival event from the input file
				newEvent anEvent = new newEvent(testInput);
				eventList.insert(anEvent); //Place the arrival event in the event list
                                arrivalTime += anEvent.getTimeOfEvent();
				while (eventList.size() !=0)
				{
					newEvent firstEvent = ((newEvent)eventList.retrieveFirstElement());
					currentTime = firstEvent.getTimeOfEvent();
					if (firstEvent.isArrival())
					{
						procressArrivalEvent(firstEvent,scanfile,eventList,bankQueue);
                                                numberOfPeople++;
                                        }
					if(firstEvent.isDepature())
					{
						procressDepartureEvent(firstEvent,eventList,bankQueue);
					}
					
				}
				System.out.println("Simulation Ends");     
                                System.out.println("Total number of people processed: " + numberOfPeople);
                                System.out.println("Average amount of time spent waiting: " + (departureTime-arrivalTime-transactionTime)/numberOfPeople);
			}
			catch(ElementAlreadyInListException e)
			{	
				System.out.println("Error in simulation, simulation now ends.");
			}
		
			
			// What was read was not an integer: eat rest of line
			catch(InputMismatchException caughtException) 
			{
			scanfile.nextLine();
			}
			//Have reach the end of the file
			catch(NoSuchElementException caughtException) 
			{
			done = true;
			}
		}	
	}//end of simulate
	
    public static void main(String[] args) {
        //ask the user to input the test file
    	BSA simulation = new BSA();
    	simulation.simulate(); //start simulation 
        
    }
}//end of BSA
