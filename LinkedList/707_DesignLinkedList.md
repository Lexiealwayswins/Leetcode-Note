# 707. Design Linked List - Medium

## Problem Statement:

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

MyLinkedList() Initializes the MyLinkedList object.
int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
void addAtTail(int val) Append a node of value val as the last element of the linked list.
void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 

### Example 1:

**Input**  
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]  
**Output**  
[null, null, null, null, 2, null, 3]

### Explanation
- MyLinkedList myLinkedList = new MyLinkedList();
- myLinkedList.addAtHead(1);
- myLinkedList.addAtTail(3);
- myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
- myLinkedList.get(1);              // return 2
- myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
- myLinkedList.get(1);              // return 3
 

### Constraints:

- 0 <= index, val <= 1000
- Please do not use the built-in LinkedList library.
- At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.

## Solution Notes:
- Try to resuse the methods
- Single Linked List
- Doubly Linked List

## Codes:
- Python
```Python
# Single Linked List
class ListNode: 
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class MyLinkedList:

    def __init__(self):
        self.size = 0
        self.dummy = ListNode()

    def get(self, index: int) -> int:
        if index < 0 or index >= self.size: return -1

        curr = self.dummy.next
        for i in range(index):
            curr = curr.next

        return curr.val

    def addAtHead(self, val: int) -> None:
        self.dummy.next = ListNode(val, self.dummy.next)
        self.size += 1

    def addAtTail(self, val: int) -> None:
        curr = self.dummy
        while curr.next:
            curr = curr.next
        curr.next = ListNode(val)
        self.size += 1

    def addAtIndex(self, index: int, val: int) -> None:
        if index < 0 or index > self.size: return
        curr = self.dummy
        for i in range(index):
            curr = curr.next
        curr.next = ListNode(val, curr.next)
        self.size += 1

    def deleteAtIndex(self, index: int) -> None:
        if index < 0 or index >= self.size: return
        curr = self.dummy
        for i in range(index):
            curr = curr.next
        curr.next = curr.next.next
        self.size -= 1


# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)

# Doubly Linked List
class ListNode: 
    def __init__(self, val=0, prev=None, next=None):
        self.val = val
        self.prev = prev
        self.next = next

class MyLinkedList:

    def __init__(self):
        self.size = 0
        self.head = None
        self.tail = None

    def get(self, index: int) -> int:
        if index < 0 or index >= self.size: return -1

        if index < self.size // 2:
            curr = self.head
            for i in range(index):
                curr = curr.next
        else:
            curr = self.tail
            for i in range(self.size - index - 1):
                curr = curr.prev

        return curr.val

    def addAtHead(self, val: int) -> None:
        newNode = ListNode(val, None, self.head)
        if self.head:
            self.head.prev = newNode
        else:
            self.tail = newNode
        self.head = newNode
        self.size += 1

    def addAtTail(self, val: int) -> None:
        newNode = ListNode(val, self.tail, None)
        if self.tail:
            self.tail.next = newNode
        else:
            self.head = newNode
        self.tail = newNode
        self.size += 1

    def addAtIndex(self, index: int, val: int) -> None:
        if index < 0 or index > self.size: return
        
        if index == 0:
            self.addAtHead(val)
            return

        if index == self.size:
            self.addAtTail(val)
            return

        if index < self.size // 2:
            curr = self.head
            for i in range(index):
                curr = curr.next
        else:
            curr = self.tail
            for i in range(self.size - index - 1):
                curr = curr.prev
        newNode = ListNode(val, curr.prev, curr)
        if curr.prev:  
            curr.prev.next = newNode
        else:
            self.head = newNode
        curr.prev = newNode
        self.size += 1

    def deleteAtIndex(self, index: int) -> None:
        if index < 0 or index >= self.size: return

        if index == 0: 
            self.head = self.head.next
            if self.head:
                self.head.prev = None
            else:
                self.tail = None
        elif index == self.size - 1:
            self.tail = self.tail.prev
            if self.tail:
                self.tail.next = None
            else:
                self.head = None
        else:
            if index < self.size // 2:
                curr = self.head
                for i in range(index):
                    curr = curr.next
            else:
                curr = self.tail
                for i in range(self.size - index - 1):
                    curr = curr.prev
            curr.prev.next = curr.next
            curr.next.prev = curr.prev
        self.size -= 1


# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)
```

- JavaScript
```JavaScript
// Singe Linked List
class ListNode {
    constructor (val, next) {
        this.val = val;
        this.next = next;
    }
}

var MyLinkedList = function() {
    this._size = 0;
    this._dummy = new ListNode(0, null);
};

/** 
 * @param {number} index
 * @return {number}
 */
MyLinkedList.prototype.get = function(index) {
    if (index < 0 || index >= this._size) return -1;
    let curr = this._dummy;
    for (let i = 0; i <= index; i++) {
        curr = curr.next;
    }
    return curr.val;
};

/** 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtHead = function(val) {
    this._dummy.next = new ListNode(val, this._dummy.next);
    this._size++;
};

/** 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtTail = function(val) {
    let curr = this._dummy;
    while (curr.next) {
        curr = curr.next
    }
    curr.next = new ListNode(val, null);
    this._size++;
};

/** 
 * @param {number} index 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtIndex = function(index, val) {
    if (index < 0 || index > this._size) return;

    let curr = this._dummy;
    for (let i = 0; i < index; i++) {
        curr = curr.next;
    }
    curr.next = new ListNode(val, curr.next);
    this._size++;
};

/** 
 * @param {number} index
 * @return {void}
 */
MyLinkedList.prototype.deleteAtIndex = function(index) {
    if (index < 0 || index >= this._size) return;

    let curr = this._dummy;
    for (let i = 0; i < index; i++) {
        curr = curr.next;
    }
    curr.next = curr.next.next;
    this._size--;
};

/** 
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = new MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(val)
 * obj.addAtTail(val)
 * obj.addAtIndex(index,val)
 * obj.deleteAtIndex(index)
 */

// Doubly Linked List
class ListNode {
    constructor (val, prev, next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}

var MyLinkedList = function() {
    this._size = 0;
    this._head = null;
    this._tail = null;
};

/** 
 * @param {number} index
 * @return {number}
 */
MyLinkedList.prototype.get = function(index) {
    if (index < 0 || index >= this._size) return -1;
    if (index < this._size / 2) {
        let curr = this._head;
        for (let i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    } else {
        let curr = this._tail;
        for (let i = this._size - 1; i > index; i--) {
            curr = curr.prev;
        }
        return curr.val;
    }
    return -1;
};

/** 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtHead = function(val) {
    let newNode = new ListNode(val, null, this._head);
    if (this._head){
        this._head.prev = newNode;
    } else {
        this._tail = newNode;
    }
    this._head = newNode;
    this._size++;
};

/** 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtTail = function(val) {
    let newNode = new ListNode(val, this._tail, null);
    if (this._tail){
        this._tail.next = newNode;
    } else {
        this._head = newNode;
    }
    this._tail = newNode;
    this._size++;
};

/** 
 * @param {number} index 
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtIndex = function(index, val) {
    if (index < 0 || index > this._size) return;

    if (index == 0) {
        return this.addAtHead(val);
    } else if (index == this._size) {
        return this.addAtTail(val);
    } else {
        if (index < this._size / 2) {
            let curr = this._head;
            for (let i = 0; i < index; i++) {
                curr = curr.next;
            }
            let newNode = new ListNode(val, curr.prev, curr);
            curr.prev.next = newNode;
            curr.prev = newNode;
        } else {
            let curr = this._tail;
            for (let i = this._size - 1; i > index; i--) {
                curr = curr.prev;
            }
            let newNode = new ListNode(val, curr.prev, curr);
            curr.prev.next = newNode;
            curr.prev = newNode;
        }
    }
    this._size++;
};

/** 
 * @param {number} index
 * @return {void}
 */
MyLinkedList.prototype.deleteAtIndex = function(index) {
    if (index < 0 || index >= this._size) return -1;
    if (index == 0) {
        this._head = this._head.next;
        if (this._head) {
            this._head.prev = null;
        } else {
            this._tail = null;
        }
    } else if (index == this._size - 1) {
        this._tail = this._tail.prev;
        if (this._tail) {
            this._tail.next = null;
        } else {
            this._head = null;
        }
    } else {
        if (index < this._size / 2) {
            let curr = this._head;
            for (let i = 0; i < index; i++) {
                curr = curr.next;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        } else {
            let curr = this._tail;
            for (let i = this._size - 1; i > index; i--) {
                curr = curr.prev;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
    }
    this._size--;
};

/** 
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = new MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(val)
 * obj.addAtTail(val)
 * obj.addAtIndex(index,val)
 * obj.deleteAtIndex(index)
 */
```

- Java
```Java
class Node {
    int val;
    Node next;
    public Node(){}
    public Node (int val) {
        this.val = val;
        this.next = null;
    }
}

class MyLinkedList {
    // singly linked list
    Node head;
    int size;

    public MyLinkedList() {
        size = 0;
        head = new Node(0); // 这里是设置了值为0的一个虚拟的头节点
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        Node curr = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }
    
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        size++;
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        
        Node newNode = new Node(val);
        newNode.next = pre.next;
        pre.next = newNode;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        size--;

        if (index == 0) {
            head = head.next;
            return;
        }

        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

```