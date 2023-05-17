import random
from datetime import datetime
from datetime import date
today_date = date.today()
now = datetime.now()
current_time = now.strftime("%H:%M:%S")

bot = "OSCAR"

discussion = {
    "hello": "Hello! How can I help?",
    "hi": "Hello! How can I help?",
    "hi there": "Hello! How can I help?",
    "hello there": "Hello! How can I help?",
    "hey": "Hello! How can I help?",

    "goodbye": "Goodbye!",
    "good bye": "Goodbye!",

    "weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, in order to avoid/prepare for any disappointment.",
    "what is the weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, in order to avoid/prepare for any disappointment.",
    "what'ss the weather": "I am not yet equipped with the ability to tell the weather. I am going to assume it is raining, in order to avoid/prepare for any disappointment.",

    "what is the time": "The time is currently: " + str(current_time),
    "what's' the time": "The time is currently: " + str(current_time),
    "time": "The time is currently: " + str(current_time),

    "what is the date": "Today's date is: " + str(today_date),
    "what's the date": "Today's date is: " + str(today_date),
    "date": "Today's date is: " + str(today_date),

    "what is your name": "My name is " + bot + ".",
    "what's your name": "My name is " + bot + ".",
    "name": "My name is " + bot + "."
}
name = input("Enter your first name to begin: ")
while True:
    user_input = input(name + ": ")
    if user_input.lower() in discussion:
        print(bot + ": " + discussion[user_input.lower()])
    else:
        print(bot + ": I'm sorry, I didn't understand that. Try removing any full stops, question or explanation marks, or commas. Otherwise, try rephrasing your query/question. Capitalisation or lowercases do not matter. If that doesn't work, please be patient, as I am still learning!")

