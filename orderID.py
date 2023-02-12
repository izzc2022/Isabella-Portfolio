import time

customerName = "Sally"
orders = ["A11", "B12", "C13", "D14"]

print("Customer " + customerName + "'s orders: ")
time.sleep(1)
for index, ID in enumerate(orders,1):
    time.sleep(1)
    print(str(index)+'.', ID)

time.sleep(1)
A11 = {"Hawks Ver B" : "(POP, Anime, MHA)", "Gojo" : "(Figure, Anime, Jujutsu Kaisen)"}

time.sleep(1)
print("Order A11's contents:")
for name, info in enumerate(A11,1):
    time.sleep(1)
    print(str(name)+'.', info)

time.sleep(1)
print("Backend information:")
for name, info in A11.items():
    time.sleep(1)
    print(name, info)