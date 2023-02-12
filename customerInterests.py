import pandas as pd
import numpy as np
import time

print("Customer Collection Database EXAMPLE")
time.sleep(1)
print("Developed by Isabella C for Business Assignment Demonstration, 2022")
time.sleep(1)

df = pd.read_csv('businessIdea.csv')

print('')
df.sort_values(["Item", "Category", "Franchise", "Name"], 
                    axis=0,
                    ascending=[True, True, True, True], 
                    inplace=True)
time.sleep(1)
print(df)

time.sleep(1)
print('')

newItemName = "Gojo"
newItemType = "Pop"
newItemCategory = "Anime"
newItemFranchise = "Jujutsu Kaisen"

if newItemType == "Pop":
    if newItemCategory == "Anime":
        if newItemFranchise == "Jujutsu Kaisen" or newItemFranchise == "MHA":
            print("New item available to add to/complete your collection: " + newItemName + " " + newItemType + " !")
        else:
            print("Item " + newItemName + " is not of interest to this customer, and will therefore not be shown to them!")
    else:
        print("Item " + newItemName + " is not of interest to this customer, and will therefore not be shown to them!")
elif newItemType == "Figure":
    if newItemCategory == "Anime":
        if newItemFranchise == "Jujutsu Kaisen" or newItemFranchise == "MHA":
            print("New Item available to add to/complete your collection: " + newItemName + " " + newItemType + " !")
        else:
            print("Item " + newItemName + " is not of interest to this customer, and will therefore not be shown to them!")
    else:
        print("Item " + newItemName + " is not of interest to this customer, and will therefore not be shown to them!")
else:
    print("Item " + newItemName + " is not of interest to this customer, and will therefore not be shown to them!")


