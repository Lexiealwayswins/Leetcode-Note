# 131. Palindrome Partitioning - Medium

## Problem Statement:  
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

### Example 1:  

**Input:** s = "aab"  
**Output:** [["a","a","b"],["aa","b"]]  

### Example 2:  

**Input:** s = "a"  
**Output:** [["a"]]  
 

### Constraints:  

- 1 <= s.length <= 16  
- s contains only lowercase English letters.  

## Solution Notes:   
- Use tracking back recursion
- Use stringbuilder to get Palindrome string
- Use two pointer to check the Palindrome

## Codes:  
```Java
class Solution {
    List<List<String>> res;
    List<String> p;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        p = new ArrayList<>();
        getPalindrome(s, 0, new StringBuilder());
        return res;
    }

    public void getPalindrome(String s, int index, StringBuilder sb){
        if (s.length() == 0) return;
        if (index == s.length()) {
            res.add(new ArrayList<>(p));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (isPalindrome(sb.toString())) {
                p.add(sb.toString());
                getPalindrome(s, i + 1, new StringBuilder());
                p.remove(p.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s){
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
```Java