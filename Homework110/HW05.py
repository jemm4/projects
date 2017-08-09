# HW05 2/22/15
# Jeffrey Martinez

# This function will get the data from the data file
# it opens and reads it line by line. returns the
# list of years and the location list
def getLocation():
    yearList = []
    locationList = []

    fileName = input("Enter the name of the file: ")
    infile = open(fileName, 'r')

    line = infile.readline()
    line = line.strip()


    while line != "":
        year, loc = line.split("\t")
        yearList.append(year)
        locationList.append(loc)
        line = infile.readline()
        line = line.strip()

    return yearList, locationList


# This function takes the year list the loser list
# and the year to search for the location associated
# with that year.
# returns the location or an error when the year
# is not on the list
def findLocation(year, yearList, locationList):
    i = 0
    found = 0

    while i < len(yearList) and found == 0:
        if yearList[i] == year:
            found = 1
        else:
            i += 1
    if found == 1:
        location = locationList[i]
    else:
        return ""
        
    return location

# Uses both functions above and gets the year from the
# user and inputs it into the findLocation functions
def main():
    yearList, locationList = getLocation()

    year = input("Enter the year to search for: ")

    location = findLocation(year, yearList, locationList)

    print("The location for the olympics that year was " + str(location) + ".")
    
    
