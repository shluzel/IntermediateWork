import csv


def importcsv(data, path, rec_mode):
	with open(path, rec_mode, newline='') as file:
		writer = csv.writer(file)
		writer.writerow(data)

def rewritecsv(data_list, path, rec_mode):
	with open(path, rec_mode, newline='') as file:
		for note in data_list:
			for text in note:
				if note.index(text) == (len(note) - 1):
					file.write(text)
					break
				file.write(text + ',')
			file.write('\n')