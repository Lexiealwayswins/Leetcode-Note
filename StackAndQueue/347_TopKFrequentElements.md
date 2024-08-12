# 347. Top K Frequent Elements - Medium

## Problem Statement:

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

### Example 1:  

**Input:** nums = [1,1,1,2,2,3], k = 2  
**Output:** [1,2]   

### Example 2:  

**Input:** nums = [1], k = 1  
**Output:** [1]  
 

### Constraints:  

- 1 <= nums.length <= 105
- -104 <= nums[i] <= 104
- k is in the range [1, the number of unique elements in the array].
- It is guaranteed that the answer is unique.
 

### Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

## Solution Notes:
- use a map to store the num and the frequency of this num
- use PriorityQuene to rank the frequency

## Codes:

```Java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }
}
···Java