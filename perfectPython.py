def main_menu():
    name = input("Please enter your first name: ")
    age = input("Please enter your age in years: ")
    print(f"Welcome, {name} (age - {age}), to 'Perfect Python', your python-learning companion!")
    start(name, age)

def check(start, name, age):
    while True:
        if start.lower() == 's':
            run_program(start, name, age)
            break
        elif start.lower() == 'e':
            print(f"Thanks for learning, {name}! See you next time.")
            break
        else:
            print("Incorrect input! Try again.")
            start = input("Type 's' to start, or 'e' to end: ")

def start(name, age):
    start = input("Type 's' to start, or 'e' to end: ")
    check(start, name, age)

def run_program(start, name, age):
    print("SUCCESS")

# RUN MAIN
main_menu()
