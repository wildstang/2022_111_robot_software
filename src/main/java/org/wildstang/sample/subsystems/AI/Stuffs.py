import numpy as np

#ONLY for continous control. Timestep stuff kinda broken (ton of 1-tick delays)
class Column:
    def __init__(self,n=500,inp = 10,oup = 10,gamma = 0.99):
        self.Inp = inp
        self.Oup = oup
        self.Gamma = gamma
        self.Waets = (2*np.random.random((n,n)))-1
        self.Vector = np.zeros((n))
        self.Last = self.Vector

    def Save(self,file):
        np.save(self.Waets,file+"W.npy")
        np.save(self.Vector,file+"V.npy")

    def Load(self,file):
        self.Waets = np.load(file+"W.npy")
        self.Vector = np.load(file+"V.npy")
    
    def Sigmoid(self,v): #sigmoid function
        return np.nan_to_num((np.exp(v)/(np.exp(v)+1)))

    def Update(self,inp = None,oup = None,Learn = 0): #do stuff
        self.Last = self.Vector
        self.Vector = self.Sigmoid(np.dot(self.Waets,self.Vector)) #no biases? W matrix format is (neurons,connections)
        if (inp):
            self.Vector[0:self.Inp] = inp
        if (oup):
            self.Vector[self.Inp,self.Inp+self.Oup] = oup
        if (not (Learn == 0)):
            self.Waets = (self.Waets*self.Gamma) +(Learn*np.outer(self.Vector,self.Last))
        return "BLURGH"
    def Read(self): #get output
        return self.Vector[self.Inp,self.Inp+self.Oup]

class Cortex:
    def __init__(self,n = 10, inp = 100, oup = 5, extra = 10,gamma = 0.99, learn = 0.001, epsilon = 0.01, ColumnParameters = [500,10,10,0.99]):
        self.cp = ColumnParameters 
        self.Learn = learn
        self.Waets = (2*np.random.random(((n*cp[1])+inp+oup+extra,(n*cp[1])+oup+inp+extra)))-1 #now square
        self.Inp = inp
        self.Oup = oup
        self.Gamma = gamma
        self.Columns = []
        self.Vector = np.zeros((n+oup))#?????? no??? 
        self.Extra = extra
        self.Epsilon = epsilon
        #self.ListOfVectors = [] could use too much memory, so implementing in TimeSeriesPredictor instead.
        c = 0
        while(c<n):
            self.Columns.append(Column(self.cp[0],self.cp[1],self.cp[2],self.cp[3]))
            c += 1
        print("BLUUURRRGGGH!?")
    
    #save/load
    def Save(self,file):
        c = 0
        while(c<len(self.Columns)):
            self.Columns[c].Save(file+"C"+str(c))
            c += 1
    def Load(self,file):
        c = 0
        while(c<len(self.Columns)):
            self.Columns[c].Load(file+"C"+str(c))
            c += 1
      
    
    #main functions
    def Sigmoid(self,v):
        return np.nan_to_num((np.exp(v)/(np.exp(v)+1)))
    def Update(self,inp = None,oup = None,Reward = 1):
        self.Last = self.Vector
        if (not inp == None):
            self.Vector[len(self.Columns)*self.cp[1]:len(self.Columns)*self.cp[1]+self.Inp] = inp
        self.Vector = self.Sigmoid(np.dot(self.Waets,self.Vector))
        c = 0
        while(c<len(self.Columns)):
            blurgh = self.Columns[c].Update(inp = self.Vector[c*self.cp[1]:(c+1)*self.cp[1]],Learn = Reward*self.Learn)
            self.Vector[c*self.cp[1]:(c+1)*self.cp[1]] = self.Columns[c].Read()
            c += 1
        
        if(self.Epsilon >0):
            self.Vector += ((2*np.random.random(len(self.Vector)))-1)*self.Epsilon
        if(not oup == None):
            self.Vector[c*self.cp[1]+self.Inp+self.Extra:] = oup
        if (not (self.Learn == 0)):
            self.Waets = (self.Waets*self.Gamma) +(self.Learn*np.outer(self.Vector,self.Last))
        return "SUPERRBLUURRGGH."
    def GetOutput(self):
        return self.Vector[len(self.Columns)*self.cp[1]+self.Inp+self.Extra:]
   

    #for diagnostics
    def GetColumnActivities(self):
        Avgs = []
        c = 0
        while(c<len(self.Columns)):
            Avgs.append(np.sum(np.abs(self.Columns[c].Vector))/len(self.Columns[c].Vector)
            c += 1
        return Avgs
    def GetNeuron(self,c,n):
        return self.Columns[c].Vector[n]
                        
