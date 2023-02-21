import pandas as pd
import numpy as np
import time

inventory = pd.read_csv('farm_inventory.csv')
shop = pd.read_csv('farm_shop.csv')
cook = pd.read_csv('farm_cook.csv')

version = 1.0

time.sleep(1)
print("Welcome to:")
time.sleep(1)
print("Pocket Python Patch" + " (version " + str(version) + ")")
time.sleep(1)

start = input("Type 's' or 'S' to start: ")
if start == 's' or start == 'S':
    game = True

while game:
    play = input("Type 's' to enter the shop, 'i' to view your inventory, 'c' to cook/view recipes, or 'e' to end: ")

    if play == 's' or play == 'S':
        print(shop)
    elif play == 'i' or play == 'I':
        print(inventory)
    elif play == 'c' or play == 'C':
        print(cook)
    elif play == 'e' or play == 'E':
        print("Thank you for playing 'Pocket Python Patch' " + " (version " + str(version) + "). See you next time!")
        game = False
    
