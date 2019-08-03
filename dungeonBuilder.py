# f = open("newJSON.txt", "a")
# try:
# 	while True:
# 		xCoord = input("Enter the x-coord: ")
# 		yCoord = input("Enter the y-coord: ")
# 		f.write('{\n')
# 		f.write('  "x": ' + xCoord + '\n')
# 		f.write('  "y": ' + yCoord + '\n')
# 		f.write('  "type": "wall"' + '\n')
# 		f.write('},' + '\n')
# except KeyboardInterrupt:
# 	f.close()
def getType(char):
	if char == "w":
		return "wall"
	elif char == "e":
		return "enemy"
	elif char == "p":
		return "player"
	elif char == "E":
		return "exit"
	elif char == "t":
		return "treasure"
	elif char == "d":
		return "door"
	elif char == "k":
		return "key"
	elif char == "b":
		return "boulder"
	elif char == "s":
		return "switch"
	elif char == "B":
		return "bomb"
	elif char == "S":
		return "sword"
	elif char == "P":
		return "invincibility"


xCoord = 0
yCoord = 0
with open("fakeDung.txt", 'rU') as f:
	fw = open('newJSON.txt', 'a')
	for line in f:
		for char in line:
			print(char)
			if (char == 'F'):
				continue
			elif (char == '\n'):
				break
			fw.write('{\n')
			fw.write('  "x": ' + str(xCoord) + ',\n')
			fw.write('  "y": ' + str(yCoord) + ',\n')
			fw.write('  "type": "' + getType(char) + '"' + '\n')
			fw.write('},' + '\n')
			xCoord += 1
		yCoord += 1
	fw.close()
