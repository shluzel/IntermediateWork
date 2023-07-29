import csv
from check import Checking as ch
from interface import userinterface as console


def readdata(path): 
    ch.checkreadfile(path)
    with open(path, "r") as file:
        reader = csv.reader(file)
        data_list = []
        for row in reader:
            data_list.append(row)
        return data_list
			
def showallnotes(path): 
	list_note = readdata(path)
	print('''\t ID ||   Дата   ||   Заголовок  ''')
	print('=' * 50)
	for note in list_note:
		print(f'\t {note[0]}. || {note[1]} ||   {note[2]} \n       Note: {note[3]}' )
	print('=' * 50)

def selectedfilter(path):
    while (True):
        console.Interface("Notes_Search")
        k = ch.action()
        if k == "1":  
            print('-'*60)
            print('Your choice "ID"')
            print('-'*60)
            showselectednote(path, 0)

        elif k == "2": 
            print('-'*60)
            print('Your choice "Header"')
            print('-'*60)
            showselectednote(path, 2)
                    
        elif k == "3": 
            print('-'*60)
            print('Your choice "Date"')
            print('-'*60)
            showselectednote(path, 1)        

        elif k == "4":
            print('-'*60)
            print("Exiting in main menu")
            print('-'*60)
            break
                     
        else:
            print('-'*60)
            print("Wrong data")
            print('-'*60)


def showselectednote(path, x):
	list_note = readdata(path)
	print('Options for search: ')
	search_filter = ch.checkinputstring('Your data')
	for note in list_note:
		if search_filter == note[x] in note:
			index = list_note.index(note)
			print(list_note[index])