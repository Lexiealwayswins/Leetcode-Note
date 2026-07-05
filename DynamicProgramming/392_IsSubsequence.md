# 392. Is Subsequence - Easy

## Problem Statement:  

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

 
### Example 1:

**Input:** s = "abc", t = "ahbgdc"
**Output:** true

### Example 2:

**Input:** s = "axc", t = "ahbgdc"
**Output:** false
 

### Constraints:

- 0 <= s.length <= 100
- 0 <= t.length <= 104
- s and t consist only of lowercase English letters.



## Solution Notes:  
- Dynamic Programming; similar to 1143
- Two pointers

## Codes:
```Java
// DP
class Solution {
    public boolean isSubsequence(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }
}

// Two pointers
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.equals("")) return true;
        if (t.equals("")) return false;
        int s_p = 0;
        int t_p = 0;
        while (s_p < s.length() && t_p < t.length()) {
            if (s.charAt(s_p) == t.charAt(t_p)) s_p++; 
            t_p++;
        }
        return s_p == s.length();
    }
}
```

### Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?

针对你现在的动态规划做法，其时间复杂度为 $O(S \times T)$，在面对大量查询（$k \ge 10^9$）时显然是不够的。

### 针对追问的分析与优化方案

当 `t` 固定，而 `s` 需要进行大量查询时，我们可以通过**预处理（Preprocessing）** `t` 来优化查询时间。

#### 核心思路：预处理索引表
我们可以将 `t` 中每个字符出现的所有位置按照升序存入一个哈希表（或数组）中，例如 `Map<Character, List<Integer>>`。

在查询时，我们维护一个指针 `currentIndex`，表示当前已匹配到的 `t` 中的位置。对于 `s` 中的每一个字符，我们在预处理的索引表中通过**二分查找（Binary Search）**，寻找第一个大于 `currentIndex` 的位置。如果找不到，说明无法匹配。

#### 优化后的算法：
1. **预处理阶段**：遍历一次 `t`，记录每个字符出现的所有下标。
   * 比如 `t = "ahbgdc"`，记录为：
     `{'a': [0], 'h': [1], 'b': [2], 'g': [3], 'd': [4], 'c': [5]}`
2. **查询阶段**：
   * 定义 `currentIndex = -1`。
   * 遍历 `s` 中的每个字符 `c`：
     * 在 `c` 对应的下标列表中查找第一个大于 `currentIndex` 的值。
     * 使用二分查找（`Collections.binarySearch` 或手写）可以快速找到该位置。
     * 如果找到，更新 `currentIndex`；如果没找到，返回 `false`。

#### 复杂度分析：
* **预处理时间**：$O(T)$，只需执行一次。
* **单次查询时间**：$O(S \log T)$，其中 $\log T$ 是二分查找的时间。相比原来的 $O(S \times T)$，效率大幅提升。

---

### Java 代码实现

```java
import java.util.*;

class Solution {
    // 将索引表存在成员变量中，以便多次查询
    Map<Character, List<Integer>> map;

    public Solution(String t) {
        map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.computeIfAbsent(t.charAt(i), k -> new ArrayList<>()).add(i);
        }
    }

    public boolean isSubsequence(String s) {
        int currentIndex = -1;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) return false;
            
            List<Integer> list = map.get(c);
            // 二分查找：找到第一个大于 currentIndex 的下标
            int pos = binarySearch(list, currentIndex);
            if (pos == -1) return false;
            currentIndex = pos;
        }
        return true;
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                result = list.get(mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
```

### 总结
* **为什么弃用 DP**：DP 空间复杂度过大（如果 $T=10^4$，空间 $O(S \times T)$ 可能会溢出），且单次查询时间随 $T$ 线性增长。
* **为什么要用二分查找**：通过预处理将“寻找下一个字符”的问题转化为在有序数组中找“大于当前坐标的最小值”，这是标准的二分查找场景。
* **面对 $k \ge 10^9$**：这种优化后的方法在处理大规模查询时非常高效，是工业界处理此类问题的常用手段。