# Implement a Range-Based LCM Calculator

## Problem Statement:

In this lab, you will create a function that takes an array of two numbers and returns the least common multiple (LCM) of those two numbers and all the numbers between them.

Objective: Fulfill the user stories below and get all the tests to pass to complete the lab.

User Stories

- You should have a smallestCommons function that accepts an array of two numbers as an argument.
- The smallestCommons function should return the smallest common multiple that is evenly divisible by both numbers and all sequential numbers in the range between them.
- The function should handle input where the two numbers are not in numerical order.

### Example:

- smallestCommons([1, 5]) should return a number.
- smallestCommons([1, 5]) should return 60.
- smallestCommons([5, 1]) should return 60.
- smallestCommons([2, 10]) should return 2520.
- smallestCommons([1, 13]) should return 360360.
- smallestCommons([23, 18]) should return 6056820.

## Solution Notes:
- using GCD and LCM

## Codes:
- JavaScript
```JavaScript
function smallestCommons (arr) {
  let [l, r] = arr.sort((a, b) => a - b);

  let m = l;
  for (let i = l + 1; i <= r; i++) {
    m = lcm(m, i);
  }
  return m;
}

function gcd (a, b) {
  while (b !== 0) {
    [a, b] = [b, a % b];
  }
  return a;
}

function lcm (a, b) {
  return a * b / gcd(a, b);
}

```
