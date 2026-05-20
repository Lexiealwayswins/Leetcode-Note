# 79. Word Search - Medium

## Problem Statement:  
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.


### Example 1:  
![](https://assets.leetcode.com/uploads/2020/11/04/word2.jpg)

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg)

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

### Example 3:  
![](https://assets.leetcode.com/uploads/2020/10/15/word3.jpg)

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

 
### Constraints:

- m == board.length
- n = board[i].length
- 1 <= m, n <= 6
- 1 <= word.length <= 15
- board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?


## Solution Notes:   
- backtracking 4 directions
- mark the tracked elements for deduplication

## Codes:  
```TypeScript
function exist(board: string[][], word: string): boolean {
    function backtracking (i: number, j:number, k:number): boolean {
        if (k == word.length) return true;
        if (i < 0 || i >= board.length 
            || j < 0 || j >= board[0].length 
            || board[i][j] !== word[k]
        ) return false;

        // deduplicate
        const temp = board[i][j];
        board[i][j] = "";

        if (backtracking(i - 1, j, k + 1) 
            || backtracking(i + 1, j, k + 1)
            || backtracking(i, j - 1, k + 1)
            || backtracking(i, j + 1, k + 1)
        ) return true;

        board[i][j] = temp;
        return false;

    }

    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[0].length; j++) {
            if (backtracking(i, j, 0)) return true;
        }
    }
    return false;
};
```

