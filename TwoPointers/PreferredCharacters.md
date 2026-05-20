# **偏爱的字符（Preferred Characters）**

## 📘 **题目描述（中文）**

小李天生偏爱一些字符。对于一个字符串，他总是想把字符串中的字符变成他偏爱的那些字符。

如果字符串中某个字符不是他所偏爱的字符，称为 **非偏爱字符**，那么他会将该字符替换为 **距离它最近的偏爱字符**。

- 距离定义：字符下标之差的绝对值  
- 如果有多个偏爱字符距离相同，则选择 **最左边** 的那个  
- 所有替换操作 **同时进行**

小李有 \(m\) 个偏爱的字符，分别是：

\[
c_1, c_2, \ldots, c_m
\]

当他看到一个长度为 \(n\) 的字符串 \(s\) 时，请输出小李完成全部替换操作后的字符串。

---

### **输入描述**

- 第一行：两个整数  
  - \(n\)：字符串长度  
  - \(m\)：偏爱字符数量  
- 第二行：\(m\) 个偏爱字符（大写字母，互不相同）  
- 第三行：字符串 \(s\)（长度 \(n\)，全部为大写字母）

**数据范围：**

- \(1 \le n \le 10^5\)  
- \(1 \le m \le 26\)  
- 偏爱字符至少在字符串中出现一次

---

### **输出描述**

输出替换后的字符串。

---

### **示例**

**输入：**
```
12 4
Z G B A
ZQWEGRTBYAAI
```

**输出：**
```
ZZZGGGBBBAAA
```

**说明：**

- Q → 最近偏爱字符是 Z  
- E → 最近偏爱字符是 G  
- W → Z 和 G 距离相同 → 选左边的 Z  
- I → 右边无偏爱字符 → 选左边最近的 A  
- 同一个偏爱字符可能在字符串中出现多次

---

# **Preferred Characters (English Version)**

## 📘 **Problem Description**

Xiao Li prefers certain characters. Given a string, he wants to transform every character into one of his preferred characters.

For any character in the string that is **not** one of his preferred characters (called a *non-preferred character*), he replaces it with the **nearest preferred character** in the string.

- Distance = absolute difference of indices  
- If multiple preferred characters are equally close, choose the **leftmost** one  
- All replacements happen **simultaneously**

Xiao Li has \(m\) preferred characters:

\[
c_1, c_2, \ldots, c_m
\]

Given a string \(s\) of length \(n\), output the final string after all replacements.

---

### **Input**

- First line: two integers  
  - \(n\): length of the string  
  - \(m\): number of preferred characters  
- Second line: \(m\) uppercase characters (distinct)  
- Third line: string \(s\) of length \(n\)

**Constraints:**

- \(1 \le n \le 10^5\)  
- \(1 \le m \le 26\)  
- All characters are uppercase  
- Each preferred character appears at least once in the string

---

### **Output**

Print the resulting string after all replacements.

---

### **Example**

**Input**
```
12 4
Z G B A
ZQWEGRTBYAAI
```

**Output**
```
ZZZGGGBBBAAA
```

**Explanation**

- Q → nearest preferred character is Z  
- E → nearest preferred character is G  
- W → Z and G are equally close → choose leftmost Z  
- I → no preferred character on the right → choose nearest A on the left  
- A preferred character may appear multiple times in the string

---

```JavaScript
const rl = require("readline").createInterface({ input: process.stdin });
var iter = rl[Symbol.asyncIterator]();
const readline = async () => (await iter.next()).value;

let N, M;
let like;
let s;
const initInput = async () => {
    let line = await readline();
    [N, M] = line.split(" ").map(Number);
    line = await readline();
    like = line.split(" ");
    line = await readline();
    s = line.split("");
}

void async function () {
    // Write your code here
    await initInput();
    const set = new Set(like);
    let m = 0;
    let prev = 0;
    for (let i = 0; i < N; i++) {
        if (set.has(s[i])) {
            if (!set.has(s[prev])) {
                for (let j = 0; j < i; j++) {
                    s[j] = s[i];
                }
            } else {
                m = prev + Math.floor((i - prev) / 2);
                for (let k = prev + 1; k < i; k++) {
                    if (k <= m) {
                        s[k] = s[prev];
                    } else {
                        s[k] = s[i];
                    }
                }
            }
            prev = i;
        } else {
            if (i === N - 1) {
                for (let j = prev + 1; j <= i; j++) {
                    s[j] = s[prev];
                }
            }
        }
    }
    console.log(s.join(""));
}()

```