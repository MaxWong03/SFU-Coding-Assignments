/**
 * @(#)Player.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


import java.util.Random;
import java.util.Scanner;

public class Player extends Creatures {
	boolean ship;
	int position;

    public Player(String s)
    {
    	super(s,0, false);//s = name, 0= intial weapon = sword, false means that there is no voodoo at the beginning
    	SetHealth();
    	SetWealth();
    }

    public void SetHealth()
    {
    	health = 500;//starting health is 500
    }

    public void SetWealth()
    {
    	wealth = 500; //starting wealth is 500
    }
    
    public void adjustHealth(int hp)//method i added, adjusts player`s hp
    {
    	health = hp;
    }
    public void adjustWealth(int g)//method i added, adjusts player`s gold
    {
    	wealth = g;
    }

    public boolean GetShip()
    {

    	return ship;
    }

    public void SetShip()
    {
    	ship = true;

    }

    public void ResetShip()
    {
    	ship = false;

    }

    public int GetPosition()
    {
    	return position;
    }

    public void SetPosition(int p)
    {
    	position = p;
    }

    public void Move()
    {
    	Random die = new Random();
    	int move_space = die.nextInt(6) + 1 ;//generate a value from 1-6
    	if (ship == true)
    		position = position + (move_space) * 2; //if player has a ship, it move tice the value that was created previously
    	else
    		position += move_space;
    	System.out.println(name + " has rolled " + move_space + ".\n" + name + " has moved to cell#" + position); 
    }

    public int TradeOrFight(String enemyName)
    {	int choice;
    	Scanner scan = new Scanner(System.in);
     	System.out.println (name + " encounters " + enemyName + " pirate!");
    	System.out.println("Would you like to offer to trade OR fight with this enemy (0=trade, 1=fight)?");
    	choice = scan.nextInt();
    	while (choice != 0 && choice !=1)//error checking
    	{
    		System.out.println("Error in Input! Would you like to offer to trade OR fight with this enemy (0=trade, 1=fight)? ");
    		choice = scan.nextInt();
    	}
    	return choice;
    }

    public boolean DoBlackMagic() 
    {	
    	boolean succeed = false;
    	System.out.println(name + " has no Voodoo Doll");//tells the player he has no voodoo after using it 
    	Random generator = new Random();
 		int use = generator.nextInt(100) + 1 ;//generates a number from 1-100
 		if (use<=25)                          //1-25 = backfire
 			succeed = false;
 		if (use>25)                          //26-100 = succeed, so the chance of back fire is 25% and chance of succeed is 75% as stated in the instruction
 			succeed = true;
    	voodoo = false;//set voodoo to false after player using it 
    	return succeed;
    }

    public void Reinforcement() 
    {	
    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n$$$$$$$$$$$$$ REINFORCEMENTS $$$$$$$$$$$$$$$$$\n\n\n");
    	while(wealth > 0)
    	{
    		System.out.println(name+", you have " + wealth + " gold coins.\n");
    		System.out.println("$$$$$$$$  RATES  $$$$$$$$\n1 health potions for 1 gold coin\nAn improved weapon for 150 gold coins\nNon-refundable :)\n$$$$$$$$$$$$$$$$$$$$$$$$$\n");
    		if (wealth < 150 ) //once player`s wealth is less than 150 then it will give him health potions according to his wealth
    		{
    			health += wealth;
    			System.out.println(name + " has increases health by " + wealth + "potions.");
    			System.out.println(name + " has decreases wealth by " + wealth + "gold coins.\n\n\n");
    			wealth = 0;
    			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    			break;
    		}
    		Scanner scan = new Scanner(System.in);
    		System.out.println("What would you like to buy (0=nothing/done, 1=health potions, 2=better weapon)?");
    		int choice = scan.nextInt();
    		while (choice != 0 && choice !=1 && choice !=2) //Error checking
    		{
    			System.out.println("Error in Input!What would you like to buy (0=nothing/done, 1=health potions, 2=better weapon)?");
    			choice = scan.nextInt();
    		}
    		if (choice ==0)
    			break;
    		if (choice == 1 && wealth >=150)
    		{
    			
    			System.out.println("How many health potions would you like? ");
    			int hp_potion = scan.nextInt();
    			while (hp_potion <0 || hp_potion > wealth) //Error checking
    				{
    					System.out.println("Error in Input! How many health potions would you like? (cannot be negative or more than your gold coins)");
    					hp_potion = scan.nextInt();
    				}
    			wealth -= hp_potion;
    			health += hp_potion;//give player health potion
    			System.out.println(name + " increases health by " + hp_potion + " potions.");
    			System.out.println(name + " decreases wealth by " + hp_potion + " gold coins.\n");
    		}
    		
    			
    		if (choice ==2 && wealth>=150)
    		{
    		
    				wealth -= 150;
    				weapon += 1;//upgrade weapon
    				System.out.println(name + "'s weapon has been improved to " + WeaponName(weapon));
    				System.out.println(name + " decreases wealth by 150 gold coins.\n");
    				
    			
    		}
    		
    	
    	}
    	


    }
    
    public boolean Fight(Player p)
    {	
    	Scanner scan = new Scanner(System.in);
    	if (voodoo == true) //player`s turn to use the voodoo if he has one
    	{
    		System.out.println(name + ", You have a voodoo doll which you can use to curse your\nopponent through black magic. However, BEWARE as it may backfire!\nWould you like to curse the enemy with black magic (0=no, 1=yes)?");
    		int choice = scan.nextInt();
    		while (choice !=0 && choice !=1)
    		{
    			System.out.println("Error Input! Would you like to curse the enemy with black magic (0=no, 1=yes)?");
    			choice = scan.nextInt();
    		}
    		if (choice == 1)
    		{
    			boolean succeed = DoBlackMagic();
    			if (succeed == true) //succeed loop
    				{
    				System.out.println("Opponent has been cursed! Their health potions have been halved!\nNice :)!");
    				System.out.println(p.GetName() + " decreases health by " + p.GetHealth()/2 + " potions.");
    				p.adjustHealth(p.GetHealth()/2);
    				}
    			
    			else //back fire loop
    			{
    				System.out.println("OH NO! YOU have been cursed! YOUR health potions have been halved!\n:(");
    				System.out.println(name + " decreases health by " +health/2 + " potions.");
    				health = health/2;
    			}
    				
    		}
    	}
    	
    	if (p.GetVoodoo() == true) //opponent`s turn to use voodoo if he has one
    	{
    	
    		System.out.println(p.GetName() + ", You have a voodoo doll which you can use to curse your\nopponent through black magic. However, BEWARE as it may backfire!\nWould you like to curse the enemy with black magic (0=no, 1=yes)?");
    		int choice = scan.nextInt();
    		while (choice !=0 && choice !=1)
    		{
    			System.out.println("Error Input! Would you like to curse the enemy with black magic (0=no, 1=yes)?");
    			choice = scan.nextInt();
    		}
    		if (choice == 1)
    		{
    			boolean succeed = p.DoBlackMagic();
    			if (succeed == true) //succeed loop
    				{
    				System.out.println("Opponent has been cursed! Their health potions have been halved!\nNice :)!");
    				System.out.println(name + " has decreases health by " + health/2 + " potions.");
    				health = health/2;
    				}
    			
    			else  //back fire loop
    			{
    				System.out.println("OH NO! YOU have been cursed! YOUR health potions have been halved!\n:(");
    				System.out.println(p.GetName() + " decreases health by " + p.GetHealth()/2);
    				p.adjustHealth(p.GetHealth()/2);
    			}
    				
    		}
    	}
    	
    	System.out.println("_____________________________________________\nXXxxXXxxXXxxXXxxXX  FIGHT  XXxxXXxxXXxxXXxxXX");
    	int opponent_weapon = p.GetWeapon(); //gets the corresponding value of opponent`s weapon
    	int opponent_hp = p.GetHealth();//gets the value of opponent`s health
    	if (weapon <= 6)
    	{
    		weapon +=1; //+1 is for the number of die purposes, anything that is below ok thats pretty ugly will have +1
    	}               //for example, sword has a value of 0, so it has 0+1 number of die
    	if( weapon >6 )
    	{
    		weapon = 8; //anything that is equal to or greater than ok thats pretty ugly will have 8 die
    	}
    	if ( opponent_weapon <= 6)
   		{
   			opponent_weapon += 1; 
   		}
    	if ( opponent_weapon >6)
    	{
    		opponent_weapon = 8;
    	}
    	int round =1;
    	Random generator = new Random();
    	
    	while (health > 0 && opponent_hp > 0) //fight will continue until one of the player hp is =0 or falls below 0
    	{		
    			int [] attack = new int [weapon]; //create an array that is the size of player`s weapon value , it stores player`s throw
    			int [] opponent_attack = new int [opponent_weapon];
    			String throw_1 = "{" ;
    			String throw_2 = "{" ;
    			for (int i = 0; i < attack.length ; i++) //throwing 
    			{
    				attack[i] = generator.nextInt(6)+1;
    			
    				
    			}
    			for (int pos = 0; pos < attack.length; pos++) //sortting
    			{	
    				int small = attack[pos];//smallest value seen so far
    				int large = attack[pos]; // largest value seen so far 
    				int largepos = pos; //position of large in array
    				for (int i = pos+1; i < attack.length; i ++)
    				{
    					if (attack[i] > large)
    					{	
    						small = small;                  
    						large = attack[i];
    						largepos = i;
    					}
    					if (attack[i] == large)
    					{
    						large = large;
    						small = small;
    						largepos = largepos;
    					}
    					if (attack[i] < large)
    					{
    						large = large;
    						small = small;
    						largepos = largepos;
    						
    					}
    				}
    				attack[pos] = attack[largepos];
    				attack[largepos] = small;
    				
    		
    			}
    		
    			for (int i =0; i < attack.length; i++) // this is for printing the value player has thrown
    			{
    				if (i != attack.length-1)	      //is for the purposing of eliminating "," for the last value
    					throw_1 += attack[i] +",";
    				if (i == attack.length-1)
    					throw_1 += attack[i]; 
    			}
    			for (int i =0; i < opponent_attack.length; i++) //opponent throws
    			{
    				opponent_attack[i] = generator.nextInt(6)+1;
    			
    			}
    			for (int pos = 0; pos < opponent_attack.length; pos++) //sorting 
    			{	
    				int small = opponent_attack[pos];//smallest value seen so far
    				int large = opponent_attack[pos]; // largest value seen so far 
    				int largepos = pos; //position of large in array
    				for (int i = pos+1; i < opponent_attack.length; i ++)
    				{
    					if (opponent_attack[i] > large)
    					{	
    						small = small;  
    						large = opponent_attack[i];
    						largepos = i;
    					}
    					if (opponent_attack[i] == large)
    					{
    						large = large;
    						small = small;
    						largepos = largepos;
    					}
    					if (opponent_attack[i] < large)
    					{
    						large = large;
    						small = small;
    						largepos = largepos;
    						
    					}
    				}
    				opponent_attack[pos] = opponent_attack[largepos];
    				opponent_attack[largepos] = small;
    				
    		
    			}	
    			
    			for (int i =0; i < opponent_attack.length; i++) 
    			{
    				if (i != opponent_attack.length-1)	
    					throw_2 += opponent_attack[i] +",";
    				if (i == opponent_attack.length-1)
    					throw_2 += opponent_attack[i]; 
    			}
    			
    			System.out.println("~~~~~Round: " + round + "~~~~~");
    			System.out.println("++" + name + " Health: " + health + " potions.");
    			System.out.println("++" + p.GetName() + " Health: " + p.GetHealth() + " potions.");
    			System.out.println(name + " has thrown:" + throw_1 +"}");
    			System.out.println(p.GetName() + " has thrown;" + throw_2 + "}");
    			
    			int num_of_die =0;                 //determine which player`s weapon corresponde to a lesser value 
    			if (opponent_weapon > weapon)      //and use that value to determine the number of strike there will be in the fight
    				num_of_die = weapon;
    			if(opponent_weapon < weapon)
    				num_of_die = opponent_weapon;
    			if (opponent_weapon == weapon)
					num_of_die = weapon;   
						
				for (int i =0; i<num_of_die; i++)
				{	
					if (health > 0 && opponent_hp > 0)
					{
						if (attack[i] > opponent_attack[i]) //if player attack is greater than the opponent, player strikes
						{
						System.out.println("***Strike " + (i+1) + ":" + name + " does damage!");
						int take_away_hp = attack[i]*10; //attack *10
						System.out.println(p.GetName() + " decreases health by " + take_away_hp + " potions.");
						opponent_hp -=take_away_hp;
						p.adjustHealth(opponent_hp);
						
						}
					
						if (attack[i] < opponent_attack[i]) //if opponent`s attack is greater than the player, opponent strikes
						{
						System.out.println("***Strike " + (i+1) + ":" + p.GetName()+ " does damage!");
						int take_away_hp = opponent_attack[i] *10;//attack *10
						System.out.println(name + " decreases health by " + take_away_hp+ " potions.");
						health -= take_away_hp;
						}
					
						if (attack[i] == opponent_attack[i]) //both suffer from strike
						{	
						System.out.println("***Strike " + (i+1) + ": BOTH suffer blows!!!.");
						int take_away_hp = attack[i] *10;//attack *10
						System.out.println(name + " decreases health by " + take_away_hp+ " potions.");
						System.out.println(p.GetName() + " decreases health by " + take_away_hp + " potions.");
						opponent_hp -= take_away_hp;
						p.adjustHealth(opponent_hp);
						health -= take_away_hp;
						}
					}
				} 
				round +=1 ;	
				System.out.println("~~~~~~~~~~~~~~~~~~");
							
    		}
    			return (true == (p.GetHealth() <=0));//return true if opponent`s health is less than or equal to 0, true = player win
    }                                                                   //false = opponent win 

}