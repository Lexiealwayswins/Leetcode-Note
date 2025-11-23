# 17. Letter Combinations of a Phone Number - Medium

## Problem Statement:  
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

### Example 1:  
![](https://assets.leetcode.com/uploads/2022/03/15/1200px-telephone-keypad2svg.png)

**Input:** digits = "23"   
**Output:** ["ad","ae","af","bd","be","bf","cd","ce","cf"]  

### Example 2:  

**Input:** digits = ""  
**Output:** []  

### Example 3:  

**Input:** digits = "2"  
**Output:** ["a","b","c"]  
 
### Constraints:

- 0 <= digits.length <= 4
- digits[i] is a digit in the range ['2', '9'].

## Solution Notes:   
- Use recursion to traverse the digits in for loops

## Codes:  
```Java
class Solution {
    List<String> res;
    StringBuilder item;
    Map<Integer, String> hm;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        item = new StringBuilder();
        hm = new HashMap<>();
        hm.put(2, "abc");
        hm.put(3, "def");
        hm.put(4, "ghi");
        hm.put(5, "jkl");
        hm.put(6, "mno");
        hm.put(7, "pqrs");
        hm.put(8, "tuv");
        hm.put(9, "wxyz");

        getCombination(digits, 0);
        return res;
    }

    public void getCombination(String digits, int index){
        if (digits.length() == 0) return;
        if (item.length() > digits.length()) return;

        if (item.length() == digits.length()){
            res.add(item.toString());
            return;
        }

        String s = hm.get(digits.charAt(index) - '0');
        for (int i = 0; i < s.length(); i++){
            item.append(s.charAt(i));
            getCombination(digits, index + 1);
            item.deleteCharAt(item.length() - 1);
        }
    }
}
```Java