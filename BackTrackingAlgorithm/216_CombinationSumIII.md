# 216. Combination Sum III - Medium

## Problem Statement:  
Find all valid combinations of k numbers that sum up to n such that the following conditions are true:  

Only numbers 1 through 9 are used.  
Each number is used at most once.  
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.  

### Example 1:  

**Input:** k = 3, n = 7  
**Output:** [[1,2,4]]  
**Explanation:**  
1 + 2 + 4 = 7  
There are no other valid combinations.  

### Example 2:  

**Input:** k = 3, n = 9  
**Output:** [[1,2,6],[1,3,5],[2,3,4]]  
**Explanation:**  
1 + 2 + 6 = 9  
1 + 3 + 5 = 9  
2 + 3 + 4 = 9  
There are no other valid combinations.  

### Example 3:  

**Input:** k = 4, n = 1  
**Output:** []  
**Explanation:** There are no valid combinations.  
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.  
 
### Constraints:

- 2 <= k <= 9
- 1 <= n <= 60

## Solution Notes:   
- Similar to problem 77
- Numbers are between 1 and 9, including 1 and 9
- Use recursion to traverse the 1-9 in for loops

## Codes:  
```Java
class Solution {
    List<List<Integer>> res;
    List<Integer> item;
    int sum;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        item = new ArrayList<>();
        sum = 0;
        getCombination(k, n, 1);
        return res;
    }

    public void getCombination(int k, int n, int index){
        if (k == 0 || n == 0) return;
        if (sum > n) return;
        if (item.size() > k) return;
        if (item.size() == k && sum == n) {
            res.add(new ArrayList(item));
            return;
        }

        for (int i = index; i <= 9; i++){
            item.add(i);
            sum += i;
            getCombination(k, n, i+1);
            sum -= i;
            item.remove(item.size() - 1);
        }
    }
}
```Java
