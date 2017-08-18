/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Return the remaining elements of an array after chopping off n 
 * elements from the head.
 * The head means the beginning of the array, or the zeroth index.
 */

function slasher(arr, howMany) {
  // it doesn't always pay to be first
  for(var i = 0; i < howMany; i++){
    arr.shift();
  }
    
  return arr;
}

slasher([1, 2, 3], 2);
