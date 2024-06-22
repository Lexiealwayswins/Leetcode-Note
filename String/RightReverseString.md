# Right Rotation of String 

## Problem Statement:  

Time limit: 1.000S Space limit: 128MB   

The right rotation of a string is to move several characters at the end of the string to the front of the string.   

Given a string s and a positive integer k, please write a function to move the last k characters of the string to the front of the string to implement the right rotation of the string.    

For example, for the input string "abcdefg" and the integer 2, the function should convert it to "fgabcde". Input description  The input contains two lines. The first line is a positive integer k, which represents the number of bits to be rotated right. The second line is the string s, which represents the string to be rotated. Output description  The output is a line, which is the string after the right rotation operation. 

### Example 1:

**Input**: 2 abcdefg   
**Output**: fgabcde   

### Prompt information  
- Data range:  
- 1 <= k < 10000,  1 <= s.length < 10000;

## Solution Notes:
- The idea is to reverse the order of the two substrings by reversing the order of the characters in the two substrings, and then reverse the order of the characters in the two substrings, so that negative times negative equals positive, which will not affect the order of the characters in the substrings.

## Codes:

```Java
import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        
        int len = s.length();
        char[] c = s.toCharArray();
        reverseString(c, 0, len - 1);
        reverseString(c, 0, n - 1);
        reverseString(c, n, len - 1);
        
        System.out.println(c);
    }
    
    public static void reverseString(char[] c, int start, int end) {
        while (start < end) {
            c[start]^=c[end];
            c[end]^=c[start];
            c[start]^=c[end];
            start++;
            end--;
        }
    }
}
```