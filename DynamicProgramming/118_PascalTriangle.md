# 118. Pascal's Triangle - Easy

## Problem Statement:  

Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:



### Example 1:

**Input**: numRows = 5
**Output**: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

### Example 2:

**Input**: numRows = 1
**Output**: [[1]]
 

### Constraints:

- 1 <= numRows <= 30


## Solution Notes:  
- Dynamic Programming

## Codes:
```Java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> dp = new ArrayList<>();
            if (i >= 1) dp.add(1);
            for (int j = 1; i >= 2 && j < i; j++) {
                dp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            dp.add(1);
            res.add(dp);
        }
        return res;
    }
}
```

```Python
class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """

        if numRows == 0:
            return []

        list = [[1]]
        for i in range(1, numRows):
            level = [1]
            for j in range(1, i):
                value = list[i - 1][j] + list[i - 1][j - 1]
                level.append(value)
            level.append(1)
            list.append(level)
            
        return list


            
```

