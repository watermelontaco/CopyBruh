from tkinter import *
from tkinter import tix
import tkinter as tk 

class DataStorageGUI(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.pack(fill="both", expand=True)

        # Create a list to store the data
        self.data_list = []

        # Create a dictionary to store the UI boxes
        self.ui_boxes = {}

        # Create the UI
        self.create_widgets()
        

    def create_widgets(self):
        # Create a label and text entry for entering new data
        self.new_data_label = tk.Label(self, text="Enter new data:")
        self.new_data_label.grid(row=0, column=0)
        self.new_data_entry = tk.Entry(self)
        self.new_data_entry.grid(row=0, column=1)
        self.new_data_entry.bind('<Return>', self.save_data)

        # Create a label and UI boxes for displaying existing data
        self.existing_data_label = tk.Label(self, text="Existing data:")
        self.existing_data_label.grid(row=1, column=0)
        for i in range(25):

            self.ui_boxes[i] = tk.Entry(self)
            self.
            self.ui_boxes[i].grid(row=i+2, column=0, columnspan=2)
            self.ui_boxes[i].bind('<Button-1>', self.copy_data)

    def save_data(self, event):
        # Get the new data from the text entry
        new_data = self.new_data_entry.get()

        # Add the new data to the list
        self.data_list.append(new_data)

        # Update the UI boxes with the new data
        for i in range(len(self.data_list)):
            self.ui_boxes[i].delete(0, tk.END)
            self.ui_boxes[i].insert(0, self.data_list[i])

        # Clear the text entry
        self.new_data_entry.delete(0, tk.END)

    def copy_data(self, event):
        # Get the data from the UI box that was clicked
        data = event.widget.get()

        # Copy the data to the clipboard
        self.master.clipboard_clear()
        self.master.clipboard_append(data)

if __name__ == '__main__':
    root = tk.Tk()
    root.title("CopyBuddy 1.0")
    #root.iconbitmap("icon file here")
    app = DataStorageGUI(master=root)
    app.mainloop()
