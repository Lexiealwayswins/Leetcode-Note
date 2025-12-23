# Build a Prime Number Sum Calculator

## Problem Statement:
In this lab, you will build a calculator that takes a number and returns the sum of all prime numbers that are less than or equal to that number.

Objective: Fulfill the user stories below and get all the tests to pass to complete the lab.

User Stories:

- You should have a sumPrimes function that accepts a number as an argument.
- The sumPrimes function should return the sum of all prime numbers less than or equal to the provided number.
- If the input number is less than 2, the function should return 0.

### Example:

- sumPrimes(10) should return 17.
- sumPrimes(5) should return 10.
- sumPrimes(2) should return 2.
- sumPrimes(0) should return 0.
- sumPrimes(977) should return 73156.

## Solution Notes:
- Sieve of Eratosthenes O(n log log n)

## Codes:
- JavaScript
```JavaScript
function sumPrimes(n) {
  if (n < 2) return 0;

  const isPrime = Array(n + 1).fill(true);
  isPrime[0] = isPrime[1] = false;

  for (let i = 2; i * i <= n; i++) {
    if (isPrime[i]) {
      for (let j = i * i; j <= n; j += i) {
        isPrime[j] = false;
      }
    }
  }

  let sum = 0;
  for (let i = 2; i <= n; i++) {
    if (isPrime[i]) sum += i;
  }

  return sum;
}

```
