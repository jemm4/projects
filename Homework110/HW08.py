# Jeffrey Martinez
# Lisa Dipippo
# CSC 110
# Function to convert a binary string to a decimal number

def bin_to_dec(binary):
    high_power = len(binary) - 1            # The highest power of 2 will be the length - 1
    i = high_power                          # Initialize counter at highest power to count down
    decimal = 0                             # Initialize accumulator to compute decimal number
    while i >= 0:                           # Loop while i is greater than 0
        if binary[i] == '1':                # If the current character is a 1
            power = high_power - i          # Compute the power of 2 to add
            decimal = decimal + 2**power    # Add the power of two to the decimal accumulator
        i = i - 1                           # Decrement the counter
    return decimal                          # Return the decimal accumulator

# Function to look up the ASCII representation of a capital letter 
def ascii_lookup(ch):
    if ch == 'A':
        return "01000001"
    elif ch == 'B':
        return "01000010"
    elif ch == 'C':
        return "01000011"
    elif ch == 'D':
        return "01000100"
    elif ch == 'E':
        return "01000101"
    elif ch == 'F':
        return "01000110"
    elif ch == 'G':
        return "01000111"
    elif ch == 'H':
        return "01001000"
    elif ch == 'I':
        return "01001001"
    elif ch == 'J':
        return "01001010"
    elif ch == 'K':
        return "01001011"
    elif ch == 'L':
        return "01001100"
    elif ch == 'M':
        return "01001101"
    elif ch == 'N':
        return "01001110"
    elif ch == 'O':
        return "01001111"
    elif ch == 'P':
        return "01010000"
    elif ch == 'Q':
        return "01010001"
    elif ch == 'R':
        return "01010010"
    elif ch == 'S':
        return "01010011"
    elif ch == 'T':
        return "01010100"
    elif ch == 'U':
        return "01010101"
    elif ch == 'V':
        return "01010110"
    elif ch == 'W':
        return "01010111"
    elif ch == 'X':
        return "01011000"
    elif ch == 'Y':
        return "01011001"
    elif ch == 'Z':
        return "01011010"
    elif ch == ' ':
        return "00100000"
        

# Function to compute the 8-bit checksum for a string
def checksum8(strng):
    # Initialize counter
    i = 0

    # Initialize checksum accumulator
    csum = 0

    # Loop while characters left in the string
    while (i < len(strng)):
        
        # Look up the ascii representation of the ith character in the string
        binry = ascii_lookup(strng[i])

        # Convert the 8-bit binary number representing the character to decimal
        num = bin_to_dec(binry)
        
        # Add the computed number to the checksum accumulator
        csum = csum + num

        # Increment counter
        i = i + 1

    # Mod the checksum by 2**8 to fit into 256 bits    
    checksum = csum % 2**8

    # Return the checksum
    return checksum


# Function to compute the 16-bit checksum for a string
def checksum16(strng):

    # Initialize counter
    i = 0

    # Initialize checksum accumulator
    csum = 0

    # Loop while characters left in string
    while i < len(strng):
        # Look up first character in pair
        bin1 = ascii_lookup(strng[i])
 
        # If not at the end of the string, look up second character in the pair
        # Otherwise, look up a blank to fill out the pair
        if i+1 < len(strng):
            bin2 = ascii_lookup(strng[i+1])
        else:
            bin2 = ascii_lookup(' ')

        # Put the two 8-bit strings together to make one 16-bit string
        binary = bin1 + bin2

        # Convert the 16-bit binary number to a decimal number
        num = bin_to_dec(binary)

        # Reassigning num to num times the counter and then multiply it by
        # the counter plus one in case the counter is 0
        num = num * i * (i + 1)  

        # Add the computed number to the checksum accumulator
        csum = csum + num

        # Increment the counter by 2 since we put two characters together
        i = i + 2

    # Mod the checksum by 2**16 to fit into 2**16 bits    
    checksum = csum % 2**16

    # Return the checksum
    return checksum



# Main Function
def main():
    chString = input("Enter the text for which you would like the checksum computed: ")

    chBits = int(input("Would you like to compute an 8-bit or a 16-bit checksum (type 8 or 16)? "))

    if chBits == 8:
        print("Checksum = ",checksum8(chString))
    elif chBits == 16:
        print("Checksum = ",checksum16(chString))
    else:
        print("No such algorithm is available")
        
