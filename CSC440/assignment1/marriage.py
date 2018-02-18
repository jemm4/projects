# Jeffrey Martinez
# Assignment 1 CSC 440

# imports
import sys, copy, os
#from timeit import default_timer as timer
#start=timer()

#path = 'C:\\Users\\jemm4\\Documents\\School\\CSC440\\assignment1\\marriage-inputs-small\\marriage-inputs-small\\ten.txt'
#path = sys.argv[1]
# We want to go into the file and seperate the lists of males and females and put them in a dictionary along with their preferences

'''
Invariance for getDict
Initialization: Our first two dictionaries, menDict and womenDict are empty therefore they are already sorted. The number of
        couples that can be made is already predetermined. In order for there to be an equal number of couples,
        there must be an equal number of knights and ladies.
Maintenance: 
Termination: menDict, womenDict, knights, and ladies will be filled with keys and values so that they can make a decision on
        who to get engaged to. 
'''
def getDict(file):
        menDict = {}
        womenDict = {}
        f = open(file, "r")
        # gets our halfway point between the men and the women
        '''
        Loop invariant: 
        '''
        split = (sum(1 for line in f) // 2) + 1 
        # Close the file back up because we have to start reading it from the beginning again
        f.close()
        f = open(file,"r")
        numPeople = f.readline()
        numPeople = numPeople.strip()
        # if the first line in the file is not a number then we exit
        if(not numPeople.isdigit()):
                exit(1)
        numPeople = int(numPeople)
        '''
        Loop invariant for i: 1 <= i <= split
        Loop invariant for j: split <= j <= split+numPeople
        '''
        # here is where we make the dictionaries filled with the knights and ladies and their respective preferences
        for i in range(1, split):
                man = f.readline()
                man = man.split()
                menDict[man[0]] = man[1:]
        for j in range(split, split+int(numPeople)):
                woman = f.readline()
                woman = woman.split()
                womenDict[woman[0]] = woman[1:]
        f.close()
        # All of our knights and ladies sitting in two tables. One for knights and one for ladies
        knights = sorted(menDict.keys())
        ladies = sorted(womenDict.keys())
        return menDict, womenDict, knights, ladies
##        for i in menDict: #prints dictionaries of the men and their preferences
##                print(i, menDict[i]) 
##        for i in womenDict:
##                print(i, womenDict[i])
# this is where the gale shapley alorithm is executed aka the merlin magic. We want to start off with the full list of nights and then
# set them equal to free. we also want the ladies to be free. from there we check to see if the lady is free, and if she is
# then we can go ahead and pair the knight with the lady. If at some point, the lady finds a prefered suitor,
# then she will leave the current fiance and get engaged with the new knight.
'''
Invariance for mermlinMagic
Initialization: Our first initialization of out engaged dictionary is empty therefore it is sorted. The first knight and lady will start off being engaged
Maintenance: If a lady ranks her fiance lower than the knight currently proposing on her preference list then she will go with the new knight.
        
Termination: The preference levels in each couple between each knight and lady is greater than or equal to
        the preference level between any other combination of knight and lady.
'''
def merlinMagic(menDict, womenDict, knights, ladies):
        knightsSingle = knights[:] #set all of the knights to single
        engaged = {}
        menTDict = copy.copy(menDict) # make a copy of preference dictionaries because want to be able to edit them
        womenTDict = copy.copy(womenDict)
        '''
        Loop invariant: knightsSingle[n] >= 0 
        '''
        while knightsSingle: 
                knight = knightsSingle.pop(0)
                allKnights = menTDict[knight]
                lady = allKnights.pop(0)
                fiance = engaged.get(lady)
                # if lady is single then get engaged to the night
                # In other words, we assign the key lady the current night
                if not fiance:
                        engaged[lady] = knight
                else:
                        womenDict = womenTDict[lady]
                        # if the lady likes the knight currently being evaluated more than her fiance
                        # then we want to get engaged to the knight and drop her current fiance
                        # We also want to free our exfiance so we append him back to the single knights
                        if womenDict.index(fiance) > womenDict.index(knight):
                                engaged[lady] = knight
                                if menTDict[fiance]:
                                        knightsSingle.append(fiance)
                        #if the lady likes her fiance more, then we stay with him and put our knight back
                        else:
                                if allKnights:
                                        knightsSingle.append(knight)
        return engaged
'''
Invariance for main
Initialization: argv 0 is the name of the file
Maintenance: Will always make sure there are 2 arguments and that the input file exists.
Termination: Engaged[lady] will have a value, knight to represent who each couple is
'''
def main():
        # test if we only have 2 arguments
        if(len(sys.argv) != 2):
                exit(1)
        inputFile = sys.argv[1]
        # test if the file actually exists
        if(os.path.isfile(inputFile)):
                pass
        else:
                exit(1)
        menDict, womenDict, knights, ladies = getDict(inputFile)
        engaged = merlinMagic(menDict, womenDict, knights, ladies)
        # format is:
        # knightName ladyName
        counter = 0
        '''
        Loop invariant: Each lady object <= last lady in engaged
        '''
        for lady in engaged:
                counter += 1
                if(counter < len(engaged)):
                        sys.stdout.write(engaged[lady] + " " + lady + "\n")
                else:
                        sys.stdout.write(engaged[lady] + " " + lady)
        pass
        
if __name__ == "__main__":
        main()
        

#end = timer()
#print("\nTime taken:", end-start)
# mean runtime of ten.txt: 0.004948245631948841
# mean runtime of hundred.txt: 0.018294906541647095
# mean runtime of thousand.txt: 0.964330256467108
# mean runtime of fivethousand.txt: 19.650486984097352
