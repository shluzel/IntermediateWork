from datetime import datetime as dt
import sys
import os

class Checking:
    def action(desc: str):
        while(True):
            i = input('Enter data {}: '.format(desc)) 
            if i.isdigit():
                return i
            print("Wrong data!")
    
    def inputdata():
        id = Checking.action("ID: ")
        time = dt.now().strftime('%D')
        main_note = Checking.checkinputstring('Header: ')
        note= Checking.checkinputstring('Note: ')
        return [id, time, main_note, note]

    def checkinputstring(desc: str):
        while True:
            val = input('Enter data {}: '.format(desc))
            if len(val) < 1:
                print('You entered "{}". It should not be numb.'.format(desc))
                continue
            return val
    
    def digit(x):
        while(True):
            i = input("Choose your item: ")
            if i.isdigit():
                i = int(i)
                if i < x+1:
                    return i
                else: 
                    print("Please enter correct number.")
                    continue
            print("You need to enter number:")

    def checkreadfile(file_path):
        if not os.path.exists(file_path):
            print(f'There is no such file: {file_path}')
            sys.exit()