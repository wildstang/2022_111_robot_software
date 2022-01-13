import numpy as np
import Stuffs as ai
import matplotlib.pyplot as plt
class Predictor:
  #only for discrete-time variables. For continous variables this is a major waste of computing power.
  #models a set of input and output channels. Too many hyperparameters. Includes diagnostics/visualization. 
  #oh, input modeling is passive, so probably won't work too well.
  
  def __init__(self,timesteps = 10, n = 10, inp = 100, oup = 5, extra = 10, gamma = 0.99, learn = 0.001, epsilon = 0.01,ColumnParameters = [100,10,10,0.99]):
    self.Agent = ai.Cortex(n=n,inp=inp,oup=oup+1,extra=extra,gamma=gamma,learn=learn,epsilon = epsilon,ColumnParameters=ColumnParameters)
    self.Timesteps = timestep
    self.F = 3
    self.Re = 0
    self.Lre = 0
    
  def Save(self,file):
    self.Agent.Save(file)
    
  def Load(self,file):
    self.Agent.Load(file)
    
  def Update(self,inp = None,oup = None,epsilon = 0.01,learn = 0.01, Reward = None):
    self.Agent.Epsilon = epsilon #update epsilon value
    self.Agent.Learn = learn #and learn value
    c = 0
    while(c<self.Timesteps): #run for some timesteps, applying input at first and output at last
      if(c == 0):
        if(Reward == None):
          if(self.Re>self.Lre):
            superblurgh = self.Agent.Update(inp = inp, Reward = self.Re-self.Lre)
          else:
            superblurgh = self.Agent.Update(inp = inp, Reward = 0)
            
        else:
          superblurgh = self.Agent.Update(inp = inp,Reward = Reward)
        
        
      else:
        if(Reward == None):
          if(self.Re>self.Lre):
            superblurgh = self.Agent.Update(Reward = self.Re-self.Lre)
          else:
            superblurgh = self.Agent.Update(Reward = 0)
            
        else:
            superblurgh = self.Agent.Update(Reward = Reward)
            
            
      if(not oup == None):
        self.Lre = 0
        per = (np.sum(np.abs(oup - Agent.GetOutput())))/len(oup)
        self.Re = ((self.Re*self.F)+(1-per))/(1+self.F)
      else:
        self.Lre = 0
        self.Re = 0
      c += 1
    if(not oup == None):
      superblurgh = self.Agent.Update(oup = oup, Reward = 1)
  
  
  def InitDiagnostics(self):
    blank = []
    c = 0
    while(c<100):
      blank.append(0)
      c += 1
    self.ColumnData = []
    c = 0
    while(c<len(self.Agent.Columns)):
      self.ColumnData.append([blank,blank,blank])
      c += 1
      
    self.PC1 = None
    self.PC2 = None
    self.PC3 = None
    self.Cactivities = []
    c = 0
    while(c<len(self.Agent.Columns)):
      self.Cactivities.append(blank)
      c += 1
    plt.ion()
    self.figs,self.axs = plt.subplots(len(self.Agent.Columns)+1,1)
    self.Range = np.arange(0,100)
  def UpdateDiagnostics(self):
    avgs = Agent.GetColumnActivities()
    c = 0
    while(c<len(self.Agent.Columns)):
      self.ColumnData[c][0].append(Agent.GetNeuron(c,0))
      self.ColumnData[c][1].append(Agent.GetNeuron(c,1))
      self.ColumnData[c][2].append(Agent.GetNeuron(c,2))
      
      self.ColumnData[c][0].pop(0)
      self.ColumnData[c][1].pop(1)
      self.ColumnData[c][2].pop(2)
      
      self.Cactivities.append(avgs[c])
      c += 1
  def PlotDiagnostics(self):
    self.axs[c].plot(self.Cactivities) #may need debugging.
    c = 1
    while(c<len(self.axs[c])):
      self.axs[c].plot(self.ColumnData[c])
      c += 1
    plt.show()
    
