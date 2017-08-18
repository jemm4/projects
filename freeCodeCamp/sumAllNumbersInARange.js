/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * We'll pass you an array of two numbers. Return the sum of those two numbers 
 * and all numbers between them.
 * The lowest number will not always come first.
 */

function sumAll(arr) {
  var sum = [];
  if(arr[0] < arr[1]){
    for(var i = arr[0]; i <= arr[1]; i++){
      sum.push(i);
    }
  }else{
    for(var j = arr[1]; j <= arr[0]; j++){
      sum.push(j);
    }
  }
  return sum.reduce(function(a,b){return a+b;},0);
}

sumAll([1, 4]);
