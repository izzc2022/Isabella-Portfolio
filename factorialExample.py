def factorial(n):
	result = 1
	for num in range(n, 0, -1):
		result = result * num
	return result

n = int(input("Enter a factorial (number): "))

print(factorial(n))