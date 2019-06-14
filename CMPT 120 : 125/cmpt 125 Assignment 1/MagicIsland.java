/**
 * @(#)MagicIsland.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */

import java.util.Random;

public class MagicIsland extends Island{

    public MagicIsland() 
    {
    	super("Magic Island");
    }
    

    public void UseIsland(Player p) //I found it unnecessary to use the board in this method, so I did not add the other method as given in the instruction
    {
    	Random generator = new Random();
    	int new_position = generator.nextInt(30) + 1; //generate a number from 1-30 
    	System.out.println(p.GetName() + " has moved to cell#" + new_position);
    	p.SetPosition(new_position); //adjust player`s position according to the generated number
    	
    }
    	
    }
