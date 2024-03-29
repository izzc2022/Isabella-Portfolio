import tkinter as tk

# additional import (for 'pack.()' implementation and structuring)
from tkinter import *

import keyboard_design as kd
import recognizer
from template import Point, WordTemplates


class Application(tk.Frame):
    def __init__(self, window_width, window_height, master=None):
        super().__init__(master)  # Call tk.Frame.__init__(master)
        self.master = master  # Update the master object after tk.Frame() makes necessary changes to it
        frame_bottom_height = 200
        frame_middle_height = 50
        frame_top_height = window_height - frame_bottom_height - frame_middle_height

        # the top frame is used to show input words in the text
        frame_top = tk.Frame(self.master)
        frame_top.place(x=0, y=0, width=window_width, height=frame_top_height)

        self.text = tk.Text(frame_top, bg='white', borderwidth=2, relief='groove', font=('Arial', 20))
        self.text.place(x=0, y=0, width=window_width, height=frame_top_height)

        # the middle frame is used to list word candidates (four labels)
        frame_middle = tk.Frame(self.master)
        frame_middle.place(x=0, y=frame_top_height, width=window_width, height=frame_middle_height)

        word_candidate_num = 4
        self.label_word_candidates = []  # labels used to show word candidates
        for i in range(word_candidate_num):  # the values 0 to (word_candidate_num - 1)
            label_word = tk.Label(frame_middle, bg='white', borderwidth=2, relief='groove', font=15) #anchor='w',
            label_word.place(relx=i/word_candidate_num, relwidth=1/word_candidate_num, height=frame_middle_height)
            label_word.bind("<ButtonRelease-1>", self.select_word_candidate)
            print(i/word_candidate_num)
            self.label_word_candidates.append(label_word)

        # the bottom frame is used to show the keyboard
        frame_bottom = tk.Frame(self.master)
        frame_bottom.place(x=0, y=(frame_top_height + frame_middle_height), width=window_width,
                           height=frame_bottom_height)

        self.canvas_keyboard = tk.Canvas(frame_bottom, bg='light gray', borderwidth=2, relief='groove')
        self.canvas_keyboard.place(x=0, y=0, width=window_width, height=frame_bottom_height)

        self.keyboard = kd.Keyboard(self.canvas_keyboard)
        self.keyboard.keyboard_layout()

        # generate word templates
        templates = WordTemplates(self.keyboard.get_keys())

        # generate a recognizer
        self.word_recognizer = recognizer.Recognizer(templates.set_templates())
        self.gesture_points = []

        # mouse events on the canvas keyboard
        self.canvas_keyboard.bind("<ButtonPress-1>", self.mouse_left_button_press)
        self.canvas_keyboard.bind("<ButtonRelease-1>", self.mouse_left_button_release)
        self.canvas_keyboard.bind("<B1-Motion>", self.mouse_move_left_button_down)

        # store x, y, segment tag
        self.cursor_move_position_list = []

        # store the tag for each segment of the drawn gesture
        self.line_tag = []

        # set up necessary attributes
        self.command_window = None

    # MY EDITS:
    # when users select a word candidate from the four labels in the middle frame
    def select_word_candidate(self, event):
        btn = event.widget  # event.widget is the widget that called the event
        selected_word = btn.cget('text')

        # destroy any existing windows prior to preparing to create a new one
        if self.command_window is not None:
            self.command_window.destroy()

        # if the candidate word is one of the 5 potential command words, the command popup appears
        if (selected_word.lower() == 'save' or selected_word.lower() == 'copy' or selected_word.lower()
                == 'undo' or selected_word.lower() == 'redo'):

            # Create a popup window with two buttons using Toplevel
            self.command_window = tk.Toplevel()
            self.command_window.wm_title("Command Check")
            label = tk.Label(self.command_window, text="Is this a command?", font=("Helvetica", 15))
            label.pack(side="top", fill="x", pady=90)

            # handles button functionality for a "yes" or "no" answer
            def yes_pressed():
                # Destroy the "Is this a command?" window
                self.command_window.destroy()

                # Create a new confirmation window
                self.confirmation = tk.Toplevel()
                self.confirmation.wm_title("Command Performed")
                label_2 = tk.Label(self.confirmation, text=selected_word.lower() + " performed successfully",
                                   font=("Helvetica", 15))
                label_2.pack(side="top", fill="x", pady=90)
                button_confirm = tk.Button(self.confirmation, text="OK", command=self.confirmation.destroy)
                button_confirm.pack(side=BOTTOM, fill=BOTH, expand=True)

            def no_pressed():
                # Text is inserted, as it is a regular word, and the "Is this a command?" window is destroyed
                self.text.insert(tk.END, selected_word)
                self.command_window.destroy()

            button_yes = tk.Button(self.command_window, text="Yes", command=yes_pressed)
            button_no = tk.Button(self.command_window, text="No", command=no_pressed)
            button_yes.pack(side=LEFT, fill=BOTH, expand=True)
            button_no.pack(side=LEFT, fill=BOTH, expand=True)

        else:
            self.text.insert(tk.END, selected_word)

        for i in range(len(self.label_word_candidates)):  # clear the content of all word labels
            self.label_word_candidates[i].config(text='')
    # END OF EDITS

    # press mouse left button
    def mouse_left_button_press(self, event):
        self.cursor_move_position_list.append([event.x, event.y, 0])  # store x, y, segment tag
        self.keyboard.key_press(event.x, event.y)
        self.gesture_points.clear()

        #self.gesture_points.append(Point(event.x, event.y))


    # release mouse left button
    def mouse_left_button_release(self, event):
        previous_x = self.cursor_move_position_list[-1][0]
        previous_y = self.cursor_move_position_list[-1][1]
        line_tag = self.canvas_keyboard.create_line(previous_x, previous_y, event.x, event.y)
        self.cursor_move_position_list.append([event.x, event.y, line_tag])

        self.keyboard.key_release(event.x, event.y)
        result = self.word_recognizer.recognize(self.gesture_points)
        if len(result) > 0:
            for i in range(len(result)):
                if i < len(self.label_word_candidates):
                    self.label_word_candidates[i].config(text=result[i][1])
                else:
                    break
        else:
            key = self.keyboard.get_key_pressed()
            if key == '<--':  # remove the final character from the text
                length = len(self.text.get("1.0", 'end-1c'))
                # print(length)
                if length > 0:
                    self.text.delete("end-2c") # remover the last character
            '''
            else:  # not the delete key ("<--")
                characters = self.label_word_candidates[0].cget("text")
                characters += self.keyboard.get_key_pressed().lower()  # convert to lowercase
                self.label_word_candidates[0].config(
                    text=characters)  # only one key was pressed
            '''
            
        if len(self.cursor_move_position_list) > 1:  # delete cursor trajectory
            for x in self.cursor_move_position_list[1:]:
                self.canvas_keyboard.delete(x[2])


    # users drag the mouse cursor on the keyboard while pressing the left button: drawing gestures on the keyboard
    def mouse_move_left_button_down(self, event):
        previous_x = self.cursor_move_position_list[-1][0]
        previous_y = self.cursor_move_position_list[-1][1]

        line_tag = self.canvas_keyboard.create_line(previous_x, previous_y, event.x, event.y)  # draw a line
        self.cursor_move_position_list.append([event.x, event.y, line_tag])

        self.keyboard.mouse_move_left_button_down(event.x, event.y)
        self.gesture_points.append(Point(event.x, event.y)) # store all cursor movement points


if __name__ == '__main__':
    master = tk.Tk()
    window_width = 500
    window_height = 600
    master.geometry(str(window_width) + 'x' + str(window_height))  # master.geometry('500x600')
    master.resizable(0, 0)  # can not change the size of the window
    app = Application(window_width, window_height, master=master)
    app.mainloop()  # mainloop() tells Python to run the Tkinter event loop. This method listens for events, such as button clicks or keypresses, and blocks any code that comes after it from running until the window it's called on is closed.
