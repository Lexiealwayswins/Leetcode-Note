# 295. Find Median from Data Stream - Hard

## Problem Statement:

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.


 
### Example 1:  

Input:
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output:
[null, null, null, 1.5, null, 2.0]

Explanation:
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

### Constraints:  

- -105 <= num <= 105
- There will be at least one element in the data structure before calling findMedian.
- At most 5 * 104 calls will be made to addNum and findMedian.

### Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
- 💡 核心思路：抛弃堆，使用“计数排序（频率数组）”思想
    既然所有的数字只有 101 种可能（从 0 到 100），我们完全不需要用复杂的二叉堆去维护顺序。我们只需要一个大小为 101 的数组来记录每个数字出现了几次。

    具体实现：
    数据结构：
    准备一个数组 count = new Array(101).fill(0)。
    准备一个变量 total_elements = 0 记录总共来了多少个数字。
    addNum(num) (极其极速)：
    直接 count[num]++。
    total_elements++。
    时间复杂度：绝对的 O(1)。
    findMedian()：
    找中位数，其实就是找第 total_elements / 2 名（奇数情况），或者找它和下一名的平均数（偶数情况）。
    我们只需要用一个 for 循环，从 0 遍历到 100，把 count[i] 累加起来。当累加的人数刚好达到一半时，当前的 i 就是中位数！
    时间复杂度：O(1)（因为无论总共有多少万个数据，循环最多只跑 101 次，这是常数时间）。

If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    - 核心思路：频率数组 + 两个哨兵计数器
    这题有一个非常关键的数学隐藏条件：如果 99% 的数字都在 [0,100] 里面，这意味着：
    无论那 1% 的“极端值”有多大或多小，中位数绝对、一定、肯定会落在 [0,100] 这个区间内！

    为什么？因为极端值最多只有 1%，它们根本不足以把中位数（也就是 50% 位置的数）拉出 [0,100] 的范围。

    因此，我们根本不关心那些不在 [0,100] 里面的数字具体是多少，我们只需要知道有几个比 0 小，有几个比 100 大，用来占位就行了！

    具体实现：
    数据结构：
    依然用 count = new Array(101).fill(0) 记录 [0,100] 的数字。
    增加变量 less_than_zero = 0，记录小于 0 的数字个数。
    增加变量 greater_than_100 = 0，记录大于 100 的数字个数。
    total_elements = 0。
    addNum(num)：
    如果 num < 0，就 less_than_zero++。
    如果 num > 100，就 greater_than_100++。
    否则在范围内，count[num]++。
    total_elements++。
    时间复杂度依然是 O(1)。
    findMedian()：
    找中位数的位置依然是一半：target = total_elements / 2。
    遍历 count 数组累加人数时，初始的累加值直接设定为 less_than_zero（因为这些极端小的值排在最前面占了位置）。
    然后从 0 到 100 遍历 count[i] 继续累加，到达 target 时的 i 就是中位数。
    时间复杂度依然是 O(1)。
## Solution Notes:
- use a max heap to store the left half
- use a min heap to store the right half


## Codes:
```TypeScript
class MedianFinder {
    maxheap: number[];
    minheap: number[];
    constructor() {
        this.maxheap = [];
        this.minheap = [];
    }

    addNum(num: number): void {
        this.pushMax(num);
        this.pushMin(this.popMax());
        if (this.maxheap.length < this.minheap.length) {
            this.pushMax(this.popMin());
        }
    }

    findMedian(): number {
        if (this.maxheap.length > this.minheap.length) {
            return this.maxheap[0];
        } else {
            return (this.maxheap[0] + this.minheap[0]) / 2;
        }
    }

    private pushMax(num: number): void {
        this.maxheap.push(num);
        this.heapUp(this.maxheap, true);
    }

    private pushMin(num: number): void {
        this.minheap.push(num);
        this.heapUp(this.minheap, false);
    }
    
    private popMax(): number {
        let i = this.maxheap.length - 1;
        [this.maxheap[0], this.maxheap[i]] = [this.maxheap[i], this.maxheap[0]];
        let res = this.maxheap.pop();
        this.heapDown(this.maxheap, true);
        return res;
    }

    private popMin(): number {
        let i = this.minheap.length - 1;
        [this.minheap[0], this.minheap[i]] = [this.minheap[i], this.minheap[0]];
        let res = this.minheap.pop();
        this.heapDown(this.minheap, false);
        return res;
    }

    private heapUp(heap: number[], isMax: boolean): void {
        let i = heap.length - 1;
        while (i > 0) {
            let p = Math.floor((i - 1) / 2);
            if (isMax ? heap[i] > heap[p] : heap[i] < heap[p]) {
                [heap[p], heap[i]] = [heap[i], heap[p]];
                i = p;
            } else {
                break;
            }
        }
    }

    private heapDown (heap: number[], isMax: boolean): void {
        let i = 0;
        while (true) {
            let l = i * 2 + 1;
            let r = i * 2 + 2;
            let tmp = i;

            if (l < heap.length && (isMax ? heap[l] > heap[tmp] : heap[l] < heap[tmp])) {
                tmp = l;
            } 
            if (r < heap.length && (isMax ? heap[r] > heap[tmp] : heap[r] < heap[tmp])) {
                tmp = r;
            } 

            if (tmp === i) break;
            
            [heap[i], heap[tmp]] = [heap[tmp], heap[i]];
            i = tmp;
        }
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
```