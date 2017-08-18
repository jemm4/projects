/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Write a function that splits an array (first argument) into groups the 
 * length of size (second argument) and returns them as a two-dimensional array.
 */

function chunkArrayInGroups(arr, size) {
  // Break it up.
  var pushed = [];
  for(var i = 0; i < arr.length; i = i + size){
    pushed.push(arr.slice(i, i + size));
  }
  return pushed;
}

chunkArrayInGroups(["a", "b", "c", "d"], 2);
