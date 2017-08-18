/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Convert the given number into a roman numeral.
 * All roman numerals answers should be provided in upper-case.
 */

var convertToRoman = function(num) {
  
  // things for lookup
  var decimalValue = [ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 ];
  var romanNumeral = [ 'M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I' ];

  var romanized = ''; // for the value returned

  for (var index = 0; index < decimalValue.length; index++) { // loop through available decimal values
    while (decimalValue[index] <= num) { // while the decimal value is less than the number given
      romanized += romanNumeral[index]; // value returned is equal to the romanized string plus the new roman numeral at the same index of the decimal value
      num -= decimalValue[index]; // subtract the decimalValue to the input number 
    }
  }

  return romanized;
};

// test here
convertToRoman(36);
