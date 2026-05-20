# 215. Kth Largest Element in an Array - Medium

## Problem Statement:

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

 
### Example 1:  

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

### Example 2:  

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 

### Constraints:  

- 1 <= k <= nums.length <= 105
- -104 <= nums[i] <= 104


## Solution Notes:
- Min Heap:
    - use a min heap to get k numbers
    - if there are bigger numbers after k, then replace the top and maintain the k min heap
    - min heap only need to maintain k elements (only compare the top)
    - while max heap need to maintain n elements (need to compare all rest elements)


## Codes:
```TypeScript
function findKthLargest(nums: number[], k: number): number {
    const minheap: number[] = [];
    for (const n of nums) {
        if (minheap.length < k) {
            minheap.push(n);
            heapUp(minheap);
        } else {
            if (minheap[0] < n) {
                minheap[0] = n;
                heapDown(minheap);
            }
        }
    }
    return minheap[0];
};

function heapUp (heap: number[]): void {
    let i = heap.length - 1;
    while (i > 0) {
        let p = Math.floor((i - 1) / 2);
        if (heap[i] < heap[p]) {
            [heap[p], heap[i]] = [heap[i], heap[p]];
            i = p;
        } else {
            break;
        }
    }
}

function heapDown(heap: number[]): void {
    let i = 0;
    while (true) {
        let l = i * 2 + 1;
        let r = i * 2 + 2;
        let p = i;

        if (l < heap.length && heap[p] > heap[l]) p = l;
        if (r < heap.length && heap[p] > heap[r]) p = r;
        if (p === i) break;
        [heap[i], heap[p]] = [heap[p], heap[i]];
        i = p;
    }
}
```