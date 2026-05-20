# 31. Next Permutation - Medium

## Problem Statement:
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 
### Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]

### Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]

### Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


### Constraints:

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 100
 

### Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?

## Solution Notes:
- Two Pointers approach
- 1. find the first up position, which is nums[i] < nums[i + 1], then numbers after i + 1 are all descending
- 2. if i is found, find the first number bigger than nums[i] from the right
- 3. reverse numbers after i to be ascend

## Codes:
```TypeScript
/**
 Do not return anything, modify nums in-place instead.
 */
function nextPermutation(nums: number[]): void {
    let i = nums.length - 2;
    // 1. find the first up position, which is nums[i] < nums[i + 1]
    while (i >= 0 && nums[i] >= nums[i + 1]) i--;

    let j = nums.length - 1;
    // 2. if i is found, find the first number bigger than nums[i] from the right
    if (i >= 0) {
        while (nums[j] <= nums[i]) j--;
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
 
    // 3. reverse numbers after i to be ascend
    let l = i + 1; 
    let r = nums.length - 1;
    while (l < r) {
        [nums[l], nums[r]] = [nums[r], nums[l]];
        l++;
        r--;
    }
};
```

OA真题：

## 📝 **English Version**

### **Math Challenge**

Write a function **`MathChallenge(num)`** that takes a number `num` and returns the **next greater number** that can be formed using **the same digits**.

Examples:

- If `num = 123`, return **132**  
- If `num = 12453`, return **12534**  
- If the number has **no greater permutation**, return **-1** (e.g., `999`)

---

### **Token Replacement Requirement**

After your function produces the output number, convert it to a string and **replace every character** that appears in your **ChallengeToken** with:

```
-[CHAR]-
```

Your **ChallengeToken** is:

```
7i5kx81a
```

---

### **Examples**

#### Example 1  
Input: `11121`  
Output: `11211`  
Final Output:  
```
-1----1--2--1---1--
```

#### Example 2  
Input: `41352`  
Output: `41523`  
Final Output:  
```
4--1----5--23
```

---

## 📝 **中文版本**

### **数学挑战题**

请你实现一个函数 **`MathChallenge(num)`**：

- 输入是一个数字 `num`  
- 你需要返回由 **相同数字重新排列后** 得到的 **下一个更大的数字**

示例：

- `123` → **132**  
- `12453` → **12534**  
- 如果没有更大的排列（例如 `999`），返回 **-1**

---

### **Token 替换要求**

当你得到最终结果后，将其转换为字符串，并把所有出现在 **ChallengeToken** 中的字符替换成：

```
-[字符]-
```

你的 **ChallengeToken** 是：

```
7i5kx81a
```

---

### **示例**

#### 示例 1  
输入：`11121`  
输出：`11211`  
最终输出：  
```
-1----1--2--1---1--
```

#### 示例 2  
输入：`41352`  
输出：`41523`  
最终输出：  
```
4--1----5--23
```

---

```JavaScript
function MathChallenge(num) {
  const token = "7i5kx81a";

  // 1. 求下一个更大的排列（next permutation）
  function nextPermutation(arr) {
    let i = arr.length - 2;

    // 找到第一个下降点
    while (i >= 0 && arr[i] >= arr[i + 1]) i--;

    if (i < 0) return null; // 已经是最大排列

    // 找到右侧比 arr[i] 大的最小数字
    let j = arr.length - 1;
    while (arr[j] <= arr[i]) j--;

    // 交换
    [arr[i], arr[j]] = [arr[j], arr[i]];

    // 反转右侧
    let left = i + 1, right = arr.length - 1;
    while (left < right) {
      [arr[left], arr[right]] = [arr[right], arr[left]];
      left++; right--;
    }

    return arr;
  }

  // 转成数组
  let digits = num.toString().split("").map(Number);

  // 求 next permutation
  let next = nextPermutation([...digits]);
  if (!next) return "-1"; // 没有更大的排列

  let result = next.join("");

  // 2. 替换 ChallengeToken 中的字符
  let finalOutput = "";
  for (let ch of result) {
    if (token.includes(ch)) {
      finalOutput += `-${ch}-`;
    } else {
      finalOutput += ch;
    }
  }

  return finalOutput;
}

```