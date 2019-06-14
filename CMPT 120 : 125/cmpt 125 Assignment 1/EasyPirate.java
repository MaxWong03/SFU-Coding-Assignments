/**
 * @(#)EasyPirate.java
 *
 *
 * @author 
 * @version 1.00 2013/3/7
 */


// always accept an ofer of a trade
//wants a player to double their teasre
//has 1 die
//hp 1-50
//gold 1-50
import java.util.Random;
import java.util.Scanner;
public class EasyPirate extends Creatures{

	boolean tradeResponse;

    public EasyPirate()
    {
    	super("Easy Enemy", 0, false); //0 means Easy pirate has 1 die, false means Easy pirate has no voodoo doll
    	SetHealth();
    	SetWealth();
    }

    public void SetHealth()
    {
    	Random generator = new Random(); //set health from 1-50
    	int hp = generator.nextInt(50) + 1;
    	health = hp;
    }

    public void SetWealth()
    {
    	Random generator = new Random();//set wealth from 1-50
    	int gold = generator.nextInt(50) + 1;
    	wealth = gold;
    }

    public boolean GetTradeResponse(Player p) 
    {
    	int choice = p.TradeOrFight("Easy Enemy");
    	switch (choice)
  		{
  			case 0:
  				tradeResponse = true;
  				break;
  			case 1:
  				tradeResponse = false;
  				break;
  		}
  		return tradeResponse;
    	
    }


    public void Trade(Player p)//easy enemny always accept trade offer, so no need to generate a random value
    	
    {	
    	int player_wealth = p.GetWealth(); 
    	int get_gold_from_player = wealth;
    	if((player_wealth - get_gold_from_player) <0) //if player doesn`t have enough gold to trade he has to fight
    	{
    		System.out.println("You don`t have enough gold to trade! You have to Fight !");
    		Fight(p);
    	}
    	else 
    	{
    	System.out.println(p.GetName()+"'s trade offer has been accepted. The enemy has been paid " + get_gold_from_player + " gold coins to let " + p.GetName() + "pass through their shores unscathed." );
    	System.out.println(p.GetName()+ " decreases wealth by " + get_gold_from_player + " gold coins.");
    	System.out.println("Easy Enemy increases wealth by " + get_gold_from_player + " gold coins.");
    	p.adjustWealth(player_wealth - get_gold_from_player );	
    	}
    }

    public void Fight(Player p) 
    {	
    	System.out.println(p.GetName() + " has chosen to FIGHT!");
    	Scanner scan = new Scanner(System.in);
    	if (p.GetVoodoo() == true) //ask player if he wants to use a voodoo if he has one 
    	{
    	
    		System.out.println(p.GetName() + ", You have a voodoo doll which you can use to curse your\nopponent through black magic. However, BEWARE as it may backfire!\nWould you like to curse the enemy with black magic (0=no, 1=yes)?");
    		int choice = scan.nextInt();
    		while (choice !=0 && choice !=1) // Error checking
    		{
    			System.out.println("Error Input! Would you like to curse the enemy with black magic (0=no, 1=yes)?");
    			choice = scan.nextInt();
    		}
    		if (choice == 1)
    		{
    			boolean succeed = p.DoBlackMagic();
    			if (succeed == true)
    				{
    				System.out.println("Opponent has been cursed! Their health potions have been halved!\nNice :)!");
    				System.out.println("Easy Enemy has decreases health by " + health/2 + " potions.");
    				health = health/2;
    				}
    			
    			else 
    			{
    				System.out.println("OH NO! YOU have been cursed! YOUR health potions have been halved!\n:(");
    				System.out.println(p.GetName() + " decreases health by " + p.GetHealth()/2);
    				p.adjustHealth(p.GetHealth()/2);
    			}
    				
    		}
    	}
    	int player_weapon = p.GetWeapon(); //gets the corresponding value of player`s weapon
   		if ( player_weapon <= 6)  
   		{
   			player_weapon += 1;  //+1 is for the number of die purposes 
   		}
    	if ( player_weapon >6)
    	{
    		player_weapon = 8; 
    	}
    	int player_hp = p.GetHealth(); //gets player hp
    	System.out.println("_____________________________________________\nXXxxXXxxXXxxXXxxXX  FIGHT  XXxxXXxxXXxxXXxxXX");
    	int round =1;
    	while (player_hp > 0 && health > 0)
    		{	
    			System.out.println("~~~~~Round: " + round + "~~~~~");
    			System.out.println("++" + p.GetName() + " Health: " + p.GetHealth() + " potions.");
    			System.out.println("++Enemy Health: " + health + " potions.");
    			Random generator = new Random();
    			int attack = generator.nextInt(6) + 1; //easy pirate throws
    			int [] player_attack = new int [player_weapon];//array that stores player`s throw
    			int player_highest_attack = 0;
    			for (int i = 0; i < player_attack.length; i++) //player throws
    			{
    				player_attack[i] = generator.nextInt(6) + 1;
    				
    			}
    			if (player_attack.length == 1) //if player only has a sword, then he only has one throw, and that will be his highest attack
    				player_highest_attack = player_attack[0];
    					
    			else
    				for (int i =1; i<player_attack.length; i++) //determine which attack is the higest attack for the player
    					{		
    						{	
    							if (player_attack[i-1] > player_attack[i])
    								player_highest_attack = player_attack[i-1];
    							if (player_attack[i-1] < player_attack[i])
    								player_highest_attack = player_attack[i];
    							if (player_attack[i-1] == player_attack[i])
    								player_highest_attack = player_attack[i];
    						}
    					}
    				
    			System.out.println(p.GetName() + " has thrown: " +player_highest_attack );
    			System.out.println("Easy Enemy has thrown: " + attack);
    			if (player_highest_attack > attack) //player wins round
    			{
    				health = health - (player_highest_attack)*5;
    				System.out.println(p.GetName() + " has won this round!");
    				System.out.println("Easy Enemy decreases health by " + (player_highest_attack)*5 + "potions." );
    			}	
    			if (player_highest_attack<attack) //easy pirate wins round
    			{
    				player_hp = player_hp - (attack)*5;
    				p.adjustHealth(player_hp);
    				System.out.println("Easy Enemy has won this round!");
    				System.out.println(p.GetName() + " decreases health by " + attack*5 + "potions.");
    			}
    			if (player_highest_attack == attack) //tie
    			{	
    				System.out.println("Its a TIE.");
    				health = health;
    				player_hp = player_hp;
    				p.adjustHealth(player_hp);
    			}
    			
    	
    			
    			System.out.println("~~~~~~~~~~~~~~~~~~");
    			round +=1;
    		}
    		
    	if (player_hp > 0 && health <= 0) //player wins the battle, taking the gold coins of easy pirate and get weapon upgraded
    			
    		{
    			System.out.println(p.GetName() + " has won the BATTLE!");
    			System.out.println(p.GetName() + " increases wealth by " + wealth + "gold coins.");
    			p.adjustWealth(p.GetWealth() + wealth);
    			System.out.println(p.GetName() + "'s weapon has been improved to " + p.WeaponName(p.GetWeapon()+1));
    			p.SetWeapon(p.GetWeapon()+1);
    		}
    		
    	if (player_hp <= 0 && health > 0) //player loses 
    		{
    			System.out.println("Oh no you die!.");
    		} 
    	
    }
}

