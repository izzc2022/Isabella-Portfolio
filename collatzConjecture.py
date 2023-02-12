#By Isabella C. Made in 2022 (after watching a youtube video at 3am about the Collatz Conjecture)

import time

x = int(input("Enter a number to view it in the Collatz Conjecture: "))

while x != 1:
  if x % 2 == 0:
    x = x / 2
    time.sleep(1)
    print(f'{x:.0f}')
  elif x % 2 != 0 :
    x = 3*x+1
    time.sleep(1)
    print(f'{x:.0f}')