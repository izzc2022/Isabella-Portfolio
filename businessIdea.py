import pandas as pd
import numpy as np
import time

print("Customer Collection Database EXAMPLE")
time.sleep(1)
print("Developed by Isabella C for Business Assignment Demonstration, 2022")
time.sleep(1)

df = pd.read_csv('businessIdea.csv')

print('')
print("BEFORE SORTING:")
time.sleep(1)
print(df)

time.sleep(1)
print('')
print("AFTER SORTING:")
df.sort_values(["Item", "Category", "Franchise", "Name"], 
                    axis=0,
                    ascending=[True, True, True, True], 
                    inplace=True)
time.sleep(1)
print(df)