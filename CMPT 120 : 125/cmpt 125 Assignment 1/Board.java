/**
 * @(#)Board.java
 *
 *
 * @author 
 * @version 1.00 2013/3/6
 */


import java.util.Scanner;
import java.util.Random;

public class Board {
	
	final int Size = 30;  //board has a size of 30
	final int Num_Players = 2;  //2 players
	int diffLevel;
	Player[] players;
	char[] hurdles;

    public Board() 
    {	
    	System.out.println("|||||||||||||||||||||||||||||||||||||||||||||\n||||||||||||||||||||START||||||||||||||||||||\n|||||||||||||||||||||||||||||||||||||||||||||");
    	SetDifficulty(); // set difficulty 
    	players = new Player [Num_Players];
    	for (int i =1; i<=Num_Players; i++)  //get the name of the players, and initialize the player class with those name
    	{
    		players[i-1] = new Player(GetPlayerName(i));
    	}
    	
    	DisplayPlayerDetails(); //display player details
    	SetHurdles();           //set up the board
    	DisplayGameBoardLegend();  //display game board legend
   		DisplayBoard();           //display game board
    	
    }

    	public void SetDifficulty() //ask what level player wants to play in 
    	{	Scanner scan = new Scanner(System.in);
    		System.out.println("Enter Diffculty Level for the Game (0=easy, 1=50/50 i.e. not too easy,\nnot too hard, 2=HARD)"); 
    		diffLevel = scan.nextInt();
    		while (diffLevel !=0 && diffLevel !=1 && diffLevel !=2) //Error checking
    		{
    			System.out.println("Error Input! Enter Diffculty Level for the Game (0=easy, 1=50/50 i.e. not too easy,\nnot too hard, 2=HARD)");
    			diffLevel = scan.nextInt();
    		}
    		
    	}
    
    	public String GetPlayerName(int index) //get the name of the player 
    	{
    		String name;
    		Scanner scan = new Scanner(System.in);
    		System.out.println("Enter Player " + index + "'s name:" );
    		name = scan.nextLine();
    		return name;
    	}
    	
    	public void SetHurdles() //12 enemy(0 = all easy, 1 = half easy half hard, 2 = all hard) , 6 treasure island, 6 abandoned island , 3 mystery island, 3 magic island
    	{
    		hurdles = new char [Size];
    		char [] cells = {'e','h','t','a','m','j'}; //char of the cells
    		int e=0, h=0, t=0, a=0, m=0, j=0; //this works as a counter, to see how many times each cell is created 
    		Random generator = new Random();
    		if (diffLevel == 0) // easy mode
    			{
    				while (e <12) //make sure easy enemy is created 12 times 
    				{
    					int repeated =0;
    					int i = generator.nextInt(30); //randomly assign it from 0-29 on the board 
						for (char cell : cells)
						{	
							if (hurdles[i] == cell)
							{
								repeated += 1; //to see if the assigning position already has a cell in it or not, repeated >= 1 means there is 
							}
						}
						if (repeated ==0)
						{
						hurdles[i] = cells[0];// only assign cells to position where there is no cells 
						e +=1;
						}	
				
					}
    				
    			}
    			
    		if (diffLevel == 1) //   50/50 mode 
    			{
    				while (e <6) //6 easy enemy
    				{
    					int repeated =0;
    					int i = generator.nextInt(30);
						for (char cell : cells)
						{	
							if (hurdles[i] == cell)
							{
								repeated += 1;
							}
						}
						if (repeated ==0)
						{
						hurdles[i] = cells[0];
						e +=1;
						}	
				
					}
					
					while (h <6) //6 hard enemy
    				{
    					int repeated =0;
    					int i = generator.nextInt(30);
						for (char cell : cells)
						{	
							if (hurdles[i] == cell)
							{
								repeated += 1;
							}
						}
						if (repeated ==0)
						{
						hurdles[i] = cells[1];
						h +=1;
						}	
				
					}
    					
    			}
    		if (diffLevel == 2) //hard mode 
    			{
    				while (h <12) //12 hard enemy
    				{
    					int repeated =0;
    					int i = generator.nextInt(30);
						for (char cell : cells)
						{	
							if (hurdles[i] == cell)
							{
								repeated += 1;
							}
						}
						if (repeated ==0)
						{
						hurdles[i] = cells[1];
						h +=1;
						}	
				
					}
    				
    			}
    				
    		
    		
    		while (t < 6) //6 treasure island
    		{	
    			int repeated =0;
    			int i = generator.nextInt(30);
				for (char cell : cells)
				{	
					if (hurdles[i] == cell)
					{
						repeated += 1;
					}
				}
				if (repeated ==0)
				{
					hurdles[i] = cells[2];
					t +=1;
				}	
				
			}
			
			while (a < 6) //6 abandoned island
    		{	
    			int repeated =0;
    			int i = generator.nextInt(30);
				for (char cell : cells)
				{	
					if (hurdles[i] == cell)
					{
						repeated += 1;
					}
				}
				if (repeated ==0)
				{
					hurdles[i] = cells[3];
					a +=1;
				}	
				
			}
			
			while (m < 3) //3 mystery island
    		{
    			int repeated =0;
    			int i = generator.nextInt(30);
				for (char cell : cells)
				{	
					if (hurdles[i] == cell)
					{
						repeated += 1;
					}
				}
				if (repeated ==0)
				{
					hurdles[i] = cells[4];
					
					m +=1;
				}	
				
			}
			
			while (j < 3) //3 magic island
    		{
    			int repeated =0;
    			int i = generator.nextInt(30);
				for (char cell : cells)
				{	
					if (hurdles[i] == cell)
					{
						repeated += 1;
					}
				}
				if (repeated ==0)
				{
					hurdles[i] = cells[5];
					j +=1;
				}	
				
			}
			
			
    	}
    
    	
    	public void DisplayPlayerDetails() //display player details
    	{
    		    	
    	System.out.println("-------------  PLAYER  DETAILS  -------------");
    	
    	for (int i =1; i<=Num_Players; i++)
    	{
    		System.out.println("\t\t-->Player " + i +"<--");
    		System.out.println("Name: " + players[i-1].GetName());
    		System.out.println("Health: " + players[i-1].GetHealth() + " potions");
    		System.out.println("Treasure: " + players[i-1].GetWealth() + " gold coins");
    		System.out.println("Weapon: " + players[i-1].WeaponName(players[i-1].GetWeapon()));
    		System.out.println("Voodoo Doll: " + players[i-1].GetVoodoo());
    		System.out.println("Ship: " + players[i-1].GetShip());
    		System.out.println("Position: " + players[i-1].GetPosition());
    		System.out.println("\t\t------><------");
    		System.out.println("---------------------------------------------");
    		
    	}
    	}
    	
    	public void DisplayGameBoardLegend() //display the game board legend
    	{
    		System.out.println("---------------------------------------------  \n<<<--GAME BOARD LEGEND-->>>");
    		System.out.println("e = This cell contains an easy enemy pirate.");
    		System.out.println("h = This cell contains a hard enemy pirate.");
    		System.out.println("t = This cell contains a treasure island.");
    		System.out.println("a = This cell contains an abandoned ship.");
    		System.out.println("m = This cell contains a mystery island.");
    		System.out.println("j = This cell contains a magic chamber.");
    		System.out.println(",P = This cell has a player in it. \n<<<------- ----- ------->>>" );
    		System.out.println("---------------------------------------------");	
    	}
    	
    	   	public void DisplayBoard()
    	{	int player_position_1 = players[0].GetPosition();
    		int player_position_2 = players[1].GetPosition();
    		System.out.println("---------------------------------------------");
    		System.out.println("********PIRATES OF 125 GAME BOARD********");
    		for (int i =1; i < Size+1; i++)
    		{	
    		
    			if (player_position_1 ==0 && player_position_2 ==0)	//both at start position, no need to print ,P	
    				System.out.print(i+": " + hurdles[i-1] + "\t");
    			
    			if (player_position_1 !=0 && player_position_2 ==0) //player 1 started
    			{
    				if (i == player_position_1)
    					System.out.print(i + ": " + hurdles[(player_position_1) -1] + ", P ");	
    				
    				else
    					System.out.print(i+": " + hurdles[i-1] + "\t");
    			}	
    			if (player_position_1 !=0 && player_position_2 !=0) //player 1 starts and player2 started
    			{
    				if (i == player_position_2)
    					System.out.print(i + ": " + hurdles[(player_position_2) -1 ] + ", P " );	
    				
    				else
    					System.out.print(i+": " + hurdles[i-1] + "\t");
    			}
    			
    			if (i%5 == 0) //prints a new line per every 5 cells
    				System.out.print("\n");
    			
    		}	
    		System.out.println("---------------------------------------------");
    			
    	}
    	
    	public void Play()
    	{	
    		DisplayPlayerDetails(); //display player details
    		for (int i =0; i < Num_Players; i++) //run twice, since there is only 2 players
    		{
    		
    			while (players[i].GetHealth() >0 && players[i].GetPosition()<=30) //if player does not die or cross the line
    			{	
    	
    			players[i].Move(); //move the player 
    			if (players[i].GetPosition()>30 || players[i].GetHealth()<=0) //the loop is stop and player detail is displayed if player cross the line 
    			{
    				DisplayPlayerDetails();
    				break;
    			}
    			DisplayBoard();//display board 
			
				AbandonedIsland abandoned_island = new AbandonedIsland(); //initialize the abandoned island class
			
				MysteryIsland mystery_island = new MysteryIsland(); //initialize the mystery island class
			
				MagicIsland magic_island = new MagicIsland(); //initialize the magic island class
			
				TreasureIsland treasureIsland = new TreasureIsland(); //initialize the treasure island class
			
				EasyPirate easy_enemy = new EasyPirate(); //initialize the easy pirate class
			
				HardPirate hard_enemy = new HardPirate(); //initialize the hard pirate class 
			
				if (hurdles[players[i].GetPosition()-1] == 'e') //players land on easy pirate 
				{
					System.out.println("Easy Enemy has no Voodoo Doll.");
					if ((easy_enemy.GetTradeResponse(players[i]) == false))
					easy_enemy.Fight(players[i]);
				
					else
						easy_enemy.Trade(players[i]);
				}
				if (hurdles[players[i].GetPosition()-1] == 'h') //player lands on hard pirate
				{
					System.out.println("Hard Enemy has no Voodoo Doll.");
					if (hard_enemy.GetVoodoo() == true)
						System.out.println("Hard Enemy has found a Voodoo Doll.");
					if ((hard_enemy.GetTradeResponse(players[i])) == false)
						hard_enemy.Fight(players[i]);
					else
						hard_enemy.Trade(players[i]);
				}
				if (hurdles[players[i].GetPosition()-1] == 't')	 //player lands on treasure island
				{
					System.out.println(players[i].GetName() + " has landed on a " + treasureIsland.GetName());
					treasureIsland.UseIsland(players[i]);
				}
				if (hurdles[players[i].GetPosition()-1] == 'a') //player lands on abanonded island
				{
					System.out.println(players[i].GetName() + " has landed on a " + abandoned_island.GetName());
					abandoned_island.UseIsland(players[i]);
				}
				if(hurdles[players[i].GetPosition()-1] == 'm') //player lands on mystery island
				{
					System.out.println(players[i].GetName() + " has landed on a " + mystery_island.GetName());
					mystery_island.UseIsland(players[i]);
				}
				if(hurdles[players[i].GetPosition()-1] =='j') //player lands on magic island
				{
					System.out.println(players[i].GetName() + " has landed on a " + magic_island.GetName());
					magic_island.UseIsland(players[i]);
				}
    			}
			
			}
			
			boolean player_0_win = false;
			
			boolean player_1_win = false;
			
			// the above loops determine which player wins
			
			if (players[0].GetHealth()>0 && players[1].GetHealth()<=0) //player 1 cross the finish line
			{
				player_0_win = true;
				player_1_win = false;
			}
			if (players[0].GetHealth()<=0 && players[1].GetHealth()>0) // player 2 cross the finish line
			{
				player_0_win = false;
				player_1_win = true;
			}
			
			if(players[0].GetHealth()<=0 && players[1].GetHealth()<=0) //none cross the finish line
			{
				if (players[0].GetPosition()>players[1].GetPosition()) //player 1 went farther than player 2
				{
					player_0_win = true;
					player_1_win = false;
				}
				
				if (players[0].GetPosition()>players[1].GetPosition()) //player 2 went farther than player 1
				{
					player_0_win = false;
					player_1_win = true;
				}
				
				if (players[0].GetPosition() == players[1].GetPosition()) // both travel equal distance
				{
					if(players[0].GetWealth() > players[1].GetWealth()) // player 1 get more gold 
					{
						player_0_win = true;
						player_1_win = false;
					}
					if (players[0].GetWealth() < players[1].GetWealth()) //player 2 get more gold
					{
						player_0_win = false;
						player_1_win = true;
					}
					
					if(players[0].GetWealth() == players[1].GetWealth()) // both have the same gold
					{
						player_0_win = false;
						player_1_win = false;
						System.out.println("\n_____________________________________________\n\n\n");
						System.out.println("No one wins Elixir of Life."); //No one wins the Elixir of life under this condition as stated in the instruction
						System.out.println("\n\n\n_____________________________________________");
						
					}
				}
				
			}
	
			if (players[0].GetHealth()>0 && players[1].GetHealth()>0) //both cross the line 
			{for (int i =0; i < Num_Players; i++)
			{	
				players[i].Reinforcement(); //goes into reinforcement
			}
				if (players[0].Fight(players[1]) == true ) //players fight, and player 2 dies 
				{
					if(players[0].GetHealth() > 0 ) //player 1 does not die while player 2 does 
						player_0_win = true;
					if (players[0].GetHealth() <=0 ) //player 1 dies in the battle as well
					{
						player_0_win = false;
						player_1_win = false;
						System.out.println("\n_____________________________________________\n\n\n");
						System.out.println("Its a DRAW!\nNo one wins Elixir of Life." );
						System.out.println("\n\n\n_____________________________________________");
							
					}
				}
				else //player 1 dies and player 2 wins 
				{
					player_1_win = true;
				}
			}
			
			if (player_0_win == true) //player 1 wins, tells him that he wins the elixir of life
			{
				System.out.println("\n_____________________________________________\n\n\n");
				System.out.println("Elixir of Life goes to:\n||||||||~!@#$%^&*(  "+players[0].GetName()+"  )*&^%$#@!~||||||||");
				System.out.println("\n\n\n_____________________________________________");
			}
			if (player_1_win == true) //player 2 wins, tells him that he wins the elixir of life
			{
				System.out.println("\n_____________________________________________\n\n\n");
				System.out.println("Elixir of Life goes to:\n||||||||~!@#$%^&*(  "+players[1].GetName()+"  )*&^%$#@!~||||||||");
				System.out.println("\n\n\n_____________________________________________");
			}
			
			
			
			
			System.out.println("|||||||||||||||||||||||||||||||||||||||||||||\n|||||||||||||||||||||END|||||||||||||||||||||\n|||||||||||||||||||||||||||||||||||||||||||||");	
			// The end 
    	}
    		
}
    


    
