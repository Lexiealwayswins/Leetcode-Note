# Replace String

## Problem Statement:

[KamaCoderLink](https://kamacoder.com/problempage.php?pid=1064)  
Given a string s, which contains lowercase letters and numeric characters, write a function that leaves the alphabetic characters in the string unchanged and replaces each numeric character with number. For example, given the input string "a1b2c3", the function should convert it to "anumberbnumbercnumber".

### Example 1:

**Input**:  
"a1b2c3"
**Output**:   
"anumberbnumbercnumber"

## Solution Notes:
- Two pointer approach
- Both pointers start from the end so that we don't need to move the character backwards
- Try to make space for extra length

## Codes:

```Java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        
        System.out.println(replaceNumber(s));
    }
    
    public static String replaceNumber(String s){
        // count the number of digits
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))){
                count++;
            }
        }
    
        int len_new = s.length() + 5 * count;
        char[] newS = new char[len_new];
        
        System.arraycopy(s.toCharArray(), 0, newS, 0, s.length());
        
        for(int i=s.length()-1, j=len_new-1; i<j; i--, j--){
            if (!Character.isDigit(s.charAt(i))) {
                newS[j] = newS[i];
            } else {
                newS[j] = 'r';
                newS[j-1] = 'e';
                newS[j-2] = 'b';
                newS[j-3] = 'm';
                newS[j-4] = 'u';
                newS[j-5] = 'n';
                j-=5;
            }
        }
        return new String(newS);
    }
}
```