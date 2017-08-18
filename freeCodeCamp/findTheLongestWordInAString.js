/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Return the length of the longest word in the provided sentence.
 * Your response should be a number.
 */


function findLongestWord(str) {
  var words = str.split(" ");
  var k = 0;
  for (var i =0; i < words.length; i++){
    if(words[i].length > k){
      k = words[i].length;
    }
  }
  return k;
}

findLongestWord("The quick brown fox jumped over the lazy dog");
