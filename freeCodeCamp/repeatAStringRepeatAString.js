/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Repeat a given string (first argument) num times (second argument). 
 * Return an empty string if num is not a positive number.
 */

function repeatStringNumTimes(str, num) {
  // repeat after me
  var strBNum = "";
  if(num < 0){
    return "";  
  }
  else{
    for(var i = 0; i < num; i++){
      strBNum = strBNum + str;
    }
    return strBNum;
  }
}

repeatStringNumTimes("abc", 3);
