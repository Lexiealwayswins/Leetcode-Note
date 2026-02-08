# 232. Implement Queue using Stacks - Easy

## Problem Statement:
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 

### Example 1:

**Input**: 
["MyQueue", "push", "push", "peek", "pop", "empty"]  
[[], [1], [2], [], [], []]  
**Output**:
[null, null, null, 1, 1, false]  

### Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

### Constraints:
- 1 <= x <= 9
- At most 100 calls will be made to push, pop, peek, and empty.
- All the calls to pop and peek are valid.
 
Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.

## Solution Notes:
- We can implement queue using two stacks.  

## Codes:
```Python
class MyQueue:
    def __init__(self):
        self.stackIn = []
        self.stackOut = []

    def push(self, x: int) -> None:
        self.stackIn.append(x)

    def pop(self) -> int:
        if self.empty(): return None
        if self.stackOut: return self.stackOut.pop()
        while self.stackIn: self.stackOut.append(self.stackIn.pop())
        return self.stackOut.pop()

    def peek(self) -> int:
        x = self.pop()
        self.stackOut.append(x)
        return x

    def empty(self) -> bool:
        return not (self.stackOut or self.stackIn)


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

```JavaScript
var MyQueue = function() {
    this.stackIn = [];
    this.stackOut = [];
};

/** 
 * @param {number} x
 * @return {void}
 */
MyQueue.prototype.push = function(x) {
    this.stackIn.push(x);
};

/**
 * @return {number}
 */
MyQueue.prototype.pop = function() {
    if (this.stackOut.length) return this.stackOut.pop();
    while (this.stackIn.length) this.stackOut.push(this.stackIn.pop());
    return this.stackOut.pop();
};

/**
 * @return {number}
 */
MyQueue.prototype.peek = function() {
    const x = this.pop();
    this.stackOut.push(x);
    return x;
};

/**
 * @return {boolean}
 */
MyQueue.prototype.empty = function() {
    return !this.stackIn.length && !this.stackOut.length;
};

/** 
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
```

```Java
class MyQueue {
    Stack<Integer> stackin;
    Stack<Integer> stackout;

    public MyQueue() {
        stackin = new Stack<>();
        stackout = new Stack<>();
    }
    
    public void push(int x) {
        stackin.push(x);
    }
    
    public int pop() {
        dumpstack();
        return stackout.pop();
    }
    
    public int peek() {
        dumpstack();
        return stackout.peek();
    }
    
    public boolean empty() {
        return stackin.empty() && stackout.empty();
    }

    public void dumpstack(){
        if (stackout.empty()){
            while (!stackin.empty()){
                stackout.push(stackin.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```Java