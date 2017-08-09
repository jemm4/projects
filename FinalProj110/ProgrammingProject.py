# Jeffrey Martinez CSC110 - 01  Airline Flight Schedule Program
# Dipippo
# 29 April 2015
#-------------------------------------------------------------------------------
# Description of the program:
# This program reads through a large data file with flight information
# for all direct flights from Providence to Orlando. The program
# will offer the user various options for finding the right flight.
#-------------------------------------------------------------------------------
# General solution:
# This program reads through a given text file and then sorts the information
# in that text file and stores them into lists. The program will then ask the user
# to choose an option. Based on that option, the program will execute the option
# Once the program has executed said option then it will print out the result.
#-------------------------------------------------------------------------------
# Pseudocode:
# Make empty lists where the information will be stored
# Opens the text file that the user inputs
# Takes the data and then stores it into the empty lists
# The program then will read through and sort the lists
# The program will print out options that the user can input and then
# the program will run the option depending on what the user inputs
# Prints the results of the functions
#-------------------------------------------------------------------------------
# Design:

#importing sys to be able to quit the program
import sys
from datetime import datetime

# @return the airlines, flightNumbers, departureTimes, arrivalTimes and prices in
# lists 
def getInfo(file):
    # This function will get the file, open it and read it. It will then
    # sort the information and store it into empty lists created beforehand.

    # Empty lists where everything will be held
    airlines = []
    flightNumbers = []
    departureTimes = []
    arrivalTimes = []
    prices = []
    
    infile = open(file, 'r')

    # Reads through the file and sorts the lists accordingly
    line = infile.readline()
    line = line.strip()
    while line != "":
        airline, flightNum, departureTime, arrivalTime, price = line.split(',')
        airlines+=[airline]
        flightNumbers+=[flightNum]
        departureTimes+=[departureTime]
        arrivalTimes+=[arrivalTime]
        prices+=[price]
        line = infile.readline()
        line = line.strip()

    # Gets rid of the $ for the prices
    for i in range(len(prices)):
        prices[i] = prices[i][1:4]
        prices[i] = int(prices[i])
    return airlines, flightNumbers, departureTimes, arrivalTimes, prices

def choice1(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds all the flights on a particular airline

    # Keeps the program running until this option has been fully executed
    run = True
    while run:
        airline = input("Enter the airline to find the flights for: ")
        found = 0
        i = 0

        # Searches through the airlines and finds if at some point in time it matches
        while i < len(airlines) and found == 0:
            if airlines[i] == airline:
                found = 1
            else:
                i+=1

        # If the airline is not found then it loops because run is still true
        if found == 0:
            print("That airline does not exist...")

        # Otherwise if the airline is found, this is printed out along with the appropriate information
        # asked for and run is switched to false
        else:
            print("The flights that meet your criteria are:\n")
            print("Airline\t\tFlight Number\tDeparture Time\tArrival Time\tPrice")
            print("---------------------------------------------------------------------------------------------------")
            for i in range(len(airlines)):
                if airlines[i] == airline:
                    print(airlines[i],"  \t",flightNumbers[i],"\t\t",departureTimes[i],
                          "\t\t",arrivalTimes[i],"\t\t$",prices[i])
            run = False

                     
def choice2(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds the cheapest flight

    # Empty strings to hold the cheapest airline and the cheapest flightNumber
    cheapestA = []
    cheapestF = []

    # Sets lowest equal to the first value in the list of prices
    lowest = prices[0]

    # Loops through the list of prices to look for the lowest price in the list
    for i in range(len(prices)):
        if lowest > prices[i]:
            lowest = prices[i]
            cheapestA = airlines[i]
            cheapestF = flightNumbers[i]
        elif lowest == prices[i]:
            lowest = prices[i]
            cheapestA = airlines[i]
            cheapestF = flightNumbers[i]

    # Prints the cheapest price along with the airline and flight number
    print("The cheapest airline is,", str(cheapestA) + ", with the flight number,", cheapestF, "at $" + str(lowest))

def choice3(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds all flights less than a specified price

    # Keeps the program running until it is switched to false
    run = True
    while run:

        # Prompts the user to input what the threshold is
        threshold = int(input("Enter a max price: $"))
        found = 0
        i = 0

        # Loops through the prices list to look to see if there is something cheaper
        # than what the user inputed
        while i < len(prices) and found == 0:
            if prices[i] < threshold:
                found = 1
            else:
                i+=1

        # If nothing is cheaper than what the user inputed, then it loops back
        if found == 0:
            print("There's nothing cheaper...")

        # Otherwise if there are cheaper prices, it prints it along with the important information
        # and stops this function from running
        else:
            print("The flights that meet your criteria are:\n")
            print("Airline\t\tFlight Number\tDeparture Time\tArrival Time\tPrice")
            print("---------------------------------------------------------------------------------------------------")
            for i in range(len(prices)):
                if prices[i] < threshold:
                    print(airlines[i],"  \t",flightNumbers[i],"\t\t",departureTimes[i],
                          "\t\t",arrivalTimes[i],"\t\t$",prices[i])
            run = False
        
def choice4(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds the shortest flight
    
    form = '%H:%M'

    # Find the first time difference in minutes and temporarily set it as the shortest
    time1 = departureTimes[0]
    time2 = arrivalTimes[0]
    timediff = datetime.strptime(time2, form) - datetime.strptime(time1, form)
    
    # Converts HH:MM format of shortest time to minutes
    timediff = str(timediff)
    timePrt = timediff.split(':')
    mins = int(timePrt[0])*(60)+int(timePrt[1])+int(timePrt[2])
    shortest = mins

    # Goes through each time on list and finds difference in HH:MM format
    for i in range(len(arrivalTimes)):
        time1 = departureTimes[i]
        time2 = arrivalTimes[i]
        timediff = datetime.strptime(time2, form) - datetime.strptime(time1, form)
        
        # Converts HH:MM format of shortest time to minutes
        timediff = str(timediff)
        timePrt = timediff.split(':')
        mins = int(timePrt[0])*(60)+int(timePrt[1])+int(timePrt[2])

        # Replaces the shortest time and saves the index
        while mins < shortest:
            shortest = mins
            index = i
    print('The shortest flight is',airlines[index],flightNumbers[index],'at',shortest,'minutes')

def choice5(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds all flights that depart within a specified range

    form = '%H:%M'

    # Keeps the program from running
    run = True
    while run:
        # Prompts the user to enter the earliest and latest departure time
        # they want to look for
        earliestTime = input("Enter the earliest time: ")
        latestTime = input("Enter the latest time: ")

        found = 0
        i = 0

        # Loop looks throughthe departure times list and finds times within the range
        while i < len(departureTimes) and found == 0:

            # If the departureTime at the time is greater than and less than the latest time
            # then found is turned to 1 to proceed with the program
            if departureTimes[i] > earliestTime and departureTimes[i] < latestTime:
                found = 1
            else:
                i += 1
        # If the time interval is not valid then it prints this and reruns this function
        if found == 0:
            print('No flights exist in that range...')
        # Otherwise if it is valid then it prints out all the appropriate information and
        # stops the function from running
        else:
            print('Here are flights in that time range: ')
            print("Airline  \tFlight Number\tDeparture Time\tArrival Time\tPrice")
            print("---------------------------------------------------------------------------------------------------")
            for i in range(len(departureTimes)):
                if departureTimes[i] >= earliestTime and departureTimes[i] <= latestTime:
                    print(airlines[i],"  \t",flightNumbers[i],"\t\t",departureTimes[i],"\t\t",
                          arrivalTimes[i],"\t\t$",prices[i])
            run = False
                     
def choice6(airlines, flightNumbers, departureTimes, arrivalTimes, prices):
    # Finds the average price for a specified airline

    # Keeps the function running
    run = True
    while run:
        found = 0
        i = 0
        total = 0
        counter = 0
        averagePrice = 0

        # Prompts the user to input an airline they want to find the average price for
        airline = input("Enter an airline: ")

        # Loops through airline list and if the airline is in the airline list
        while i < len(airlines) and found == 0:
            if airlines[i] == airline:
                found = 1
            else:
                i+=1
                
        # If the airline is not found then it prints this out and loops back to the beginning
        if found == 0:
            print("That airline does not exist in this list...")

        # Otherwise if the airline is found then it prints out the appropriate information and
        # stops the function from running
        else:
            for i in range(len(airlines)):
                if airlines[i] == airline:
                    total += prices[i]
                    counter+=1
            averagePrice = round((total/counter),2)
            print("The average price for", airline, "is $" + str(averagePrice))
            run = False  
# Quits the program
def quit():
    sys.exit(1)
    
def main():
    # The main function will call both prior functions and will print the
    # results of the option that was chosen by the user/
    running = True

    # gets the file from the user and then puts the information from the file into lists
    file = input("Enter name of data file: ")
    airlines, flightNumbers, departureTimes, arrivalTimes, prices = getInfo(file)

    print("\nPlease choose one of the following options:\n1 -- Find all flights on a particular airline\n"+
          "2 -- Find the cheapest flight\n3 -- Find all flights less than a specified price\n4 -- Find the shortest flight"+
          "\n5 -- find all flights that depart within a specified range\n6 -- Find the average price for a specified airline"+
          "\n7 -- Quit")

    # Keeps the program running until the user enters option 7 and if the user enters an invalid
    # input then it will reprompt the user to enter a new one after saying that the input is
    # not valid.
    while running:
        optionNum = input("Choice ==> ")
        if optionNum == '1':
            choice1(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '2':
            choice2(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '3':
            choice3(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '4':
            choice4(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '5':
            choice5(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '6':
            choice6(airlines, flightNumbers, departureTimes, arrivalTimes, prices)
        elif optionNum == '7':
            print("Adios!")
            quit()
        else:
            print("No")

# Runs the main function automatically instead of having to type it out in IDLE    
if __name__ == "__main__":
    main()
