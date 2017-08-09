# Jeffrey Martinez CSC110 - 01  Airline Flight Schedule Design
# Dipippo
# 8 April 2015
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

def getInfo(file):
    # This function will get the file, open it and read it. It will then
    # sort the information and store it into empty lists created beforehand.

    return airlines, flightNumbers, departureTimes, arrivalTimes, prices

def choices(optionNum):
    # This function will contain most of the code. The
    # options are printed into the module and then the user inputs their
    # option number which will be taken in as a parameter
    # With an if-statement, the option will be executed

    return optionRes

def main():
    # The main function will call both prior functions and will print the
    # results of the option that was chosen by the user/

    print(optionResults)
    
