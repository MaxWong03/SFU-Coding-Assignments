/**
 * @(#)Haar_Wavelet_Transformation.java
 *
 *
 * @author 
 * @version 1.00 2013/4/6
 */

import java.util.Random;
import java.util.ArrayList;

public class Haar_Wavelet_Transformation {
	
	private static ArrayList<Integer> sequence = new ArrayList<Integer> ();
	private static ArrayList<Integer> transformed_sequence = new ArrayList<Integer>();
	private static ArrayList<Integer> transformed_sequence_2 = new ArrayList<Integer>();
	int half;
	int L = 1;
    
   //basically the fromula for this transformation is H(half of H(n-1)), if n = 1, we just do a level 1 transformation
   // if n>1, we first do a level 1 transformation first, and then take half of it and do a level 1 transformation, and so on 
   //so basically we do level 1 transformation n times and do it on half of the sequence obtained from the previous level
   
   
   public ArrayList<Integer> HarrTransformation(ArrayList<Integer> s, int level)
   {
   	
   	int size = s.size();

   	if (level ==1) //leve 1 transformation , base case
   	{
   		
   		int sum = 0;
   		int startingpoint = 0;
   		
   		for(int i = 0; i < size; i ++) //determine the sum of adjacent values
   		{
   			
   			sum = s.get(i) + s.get(i+1);
   			transformed_sequence.set(startingpoint, sum);
   			startingpoint++;
   			i++;
   			
   		}
   		
   		int diff;
   		int starting_point = size/2;
   		for(int i = 0; i<size; i++) //determine the difference of adjacent values
   		{
   			diff = s.get(i)- s.get(i+1);
   			transformed_sequence.set(starting_point,diff);
   			starting_point++;
   			i++;
   		}
   		System.out.print("Sequence after Transformation level " + L + " transformation is : " ); //display the sequence after transformation
   		L++;
   		for (int integer : transformed_sequence)
    		System.out.print( integer + " ");
    	System.out.println(" " );
   	
   		
   		return transformed_sequence;
   	}
   	
   	else //recursive case 
   	{	
   		transformed_sequence = HarrTransformation((cut(HarrTransformation(s,level-1))),1);
   		return transformed_sequence;
   		
   	}
   	
   }
   
   private ArrayList<Integer> cut(ArrayList<Integer> s) //this is the method i add on my own, it takes half of the sequence we obatined from the previous level
   {		

   		if(transformed_sequence_2.size()==0)
   			{
   			for(int i =0; i < half; i++)
   				transformed_sequence_2.add(s.get(i));
   			}	
   		else
   			{
   			transformed_sequence_2.clear();
   			for(int i =0; i < half; i++)
   				transformed_sequence_2.add(s.get(i));
   			}
   	
   		half = half/2; // everytime this method is used, half = half/2, so when it runs for the second time  we take 1/4 of the sequence (level 3 transformation), and (1/8) of the sequence if it is run for the third time (level 4 transformation)
   		return transformed_sequence_2;
   }
   
    public int levels_of_transformation(ArrayList<Integer> s) //generate the level of the transforamtion
    {
    	//if size of (sequence is 2, max level =1)  (size = 4, max level = 2), (size = 8, max leve =3), (size = 16, max level=4)
    	int size = s.size();
    	int level = 0;
    	Random generator = new Random();
    	switch(size)
    	{
    		case 2:
    			level = 1;
    			break;
    		case 4:
    			level = generator.nextInt(2)+1; //from 1 -2 
    			break;
    		case 8:
    			level = generator.nextInt(3)+1; //from 1 -3 
    			break;
    		case 16:
    			level = generator.nextInt(4)+1; //from 1 - 4
    			break;
    	}
    	
    	//display the level 
    	System.out.println("The level of transformation is : " + level);
    	
    	return level;
    	
    }
    
    public ArrayList<Integer> GenerateSequence() //generate a sequence
    {
    	//y:0 = 2, y:1 = 4, y:2 = 8, y:3 = 16
    	Random generator = new Random();
    	int y = generator.nextInt(4); //from 0-3
    	switch(y)
    	{
    		case 0:
    			y = 2;
    			break;
    		case 1:
    			y = 4;
    			break;
    		case 2:
    			y = 8;
    			break;
    		case 3:
    			y = 16;
    			break;
    	}
    	for (int i =0; i < y; i ++)
    	{
    		int x = generator.nextInt(100) + 1;
    		sequence.add(x);
    		
    	}
    	
    	copy_sequence(sequence);
    	//display the sequence
    	System.out.print ("the sequence before the transformation : ");
    	for (int integer : sequence)
    		System.out.print(integer + " ");
    
    	return sequence;
    }
	private void copy_sequence(ArrayList<Integer> s) 
	{
		for (int integer : s)
			transformed_sequence.add(integer);
		half = transformed_sequence.size() / 2 ; //this is for the cut method
	}
    
}