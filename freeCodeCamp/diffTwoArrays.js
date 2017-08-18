/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Compare two arrays and return a new array with any items only found in one 
 * of the two given arrays, but not both. In other words, return the symmetric 
 * difference of the two arrays.
 */

function diffArray(arr1, arr2) {
  var newArr = arr1.concat(arr2);

  function check(item) {
    if (arr1.indexOf(item) === -1 || arr2.indexOf(item) === -1) {
      return item;
    }
  }

  return newArr.filter(check);
}
diffArray([1, 2, 3, 5], [1, 2, 3, 4, 5]);
