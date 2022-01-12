import numpy as np
import Stuffs as ai




class Idiot:
    def __init__(self,thinkingTime = 1):
        self.Time = thinkingTime
        self.Agent = ai.Cortex(n = 10, inp = 9, oup = 3,gamma = 0.99, learn = 0.01,ColumnParameters = [500,10,10,0.99])
    def Save(self,file):
        self.Agent.Save(file)
    def Load(self,file):
        self.Agent.Load(file)
    def Update(self,inp,oup = None,Reward = 1): #standard reward would be binary variable, 1 if it has IMPROVED, 0 if it has not IMPROVED
        #set reward to 1 if using oup
        #Setting reward to a negitive value will destroy it. Try to avoid.
        c = 0
        while(c<self.Time):
            superblurgh = self.Agent.Update(inp,oup,Reward)
            c += 1
        return self.Agent.GetOutput()



Agent = Idiot()
# Agent.Load("IDIOT")
past = [0,0,0,0,0,0,0,0,0]
empty = np.zeros((3))
moves = ['R','P','S']
CompScore = 0
LScore = 0

Cscore = 0
Pscore = 0
while True:
    re = 0
    if (CompScore>LScore):
        re = CompScore-LScore
    cmove = Agent.Update(past,Reward = re)
    cmoveActual = empty
    cmoveActual[cmove.index(max(cmove))] = 1
    move = input("R, P, or S?")
    m = moves.index(moves)
    playerMove = empty
    playerMove[m] = 1
    past.append(playerMove[0])
    past.append(playerMove[1])
    past.append(playerMove[2])
    past.pop(0)
    if(playerMove>cmoveActual):
        if(playerMove == 2 and cmoveActual == 0):
            print("Comp Wins")
            CompScore = ((CompScore*3)+1)/4
            Cscore += 1

        else:
            print("Player Wins")
            CompScore = ((CompScore*3)+0)/4
            Pscore += 1

    elif(playerMove<cmoveActual):
        if(cmoveActual == 2 and playerMove == 0):
            print("Player Wins")
            CompScore = ((CompScore*3)+0)/4
            Pscore += 1
        else:
            print("Comp Wins")
            CompScore = ((CompScore*3)+1)/4
            Cscore += 1
    else:
        CompScore = ((CompScore*3)+0.5)/4
        Cscore += 0.5
        Pscore += 0.5
    Agent.Save("IDIOT")
    print( "Computer: "+str(Cscore)+"     Player: "+str(Pscore))
        