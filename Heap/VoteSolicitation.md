# 📝 **拉票（Vote Solicitation）**

## 📘 **题目描述（中文）**

小明正在参加一次选举。这次选举一共有 **n** 个人参与投票，每个人都 **最多投 1 票且必须投 1 票**。  
每个人都隶属于且仅隶属于一个阵营，第 \(a_i\) 个人隶属的阵营编号为 \(a_i\)。

小明隶属的阵营编号为 \(x\)。  
所有隶属于该阵营的人（允许没有人属于该阵营）都会投票给小明，其他阵营的人都不会主动投票给小明。

小明自己不参与投票，这 n 个人当中也不包含小明。

---

在最终投票前，小明可以 **游说 1 个阵营** 投票给自己。  
如果游说成功，则该阵营的所有人都会投票给小明。

小明会选择一个阵营进行游说，使得最终获得的票数尽可能多。  
如果在游说前，这 n 个人已经全部隶属于小明所在的阵营，则小明不会展开游说。

假设小明游说一定成功，问：

> **小明最终最多可以获得多少票？**

---

### **输入描述**
- 第一行：两个整数  
  - \(n\) — 参与投票的总人数（\(1 \le n \le 10^5\)）  
  - \(x\) — 小明所在的阵营编号（\(1 \le x \le n\)）

- 第二行：n 个整数  
  - 第 i 个整数 \(a_i\) 表示第 i 个人所属的阵营编号（\(1 \le a_i \le n\)）

---

### **输出描述**
输出一个整数，表示小明最终最多可以获得的票数。

---

### **示例 1**

**输入：**
```
6 2
1 2 2 2 3 3
```

**输出：**
```
5
```

**解释：**  
小明隶属于阵营 2，原本获得 3 票。  
游说阵营 3 后，再获得 2 票，总共 5 票。

---

# 📘 **Problem Description (English)**

Xiaoming is participating in an election. There are **n** voters, and each person must cast **exactly one vote**.  
Each voter belongs to exactly one faction, and the faction of the \(i\)-th person is \(a_i\).

Xiaoming belongs to faction \(x\).  
All people in faction \(x\) will vote for Xiaoming, and people from other factions will **not** vote for him voluntarily.

Xiaoming himself does not vote, and he is not included among the n voters.

---

Before the final voting, Xiaoming can **persuade exactly one faction**.  
If he persuades a faction successfully, then **all members of that faction** will vote for him.

He will choose the faction that maximizes his final vote count.  
If all n people already belong to Xiaoming’s faction, he will not persuade anyone.

Assume persuasion always succeeds.  
Your task is to determine:

> **What is the maximum number of votes Xiaoming can obtain?**

---

### **Input**
- First line: two integers  
  - \(n\): number of voters (\(1 \le n \le 10^5\))  
  - \(x\): Xiaoming’s faction ID (\(1 \le x \le n\))

- Second line: n integers  
  - \(a_i\): faction ID of the \(i\)-th voter (\(1 \le a_i \le n\))

---

### **Output**
Print one integer — the maximum number of votes Xiaoming can obtain.

---

### **Example**

**Input**
```
6 2
1 2 2 2 3 3
```

**Output**
```
5
```

**Explanation:**  
Xiaoming belongs to faction 2 and initially gets 3 votes.  
He persuades faction 3 and gains 2 more votes, for a total of 5.

---



```JavaScript
// Solution 1: 简单解法
const rl = require("readline").createInterface({ input: process.stdin });
var iter = rl[Symbol.asyncIterator]();
const readline = async () => (await iter.next()).value;

let N, NO;
let arr;
const initInput = async () => {
    let line = await readline();
    [N, NO] = line.split(' ').map(Number);

    line = await readline();
    arr = line.split(" ").map(Number);
}


(async function () {
    await initInput();
    const map = new Map();
    let res = 0;
    let max = 0;
    for (const a of arr) {
        map.set(a, (map.get(a) || 0) + 1);
        if (a === NO) {
            res++;
        } else {
            max = map.get(a) > max ? map.get(a) : max;
        }
    }

    res += max;
    console.log(res);
})()


// Solution 2: 复杂解法——用heap
const rl = require("readline").createInterface({ input: process.stdin });
var iter = rl[Symbol.asyncIterator]();
const readline = async () => (await iter.next()).value;

let N, NO;
let arr;
const initInput = async () => {
    let line = await readline();
    [N, NO] = line.split(' ').map(Number);

    line = await readline();
    arr = line.split(" ").map(Number);
}

const heapUp = (heap) => {
    let idx = heap.length - 1;
    while (idx > 0) {
        const parent = Math.floor((idx - 1) / 2);
        if (heap[idx][1] >= heap[parent][1]) break;
        [heap[idx], heap[parent]] = [heap[parent], heap[idx]];
        idx = parent;
    }
}

const heapDown = (heap) => {
    let idx = 0;
    const length = heap.length;
    while (true) {
        let l = 2 * idx + 1;
        let r = 2 * idx + 2;
        let small = idx;

        if (l < length && heap[l][1] < heap[small][1]) small = l;
        if (r < length && heap[r][1] < heap[small][1]) small = r;

        if (small === idx) break;
        [heap[idx], heap[small]] = [heap[small], heap[idx]];
        idx = small;
    }
}

(async function () {
    await initInput();
    const map = new Map();
    let res = 0;
    for (const a of arr) {
        map.set(a, (map.get(a) || 0) + 1);
        if (a === NO) res++;
    }

    if (map.size === 1) {
        console.log(res);
        return;
    } else {
        const minHeap = [];
        for (const [num, freq] of map.entries()) {
            if (minHeap.length < 2) {
                minHeap.push([num, freq]);
                heapUp(minHeap);
            } else {
                if (freq > minHeap[0][1]) {
                    minHeap[0] = [num, freq];
                    heapDown(minHeap);
                }
            }
        }

        if (minHeap[minHeap.length - 1][0] === NO) {
            res += minHeap[0][1];
        } else {
            res += minHeap[minHeap.length - 1][1];
        }
        console.log(res);
    }
})()
```