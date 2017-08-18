/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Given a positive integer num, return the sum of all odd Fibonacci numbers 
 * that are less than or equal to num.
 * The first two numbers in the Fibonacci sequence are 1 and 1. Every additional 
 * number in the sequence is the sum of the two previous numbers. The first six 
 * numbers of the Fibonacci sequence are 1, 1, 2, 3, 5 and 8.
 * For example, sumFibs(10) should return 10 because all odd Fibonacci numbers 
 * less than 10 are 1, 1, 3, and 5.
 */

function sumFibs(num) {
    var prev = 0;
    var curr = 1;
    var result = 0;
    while (curr <= num) {
        if (curr % 2 !== 0) {
            result += curr;
        }
        var added = curr + prev;
        prev = curr;
        curr = added;
    }

    return result;
}

sumFibs(4);
