# bubble sort algorithm, Isabella C 2023
def bubble_sort(list):
  n = len(list)
  swaps = 0
  passes = 0
  swapped = False
  for i in range(n-1):
    passes += 1
    for j in range(0, n-i-1):
      if list[j] > list[j+1] :
        swapped = True
        swaps += 1
        list[j], list[j + 1] = list[j + 1], list[j]
  print("swaps: " + str(swaps))
  print("passes: " + str(passes))

# List of integers
int_data = [2, 4, 6, 5, 9, 1, 3, 8]
print(f'Unsorted list of integers: {int_data}')
bubble_sort(int_data)
print(f'Sorted list of integers: {int_data}')

# List of strings
str_data = ['zebra', 'frog', 'cat', 'mouse', 'dog', 'cheetah', 'leopard']
print(f'Unsorted list of strings: {str_data}')
bubble_sort(str_data)
print(f'Sorted list of strings: {str_data}')