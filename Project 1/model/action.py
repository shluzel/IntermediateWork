from check import Checking as ch
import csv
from model import export as exp
from model import action as act
from model import imp as imp


class action:
    def createnote(path):
        ls = ch.inputdata()
        with open(path, 'a', encoding='utf-8') as file:
            writer = csv.writer(file)
            writer.writerow(ls)
        with open(path, 'r', encoding='utf-8') as file:
            lines = file.readlines()
            lines = lines[:-1]
        with open(path, 'w') as f:
            f.writelines(lines)

    def changenote(path, x):
        index_list = []
        list_note = exp.readdata(path)
        change_filter = ch.checkinputstring('Your options')
        i = 1
        for note in list_note:
            if change_filter == note[x] in note:
                index = list_note.index(note)
                print(i, ". ", list_note[index])
                index_list.append(index)
                i = i+1
            
        if len(index_list) == 1:
            ls= ch.inputdata()
            print(f'Note {list_note[index]} successfully changed. \nNote now: {ls}')
            list_note.pop(index)
            list_note.insert(index, ls)
            imp.rewritecsv(list_note, path, 'w')
            return
        elif len(index_list) > 1:
            i = ch.digit(len(index_list))
            ls= ch.inputdata()
            print(f'Note {list_note[index_list[i-1]]} succesfully changed. \nNote now {ls}')
            list_note.pop(index)
            list_note.insert(index, ls)
            imp.rewritecsv(list_note, path, 'w')
            return
        else:
            print('Wrong options:"{}" Note not found.'.format(change_filter))

    def deletenote(path, x):
        index_list = []
        list_note = exp.readdata(path)
        delete_filter = ch.checkinputstring('Your options')
        i = 1
        for note in list_note:
            if delete_filter == note[x] in note:
                index = list_note.index(note)
                print(i, ". ", list_note[index])
                index_list.append(index)
                i = i+1

        if len(index_list) == 1:
            print(f'Note {list_note[index]} succesfully deleted.')
            list_note.pop(index)
            imp.rewritecsv(list_note, path, 'w')
            return
        elif len(index_list) > 1:
            i = ch.digit(len(index_list))
            print(f'Note {list_note[index_list[i-1]]} succesfully deleted.')
            list_note.pop(index_list[i-1])
            imp.rewritecsv(list_note, path, 'w')
            return
        else:
            print('Wrong options:"{}" Note not found.'.format(delete_filter))