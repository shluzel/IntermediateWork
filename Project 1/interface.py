from check import Checking as ch

class userinterface:
    def Interface(i: str):
        match i:
            case "Welcome":
                print('-'*60)
                print('Hello, user!')
                
            case "Menu":
                print('-'*60)
                print('MENU')
                print('-'*50)
                print('1. Creating, editing and deleting notes')
                print('2. Reading your notes')
                print('3. Exit')
                
            case "Export":
                print('-'*60)
                print('MENU')
                print('-'*60)
                print('1. Read all notes')
                print('2. Read specified notes')
                print('3. Exit')

            case "Notes_Search":
                print('-'*60)
                print('MENU')
                print('-'*60)
                print('1. Search notes for ID')
                print('2. Search notes for header')
                print('3. Search notes for date')
                print('4. Exit')
                
            case "Mistake":
                print('-'*60)
                print('------ ERROR -------')
                print('-'*60)
                
            case "Notes_Action":
                print('-'*60)
                print('MENU')
                print('-'*60)
                print('1. Create note')
                print('2. Edit note')
                print('3. Delete note')
                print('4. Exit')

            case "Notes_Changes":
                print('-'*60)
                print('MENU')
                print('-'*60)
                print('1. Edit exact note for ID')
                print('2. Edit exact note for header')
                print('3. Edit exact note for date')
                print('4. Exit')
            
            case "Notes_Delete":
                print('-'*60)
                print('MENU')
                print('-'*60)
                print('1. Delete exact note for ID')
                print('2. Delete exact note for header')
                print('3. Delete exact note for date')
                print('4. Exit')
                
            case "End":
                print('-'*60)
                print('Choose your next step')
                print('-'*60)
                print('1. Exit in main menu')
                print('2. End of programm')

    def selectfilter(case):
        userinterface.Interface(case)
        while (True):
            i = ch.action("item")
            if i == "1":
                print('Please enter item for search')
                return  int(0) 
                
            elif i == "2":
                print('Please enter header for search')
                return int(2)
            
            elif i == "3":
                print('Please enter the date for search')
                return int(1)
            
            elif i == "4":
                print('-'*60)
                print("End of programm")
                print('-'*60)
                break

            else:
                print('-'*60)
                print("Wrong data")
                print('-'*60)
