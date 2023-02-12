#By Isabella C, 2022 (for Quantum Physics exam study)
#PLEASE NOTE that this code is not foolproof and should only be used as a guide, and NOT as an absolute/guaranteed calculator.

def ComplementCalculator(input):
    print("original binary number: " + input)
    flipped = ''
    for num in input:
        if num == '0':
            flipped = flipped + '1'
        elif num == '1':
            flipped = flipped + '0'
    f = flipped
    print("flipped binary number: " + f)
    end = '1'
    input = bin(int(f,2) + int(end,2)) 
    print("2's complement: " + input[2:])

input = input('Enter binary number: ')
ComplementCalculator(input)
