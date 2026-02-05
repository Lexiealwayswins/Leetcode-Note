# 202. Happy Number - Easy

## Problem Statement:

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.

Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

### Example 1:

**Input:** n = 19
**Output:** true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

### Example 2:

**Input:** n = 2
**Output:** false
 
### Constraints:

- 1 <= n <= 231 - 1

## Solution Notes:
- Using hashsets to do de-duplication
- If sum appears repeatedly, it means the loop is infinite and cannot become 1, so we should immediately return false

## Codes:
- Python
```Python
# Solution 1:
class Solution:
    def isHappy(self, n: int) -> bool:
        s = set()
        while True:
            if n == 1: return True
            if n in s: return False
            s.add(n)
            n = self.getSum(n)

    def getSum(self, n: int) -> int:
        sum = 0
        while n:
            # divmod(a, b) == (a // b, a % b)
            n, r = divmod(n, 10)
            sum += r ** 2
        return sum

# Solution 2:
class Solution:
   def isHappy(self, n: int) -> bool:
       record = set()
       while n not in record:
           record.add(n)
           new_num = 0
           n_str = str(n)
           for i in n_str:
               new_num += int(i)**2
           if new_num==1: return True
           else: n = new_num
       return False
```

- JavaScript
```JavaScript
/**
 * @param {number} n
 * @return {boolean}
 */
var isHappy = function(n) {
    let map = new Map();

    function getSum(n) {
        let sum = 0;
        while (n) {
            sum += (n % 10) ** 2;
            n = Math.floor(n / 10)
        }
        return sum;
    }

    while (true) {
        if (n === 1) return true;
        if (map.has(n)) return false;
        map.set(n, 1);
        n = getSum(n);
    }
};
```

- Java
```Java
class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();

        while(n != 1 && !set.contains(n)){
            set.add(n);
            n = getN(n);
        }
        
        return n == 1;
    }

    private int getN(int n) {
        int num = 0;
        while(n > 0) {
            num += (n % 10) * (n % 10);
            n /= 10;
        }
        return num;
    }
}
```