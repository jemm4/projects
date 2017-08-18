/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Return true if the given string is a palindrome. Otherwise, return false.
 */

function palindrome(str) {
//   var lowRegStr = str.toLowerCase().replace(/[\W_]/g, '');
//   var reverseStr = str.toLowerCase().replace(/[\W_]/g, '').split('').reverse().join(''); 
  return str.toLowerCase().replace(/[\W_]/g, '').split('').reverse().join('') === str.toLowerCase().replace(/[\W_]/g, '');
}


palindrome("eye");
