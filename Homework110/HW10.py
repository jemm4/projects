# Jeffrey Martinez
# 4/20
# Bioinformatics HW 1010(10)
# Program to find all possible alignments of a pair of strings when adding
# gaps to the shorter string

# Given:  Two strings of nucleotides
# Find:  All possible alignments when adding gaps to the shorter string


# Function to create a list of all ways one gap can be inserted into
# a string.  The input is a string, the output is a list of strings
# with a gap inserted into all positions of the input string

def insertOneGap(strng, work):
    alignments = []
    for i in range(len(strng)):
        newStrng = strng[0:i] + '-' + strng[i:len(strng)]
        alignments = alignments + [newStrng]
        work +=1
    alignments = alignments + [strng + '-']
    return alignments,work
            
# Function to take a set union of a pair of lists
# This is used to eliminate any duplicates in the list when they are combined
def Union(list1, list2):
    for a in list2:
        if a not in list1:
            list1 = list1 + [a]
    return list1

# Function to create all possible alignments of a string with a certain number
# of gaps inserted
def insertAllGaps(strng, gaps, work):
    # List of alignments starts with the initial string
    alignments = [strng]
    # Loop to insert one gap at a time
    for i in range(gaps):
        # Initialize list of new alignments with i gaps in the string
        newAlignments = []

        # For every string in the list of alignments
        for st in alignments:
            # Insert one gap in each alignment in the list
            al, work = insertOneGap(st, work)
            work += 1
            # Add the new alignment to the list of new alignments being created
            newAlignments = Union(newAlignments,al)

        # The alignments list now becomes the new alignments list to now
        # add another gap to each of the alignments in the new list
        alignments = newAlignments
    return alignments,work

# Function to compute the scores for all alignments compared to another string
# Gap score = -1; Match score = 1; Mismatch score = 0
def computeScores(st, alignments):
    scores = []
    for al in alignments:
        score = 0
        for i in range(len(st)):
            if al[i] == "-":
                score = score - 1
            elif al[i] == st[i]:
                score = score + 1
            else:
                score = score + 0
        scores = scores + [score]
    return scores


# Function to print all of the alignments along with the score for
# each alignment

def printResults(st, alignments, scores):
    print("The optimal alignments:")
    maxscores = max(scores)
    for i in range(len(alignments)):
        if scores[i] == maxscores:
            print("This is an optimal alignment")
            print(st)
            print(alignments[i])
            print("Score = ", scores[i])
            print(" ")
        
# Main function
def main():
    # Get the two strings to align
    str1 = input("Enter string 1: ")
    str2 = input("Enter string 2: ")
    # Compute alignments adding gaps to the shorter string
    if len(str1) > len(str2):
        longStr = str1
        shortStr = str2
    else:
        longStr = str2
        shortStr = str1
    work = 0
    alignments, work = insertAllGaps(shortStr,len(longStr)-len(shortStr), work)
    scores = computeScores(longStr, alignments)
    printResults(longStr,alignments, scores)
    print("The amount of work is: ",work)


# Part B - The algorithm does 675 units of work. There were numerous counters placed throughout
# the code in order to count the amount of work done. The algorithm then did the counting
# by itself by counting every alignment and counting the comparisons that took place in the
# program and it just printed the amount of work done in the main function.
