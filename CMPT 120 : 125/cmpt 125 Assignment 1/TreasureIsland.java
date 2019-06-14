/**
 * @(#)TreasureIsland.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */

import java.util.Random;

public class TreasureIsland extends Island {

    public TreasureIsland() 
    {
    	super("Treasure Island");
    }
    
    public void UseIsland(Player p)
    {
    	Random generator = new Random();
    	int give_gold = generator.nextInt(30) + 1 ; // generate a number between 1-30, and add that to player`s wealth
    	System.out.println(p.GetName()+ " increases wealth by " + give_gold + "gold coins.");
    	int player_wealth = p.GetWealth();
    	p.adjustWealth(player_wealth + give_gold);
    }
    
}