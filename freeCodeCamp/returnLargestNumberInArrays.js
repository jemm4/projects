/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Return an array consisting of the largest number from each provided 
 * sub-array. For simplicity, the provided array will contain exactly 4 
 * sub-arrays.
 * Remember, you can iterate through an array with a simple for loop, and access each member with array syntax arr[i].
 */

function largestOfFour(arr) {
  // You can do this!
  // go into the main array
  var largeArr = [];
  for(var i = 0; i < arr.length; i++){
    // go into the sub arrays
    // determine the biggest number and push it into an array
    largeArr.push(Math.max.apply(null, arr[i]));
    
  }
  return largeArr;
}

largestOfFour([[4, 5, 1, 3], [13, 27, 18, 26], [32, 35, 37, 39], [1000, 1001, 857, 1]]);
