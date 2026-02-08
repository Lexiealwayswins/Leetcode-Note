# 347. Top K Frequent Elements - Medium

## Problem Statement:

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

### Example 1:  

**Input:** nums = [1,1,1,2,2,3], k = 2  
**Output:** [1,2]   

### Example 2:  

**Input:** nums = [1], k = 1  
**Output:** [1]  
 

### Constraints:  

- 1 <= nums.length <= 105
- -104 <= nums[i] <= 104
- k is in the range [1, the number of unique elements in the array].
- It is guaranteed that the answer is unique.
 

### Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

## Solution Notes:
- use a map to store the num and the frequency of this num
- use PriorityQuene to rank the frequency

## Codes:
```Python
# Solution 1: 
# collections.Counter can make a frequency map
# most_common() can sort the map according to the frequency
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        counter = collections.Counter(nums)
        res = []
        for t in counter.most_common():
            if len(res) < k:
                res.append(t[0])
        return res

# Solution 2: 
import heapq
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        map = {}
        for n in nums:
            map[n] = map.get(n, 0) + 1
        
        heap = []
        for n, freq in map.items():
            heapq.heappush(heap, (freq, n))
            if len(heap) > k:
                heapq.heappop(heap)
        
        res = [0] * k
        for i in range(k - 1, -1, -1):
            res[i] = heapq.heappop(heap)[1]

        return res

```

```JavaScript
// js 没有堆 需要自己构造
class Heap {
    constructor(compareFn) {
        this.compareFn = compareFn;
        this.queue = [];
    }

    // 添加
    push(item) {
        // 推入元素
        this.queue.push(item);

        // 上浮
        let index = this.size() - 1; // 记录推入元素下标
        let parent = Math.floor((index - 1) / 2); // 记录父节点下标

        while (parent >= 0 && this.compare(parent, index) > 0) { // 注意compare参数顺序
            [this.queue[index], this.queue[parent]] = [this.queue[parent], this.queue[index]];

            // 更新下标
            index = parent;
            parent = Math.floor((index - 1) / 2);
        }
    }

    // 获取堆顶元素并移除
    pop() {
        // 边界情况，只有一个元素或没有元素应直接弹出
        if (this.size() <= 1) {
            return this.queue.pop()
        }

        // 堆顶元素
        const out = this.queue[0];

        // 移除堆顶元素 填入最后一个元素
        this.queue[0] = this.queue.pop();

        // 下沉
        let index = 0; // 记录下沉元素下标
        let left = 1; // left 是左子节点下标 left + 1 则是右子节点下标
        let searchChild = this.compare(left, left + 1) > 0 ? left + 1 : left;

        while (this.compare(index, searchChild) > 0) { // 注意compare参数顺序
            [this.queue[index], this.queue[searchChild]] = [this.queue[searchChild], this.queue[index]];

            // 更新下标
            index = searchChild;
            left = 2 * index + 1;
            searchChild = this.compare(left, left + 1) > 0 ? left + 1 : left;
        }

        return out;
    }

    size() {
        return this.queue.length;
    }

    // 使用传入的 compareFn 比较两个位置的元素
    compare(index1, index2) {
        // 处理下标越界问题
        if (this.queue[index1] === undefined) return 1;
        if (this.queue[index2] === undefined) return -1;

        return this.compareFn(this.queue[index1], this.queue[index2]);
    }

}

const topKFrequent = function (nums, k) {
    const map = new Map();

    for (const num of nums) {
        map.set(num, (map.get(num) || 0) + 1);
    }

    // 创建小顶堆
    const heap= new Heap((a, b) => a[1] - b[1]);

    // entry 是一个长度为2的数组，0位置存储key，1位置存储value
    for (const entry of map.entries()) {
        heap.push(entry);

        if (heap.size() > k) {
            heap.pop();
        }
    }

    // return heap.queue.map(e => e[0]);

    const res = [];

    for (let i = heap.size() - 1; i >= 0; i--) {
        res[i] = heap.pop()[0];
    }

    return res;
};
```

```Java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }
}
···Java