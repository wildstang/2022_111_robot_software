import numpy as np
import Stuffs as ai

class ChessAgent:
    def __init__(self,thinkingTime = 10):
        self.Time = thinkingTime
        self.Agent = ai.Cortex(n = 10, inp = 64*6, oup = 64*6,gamma = 0.99, learn = 0.01,ColumnParameters = [500,10,10,0.99])
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