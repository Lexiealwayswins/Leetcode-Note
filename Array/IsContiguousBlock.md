
# 📝 Is Contiguous Block

## 📌 Problem Description

You are managing the guest seating arrangement for a banquet using a simple ticketing system. Each guest receives a ticket labeled with an integer. The system generates an array containing all ticket numbers, but the order of the array does **not** represent the order in which guests arrive.

Your task is to determine whether all ticket numbers within a given range `[A, B]` (where `A < B`) appear in the array as **one single contiguous block**.

To return `true`, the following conditions must both be satisfied:

1. **Every integer from A to B (inclusive) appears in the array.**  
2. **All occurrences of these numbers occupy one continuous segment in the array, with no extra numbers in between.**

If either condition fails, return `false`.

---

## 📌 Input

- Two integers `A` and `B` such that `A < B`
- An integer array representing all ticket numbers currently in the system

---

## 📌 Constraints

- Array length: `1 <= n <= 10000`
- Ticket number range: `0 <= ticket <= 61`

---

## 📌 Edge Cases to Consider

- All numbers in `[A, B]` appear, but **not in a single contiguous block**
- Some numbers in `[A, B]` are **missing**
- The block is continuous in position but contains **numbers outside the range**

---

## 📌 Examples

### ✔ Example 1

**Input**
```
Array: [3, 8, 7, 14, 10, 9, 12, 11]
A = 9, B = 14
```

**Output**
```
true
```

**Explanation**  
All numbers from 9 to 14 appear, and their positions form one contiguous block (order does not matter).

---

### ✘ Example 2

**Input**
```
Array: [2, 5, 1, 4, 6, 8, 3, 7]
A = 1, B = 6
```

**Output**
```
false
```

**Explanation**  
Although all numbers from 1 to 6 appear, they are scattered across the array and do not form a single contiguous block.

---

## 📌 Reference Solution (JavaScript)

```js
function isContiguousBlock(arr, A, B) {
    const neededCount = B - A + 1;
    const positions = [];

    // Collect positions of numbers within [A, B]
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] >= A && arr[i] <= B) {
            positions.push(i);
        }
    }

    // 1. Missing numbers
    if (positions.length !== neededCount) return false;

    // 2. Check if positions form a continuous segment
    const minPos = Math.min(...positions);
    const maxPos = Math.max(...positions);

    if (maxPos - minPos !== neededCount - 1) return false;

    // 3. Ensure no extra numbers inside the segment
    for (let i = minPos; i <= maxPos; i++) {
        if (arr[i] < A || arr[i] > B) return false;
    }

    return true;
}
```