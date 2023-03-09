import math
import random
import time

class Character(object): 
    def __init__(self, number, answer, start, coins, lives): 
        self.number = number
        self.coins = coins
        self.lives = lives
        self.answer = answer
        self.start = start

    def trade_coins(self):
        if self.answer == False:
            self.lives = self.lives - 1
            time.sleep(1)
            print("Player " + str(self.number) + " lost")
            time.sleep(1)
            print("Coins: " + str(self.coins) + ", Lives: " + str(self.lives))
            Character.return_coins(self)
            Character.return_lives(self)
        elif self.answer == True:
            self.coins = self.coins + 10
            time.sleep(1)
            print("Player " + str(self.number) + " win")
            time.sleep(1)
            print("Coins: " + str(self.coins) + ", Lives: " + str(self.lives))
            Character.return_coins(self)
            Character.return_lives(self)
        if self.lives == 0:
            self.start = False
            Character.return_start(self)
    
    def return_coins(self):
        return self.coins
    
    def return_lives(self):
        return self.lives
    
    def return_start(self):
        return self.start


def game_start():
    answer = True
    number1 = random.randint(1,100)
    number2 = random.randint(1,100)
    math = number1 + number2
    time.sleep(1)
    play = int(input("Solve This: " + str(number1) + " + " + str(number2) + " = "))
    if math != play:
        answer = False
    return answer

start = True
count = 0
c1 = 0
c2 = 0
l1 = 3
l2 = 3
print("Welcome to the Coin Game!")
time.sleep(1)
while start == True:
    count = count + 1
    time.sleep(1)
    print("Round: " + str(count))
    
    time.sleep(1)
    print("Player 1: Your turn ! ")
    p1 = game_start()
    player1 = Character(1,p1,start,c1,l1)
    time.sleep(1)

    print("Player 2: Your turn ! ")
    p2 = game_start()
    player2 = Character(2,p2,start,c2,l2)
    
    player1.trade_coins()
    player2.trade_coins()

    c1 = player1.return_coins()
    c2 = player2.return_coins()
    l1 = player1.return_lives()
    l2 = player2.return_lives()

    if player1.return_start() == False:
        l1 == 0
        print("Thanks for PLaying!")
        break
    elif player2.return_start() == False:
        l2 = 0
        print("Thanks for PLaying!")
        break