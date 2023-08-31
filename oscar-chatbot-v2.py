# A super simple text-based Python chatbot to demonstrate the fundamentals of Artificial Intelligence, decision making, and language processing. 
# You can run this code through an online Python compiler if needed, such as <https://www.programiz.com/python-programming/online-compiler/#google_vignette>.
# Developed by Isabella Ciccone as part of her 2023 Software Engineering Internship.

import random
from datetime import datetime, date
import string

today_date = date.today()
now = datetime.now()
current_time = now.strftime("%H:%M:%S")

bot = "OSCAR"

welcome = 'Hello! How can I help?'
        
discussion = {
    "hello": str(welcome),
    "hi": str(welcome),
    "hi there": str(welcome),
    "hello there": str(welcome),
    "hey": str(welcome),
    "hey there": str(welcome),

    "goodbye": "Goodbye!",
    "good bye": "Goodbye!",
    "bye": "Goodbye!",
    
    "thank you": "My pleasure. Anything else I can help you with today?",
    "thankyou": "My pleasure. Anything else I can help you with today?",
    "thanks": "My pleasure. Anything else I can help you with today?",

    "weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, to avoid disappointment.",
    "what is the weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, to avoid disappointment.",
    "whats the weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, to avoid disappointment.",
    
    "services": "I offer a variety of custom, unique, quality and efficient services.",
    "what are your services": "I offer a variety of custom, unique, quality and efficient services.",
    "what services": "I offer a variety of custom, unique, quality and efficient services.",

    "what is the time": "The time is currently: " + str(current_time),
    "whats the time": "The time is currently: " + str(current_time),
    "time": "The time is currently: " + str(current_time),

    "what is the date": "Today's date is: " + str(today_date),
    "whats the date": "Today's date is: " + str(today_date),
    "date": "Today's date is: " + str(today_date),

    "what is your name": "My name is " + bot + ".",
    "whats your name": "My name is " + bot + ".",
    "name": "My name is " + bot + "."
}

name = input("Enter your first name to begin: ")
counter = 0
list = ['a','b','c','d','e',1,2,3,4,5,6,7,8,9,0]
identifier = str(random.choice(list)) + str(random.choice(list)) + str(random.choice(list)) + str(random.choice(list)) + str(random.choice(list))
print("Your unique identifier is: " + identifier)
while True:
    user_input = input(name + ": ")
    cleaned_input = ''.join(char for char in user_input if char not in string.punctuation)
    cleaned_input_lower = cleaned_input.lower()

    if cleaned_input_lower in discussion:
        print(bot + ": " + discussion[cleaned_input_lower])
        if cleaned_input_lower == "goodbye" or cleaned_input_lower == "good bye" or cleaned_input_lower == "bye":
            break
    else:
        counter = counter + 1
        if counter <= 2:
            print(bot + ": I'm sorry, I didn't understand that. Try again!")
    if counter > 2:
        print(bot + ": It seems that I am struggling to answer your query. I will connect you with a live chat assistant now. Thank you for your patience. A reminder that your unique identifier is: " + str(identifier))
        break
