/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Convert the characters &, <, >, " (double quote), and ' (apostrophe), in a 
 * string to their corresponding HTML entities.
 */

function convertHTML(str) {
  // &colon;&rpar;
  return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g, '&apos;');
}

convertHTML("Dolce & Gabbana");
