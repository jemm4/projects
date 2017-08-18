/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Sum all the prime numbers up to and including the provided number.
 * A prime number is defined as a number greater than one and having only two 
 * divisors, one and itself. For example, 2 is a prime number because it's only 
 * divisible by one and two.
 * The provided number may not be a prime.
 */

function sumPrimes(num) {
  var res = 0;

  // get the primes up to max in an array
  function getPrimes(max) {
    var hold = [];
    var i;
    var j;
    var primes = [];
    for (i = 2; i <= max; ++i) {
      if (!hold[i]) {
        // i has not been marked -- it is prime
        primes.push(i);
        for (j = i << 1; j <= max; j += i) {
          hold[j] = true;
        }
      }
    }

    return primes;
  }

  // Add the primes
  var primes = getPrimes(num);
  for (var p = 0; p < primes.length; p++) {
    res += primes[p];
  }

  return res;
}

sumPrimes(10);
