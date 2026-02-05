# 1002. Find Common Characters - Easy

## Problem Statement:
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

### Example 1:

**Input**: words = ["bella","label","roller"]
**Output**: ["e","l","l"]

### Example 2:

**Input**: words = ["cool","lock","cook"]
**Output**: ["c","o"]

### Constraints:

- 1 <= words.length <= 100
- 1 <= words[i].length <= 100
- words[i] consists of lowercase English letters.

## Solution Notes:
- Count the frequency of each of the 26 characters in the search string
- then take the minimum frequency of each character
- and finally convert it into the output format.

## Codes:
- Python
```Python
# Solution 1:
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        arr = [0] * 26
        for c in words[0]:
            arr[ord(c) - ord('a')] += 1
        
        for w in words[1 : len(words)]:
            arrNew = [0] * 26
            for c in w:
                arrNew[ord(c) - ord('a')] += 1
            
            for i in range(26):
                arr[i] = min(arr[i], arrNew[i])
        
        res = []
        for i in range(26):
            while arr[i]:
                res.append(chr(ord("a") + i) )
                arr[i] -= 1
        return res

# Solution 2:
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        from collections import Counter
        count = Counter(words[0])

        for w in words[1 : len(words)]:
            count = count & Counter(w)
        
        res = []
        for k in count:
            while count[k]:
                res.append(k)
                count[k] -= 1
        return res
```

- JavaScript
```JavaScript
// Solution 1:
/**
 * @param {string[]} words
 * @return {string[]}
 */
var commonChars = function(words) {
    let arr = new Array(26).fill(0);
    for (const i of words[0]) {
        arr[i.charCodeAt() - "a".charCodeAt()]++;
    }
    for (let i = 1; i < words.length; i++) {
        let arrNew = new Array(26).fill(0);
        for (const s of words[i]) {
            arrNew[s.charCodeAt() - "a".charCodeAt()]++;
        }
        for (let j = 0; j < 26; j++) {
            arr[j] = Math.min(arr[j], arrNew[j]);
        }
    }
    let res = [];
    for (let i = 0; i < 26; i++) {
        while (arr[i] > 0) {
            res.push(String.fromCharCode(i + "a".charCodeAt()));
            arr[i]--;
        }
    }
    return res;
};

// Solution 2 (Map):
/**
 * @param {string[]} words
 * @return {string[]}
 */
var commonChars = function(words) {
    let map = new Map();
    for (const i of words[0]) {
        map.set(i, (map.get(i) || 0) + 1);
    }
    for (let i = 1; i < words.length; i++) {
        let mapNew = new Map();
        for (const s of words[i]) {
            mapNew.set(s, (mapNew.get(s) || 0) + 1);
        }
        for (const m of map) {
            map.set(m[0], Math.min((map.get(m[0]) || 0), (mapNew.get(m[0]) || 0)));
        }
    }
    let res = [];
    map.forEach((v, k) => {
        while (v) {
            res.push(k);
            v--;
        }
    })
    return res;
};
```

- Java
```Java
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        if (A.length == 0) return result;
        int[] hash= new int[26]; // 用来统计所有字符串里字符出现的最小频率
        for (int i = 0; i < A[0].length(); i++) { // 用第一个字符串给hash初始化
            hash[A[0].charAt(i)- 'a']++;
        }
        // 统计除第一个字符串外字符的出现频率
        for (int i = 1; i < A.length; i++) {
            int[] hashOtherStr= new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                hashOtherStr[A[i].charAt(j)- 'a']++;
            }
            // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        // 将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) { // 注意这里是while，多个重复的字符
                char c= (char) (i+'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;
    }
}
```