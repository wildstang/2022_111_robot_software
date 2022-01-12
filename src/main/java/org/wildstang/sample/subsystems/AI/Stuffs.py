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
    
    def Sigmoid(self,v):
        return np.nan_to_num((np.exp(v)/(np.exp(v)+1)))

    def Update(self,inp = None,oup = None,Learn = 0):
        self.Last = self.Vector
        self.Vector = self.Sigmoid(np.dot(self.Waets,self.Vector)) #no biases? W matrix format is (neurons,connections)
        if (inp):
            self.Vector[0:self.Inp] = inp
        if (oup):
            self.Vector[self.Inp,self.Inp+self.Oup] = oup
        if (not (Learn == 0)):
            self.Waets = (self.Waets*self.Gamma) +(Learn*np.outer(self.Vector,self.Last))
        return "BLURGH"
    def Read(self):
        return self.Vector[self.Inp,self.Inp+self.Oup]

class Cortex:
    def __init__(self,n = 10, inp = 100, oup = 5,gamma = 0.99, learn = 0.001,ColumnParameters = [500,10,10,0.99]):
        self.Learn = learn
        self.Waets = (2*np.random.random((n+inp+oup,n+oup)))-1
        self.Inp = inp
        self.Oup = oup
        self.Gamma = gamma
        self.Columns = []
        self.cp = ColumnParameters
        self.Vector = np.zeros((n+oup))#?????? no??? 
        c = 0
        while(c<n):
            self.Columns.append(Column(self.cp[0],self.cp[1],self.cp[2],self.cp[3]))
            c += 1
        print("BLUUURRRGGGH!?")

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
    def Sigmoid(self,v):
        return np.nan_to_num((np.exp(v)/(np.exp(v)+1)))
    def Update(self,inp,oup = None,Reward = 1):
        self.Last = self.Vector
        totalIn = np.concatenate((inp,self.Vector))
        self.Vector = self.Sigmoid(np.dot(self.Waets,totalIn))
        c = 0
        while(c<len(self.Columns)):
            blurgh = self.Columns[c].Update(inp = self.Vector[c*self.cp[1]:(c+1)*self.cp[1]],Learn = Reward*self.Learn)
            self.Vector[c*self.cp[1]:(c+1)*self.cp[1]] = self.Columns[c].Read()
            c += 1
        if(oup):
            self.Vector[c*self.cp[1]:] = oup
        if (not (self.Learn*Reward == 0)):
            self.Waets = (self.Waets*self.Gamma) +(self.Learn*np.outer(self.Vector,self.Last))
        return "SUPERRBLUURRGGH."
    def GetOutput(self):
        return self.Vector[len(self.Columns)*self.cp[1]:]
