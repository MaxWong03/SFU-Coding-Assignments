#This function asks the number of players and return the number of players.
def player():
    flag = "True"
    while flag == "True":
        players = raw_input("How many players are there?")
        if players.isdigit() == False:
            flag = "True"
        elif int(players)<=0:
            flag ="True"
        else:
            flag = "False"
    players = int(players)
    return players

#This function takes in the number of players.Then create an empty list called account.Then create multiple lists inside according to the number of players.
def account(players):
    account = []
    for i in range(players):
        account.append([])
    for j in range(players):
        account[j].append(1000)
    return account

"""
This function takes in the number of players and deck. Then create an empty list called players_hand.Then create multiple lists inside according to the number of players.
Then give 2 cards to each inside lists from the deck
"""
def playershand(players, deck):
    players_hand = []
    for i in range(players):
        players_hand.append([])
    for i in range(players):
        for j in range(2):
            x = deck.pop()
            players_hand[i].append(x)
    return players_hand

#This function create a list inside an empty list and give the inside list 2 cards and call it dealer_hand
def dealerhand():
    dealer_hand = [[]]
    for i in range(2):
        x = deck.pop()
        dealer_hand[0].append(x)
    return dealer_hand

"""
This function takes in dealer_hand and it is only for the inital dealer`s hand display. 
It returns  ** and the 2nd card of the dealer
"""
def dealerdisplay(dealer_hand):
    display = "** " + dealer_hand[0][1].shortname()
    return display

"""
This function is used after the initial hands are due for both players and dealer
It shows each player`s hand and dealer`s hand(dealer`s first card is going to be printed as ** using the previous function)
"""
def show_initial_hand(dealer_hand,players_hand, players):
    print "Hands:\nDealer:","  ",dealerdisplay(dealer_hand)
    for i in range(players):
        print "Player #"+str(i+1)+":",hand_display(players_hand[i])


#This function takes in a list, and shortname all the cards inside the list and return those cards as string
def hand_display(hand):
    display = ""
    length = len(hand)
    for i in range(length):
        if i == 0:
            display = display + hand[i].shortname()
        else:
            display = display + " " + hand[i].shortname()
    return display

"""
This function takes in the number of players and the "account" list. It then creates an empty list called bet.
Then create multiple lists inside according to the number of players. Afterwards, it asks the players to make bet
and at the end it will store the bet in those inside lists.
"""
def makebet(players, account):
    bet = []
    for i in range(players):
        bet.append([])
    for j in range(players):
        print "Player #"+str(j+1)+":\nYou have $",str(account[j][0])+"."
        flag = "True"
        while flag == "True":
            betmoney = raw_input("How much would you like to bet?")
            if betmoney.isdigit() == False:
                flag = "True"
            elif int(betmoney) > account[j][0]:
                flag = "True"
            elif int(betmoney) <=0 and account[j][0]!= 0:
                flag = "True"
            else:
                flag = "False"
        bet[j].append(int(betmoney))
    return bet
    
"""
This function takes in a list, and evaluate the points that the cards in those lists represent.
It also creates an empty list called pointlist. Then create multiple lists inside according to the number of players.
After evaluating the points, it store the points of each player in those inside lists.
"""
def readpoint(hand):
    length = len(hand)
    pointlist = []
    for i in range(length):
        pointlist.append([])
    for i in range(length):
        point = 0
        counter = 0
        for j in range(len(hand[i])):
            rank = hand[i][j].rank()
            if rank.isdigit() == True:
                point = point + int(rank)
            if rank.isdigit() == False and rank!= "A":
                point = point + 10
            if rank.isdigit() ==False and rank == "A":
                point = point + 11
                counter = counter +1
        if point >21 and counter >= 1:
            point = point - 10*counter
        pointlist[i].append(point)
    return pointlist



"""
This function takes in players` decision , players_hand(which is a list), and the deck.
Then it will add a card to the players_hand if the decision is hit. If the decision is stay players_hand will stand the same
"""
def hit_or_stand(decision,players_hand, deck):
    if decision == "h":
        card = deck.pop()
        players_hand.append(card)
    if decision == "s":
        players_hand = players_hand
    return players_hand
        
"""
This function takes in players_hand(which is a list, the number of players, and playerspoint(which is a list)
It then asks players to hit or to stand. If players decide to hit, it will use the above function to add a card to the players_hand.
Afterwards it will re-evaluate playerspoint. If the point is under 21 it will ask players` decision again.
"""
def players_turn(players_hand,players, playerspoint):
    for i in range(players):
        print "\nPlayer #"+str(i+1)+"\nCards:",hand_display(players_hand[i]),"  total:",playerspoint[i][0]
        decision = "h"
        while playerspoint[i][0] <= 21 and decision == "h" :
            if decision == "h":
                decision = raw_input("Hit (h) or stand (s) ?").lower()
                while decision != "h" and decision !="s":
                    decision = raw_input('Please enter "h" or "s" !').lower()
                players_hand[i] = hit_or_stand(decision, players_hand[i],deck)
                playerspoint = readpoint(players_hand)
                if decision == "h":
                    print "Cards:",hand_display(players_hand[i]),"    total:",playerspoint[i][0]
                if playerspoint[i][0] >21:
                    print "Bust!"
    return playerspoint

"""
This function takes in playerspoint(which is a list) and dealerpoint(which is a list). Then it compares players` point with dealer`s point and determine whether the certain player wins or loses.
It also creates an empty list called result_list, create multiple lists inside according to the number of players, and store each player`s result to the inside lists.
"""
def result(playerspoint, dealerpoint):
    result_list = []
    for i in range(len(playerspoint)):
        result_list.append([])
    for i in range(len(playerspoint)):
        if playerspoint[i][0] >21:
            result_list[i].append("loses")
        elif playerspoint[i][0] < dealerpoint[0][0] and dealerpoint[0][0] <= 21:
            result_list[i].append("loses")
        elif playerspoint[i][0] == dealerpoint[0][0] and dealerpoint[0][0] <=21:
            result_list[i].append("pushes")
        elif playerspoint[i][0] > dealerpoint[0][0] and playerspoint[i][0] <=21:
            result_list[i].append("wins")
        elif playerspoint[i][0] < dealerpoint[0][0] and dealerpoint[0][0] > 21:
            result_list[i].append("wins")
    return result_list



#This function takes in result(which is a list), account(which is a list) and bet(which is a list).Then it addjusts the amount in players` account according to the results and the amount of bet.
def arrangemoney(result, account, bet):
    for i in range(len(result)):
        if result[i][0] == "wins":
            account[i][0] = account[i][0] + bet[i][0]
        if result[i][0] == "loses":
            account[i][0] = account[i][0] - bet[i][0]
        if result[i][0] == "pushes":
            account[i][0] = account[i][0]
    return account


#This funtion takes in the number of players, result_list(which is a list), and bet(which is a list). Then it prints out whether each player wins or loses and the amount of they win or lose.
def show_result(players, result_list,bet):
        for i in range(players):
            if result_list[i][0] != "pushes":
                print "Player #"+str(i+1),result_list[i][0], "$"+str(bet[i][0])+"."
            else:
                print "Player #"+str(i+1),result_list[i][0]+"."




"""
This function takes in dealer_hand(which is a list), dealerpoint(which is a lit). Then it will keep adding cards to dealer_hand until dealer`s point is equal or more than 17.
Then it shows dealer`s hand and point and return dealer`s point
"""
def dealer_turn(dealer_hand, dealerpoint):
    print "\n"
    while dealerpoint[0][0] <17:
        card = deck.pop()
        print "Dealer draws", card.shortname()+"."
        dealer_hand[0].append(card)
        dealerpoint = readpoint(dealer_hand)

    print "Dealer`s hand:",hand_display(dealer_hand[0]),"    total:",dealerpoint[0][0]
    return dealerpoint
    
#This function takes in the number of players and account (which is a list). It is used after the for loop is run 3 times. It prints out each player final amount of money after 3 rounds.
def show_final_money(players, account):
        for i in range(players):
            print "Player #"+str(i+1)+":","$"+str(account[i][0])

players = player()
account = account(players)
for i in range(3):
    import cards
    import random
    deck = cards.deck()
    random.shuffle(deck)
    dealer_hand = dealerhand()
    players_hand = playershand(players, deck)
    bet = makebet(players, account)
    show_initial_hand(dealer_hand,players_hand,players)
    dealerpoint = readpoint(dealer_hand)
    playerspoint = readpoint(players_hand)
    playerspoint = players_turn(players_hand,players, playerspoint)
    dealerpoint = dealer_turn(dealer_hand, dealerpoint)
    result_list = result(playerspoint, dealerpoint)
    account = arrangemoney(result_list, account, bet)
    show_result(players,result_list,bet)
    x = raw_input("Press enter to continue.")
    print "\n"*2
    
show_final_money(players, account)


