# 151. Reverse Words in a String - Medium

## Problem Statement:

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

### Example 1:  

**Input:** s = "the sky is blue"
**Output:** "blue is sky the"  
### Example 2:  

**Input:** s = "  hello world  "
**Output:** "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.  
### Example 3:

**Input:** s = "a good   ### Example"
**Output:** "### Example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.  
 

### Constraints:  

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
 

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?

## Solution Notes:
- 3 steps:
- 1. remove space in the start and the end
- 2. reverse all the characters in the whole string (use StringBuilder to save the string)
- 3. reverse each words in the string

## Codes:

```Java
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWords(sb);

        return sb.toString();
    }

    public StringBuilder removeSpace(String s){
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length() - 1;

        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;

        while (start <= end) {
            if (s.charAt(start) != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(s.charAt(start));
            }
            start++;
        }

        return sb;
    }

    public StringBuilder reverseString(StringBuilder sb, int start, int end){

        while (start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start++;
            end--;
        }

        return sb;
    }

    public StringBuilder reverseEachWords(StringBuilder sb){
        int start = 0;
        int end = 1;

        while (start < sb.length()) {
            while(end < sb.length() && sb.charAt(end) != ' '){
                end++;
            }

            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }

        return sb;
    }
}

```