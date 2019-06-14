/**
 * @(#)AbandonedIsland.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


public class AbandonedIsland extends Island{

    public AbandonedIsland() 
    {
    	super("Abandoned Island");
    }
    
    public void UseIsland(Player p) 
    { 
    	int weapon = p.GetWeapon(); 
    	int upgrade = weapon+1; //upgrade weapon
    	p.SetWeapon(upgrade);   
    	System.out.println(p.GetName() + "'s weapon has been improved to " + p.WeaponName(p.GetWeapon()));
    	
    }
}