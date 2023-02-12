import time

finish = False
while finish == False:
   
    status = input("Type 'start' to begin. Type 'end' to finish: ")
    if status == "start":
        finish == False
        print("Welcome to the gas converter.")
       
        time.sleep(1)
       
        hour = input("Enter the number of hours: ")
        rate = 0.03267
        day = 0.7689
       
        total = (int(hour) * rate) + day
       
        print ("Thank you for using the converter. The total charged for your day's gas is $" + str(total))
       
    elif status == "end":
            print ("Finished")
            break
    else:
            print ("ERROR: Unacceptable values entered. Please type 'start' or 'end':")