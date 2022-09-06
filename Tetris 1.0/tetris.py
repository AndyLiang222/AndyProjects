#Name: Tetris 
#By: Andy Liang
#Date: January 27th, 2021
#Description: Recreated version of tetris with single player and multiplayer

#import the graphics, operating system, csv, random, and operator 
import pygame
import os
import csv
from random import randint
from pygame.locals import *
import operator
#initialize the pygame fonts
pygame.font.init()
#tetris board is 20x10
#fonts for my text in the game
HeaderF = pygame.font.SysFont('Comic Sans MS', 40)
myFont = pygame.font.SysFont('Comic Sans MS', 20)
#dimensions of my game window 
WIN_WIDTH = 900
WIN_HEIGHT = 600
#rgb for white and array of colors for tetris game pieces
white = (255,255,255)
colors = [0,(255, 213, 0),(128,0,128), (255, 50, 19), (114, 203, 59),(255, 151, 28), (3, 65, 174), (0,255,255),(0,0,0)
]
black = (0,0,0)
#creates window and loads the background images for the game 
win = pygame.display.set_mode((WIN_WIDTH,WIN_HEIGHT),0,24)
loading = pygame.image.load(os.path.join("images", "loading.gif"))
logo = pygame.image.load(os.path.join("images","logo.gif"))
rule = pygame.image.load(os.path.join("images", "rule.gif"))
gameBack = pygame.image.load(os.path.join("images", "Game.gif"))
#array for loading screen buttons
buttons1 = []
#x and y values for the tetris game pieces
gridPosX = [0,[1,2,1,2],[1,2,3,2],[1,2,2,3],[1,2,2,3],[1,2,3,3],[1,1,2,3],[0,1,2,3]]
gridPosY = [0,[1,2,2,1],[1,1,1,2],[1,1,2,2],[2,2,1,1],[1,1,1,2],[2,1,1,1],[1,1,1,1]]
# type : 1 = square, 2 = t , 3 = z left, 4 = z right, 5 = L left, 6  = l right, 7 = line
#Variables for actual game

#array to hold the different games running
cou = 0

games =[]

#repersents what status the game is in 
#status meaning: 1 = title, 2 = setting, 3 = single
status = 1
#rule type meaning: 0 = basic , 1 = single, 2 = multi
rStatus = 0
#variable to see if the current score has already been recorded. So the same score can't be entered multiple times
recorded = False
#texts for rules and loading screens
basic = HeaderF.render('How to play', False, white)
gameOver = HeaderF.render('Game Over', False , white)
singleplayer = HeaderF.render('How to play - single', False, white)
multiplayer  = HeaderF.render('How to play - Multi', False, white)
header = [basic, singleplayer, multiplayer]
r1 = myFont.render('1. Use arrow keys to move pieces and use c to hold', False, white)
r2 = myFont.render('2. Use down arrows to soft drop and space to hard drop', False, white)
r3 = myFont.render('3. complete horizontal lines to earn to lower the stack ', False, white)
r4 = myFont.render('4. when the stack reaches the top, you lose', False, white)
r5 = myFont.render('5. The next column will show what pieces will come next', False, white)
rules1 = [r1,r2,r3,r4,r5]

r6 =  myFont.render('1. every time you complete lines you gain points', False, white)
r7 =  myFont.render('2. You get bonuses for completing more lines at once', False, white)
r8 =  myFont.render('3. getting a tetris will gain a lot of points', False, white)
r9 =  myFont.render('4. get as many points as possible to get high scores', False, white)
rules2 = [r6, r7, r8,r9]

r10 =  myFont.render('1. send lines to your opponent to raise their stack', False, white)
r11 =  myFont.render('2. complete more lines in row to send more lines', False, white)
r12 =  myFont.render('3. player the last the longest without dying wins', False, white)
r13 =  myFont.render('4. Game will keep playing until someone dies', False, white)
#array to hold rules, the loser for multiplayer, and the grids for each game
rules3 = [r10, r11, r12,r13]

rules = [rules1,rules2, rules3]
loser = -1
grid=[]
grid2 = []
#class for button
class button :
    
    def __init__ (self, name, function, x, y):
        #bariables like the size, location, and the text
        self.activate = True
        self.name = name
        self.width = 100
        self.length = 50
        self.func = function
        self.b = Rect(x, y, self.width, self.length)
        self.x = x
        self.y = y
        self.x1 = x
        self.x2 = x+self.width
        self.y1 = y
        self. y2 = y+self.length
        self.tex = myFont.render(name, False,black )
        
    #functions for drawing the button, unactivating, and checking to see if mouse collides
    def draw(self):
        self.activate = True
        pygame.draw.rect(win, white, self.b)
        win.blit(self.tex, (self.x+5,self.y+5))
    def unactivate(self):
        self.activate = False
    def check(self,x, y):
        if (x >= self.x1 and x <=self.x2 and y >= self.y1 and y<= self.y2 and self.activate == True):
            run(self.func)
            
            #print("0k")
#class for game piece 
class piece: 
    def __init__ (self, typ , gam):
        #variables to hold position, rotation, size, and location
        self.rotation = 1
        self. left = 0
        self. right = 0
        self.bottom = 0
        self.type = typ
        self.held = False
        self. rX = []
        #variable to hold what game it is part of
        self.game = gam
        #based on type create location of the piece
        # type : 1 = square, 2 = t , 3 = z left, 4 = z right, 5 = L left, 6  = l right, 7 = line
        if (self.type == 1):
            self. x = [5, 6, 5,6]
            self.y = [0, 0, 1,1]
            self.left = 5
            self. right = 6
            self.bottom = 1
        if (self.type == 2):
            self. x = [5, 6, 7,6]
            self.y = [0, 0, 0,1]
            self.left = 5
            self. right = 7
            self.bottom = 1
        if (self.type == 3):
            self. x = [5, 6, 6,7]
            self.y = [0, 0, 1,1]
            self.left = 5
            self. right = 7
            self.bottom = 1
        if (self.type == 4):
            self. x = [7, 6, 6,5]
            self.y = [0, 0, 1,1]
            self.left = 5
            self. right = 7
            self.bottom = 1
        if (self.type == 5):
            
            self. x = [5, 6, 7,7]
            self.y = [0, 0, 0,1]
            self.left = 5
            self. right = 7
            self.bottom = 1
        if (self.type == 6):
            self. x = [5, 6, 7,5]
            self.y = [0, 0, 0,1]
            self.left = 5
            self. right = 7
            self.bottom = 1
        if (self.type == 7):
            self. x = [4, 5, 6,7]
            self.y = [0, 0, 0,0]
            self.left = 4
            self. right = 7
            self.bottom = 0

        #creates a locatoin of shape in terms of  held piece
        self.shapeX = self.x.copy()
        self.shapeY = self.y.copy()
        for x in range(len(self.shapeX)):
            self.shapeX[x] -= 4
        for y in range(len(self.shapeY)):
            self.shapeY[y] +=1

        #print(self.shapeY)
        #print(self.shapeX)
        #checks to see if the piece can be placed and not overlap othe pieces
        #grid2 is used to see if the piece is the current piece or previous piece
        global grid
        global grid2
        global over
        global loser
        #print("piece code is running")
        for n in range(len(self.x)):
            if (grid[self.game][self.y[n]][self.x[n]] != 0):
                over = True
                loser = self.game+1

                break
        #if able to be placed, add the piece to the grid
        if (over != True):
            for n in range(len(self.x)):
                grid[self.game][self.y[n]][self.x[n]] = self.type
                grid2[self.game][self.y[n]][self.x[n]] = 1
    #removes piece from the grid
    def remove(self):
        global grid
        global grid2
        for n in range (len(self.x)):
            grid[self.game][self.y[n]][self.x[n]] = 0
            grid2[self.game][self.y[n]][self.x[n]] = 0
    #when placed, run this function to change the current piece to placed piece
    def final(self):
        #print("final" , self.type)
        global grid2
        for n in range (len(self.x)):
            grid2[self.game][self.y[n]][self.x[n]] = 2
    #returns teh piece type
    def getType(self):
        return self.type
    #moves the piece down by removing the old location and adding the new location by going down 
    def movev (self):
        global grid
        global grid2
        self.bottom+= 1
        for n in range (len(self.x)):
            grid[self.game][self.y[n]][self.x[n]] = 0
            grid2[self.game][self.y[n]][self.x[n]] = 0
            self.y[n] += 1
        for n in range (len(self.x)):
            grid[self.game][self.y[n]][self.x[n]] = self.type
            grid2[self.game][self.y[n]][self.x[n]] = 1
    #same as the moving vertical but horizonatil
    def moveh (self, di):
        global grid
        
        global grid2
        for n in range (len(self.x)):
            grid[self.game][self.y[n]][self.x[n]] = 0
            grid2[self.game][self.y[n]][self.x[n]] = 0
            self.x[n] += di
        for n in range (len(self.x)):
            grid[self.game][self.y[n]][self.x[n]] = self.type
            grid2[self.game][self.y[n]][self.x[n]] = 1
        self.left += di
        self.right += di
        #print(grid)
    #function to check if the piece collide with an old piece or the walls. If not, move the piece based on direction given
    def checkCol(self, di ):
            global grid
            global grid2
            global games
            global over
            works = True
            #if the piece is moving down
            if di == 2 and over == False :
                """
                for ro in grid:
                    print(ro)

                print("XXXXXXXXXXX")
                for ro in grid2: 
                    print(ro)

                """
                #check the current piece overlaps with othe pieces, if it overlaps when moving down place the piece
               #print("kjflkdjafj;kakjf;jf")
                
                for n in range(len(self.y)):
                    #print(str(self.game) + "--------------------------")
                    if (grid[self.game][self.y[n]+1][self.x[n]] == 0 or grid2[self.game][self.y[n]+1][self.x[n]] == 1):
                        works = True
                    else:
                        #print("Hits the bottom")
                        works = False
                        games[self.game].place()
                        break    
                #if the movement is possible, move the piece vertically   
                if (works == True):

                    self.movev()
                #checks if the piece touches the ground, place the piece
                if self.bottom  == 19 : 

                    games[self.game].place()
            global cou
            #if direction is horizontal
            if di != 2 :
                #print ("kjalfjalkjflakfjklafkjlakjf")
                #print("yessir")
                #based on the direction, see if the piece will colide with the walls of the board
                if (self.left + di >= 0 and self.right + di < 10):
                       #check if the piece will collide with other pieces in the board
                    for n in range(len(self.y)):
                        if (grid[self.game][self.y[n]][self.x[n]+di] != 0):
                            if (grid2[self.game][self.y[n]][self.x[n]+di] != 1) :
                                works = False
                                #print(self.y[n], self.x[n]+di)
                            
                    #if it works, move piece
                    if (works == True):
                        #print("move horizontal " + str(count))
                        cou+= 1
                        self.moveh(di)

    #function to rotate piece                     
    def rotatePiece(self):
        #global variable for the grids
        global grid
        global grid2
        #hold to rotated x and y
        rY =[0,0,0,0]
        rX = [0,0,0,0]
        #variable to see if it works 
        works = True
        #if piece is not a square
        if (self.type != 1):
               #loop that goes through each point in the piece
               #if the point is the second one than don't rotate because it is the center
               # if not the center rotate the point and check if the point leaves the board 
            for point in range(len(self.y)):
                if (point == 1):
                    rY[1] = self.y[1]
                    rX[1] = self.x[1]
                    continue
                else:
                    dx = self.x[1] - self.x[point]
                    dy = self.y[1] - self.y[point]
                    dy = dy*-1
                    rY[point] = self.y[1] + dx
                    rX[point] = self.x[1] + dy

                    if(rY[point] >= 0 and rY[point] <=19 and rX[point] >= 0 and rX[point] <=9):
                        continue
                    else:
                        works = False
                        break
            #print("rotate X: ", rX)
            #print("rotate Y: ", rY)
            #if the piece doesn't leave the board, check if the piece overlaps with old pieces in the board
            if(works == True):
                #go through each point and check
                for p in range(len(rY)):
                    
                    if (grid[self.game][rY[p]][rX[p]] == 0 or grid2[self.game][rY[p]][rX[p]] == 1):
                        works = True
                    else:
                        works = False
                        break
                #if the point does overlap
                # change to location of the piece
                if (works == True):
                    self.left = rX[1]
                    self.right = rX[1]
                    self.bottom = rY[1]
                    for n in range (len(self.x)):
                        grid[self.game][self.y[n]][self.x[n]] = 0
                        grid2[self.game][self.y[n]][self.x[n]] = 0
                        self.y[n] = rY[n]
                        self.x[n] = rX[n]
                        self.left = min(rX[n], self.left)
                        self.right = max(rX[n], self.right)
                        self.bottom = max(rY[n], self.bottom)
                    for n in range (len(self.x)):
                        grid[self.game][self.y[n]][self.x[n]] = self.type
                        grid2[self.game][self.y[n]][self.x[n]] = 1
                """
                for row in grid:
                    print(row)
                print("___________________")
                for row in grid2: 
                    print(row)
        """
#object for the user to enter text 
class textBox ():
    #contains veriables like the locations, dimensions, the shape, and the text 
    #contains the draw function, add text function, removing text, and resting the text

    def __init__(self,cX, cY, length, width):
        self.x1 = cX
        self.y1 = cY
        self.l = length
        self.w = width
        self.x2 = self.x1+length
        self.y2 = self.y1+width
        self.b = Rect(self.x1, self.y1, self.l, self.w)
        self.activate = False
        self.text = ""
        self.tex = myFont.render(self.text, False,black )
        self. color = (255,255,255)
    def draw(self):
        pygame.draw.rect(win, white, self.b)
        win.blit(self.tex, (self.x1+5,self.y1+5))
        self.activate = True
    def addText(self,dt):
        self.text += dt
        self.tex= myFont.render(self.text, False,black )
        self.draw()
    def removeText(self):
        self.text = self.text[:-1]
        self.tex= myFont.render(self.text, False,black )
        self.draw()
    def reset(self):
        self.text = ""
        self.tex= myFont.render(self.text, False,black )
        #self.draw()
    #function to see if the textbox is clicked
    def check(self,x, y):
        if (x >= self.x1 and x <=self.x2 and y >= self.y1 and y<= self.y2 and self.activate == True):
                self.color = (220,220,220)
        else:
                self.color = (255,255,255)   
            
#creates a text box and an array of buttons for each screen 
#buttons1 is for main menu, buttons 2 is for the rule screen, and buttons3 is for when the game is over
textbox = textBox(50, 500, 300, 40)
# buttons
buttons2 = [button("next", 4, 50, 500),button("back", 5, WIN_WIDTH-150, 500)]

buttons3 = [button("back", 6 , 450, 500)]

#over variable to see if the game ends
over = True
level = 0
#score chart to hold the base score for the number of lines completed
scoreChart = [0,40, 100, 300,1200]
#object game, able to make multiple games 
class game ():
    #creates the variables for the game
    #main variables are nexgrid and hold grid to display the next and held pieces 
    #for loops creates the hold and nex grid and clears the game grids for the new game
    #important variables are cur piece to hold teh current piece and mode to hold if it is single player or multiplayer
    def __init__ (self,x, y, num, mode):
        global grid
        global grid2
        global over 
        over = False
        self.holdGrid = []
        self.xx = x
        self.yy = y
        self.num = num
        self.nex =[randint(1,7),randint(1,7),randint(1,7)]
        self.nexGrid = []
        self.nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])
        self.nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])
        self.nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])

        for y in range (4):
            temp = []
            for x in range (4):
                temp.append(0)
            self.holdGrid.append(temp)
        if(self.num == 0):
            grid.clear()
            grid2.clear()
        self.grid = []
        for y in range(20):
            temp = []
            for x in range(10):
                temp.append(0)
            self.grid.append(temp)
        grid.append(self.grid)
        self.grid2 = []
        for y in range(20):
            temp = []
            for x in range(10):
                temp.append(0)
            self.grid2.append(temp)
        grid2.append(self.grid2)

        #print(grid)
        #print(grid2)
        self.curPiece = 0
        self.heldType = 0
        self.run = True
        
        self.score = 0
        self.placed = False
        self.start = True
        

        self.totalLines= 0
        self.mode = mode
        self.level = 0
        
    #creates the next pieces for the game
    def nextPiece(self):
        global gridPosY
        global gridPosX
        
        #print(nex)
        #cretes the current piece
        #changes the nex piece and nex grid
        self.curPiece = piece(self.nex.pop(),self.num)
        self.nex.insert(0,randint(1,7))
        self.nexGrid.insert(0,self.nexGrid.pop())
        if (self.start == True):
            #if it is the start of the game create the next 3 pieces
            for n in range(3):
                for y in range(len(gridPosX[self.nex[n]])):
                    self.nexGrid[n][gridPosY[self.nex[n]][y]][gridPosX[self.nex[n]][y]] = self.nex[n]
            self.start = False
        else:
            #however if it is not the start the change the next grid
            for y in range(len(self.nexGrid[0])):
                    for x in range(len(self.nexGrid[0 ][y])):
                        self.nexGrid[0][y][x]= 0
            for y in range(len(gridPosX[self.nex[0]])):
                        self.nexGrid[0][gridPosY[self.nex[0]][y]][gridPosX[self.nex[0]][y]] = self.nex[0]
    def reset(self,mode):
        #resets the global variables and draws the loading screen
        if mode == 1 or mode == 2 or mode == 3:
            #global grid
            #global holdGrid
            #global nex
            #global nexGrid
            global level
            level = 0
            #global grid2
            #global curPiece
            #global heldType
            global over
            #global score
            #global placed
            #global start
            global status 
            global recorded
            global games
            games.clear()
            """
            sgrid = []
            holdGrid = []

            recorded = False
            nex =[randint(1,7),randint(1,7),randint(1,7)]
            nexGrid = []

            for y in range (4):
                temp = []
                for x in range (4):
                    temp.append(0)
                holdGrid.append(temp)

            textbox.reset()   

            nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])
            nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])
            nexGrid.append([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]])
        
            for y in range(20):
                temp = []
                for x in range(10):
                    temp.append(0)
                grid.append(temp)

            grid2 = []
            for y in range(20):
                temp = []
                for x in range(10):
                    temp.append(0)
                grid2.append(temp)

            curPiece = 0
            heldType = 0
            """
            over = False
            """
            score = 0
            placed = False
            start = True
            """
            status = 1
            drawLoading()
    #adds line to the opponent, only for 2 player    
    def addLine(self):
        #print("bruakjfalkjfkjakjfalkfjafla")
        #gets the grid 
        global grid
        global grid2
        #creates a random space in the line to be open
        openSpace = randint(1,10)
        #array to hold the new line for grid 
        lin = []
        #loop to create the line, 8 is for black 
        for x in range(10):
            if(x == openSpace):
                lin.append(0)
            else:
                lin.append(8)
        #array to hold the new line for grid 

        lin2 = []
        #loop to create the line, 2 is for placed blocks 
        for x in range(10):
            if(x == openSpace):
                lin2.append(0)
            else:
                lin2.append(2)
        #finds an empty line in the grid to remove and a line to the bottom
        for x in range(len(grid[self.num])):
            emp = True

            for y in range(len(grid[self.num][x])):
                
                if (grid[self.num][19-x][y] != 0):
                    emp = False
                    break
            #if the line is empty remove the line from grid and grid2 and break 
            if (emp == True):
                #print(grid[self.num][19-x])
                #print("remoiafkajfjodifjalfjlafjlksajfals" + str(19-x))
                grid[self.num].pop(19-x)
                grid2[self.num].pop(19-x)
                done = True
                break
        #add the new line to both grids
        grid[self.num].append(lin)
        grid2[self.num].append(lin2)
        #for row in grid2[self.num]:
            #print(row)
    #draws the game to the window 
    def drawWindow(self,  mode):
    # actual code
        #print ("daw WIndow")
        #gets all the global variables
        global grid
        global grid2
        global gameOver

        #global curPiece
        global HeaderF
        global over
        #global holdGrid
        #global nexGrid
        global buttons1
        global buttons2
        global buttons3
        global level
        global myFont
        global WIN_WIDTH
        global colors
        global gameBack
        global textbox
        global white
        global loser
        #create the texts for the window 
        scor =HeaderF.render("Score: " + str(self.score), False, white)
        levl = HeaderF.render("Level " + str(level), False, white)
        final = HeaderF.render("Final Score: " + str(self.score), False, white)
        winner = HeaderF.render("The winner is Player: " + str(loser%2+1),False, white)
        
        #if this is the first game draw the background
        if (self.num == 0):
            win.blit(gameBack, (0,0))
            
        #for row in grid:
            #print (row)
        #un activate the main menu buttons and rule buttons
        for b in buttons1:
            b.unactivate()
        for b in buttons2:
            b.unactivate()
        
        #print(over)
        #if the game is over draw the game over screen 
        if over == True:
            #print("endafjaf  ")
            #if mode is multiplayer write who won
            if (self.mode == 2):
                win.blit(winner,(WIN_WIDTH/2-100, WIN_HEIGHT/2))
            if (self.num == 0):
                win.blit(gameOver,(WIN_WIDTH/2-100, self.yy-50))
            
            win.blit(final, (self.xx-125 , self.yy))
            #draw the buttons
            for b in buttons3:
                b.draw()


            #if game is over and is single player draw the leaderboard and the textbox 
            if (self.mode == 1):
                textbox.draw()
                leaderbox = Rect(WIN_WIDTH/2-100, 300, 200, 150)
                pygame.draw.rect(win, white, leaderbox)
                #opens the csv file and creates a sorted list of the places 
                with open('highScore.csv') as csv_file:

                    csv_reader = csv.reader(csv_file, delimiter = ',')
                       
                       
                    leaderboard = []
                    lCount = 0
                    #print(csv_reader)
                    sortedlist = []
                    for r in csv_reader:
                        if (len(r) != 0):
                            leaderboard.append(r) 


                            sortedlist = sorted(leaderboard, key=operator.itemgetter(1), reverse=True)
                            #print(sortedlist)
                    #draws the user and the score
                    for i in range(min(5, len(sortedlist))):
                        wor = len(sortedlist[i][0]) +len(sortedlist[i][1])
                        space = ""
                        for x in range(19-wor):
                            space += " "
                        rank = myFont.render(str(lCount+1) + ". "+sortedlist[i][0] + space +sortedlist[i][1] ,False, (0,0,0))
                        win.blit(rank,(leaderbox.topleft[0] + 5,leaderbox.topleft[1] + 5 +lCount*25))
                        lCount+=1
        else:
            #print("started the game jglakdfjafj")
            #if in game draw the game from the locaiton of CY and cX
            #draw the boxs for the game board, hold box, and next box
            cY = self.yy
            cX = self.xx-100
            bLength = 400
            bWidth = 200
            sqL = 20
            box = Rect(cX, cY, bWidth, bLength)
            hbox = Rect(cX-100, cY, 4*sqL, 4*sqL)
            nbox = Rect(cX+bWidth+20, cY, 4*sqL, 12*sqL)
            pygame.draw.rect(win, white, box)
            pygame.draw.rect(win, white, hbox)
            pygame.draw.rect(win, white, nbox)
            #draw the score and level of the game
            win.blit(scor, (cX-80, cY-120))
            win.blit(levl, (cX-80, cY-70))
            #for row in grid[self.num]:
                #print(row)
            #goes through each cell in the 2d grid and draw a square if piece is located
            for y in range (20):
                for x in range (10):
                    if (grid[self.num][y][x] != 0):
                        sq = Rect(cX+x*sqL, cY +y * sqL, sqL, sqL)
                        pygame.draw.rect(win, colors[grid[self.num][y][x]], sq)
                #print("bababoey")
                #print(holdGrid)
                #print(holdGrid)

            #draws the hold grid like the regular grid
            for y in range(len(self.holdGrid)):
                for x in range(len(self.holdGrid[y])):
                    if(self.holdGrid[y][x] != 0):
                        sq = Rect(cX+x*sqL-100, cY +y * sqL, sqL, sqL)
                        pygame.draw.rect(win, colors[self.holdGrid[y][x]], sq)
                #print(len(nexGrid))

            #draws the next grid like the regular grid
            for i in range(2,-1,-1):
                    #print(i)
                for y in range(len(self.nexGrid[i])):
                    for x in range(len(self.nexGrid[i][y])):
                        if(self.nexGrid[i][y][x] != 0):
                            sq = Rect(cX+x*sqL+bWidth +20, cY +y * sqL+(80*i), sqL, sqL)
                            pygame.draw.rect(win, colors[self.nexGrid[i][y][x]], sq)
        

    #function to final place the piece and check if line is completed
    def place (self):
        #global variable for the grid, over, and score variables
        #global score
        #global curPiece
        global grid
        global grid2
        #global placed
        global games
        global over
        global scoreChart
        #global totalLines
        global level

        #check if the game is already over
        #set the current piece to it's final position and check if any lines have been completed
        if (over == False):
            self.curPiece.final()
            #counts the completed lines and checked lines
            completed = 0
            counter = 0
            #goes through 4 lines and checks if there is any empty space
            for x in range(4):
                remove = True
                
                for x in range(len(self.grid)):
                    remove = True
                    for st in grid[self.num][x]:
                        if (st == 0):
                            remove = False
                            counter+= 1
                            break
                    #if removed is true, than remove the line from the grid and insert new line
                    #if the gamemode is multiplayer, add line to the opponent
                    if (remove == True):
                        #print("Removed")
                        grid[self.num].pop(x)
                        grid[self.num].insert(0, [0,0,0,0,0,0,0,0,0,0])
                        grid2[self.num].pop(x)
                        grid2[self.num].insert(0, [0,0,0,0,0,0,0,0,0,0])
                        completed+= 1
                        if (len(games) > 1):
                            if (self.num == 0):
                                games[1].addLine()
                            if (self.num == 1):
                                games[0].addLine()
            
            #reset the placed variable and create a new piece
            self.placed = True
            self.nextPiece()
            #update score and level
            self.score+= (self.level+1)*scoreChart[completed]
            level = int(self.score/10000)
    #hold function to hold pieces in tetris
    def hold (self):
        #global heldType
        #global curPiece
        #global holdGrid
        #checks if there has already been a hold switch recently
        if (self.curPiece.held == False):
            #if not reset the hold grid 
            for y in range(len(self.holdGrid)):
                for x in range(len(self.holdGrid[y])):
                    self.holdGrid[y][x]= 0

            #removes the current piece from the grid
            self.curPiece.remove()
            #checks to see if there is no piece currently held
            #if not change hold grid to the current piece, change held type to the current, and create new piece
            if (self.heldType== 0):
                #print("length" , len(curPiece.shapeY))
                for y in range(len(self.curPiece.shapeY)):
                    self.holdGrid[self.curPiece.shapeY[y]][self.curPiece.shapeX[y]] = self.curPiece.getType()
                self.heldType = self.curPiece.getType()
                #print()
                self.nextPiece()
            else:
                #if there is a piece in hold change the hold grid to the current piece
                for y in range(len(self.curPiece.shapeY)):
                    self.holdGrid[self.curPiece.shapeY[y]][self.curPiece.shapeX[y]] = self.curPiece.getType()
                #save current type in temp variable
                #creates current piece asthe held type
                #sets held piece as previous piece
                curType= self.curPiece.getType()
                self.curPiece = piece(self.heldType, self.num)
                self.heldType = curType
            #set held to true so you can't spam the hold
            self.curPiece.held = True




#main code functions

#draws the loading screen/ the main menu
def drawLoading():
    #gets the game status as 1
    global status
    status = 1
    #draws the background and the logo
    win.blit(loading,(0,0))
    win.blit(logo, (WIN_WIDTH/2-458/2, 50))
    #creates the buttons for main menu and add it to the buttons1 array
    b1 = button("1 player", 1,WIN_WIDTH/2-200 , 450)
    b2 = button("2 player", 2, WIN_WIDTH/2-50, 450)
    b3 = button("Rules", 3, WIN_WIDTH/2+100, 450)
    b1.draw()
    b2.draw()
    b3.draw()
    global buttons1
    buttons1.append(b1)
    buttons1.append(b2)
    buttons1.append(b3)
    #unactivates the other buttons
    for b in buttons2:
        b.unactivate()
    for b in buttons3:
        b.unactivate()
#draws the rule screen
def drawRules(type):
    global status
    global header
    global rules
    #sets status to 2 which is rule screen, draws button array 2, and unactivates buttons1
    status = 2
    for b in buttons1:
        b.unactivate()
    win.blit(rule,(0,0))
    win.blit(header[type], (WIN_WIDTH/2-100,50))
    for b in buttons2:
        b.draw()
    #draws the rules based on whate rule page you are on
    for x in range(len(rules[type])):
        win.blit(rules[type][x], (WIN_WIDTH/2-250, 150+x*50))

#adds a score and name to the csv file
def addScore(name,score):
    global recorded
    with open('highScore.csv', 'a') as highScore_file:
        score_writer = csv.writer(highScore_file, delimiter = ',', quotechar ='"', quoting = csv.QUOTE_MINIMAL)
        score_writer.writerow([name, score])
        recorded = True
#variable for the number of players 
players = 0
#run function for buttons 
def run (function):
    global rStatus
    global curPiece
    global score
    global status
    global game
    global over
    global players
    #if the button 1 is clicked, add a single player game to the array and run the game
    #sets players to 1 and set game status to ingame
    if (function == 1):
        #print ("fuajfld")
        #print(over)
        games.append(game(450, 150, 0, 1))
        games[0].nextPiece()
        games[0].drawWindow( 1)
        players = 1
        status = 3
        over = False
    #if button 2 is clicked, add 2 games the first one being the main. Run both games
    #set players to 2 and status to ingame
    if (function == 2):
        #print(over)
        games.append(game(225, 150, 0, 2))
        games.append(game(400+275, 150, 1, 2))
        games[0].nextPiece()
        games[0].drawWindow( 1)
        games[1].nextPiece()
        games[1].drawWindow( 2)
        players = 2
        status = 3
        over = False
    #if button3 is clicked draw the rules based on the rule status
    if (function == 3):
        #settings
        drawRules(rStatus)
    #if button 4 is clicked than rotate the rule status and redraw the rules
    if (function == 4):
        
        
        rStatus += 1
        if rStatus == 3:
            rStatus = 0
        drawRules(rStatus)
    #if button 5 is clicked redraw the loading screen and reset the rule status
    if (function == 5):
        rStatus = 0
        status = 1
        drawLoading()
    #if button 6 is pressed reset the game, turn off the textbox and draw the main screen
    if (function == 6):
        for x in games:
            x.reset(1)
        textbox.reset()
        textbox.activate = False
        drawLoading()
#draw the main menu at the start
drawLoading()
#create the clock and count
clock = pygame.time.Clock()
count = 0
#infinite loop for the game
while (run):
    #print(status)
    #print("______________")
    #set the clock to repeat the loop 30 times a second 
    clock.tick(30)
    #if in game draw window for each game
    if (status == 3):
        count+= 1
        #print(count)
        g = 1
        for x in games:
            x.drawWindow( g)
            x.placed = False
            g+= 1
    #if count is 15 and the game is not over move teh pieces down and reset the count
    #speeds up based on the level
    if (count == 15-level ):
        #print("______________")
        #print(games)
        for x in games:
            x.curPiece.checkCol(2)
        count = 0
    #loop through all events
    for event in pygame.event.get():
        #closes window
        if (event.type == pygame.QUIT):
            run = False
        #when mouse button is clicked check if it presses any buttons 
        if (event.type ==  pygame.MOUSEBUTTONUP):
            #print(status)
            pos = pygame.mouse.get_pos()
            #checks butons depending on which screen the game is running 
            if (status == 1):
                for b in buttons1:
                    b.check(pos[0],pos[1])
            if (status == 2):
                for b in buttons2:

                    b.check(pos[0],pos[1])
            if (status == 3 and over == True ):
                for b in buttons3:
                    b.check(pos[0],pos[1])
                textbox.check(pos[0],pos[1])
                
        #if checs if keys are pressed and ingame is running
        if (event.type == pygame.KEYDOWN and status == 3):
               #backspace removes the latest character in the textbox
               #enter will add the score to the leader board
               #any other text will be added to the text in the textbox
            if (over == True and textbox.activate == True):
                if event.key == pygame.K_BACKSPACE:
                    textbox.removeText()
                elif event.key == pygame.K_RETURN and recorded == False:
                       print(games[0].score)
                       addScore(textbox.text, games[0].score)
                       textbox.reset()
                elif recorded == False:
                    textbox.addText(event.unicode)

            #moves game one piece to the left
            if (event.key == pygame.K_LEFT):
                #print("ight")
                games[0].curPiece.checkCol(-1)
            #moves game one piece to the right    
            if(event.key == pygame.K_RIGHT):
                games[0].curPiece.checkCol(1)
            #moves the piece down
            if (event.key == pygame.K_DOWN):
                games[0].curPiece.checkCol(2)
            #drops the piece until it can't be moved down anymore
            if (event.key == pygame.K_SPACE):
                #bprint(over)
                while (games[0].placed == False):
                    #print("ran the loop")

                    games[0].curPiece.checkCol(2)
                games[0].placed = False

            #hold the current piece
            if(event.key ==  pygame.K_c):
                #print("c key was pressed")
                games[0].hold()
            #rotates the piece
            if(event.key == pygame.K_UP):
                games[0].curPiece.rotatePiece()


            #more keybinds if there are 2 players 
            if(status == 3 and players == 2):
                #the keybinds use WASD for movements like the previous keybinds but for the second game
                if (event.key == pygame.K_a):
                    #print("ight")
                    games[1].curPiece.checkCol(-1)
                    
                if(event.key == pygame.K_d):
                    games[1].curPiece.checkCol(1)
                if (event.key == pygame.K_s):
                    games[1].curPiece.checkCol(2)

                #k is used for hardrop and l is used for the hold 
                if (event.key == pygame.K_k):
                    while (games[1].placed == False):

                        games[1].curPiece.checkCol(2)
                    games[1].placed = False
                if(event.key ==  pygame.K_l):
                    #print("c key was pressed")
                    games[1].hold()
                if(event.key == pygame.K_w):
                    games[1].curPiece.rotatePiece()
    #update pyame display
    pygame.display.update()