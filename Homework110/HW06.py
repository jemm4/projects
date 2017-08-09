# Finding out the olympic locations for user inputted years using Binary Search
# Jeffrey Martinez
# CSC 110
# 3/6/2015

def getData():
    # Finds the file, opens it, splits it and reads it
    # then assigns the values to the lists
    fname = input("Enter the name of the data file: ")
    infile = open(fname,'r')
    yearList = []
    locList = []
    line = infile.readline()
    while line != "": 
        line = line.strip()
        year, loc = line.split("\t")
        yearList = yearList + [year]
        locList = locList + [loc]
        line = infile.readline()
    infile.close()
    return yearList, locList

def findLocationBinary(yearList, locList, year):
    left = 0
    right = len(yearList) -1
    found = 0
    counter = 0

    # uses binary search to look for the location
    while right >= left and found == 0:
        m = (left+right) // 2
        if yearList[m] == year:
            found = 1
            counter +=1
        else:
            if year < yearList[m]:
                right = m-1
                counter +=1
            else:
                left = m + 1
                counter +=1

    # if the location is found return an empty list
    if found == 0:
         location = ""
    # if the location is found then the location is assigned to locList of m
    else:
         location = locList[m]

    return location, counter
# Function to find the location of the olympics for a particular year

def findLocation(yearList, locList, year):
    found = 0
    i = 0
    counterL = 0
    while found == 0 and i < len(locList):
        if year == yearList[i]:
            found = 1
            counterL +=1
        else:
            i = i + 1
            counterL +=1
    if found == 0:
        location = ""
    else:
        location = locList[i]
    return counterL

def main():

    yearList, locList = getData()
    # asks the user for the year they want to look for 
    year = input("Enter the year you are interested in: ")
    location, counter = findLocationBinary(yearList, locList, year)
    counterL = findLocation(yearList, locList, year)

    if location == "":
        print("No Olympics were held that year")
    else:
        print("In",year,"the Olympics were held in",location)
    print("The work done in the binary search function is:", counter)
    print("The work done in the linear search function is:", counterL)

main()
#-------------------------------------------------------------------------------
# EXPLANATION: 
# The work for the binary search function does not go over 5 while the
# work done for the linear search reaches 31. The linear search performs
# better if the year one is looking for is close to the beginning, like the
# year 1896. Once the year hits 1908, the binary search and the linear search
# does the same amount of work.
#-------------------------------------------------------------------------------
