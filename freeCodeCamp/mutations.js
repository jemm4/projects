/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Return true if the string in the first element of the array contains all 
 * of the letters of the string in the second element of the array.
 * For example, ["hello", "Hello"], should return true because all of the 
 * letters in the second string are present in the first, ignoring case.
 * The arguments ["hello", "hey"] should return false because the string 
 * "hello" does not contain a "y".
 * Lastly, ["Alien", "line"], should return true because all of the letters 
 * in "line" are present in "Alien".
 */


function mutation(arr) {
  var arr1 = arr[0].toLowerCase().split('');
  var arr2 = arr[1].toLowerCase().split('');
  var count = 0;
  for (var i =0; i < arr2.length; i++) {
      if(arr1.indexOf(arr2[i]) > -1 ) {
          count++;
        }
  }
  if (count == arr2.length) {
      return true;
  }else  return false;
  
}

mutation(["hello", "hey"]);
