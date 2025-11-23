# 77. Combinations - Medium

## Problem Statement: 
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.

 
### Example 1:  

**Input:** n = 4, k = 2  
**Output:** [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]  
**Explanation:** There are 4 choose 2 = 6 total combinations.  
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.  

### Example 2:  

**Input:** n = 1, k = 1  
**Output:** [[1]]  
**Explanation:** There is 1 choose 1 = 1 total combination.  
 

### Constraints:  

- 1 <= n <= 20  
- 1 <= k <= n  

## Solution Notes:   
- Use recursion to traverse the 1-n in for loops
- Can optimize the loop through trim the length of the loop, because we only need to traverse for the rest elements in k

## Codes:  
```Java
class Solution {
    List<List<Integer>> res;
    ArrayList<Integer> path;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        getPath(n, k, 1);
        return res;
    }

    public void getPath(int n, int k, int startIndex){
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            getPath(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```Java