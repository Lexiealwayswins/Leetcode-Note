# 40. Combination Sum II - Medium

## Problem Statement:  
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

### Example 1:  

**Input:** candidates = [10,1,2,7,6,1,5], target = 8  
**Output:** 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
### Example 2:

**Input:** candidates = [2,5,2,1,2], target = 5
**Output:** 
[
[1,2,2],
[5]
]  
 

### Constraints:    

- 1 <= candidates.length <= 100  
- 1 <= candidates[i] <= 50  
- 1 <= target <= 30  

## Solution Notes:   
- don't forget to skip the duplicated elements in results

## Codes:  
```Typescript
function combinationSum2(candidates: number[], target: number): number[][] {
    let res: number[][] = [];
    let path: number[] = [];
    let total: number = 0;
    candidates.sort((a, b) => a - b);

    function backTracking (c: number[], t: number, idx: number): void {
        if (total === t) {
            res.push(path.slice());
            return;
        }
        for (let i = idx; i < c.length && total + c[i] <= t; i++) {
            // skip duplicated number on the same level
            if (i > idx && c[i] == c[i - 1]) continue;
            path.push(c[i]);
            total += c[i];
            backTracking(c, t, i + 1);
            total -= path.pop();
        }
    }

    backTracking(candidates, target, 0);
    return res;
};
```

```Java
class Solution {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        res = new ArrayList<>();
        path = new ArrayList<>();
        getPath(candidates, target, 0, 0);
        return res;
    }

    public void getPath(int[] candidates, int target, int sum, int index){
        if (candidates.length == 0) return;
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            path.add(candidates[i]);
            getPath(candidates, target, sum + candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```Java