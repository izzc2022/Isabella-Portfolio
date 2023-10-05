# Code by Isabella C
# Copyright 18-06-2022
# Please note: I was bored, hungry and indecisive, so I developed this to help my family and I decide on a dessert quickly and whilst in the moment

import random
import time
from datetime import date

begin = True

print("DESSERT DECIDER - for indecisive people")
time.sleep(2)
screen = input("Type 'y' to start DESSERT DECIDER, or 'n' to end: ")

if screen == 'y' or screen == 'Y':
    begin = True
    while begin:
      dessert_1 = str(input("Enter dessert 1: "))
      dessert_2 = str(input("Enter dessert 2: "))
      dessert_3 = str(input("Enter dessert 3: "))
      dessert_4 = str(input("Enter dessert 4: "))
      try_again = "LOL try again"
      troll = "HAHA try again"
      yeet = "YEET try again"

      options = [dessert_1, dessert_2, dessert_3, dessert_4, try_again, troll, yeet]
      print(f'The possible options are: {options[0]}, {options[1]}, {options[2]}, {options[3]}')

      for n in range(5,0,-1):
        print(n)
        time.sleep(2)

      random_dessert = random.choice(options)
      print(f'The chosen dessert is: {random_dessert}')

      date = date.today()


      file = open('DESSERT_DECIDER.txt','a')
      file.write(f'{str(date)}\n')
      file.write(f'Result: {str(random_dessert)}\n')
      file.write(f'Options: {options}\n')
      file.write('\n')
      file.close()
      break
else:
    begin = False
    print("Thank you, and do come back again!")
