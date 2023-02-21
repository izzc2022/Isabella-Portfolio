import pandas as pd
import numpy as np
import time
import matplotlib.pyplot as plt
import matplotlib.image as img
import csv

inventory = pd.read_csv('farm_inventory.csv')
shop = pd.read_csv('farm_shop.csv')
cook = pd.read_csv('farm_cook.csv')

im = img.imread('farm_plant.png')
rows = []
  
version = 1.0

time.sleep(1)
print("Welcome to:")
time.sleep(1)
print("Pocket Python Patch" + " (version " + str(version) + ")")
time.sleep(1)
plt.imshow(im)
plt.show()

start = input("Type 's' or 'S' to start: ")
if start == 's' or start == 'S':
    game = True

while game:
    play = input("Type 's' to enter the shop, 'i' to view your inventory, 'c' to cook/view recipes, or 'e' to end: ")

    if play == 's' or play == 'S':
        print(shop)
        time.sleep(1)
        buy = input("Enter the name of the item you wish to purchase: ")
        with open('farm_inventory.csv', 'r') as f:
            reader = csv.reader(f)
            header = next(reader)
            for row in reader:
                rows.append(buy)

    elif play == 'i' or play == 'I':
        print(inventory)
    elif play == 'c' or play == 'C':
        print(cook)
    elif play == 'e' or play == 'E':
        print("Thank you for playing 'Pocket Python Patch' " + " (version " + str(version) + "). See you next time!")
        game = False
    else:
        print('incorrect value')
    

#print("
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |
#|        0        |       0         |        0      |
#|                 |                 |               |

#")
