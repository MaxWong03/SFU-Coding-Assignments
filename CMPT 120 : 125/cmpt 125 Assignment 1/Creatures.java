/**
 * @(#)Creatures.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


abstract public class Creatures {
	protected String name;
	protected int health;
	protected int wealth;
	protected int weapon;
	protected boolean voodoo;

    public Creatures(String nam, int weap, boolean voo) 
    {
    	name = nam;
    	weapon = weap;
    	voodoo = voo;	
    }
    
    public String GetName()
    {
    	return name;
    }
    
    public void SetName(String s)
    {
    	name = s;
    }
    
    public int GetHealth()
    {
    	return health;
    }
    
    public abstract void SetHealth();
    
    public int GetWealth()
    {
    	return wealth;
    }
    
    public abstract void SetWealth();
    
    public int GetWeapon()
    {
    	return weapon;
    }
    
    public void SetWeapon(int w)
    {
    	weapon = w;
    }
    
    public String WeaponName(int w) 
    {	String [] weapons_array = {"Sword", "Pistol", "Rifle", "Cannon", "AK47", "Machine Gun", "Rocket Laucher", "OK! Thats pretty ugly"};
    	String weapon_name = "";
    	if (0 <=w && w <= 6)
    	{
    		weapon_name = weapons_array[w];
    		
    	}
    	if(w>=7)
    	{
    		weapon_name=weapons_array[7];
    		
    	}
    	return weapon_name;
    }
    
    public boolean GetVoodoo()
    {
    	
    	return voodoo;
    }
    
    public void SetVoodoo()
    {
    	voodoo = true;
    }
    
    public void ResetVoodoo()
    {
    	voodoo = false;
    }
}