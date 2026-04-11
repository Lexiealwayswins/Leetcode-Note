# Minimum Swaps to Sort an Array  
### (Using Cycle Decomposition)

## 📘 Problem Statement

**EXAMPLE 1**  
Input: `arr = [6, 5, 4, 3, 2, 1]`  
Output: `15`  
Explanation: Sorting `arr` with 15 swaps.

**EXAMPLE 2**  
Input: `arr = [1, 2, 3, 4, 5]`  
Output: `0`  
Explanation: Sorting `arr` with 0 swaps.

**EXAMPLE 3**  
Input: `arr = [8, 7, 6, 5, 4]`  
Output: `2`  
Explanation: 

Two optimal swaps:

1. Swap `8` with `4`
2. Swap `7` with `5`

**Requirements**  
1. Given an array of unique positive integers, count the number of swaps needed to sort the array.  
2. Optimize the algorithm.  
3. Accept input and output using standard input/output.  
4. Do not use any external libraries.  
5. Ensure the algorithm works efficiently for up to 100,000 elements.  
6. On a 10-second limit.

---

## Solution Notes:  
🧩 Key Insight

To compute the minimum number of swaps, we treat the array as a permutation and decompose it into cycles.

For each cycle of size k, the number of swaps needed is: k - 1

The total minimum swaps is the sum of (cycleSize - 1) over all cycles.

This method runs in O(n log n) due to sorting.

## ✅ JavaScript Solution (Optimal)

```js
function minSwaps(arr) {
  const n = arr.length;

  // Pair each value with its original index
  const arrPos = arr.map((value, index) => ({ value, index }));

  // Sort by value to know the target positions
  arrPos.sort((a, b) => a.value - b.value);

  const visited = new Array(n).fill(false);
  let swaps = 0;

  for (let i = 0; i < n; i++) {
    // Skip if already visited or already in correct position
    if (visited[i] || arrPos[i].index === i) continue;

    let cycleSize = 0;
    let j = i;

    // Count cycle size
    while (!visited[j]) {
      visited[j] = true;
      j = arrPos[j].index;
      cycleSize++;
    }

    // Add swaps needed for this cycle
    if (cycleSize > 1) swaps += cycleSize - 1;
  }

  return swaps;
}
```