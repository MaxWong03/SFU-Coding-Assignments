/**
 * @(#)MysteryIsland.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


public class MysteryIsland extends Island {

    public MysteryIsland() 
    {
    	super("Mystery Island");
    }
    
    public void UseIsland(Player p)
    {
    	p.SetVoodoo(); // set voodoo to true for the player
    	System.out.println(p.GetName() +  " has found a Voodoo doll. ");
    }
    
}