# 75. Sort Colors - Medium

## Problem Statement:
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.


### Example 1:

**Input**: nums = [2,0,2,1,1,0]
**Output**: [0,0,1,1,2,2]

### Example 2:

**Input**: nums = [2,0,1]
**Output**: [0,1,2]
 
### Constraints:

- n == nums.length
- 1 <= n <= 300
- nums[i] is either 0, 1, or 2.

### Follow up: Could you come up with a one-pass algorithm using only constant extra space?

## Solution Notes:
- Dutch National Flag Algorithm
- Merge Sort O(nlogn): use a temp array to help store sorted value


## Codes:

```TypeScript
// 1. Dutch National Flag Algorithm
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let l = 0, m = 0;
    let r = nums.length - 1;
    while (m <= r) {
        if (nums[m] === 0) {
            [nums[l], nums[m]] = [nums[m], nums[l]];
            l++;
            m++;
        } else if (nums[m] === 1) {
            m++;
        } else {
            [nums[m], nums[r]] = [nums[r], nums[m]];
            r--;
        }
    }
};

// 2. Merge Sort
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    const temp = new Array(nums.length);
    sort(nums, temp, 0, nums.length - 1);
};

function sort(nums: number[], temp: number[], l: number, r: number): void{
    if (l >= r) return;
    const m = Math.floor((l + r) / 2);
    sort(nums, temp, l, m);
    sort(nums, temp, m + 1, r);
    merge(nums, temp, l, m, r);
}

function merge (nums: number[], temp: number[], l: number, m: number, r: number): void {
    let i = l, j = m + 1;
    let k = l;
    while (i <= m && j <= r) {
        if (nums[i] <= nums[j]) {
            temp[k++] = nums[i++];
        } else {
            temp[k++] = nums[j++];
        }
    }
    while (i <= m) temp[k++] = nums[i++];
    while (j <= r) temp[k++] = nums[j++];

    for (let x = l; x <= r; x++) nums[x] = temp[x];
}
```