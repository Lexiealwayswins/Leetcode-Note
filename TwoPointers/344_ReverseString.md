# 344. Reverse String - Easy

## Problem Statement:
Write a function that reverses a string. The **Input:** string is given as an array of characters s.

You must do this by modifying the **Input:** array in-place with O(1) extra memory.

 
### Example 1:

**Input:** s = ["h","e","l","l","o"]
**Output:** ["o","l","l","e","h"]
Example 2:

**Input:** s = ["H","a","n","n","a","h"]
**Output:** ["h","a","n","n","a","H"]
 

### Constraints:

- 1 <= s.length <= 105
- s[i] is a printable ascii character.

## Solution Notes:
- Using the bitwise XOR swap method:  
s[l] = s[l] ^ s[r];  
s[r] = s[l] ^ s[r];  
s[l] = s[l] ^ s[r];  
				
- **Bitwise XOR Swap Explanation**  
The bitwise XOR (^) operation can be used to swap two variables without using a temporary variable. The XOR operation follows these rules:  
a ^ a = 0  
a ^ 0 = a  
a ^ b ^ b = a  
a ^ b ^ a = b  

- **Swap a, b:**  
a = a ^ b;  
b = a ^ b;  
a = a ^ b;  

- **Step-by-Step Explanation** 

    Initial Values:  
    Let a = 5 (binary: 0101)  
    Let b = 3 (binary: 0011)  

    First Operation (a = a ^ b):  
    a = 0101 ^ 0011 = 0110   
    (now a becomes 6)

    Second Operation (b = a ^ b):  
    b = 0110 ^ 0011 = 0101   
    (now b becomes 5, which was the original value of a)

    Third Operation (a = a ^ b):  
    a = 0110 ^ 0101 = 0011   
    (now a becomes 3, which was the original value of b)  

    After these three operations, the values of a and b are swapped.



## Codes:
Java:
```Java
// Method 1:
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            left++;
            right--;
        }
    }
}

//Method 2:
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
```
Python:
```Python
# Method 3:
class Solution(object):
    def reverseString(self, s):
        """
        :type s: List[str]
        :rtype: None Do not return anything, modify s in-place instead.
        """
        for i in range(len(s) / 2):
            s[i], s[-i-1] = s[-i-1], s[i]
```