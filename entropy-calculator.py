# 'Entropy' and 'Gain' are important concepts within Artificial
# Intelligence fundamentals. It helps to theorise the probability of 
# Artificial Intelligence obtaining the correct answer, and the question/fact 
# that gives the A.I the most prominent and high porbabiity answer. 
# It determines the path to the greatest likelihood of success.

import math

def EntropyCalculator():
    print("Entropy Calculator:")
    variables = int(input("Enter the number of category variables (e.g, if there is only 'yes' and 'no', then it's 2. If there is red, green, blue, then it's 3): "))
    array = []
    total_count = 0
    for i in range(variables):
        value = int(input(f"Enter the count for variable {i + 1}: (e.g., if there are 4 tigers, your 'yes' variable/first variable is 4, and your 'no' variable/second variable is 4 minus your total count of data in your data set. If there are 6 outcomes, and 4 of them are tigers, your 'no' is 2): "))
        array.append(value)
        total_count += value

    print(array)
    entropy = 0
    for count in array:
        probability = count / total_count
        entropy -= probability * math.log2(probability) if probability > 0 else 0  # Condition to avoid log(0)

    print("Entropy:", entropy)

def GainCalculator():
    print("Gain Calculator:")
    entropy = float(input("Enter the Entropy value: "))
    variables = int(input("Enter the number of category variables (e.g, if there is only 'yes' and 'no', then it's 2. If there is red, green, blue, then it's 3): "))
    array = []
    total_count = 0
    
    elements = int(input("Enter the number of desired elements in your data set. For example, if you are trying to receive a probability on tigers, and you have 4 tigers possible to find in your solution, enter 4: "))
    for i in range(variables):
        value = int(input(f"Enter the count for variable {i + 1}: (e.g., if there are 4 'yes' in one category, the variable is 4: "))
        array.append(value)
        total_count += value
        
    print(array)
    gain = 0
    for count in array:
        data = int(input(f"Enter how many of the {elements} desired elements are in the category value of {count} (eg. how many tigers are there inside this variable?): "))
        probability = count / total_count
        probability_2 = (data / count) if count > 0 else 0
        probability_3 = (count - data) / count if count > 0 else 0
        if probability_2 > 0:
            gain += probability * (-probability_2 * math.log2(probability_2))
        if probability_3 > 0:
            gain -= probability * (probability_3 * math.log2(probability_3))

    information_gain = entropy - gain
    print("Information Gain:", information_gain)

while True:
    choice = int(input("Enter '1' to select Entropy Calculator. Enter '2' to select Gain Calculator: "))

    if choice == 1:
        EntropyCalculator()
    elif choice == 2:
        GainCalculator()
    else:
        try_again = input("INCORRECT OPTION. Enter 'y' to try again, or any other key to exit: ")
        if try_again.lower() != "y":
            break
