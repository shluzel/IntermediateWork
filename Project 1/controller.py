from interface import userinterface as console
from check import Checking as ch
from model.action import action as act
from model import export as exp

path = 'Project 1/note.csv'

def main(path):
    console.Interface("Welcome")
    while(True):
        console.Interface("Menu")
        i = ch.action("item")
        match i:
            case "1": 
                console.Interface("Notes_Action")
                i = ch.action("item")
                if i == "1":#create
                    print('-'*50)
                    print('Your choice "Create note"')
                    print('-'*50)
                    act.createnote(path)

                elif i == "2": #change
                    print('-'*50)
                    print('Your choice "Edit note"')
                    print('-'*50)
                    x = console.selectfilter("Notes_Changes")
                    act.changenote(path, x)
                    
                elif i == "3": #delete
                    print('-'*50)
                    print('Your choice "Delete note"')
                    print('-'*50)
                    x = console.selectfilter("Notes_Delete")
                    act.deletenote(path, x)

                elif i == "4":
                    print('-'*50)
                    print("End of programm")
                    print('-'*50)
                    break
                else:
                    print('-'*50)
                    print("Wrong data")
                    print('-'*50)
               
            case "2":
                console.Interface("Export")
                i = ch.action("item")
                if i == "1":
                    print('-'*50)
                    print('Your choice "All notes"')
                    print('-'*50)
                    exp.showallnotes(path)
 
                elif i == "2": 
                    print('-'*50)
                    print('Your choice "Note for your options"')
                    print('-'*50)
                    exp.selectedfilter(path)
                elif i == "3":
                    print('-'*50)
                    print("End of programm")
                    print('-'*50)
                    break
                else:
                    print('-'*50)
                    print("Wrong options")
                    print('-'*50)
                
        
            case "3":
                print('-'*50)
                print("End of programm")
                print('-'*50)
                break
            case _: 
                print('-'*50)
                print("Wrong data")

main(path)