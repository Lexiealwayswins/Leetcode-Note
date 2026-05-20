# 146. LRU Cache - Medium

## Problem Statement:
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

### Example 1:

**Input:**
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
**Output:**
[null, null, null, 1, null, -1, null, -1, 3, 4]

**Explanation:**
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4 
 

### Constraints:

- 1 <= capacity <= 3000
- 0 <= key <= 104
- 0 <= value <= 105
- At most 2 * 105 calls will be made to get and put.

## Solution Notes:
- Use Double Linked List + Hash Map


## Codes:
```TypeScript
class DLinkedNode {
    key: number;
    val: number;
    prev: DLinkedNode | null = null;
    next: DLinkedNode | null = null;
    constructor (key: number, value: number) {
        this.key = key;
        this.val = value;
    }
}

class LRUCache {
    cap: number;
    head: DLinkedNode | null;
    tail: DLinkedNode | null;
    map: Map<number, DLinkedNode>;
    constructor(capacity: number) {
        this.cap = capacity;
        this.head = new DLinkedNode(-1, -1);
        this.tail = new DLinkedNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new Map<number, DLinkedNode>();
    }

    addNode(newNode: DLinkedNode) {
        let tmp: DLinkedNode | null = this.head.next;
        newNode.next = tmp;
        newNode.prev = this.head;
        this.head.next = newNode;
        tmp.prev = newNode;
    }

    deleteNode(delNode: DLinkedNode) {
        let prev = delNode.prev;
        let next = delNode.next;
        prev.next = next;
        next.prev = prev;
    }

    get(key: number): number {
        if (this.map.has(key)) {
            let curr = this.map.get(key);
            this.map.delete(key);
            this.deleteNode(curr);
            this.addNode(curr);
            this.map.set(key, curr);
            return curr.val;
        }
        return -1;

    }

    put(key: number, value: number): void {
        if (this.map.has(key)) {
            let curr = this.map.get(key);
            this.map.delete(key);
            this.deleteNode(curr);
        }

        if (this.map.size === this.cap) {
            this.map.delete(this.tail.prev.key);
            this.deleteNode(this.tail.prev);
        }

        const newNode = new DLinkedNode(key, value);
        this.addNode(newNode);
        this.map.set(key, newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
```

```Python
class LRUCache:
    class Node:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.prev = None
            self.next = None

    def __init__(self, capacity: int):
        self.cap = capacity
        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.m = {}

    def addNode(self, newnode):
        temp = self.head.next
        newnode.next = temp
        newnode.prev = self.head
        self.head.next = newnode
        temp.prev = newnode

    def deleteNode(self, delnode):
        prevv = delnode.prev
        nextt = delnode.next
        prevv.next = nextt
        nextt.prev = prevv

    def get(self, key: int) -> int:
        if key in self.m:
            resNode = self.m[key]
            ans = resNode.val
            del self.m[key]
            self.deleteNode(resNode)
            self.addNode(resNode)
            self.m[key] = self.head.next
            return ans
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.m:
            curr = self.m[key]
            del self.m[key]
            self.deleteNode(curr)

        if len(self.m) == self.cap:
            del self.m[self.tail.prev.key]
            self.deleteNode(self.tail.prev)

        self.addNode(self.Node(key, value))
        self.m[key] = self.head.next
```

```Java
class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int cap;
    HashMap<Integer, Node> m = new HashMap<>();

    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node newnode) {
        Node temp = head.next;

        newnode.next = temp;
        newnode.prev = head;

        head.next = newnode;
        temp.prev = newnode;
    }

    private void deleteNode(Node delnode) {
        Node prevv = delnode.prev;
        Node nextt = delnode.next;

        prevv.next = nextt;
        nextt.prev = prevv;
    }

    public int get(int key) {
        if (m.containsKey(key)) {
            Node resNode = m.get(key);
            int ans = resNode.val;

            m.remove(key);
            deleteNode(resNode);
            addNode(resNode);

            m.put(key, head.next);
            return ans;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            Node curr = m.get(key);
            m.remove(key);
            deleteNode(curr);
        }

        if (m.size() == cap) {
            m.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        addNode(new Node(key, value));
        m.put(key, head.next);
    }
}
```