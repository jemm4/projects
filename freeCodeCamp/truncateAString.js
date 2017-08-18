/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Truncate a string (first argument) if it is longer than the given 
 * maximum string length (second argument). Return the truncated 
 * string with a ... ending.
 * Note that inserting the three dots to the end will add to the string length.
 * However, if the given maximum string length num is less than or equal to 3, 
 * then the addition of the three dots does not add to the string length in 
 * determining the truncated string.
 */

function truncateString(str, num) {
  // Clear out that junk in your trunk
  var sliced = "";
  var dots = "...";
  if(str.length > num){
    if(num > 3){
      return str.slice(0,num - 3) + dots;
    }
    else{
      return str.slice(0,num) + dots;
    }
  }else{
    return str;
  }
}

truncateString("A-tisket a-tasket A green and yellow basket", 11);
