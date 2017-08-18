/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Check if a string (first argument, str) ends with the given target 
 * string (second argument, target).
 * This challenge can be solved with the .endsWith() method, which was 
 * introduced in ES2015. But for the purpose of this challenge, we would 
 * like you to use one of the JavaScript substring methods instead.
 */

function confirmEnding(str, target) {
  var ending = str.substr((str.length - target.length), target.length);
  return ending === target;
}

confirmEnding("Bastian", "n");
