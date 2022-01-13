import numpy as np
import Stuffs as ai




class Idiot:
    def __init__(self,thinkingTime = 1):
        self.Time = thinkingTime
        self.Agent = ai.Cortex(n = 10, inp = 9, oup = 3,gamma = 0.95, learn = 0.1, epsilon = 0.005, ColumnParameters = [50,5,5,0.99])
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
Agent2 = Idiot()
#Agent.Load("IDIOT")
past = [0,0,0,0,0,0,0,0,0]
empty = np.zeros((3))
moves = ['R','P','S']
CompScore = 0
CompScore2 = 0
LScore = 0
LScore2 = 0
re2 = 0

Cscore = 0
Pscore = 0
while True:
    re = 0
    if (CompScore>LScore):
        re = CompScore-LScore
    if (CompScore2>LScore2):
        re2 = CompScore2-LScore2
    LScore2 = CompScore2
    LScore = CompScore
    cmove = Agent.Update(past,Reward = re)
    cmoveActual = empty
    #print(cmove)
    CInd = cmove.tolist().index(max(cmove))
    cmoveActual[CInd] = 1
    move = None
   # while(not (move in moves)):
    #    print("Choose a move")
   #     move = input("R, P, or S?")
   #     if(move == "q"):
    #        assert(1==2)
   # m = moves.index(move)
    cmove2 = Agent2.Update(past,Reward = re2)
    m = cmove2.tolist().index(max(cmove2))
    playerMove = empty
    playerMove[m] = 1
    past.append(playerMove[0])
    past.append(playerMove[1])
    past.append(playerMove[2])
    past.pop(0)
    if(m>CInd):
        if(m == 2 and CInd == 0):
            print("Comp Wins")
            CompScore = ((CompScore*3)+1)/4
            Cscore += 1

        else:
            print("Player Wins")
            CompScore = ((CompScore*3)+0)/4
            CompScore2 = ((CompScore2*3)+1)/4
            Pscore += 1

    elif(m<CInd):
        if(CInd == 2 and m == 0):
            print("Player Wins")
            CompScore = ((CompScore*3)+0)/4
            CompScore2 = ((CompScore2*3)+1)/4
            Pscore += 1
        else:
            print("Comp Wins")
            CompScore = ((CompScore*3)+1)/4
            Cscore += 1
    else:
        CompScore = ((CompScore*3)+0.5)/4
        CompScore2 = ((CompScore2*3)+0.5)/4
        Cscore += 0.5
        Pscore += 0.5
    Agent.Save("IDIOT")
    Agent2.Save("IDIOT2")
    print( "Computer: "+str(Cscore)+"     Also Computer: "+str(Pscore))
    print(cmove)
    print(cmove2)
        