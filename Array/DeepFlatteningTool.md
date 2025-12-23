# Create a Deep Flattening Tool

## Problem Statement:

In this lab, you will be implementing an array flattening algorithm.

Flattening an array means turning a nested array of any depth into a single, one-dimensional array. The process extracts all elements in order, unwrapping only arrays. Other types are left unchanged.

### Example 1:

**Input:** \[[1], [], [2, [3]]]  
**Output:** \[1, 2, 3]

### Example 2:

**Input:** \[1, {"foo": "bar"}, [2]]
**Output:** \[1, {"foo": "bar"}, 2]

### Example 3:

**Input:** \["baz", [1, 2], {}] 
**Output:** \["baz", 1, 2, {}]

### Constraints:

- Your solution should not use the Array.prototype.flat() or Array.prototype.flatMap() methods.
- Global variables should not be used.

## Solution Notes:
- Recursion

## Codes:
- JavaScript
```JavaScript
function steamrollArray (arr) {
  let res = [];

  function flatten (item) {
    if (Array.isArray(item)) {
      for (const i of item) {
        flatten(i);
      }
    } else {
      res.push(item);
    }
  }

  flatten(arr);
  return res;
}

```
