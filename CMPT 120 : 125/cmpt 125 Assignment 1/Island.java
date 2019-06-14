/**
 * @(#)Island.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


abstract public class Island {
	protected String name;

    public Island(String s) 
    {
    	name = s;
    }
    
    public String GetName()
    {	
  
    	return name;
    }
    
    public void SetName(String s)
    {
    	name = s;
    }
    
    public abstract void UseIsland(Player p);
}