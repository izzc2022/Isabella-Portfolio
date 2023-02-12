#By Isabella C, 2022. Developed for Quantum Computing Exam Study

def probability_1_state_qubit_state_calculator(qubit_1,qubit_2):
    qubit_1 = float(qubit_1)
    qubit_2 = float(qubit_2)
    
    prob1_00 = 1 - qubit_1
    prob1_01 = 1 - qubit_1
    prob1_10 = qubit_1
    prob1_11 = qubit_1

    prob2_00 = 1 - qubit_2
    prob2_01 = qubit_2
    prob2_10 = 1 - qubit_2
    prob2_11 = qubit_2

    combined_00 = prob1_00 * prob2_00
    combined_01 = prob1_01 * prob2_01
    combined_10 = prob1_10 * prob2_10
    combined_11 = prob1_11 * prob2_11

    print("Here are the following combined probability's for the entered qubit 1 states: ")
    print("|00> = " + str(combined_00 * 100))
    print("|01> = " + str(combined_01* 100))
    print("|10> = " + str(combined_10* 100))
    print("|11> = " + str(combined_11* 100))

qubit_1 = input("Enter first qubit's chance of being mesured in the |1> state (enter the percentage as a decimal): ")
qubit_2 = input("Enter second qubit's chance of being mesured in the |1> state (enter the percentage as a decimal): ")
probability_1_state_qubit_state_calculator(qubit_1,qubit_2)