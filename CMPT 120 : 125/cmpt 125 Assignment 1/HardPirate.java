/**
 * @(#)HardPirate.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */

// hp 51-100 ,gold 51-100 ,50% it will have a voodoo, 50% accept trade, if accept, player lose 

import java.util.Random;
import java.util.Scanner;

public class HardPirate extends Creatures {
	
	boolean tradeResponse;

    public HardPirate() 
    {
    		super("Hard Enemy", 1, false); //1 means hard pirate has 2 die, false means there is no voodoo (will have a 50% to find it later on)
    		SetHealth();
    		SetWealth();
    		Random generator = new Random();
    		int have_voodoo = generator.nextInt(2);// 0 means it can not find a voodoo, 1 means it found a voodoo so there is a 50% chance
    		switch (have_voodoo)
    		{
    			case 0:
    				break;
    				
    			case 1:
    				SetVoodoo();
    				break;
    		}
    		
    }
    
    public void SetHealth()
    {
    	Random generator = new Random();
    	int hp = generator.nextInt(50) +51;//set health from 51-100
    	health = hp;
    }
  
  	public void SetWealth()
  	{
  		Random generator = new Random();
  		int gold = generator.nextInt(50) + 51;//set wealth from 51-100
  		wealth = gold;
  	}
  	
  	public boolean GetTradeResponse(Player p)
  	{	
  		int choice = p.TradeOrFight("Hard Enemy");
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
  	
    public void Trade(Player p)
    {	Random generator = new Random();
    	int chance = generator.nextInt(2);//0 means trade is not accepted, 1 means trade is accepted, 50% chance
 		if (chance ==0)
 		{
 			System.out.println(p.GetName() + "'s trade attempts have been REFUSED! There is only one thing on their mind!!!");
 			Fight(p); //if trade is not accepted then it will fight the player
 		}
 		if (chance == 1)
 		{		
 		
 			int player_wealth = p.GetWealth();
 			int get_gold_from_player = wealth*4;
 			if((player_wealth - get_gold_from_player) <0) //if player doesn`t have enough gold to trade he has to fight
    		{
    		System.out.println("You don`t have enough gold to trade! You have to Fight !");
    		Fight(p);
    		}
    		else
    		{	
 			System.out.println(p.GetName()+"'s trade offer has been accepted. The enemy has been paid " + get_gold_from_player + " gold coins to let " + p.GetName() + "pass through their shores unscathed." );
    		System.out.println(p.GetName()+ " decreases wealth by " + get_gold_from_player + " gold coins.");
    		System.out.println("Hard Enemy increases wealth by " + get_gold_from_player + " gold coins.");
 			p.adjustWealth(player_wealth - get_gold_from_player);
 			}
 		}
    }
    
    public void Fight(Player p) 
    {	if (tradeResponse == false)
    		System.out.println(p.GetName() + " has chosen to FIGHT!");
    	Scanner scan = new Scanner(System.in);
    	if (p.GetVoodoo() == true)//ask player if he wants to use voodoo doll if he has one 
    	{
    	
    		System.out.println(p.GetName() + ", You have a voodoo doll which you can use to curse your\nopponent through black magic. However, BEWARE as it may backfire!\nWould you like to curse the enemy with black magic (0=no, 1=yes)?");
    		int choice = scan.nextInt();
    		while (choice !=0 && choice !=1)//error checking
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
    	if (voodoo == true) //hard pirate uses voodoo doll if it has one 
    	{	
    		System.out.println("Hard Enemy happens to have a voodoo doll and they WILL USE IT on you!");
    		Random generator = new Random();
    		// back fire = 1-25, succeed = 26-100 75%chance succeed, 25%chance back fire
    		int use = generator.nextInt(100) + 1 ;
    		if (use <=25)
    		{
    			System.out.println("Hard Enemy`s voodoo doll has back fired!");
    			System.out.println("Hard Enemy decreases health by " + health/2);
    			health = health/2;
    		}
    			
    		if (use>25)
    		{
    			System.out.println("OH NO! YOU have been cursed! YOUR health potions have been halved! :( ");
    			System.out.println(p.GetName() + " decreases health by " + p.GetHealth()/2);
    			p.adjustHealth(p.GetHealth()/2);
    		}
    	}
    	
    	int player_weapon = p.GetWeapon();//gets the corresponding value of player`s weapon
   		if ( player_weapon <= 6)
   		{
   			player_weapon += 1;  //+1 is for the number of die purposes 
   		}
    	if ( player_weapon >6)
    	{
    		player_weapon = 8;
    	}
    	int player_hp = p.GetHealth();//
    	System.out.println("_____________________________________________\nXXxxXXxxXXxxXXxxXX  FIGHT  XXxxXXxxXXxxXXxxXX");
    	int round =1;
    	while (player_hp > 0 && health > 0)
    	{
    			System.out.println("~~~~~Round: " + round + "~~~~~");
    			System.out.println("++" + p.GetName() + " Health: " + p.GetHealth() + " potions.");
    			System.out.println("++Enemy Health: " + health + " potions.");
    			Random generator = new Random();
    			int [] player_attack = new int [player_weapon]; //array that stores player`s throws
    			int [] pirate_attack = new int[2]; //array that stores hard pirate`s throws
    			int player_highest_attack = 0;
    			int pirate_highest_attack = 0;
    			for (int i =0; i < player_attack.length; i++) //player throws
    			{
    				player_attack[i] = generator.nextInt(6)+1;
    				
    			}
    			if (player_attack.length == 1) //if player only has a sword, then he only has one throw, and that will be his highest attack
    				player_highest_attack = player_attack[0];
    					
    			else
    				for (int i =1; i<player_attack.length; i++) //determines player`s highest attack
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
    			for (int i =0; i < pirate_attack.length; i++)//hard pirate throws
    			{
    				pirate_attack[i] = generator.nextInt(6)+1;
    			
    			}
    			for (int i =1; i<pirate_attack.length; i++) //determine hard pirate`s highest attack
    			{
    				if (pirate_attack[i] > pirate_attack[i-1])
    					pirate_highest_attack = pirate_attack[i];
    				if (pirate_attack[i] < pirate_attack[i-1])
    					pirate_highest_attack = pirate_attack[i-1];
    				if (pirate_attack[i] == pirate_attack[i-1])
    					pirate_highest_attack = pirate_attack[i];
    					
    					
    				
    			}
    			System.out.println(p.GetName() + " has thrown: " +player_highest_attack );
    			System.out.println("Hard Enemy has thrown: " + pirate_highest_attack);
    			if (player_highest_attack > pirate_highest_attack) //player wins round
    			{
    				health = health - (player_highest_attack)*5;
    				System.out.println(p.GetName() + " has won this round!");
    				System.out.println("Hard Enemy decreases health by " + (player_highest_attack)*5 + "potions." );
    			}	
    			if (player_highest_attack<	pirate_highest_attack) //hard pirate wins round
    			{
    				player_hp = player_hp - pirate_highest_attack*5;
    				p.adjustHealth(player_hp);
    				System.out.println("Hard Enemy has won this round!");
    				System.out.println(p.GetName() + " decreases health by " + pirate_highest_attack*5 + "potions.");
    			}
    			if (player_highest_attack == pirate_highest_attack )//tie
    			{	
    				System.out.println("Its a TIE.");
    				health = health;
    				player_hp = player_hp;
    				p.adjustHealth(player_hp);
    			}
    			
    	
    			
    			System.out.println("~~~~~~~~~~~~~~~~~~");
    			round +=1;
    			if (player_hp > 0 && health <=0) //player wins the battle. get weapon upgraded, taking golds, and get a ship
    			{
    			System.out.println(p.GetName() + " has won the BATTLE!");
    			System.out.println(p.GetName() + " increases wealth by " + wealth + "gold coins.");
    			p.adjustWealth(p.GetWealth() + wealth);
    			System.out.println(p.GetName() + "'s weapon has been improved to " + p.WeaponName(p.GetWeapon()+1));
    			p.SetWeapon(p.GetWeapon()+1);
    			System.out.println(p.GetName() + " has found a ship.");
    			p.SetShip();
    			}
    			
    			if (player_hp <= 0 && health > 0) //player loses 
    			{
    				System.out.println("Oh no you die!.");
    			} 
    	
    	}
    }
   
}