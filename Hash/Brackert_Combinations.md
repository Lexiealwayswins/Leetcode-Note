# Bracket Combinations - Hard

## Problem Statement:

Have the function BracketCombinations(num) read num which will be an integer greater than or equal to zero, and return the number of valid combinations that can be formed with num pairs of parentheses. For example, if the input is 3, then the possible combinations of 3 pairs of parenthesis, namely: ()()(), are ()()(), ()(()), (())(), ((())), and (()()). There are 5 total combinations when the input is 3, so your program should return 5.

### Example 1:

**Input**: 3
**Output**: 5

### Example 2:

**Input**: 2
**Output**: 2

## Solution Notes:
- Recursion
- Catalan Number

## Codes:
```Python
def BracketCombinations(num):

  r = {}
  # code goes here
  def catalan(n):
    if n <= 1: return 1
    if n in r: return r[n]

    total = 0
    for i in range(n):
      total += catalan(i) * catalan(n - i - 1)

    r[n] = total
    return total

  return catalan(num)

# keep this function call here 
print(BracketCombinations(input()))
```