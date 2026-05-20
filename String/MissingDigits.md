### **Missing Digits**

Create the function **`MathChallenge(str)`**.  
The parameter `str` will be a simple mathematical equation containing:

- three numbers  
- one operator (**+**, **-**, **\***, or **/**)  
- an equal sign (**=**)  
- and **one missing digit represented by `x`**

Your task is to determine **which digit (0–9)** should replace `x` to make the equation true.

The `x` may appear in **any** of the three numbers.  
All numbers are between **0 and 1,000,000**.

### **Example**

- Input: `"4 - 2 = x"`  
  Output: `2`

- Input: `"1x0 * 12 = 1200"`  
  Output: `0`

- Input: `"3x + 12 = 46"`  
  Output: `4`

---

## 📝 **中文版本**

### **数学挑战题**

请你实现一个函数 **`MathChallenge(str)`**。

参数 `str` 是一个简单的数学等式，包含：

- 三个数字  
- 一个运算符（**+**, **-**, **\***, **/**）  
- 一个等号（**=**）  
- 其中一个数字里会包含一个缺失的字符 **`x`**

你的任务是找出 **0–9 中的哪一个数字** 应该替换 `x`，才能让整个等式成立。

`x` 可能出现在三个数字中的任意一个位置。  
所有数字范围为 **0 到 1,000,000**。

### **示例**

- 输入：`"4 - 2 = x"`  
  输出：`2`

- 输入：`"1x0 * 12 = 1200"`  
  输出：`0`

- 输入：`"3x + 12 = 46"`  
  输出：`4`

---

```JavaScript
function MathChallenge(str) {
  // 去掉空格
  str = str.replace(/\s+/g, "");

  // 拆分表达式
  const [left, right] = str.split("=");
  let operator;
  let parts;

  // 找运算符
  if (left.includes("+")) operator = "+";
  else if (left.includes("-")) operator = "-";
  else if (left.includes("*")) operator = "*";
  else if (left.includes("/")) operator = "/";

  parts = left.split(operator);

  let A = parts[0];
  let B = parts[1];
  let C = right;

  // 尝试 0~9 填入 x
  for (let d = 0; d <= 9; d++) {
    const a = Number(A.replace("x", d));
    const b = Number(B.replace("x", d));
    const c = Number(C.replace("x", d));

    let result;
    switch (operator) {
      case "+": result = a + b; break;
      case "-": result = a - b; break;
      case "*": result = a * b; break;
      case "/": result = a / b; break;
    }

    if (result === c) return d;
  }

  return -1; // 理论上不会发生
}

```