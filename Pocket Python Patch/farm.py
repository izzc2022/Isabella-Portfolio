import pandas as pd
import numpy as np
import time

shop = pd.read_csv('shop.csv')
inventory = pd.read_csv('inventory.csv')
cook = pd.read_csv('cook.csv')

version = 1.0
start = True

while start:
    time.sleep(1)
    print("Welcome to:")
    time.sleep(1)
    print("Pocket Python Patch" + " (version) " + str(version))
    time.sleep(1)