# 130. Surrounded Regions - Medium

## Problem Statement:  

You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

 

### Example 1:

**Input:** board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

**Output:** [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

**Explanation:**
![](https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg)

In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

### Example 2:

**Input:** board = [["X"]]

**Output:** [["X"]]

 

### Constraints:

- m == board.length
- n == board[i].length
- 1 <= m, n <= 200
- board[i][j] is 'X' or 'O'.

## Solution Notes:  

- Solution Reflection:
    - Similar to problem Total Area of Isolated Islands
    - 步骤一：深搜或者广搜将地图周边的 O （陆地）全部改成 1 （特殊标记）
    - 步骤二：将水域中间 O （陆地）全部改成 水域（X）
    - 步骤三：将之前标记的 1 改为 O （陆地）

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes
```TypeScript
/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    const n = board.length;
    const m = board[0].length;
    const dir: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]];

    function dfs (board: string[][], x: number, y: number): void {
        board[x][y] = "1";
        for (const d of dir) {
            const nextX = x + d[1];
            const nextY = y + d[0];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
            if (board[nextX][nextY] === "X" || board[nextX][nextY] === "1")  continue;

            dfs(board, nextX, nextY);
        }
    }

    for (let i = 0; i < n; i++) {
        if (board[i][0] === "O") dfs(board, i, 0);
        if (board[i][m - 1] === "O") dfs(board, i, m - 1);
    }

    for (let j = 0; j < m; j++) {
        if (board[0][j] === "O") dfs(board, 0, j);
        if (board[n - 1][j] === "O") dfs(board, n - 1, j);
    }


    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (board[i][j] === "O") board[i][j] = "X";
            if (board[i][j] === "1") board[i][j] = "O";
        }
    }
};

```

---

Practice ACM:
https://kamacoder.com/problempage.php?pid=1174

## Problem Statement:  

# Total Area of Isolated Island - Medium

# **102. 沉没孤岛 **  
**102. Sinking Enclaved Islands**

---

## 📝 **题目描述（中文）**

给定一个由 `1`（陆地）和 `0`（水）组成的矩阵。  
岛屿指的是由水平或垂直方向相邻的陆地单元格组成的区域，并且完全被水域单元格包围。  
**孤岛** 是指那些位于矩阵内部、且所有陆地单元格都 **不接触矩阵边缘** 的岛屿。

你的任务是将所有孤岛“沉没”，即把孤岛中的所有 `1` 变为 `0`。

---

## 📝 **Problem Description (English)**

You are given a matrix consisting of `1` (land) and `0` (water).  
An **island** is a group of land cells connected horizontally or vertically and completely surrounded by water.  
An island is considered an **enclaved island** if **none of its land cells touches the boundary** of the matrix.

Your task is to **sink** all enclaved islands by converting every land cell (`1`) inside such islands into water (`0`).

---

## 📥 **输入描述（中文）**

- 第一行包含两个整数 `N` 和 `M`，表示矩阵的行数和列数。  
- 接下来 `N` 行，每行包含 `M` 个数字（`0` 或 `1`）。

---

## 📥 **Input Description (English)**

- The first line contains two integers `N` and `M`, the number of rows and columns.  
- The next `N` lines each contain `M` numbers (`0` or `1`).

---

## 📤 **输出描述（中文）**

输出将所有孤岛“沉没”后的矩阵。  
**注意：每个元素后面都有一个空格。**

---

## 📤 **Output Description (English)**

Output the matrix after sinking all enclaved islands.  
**Note: Each element should be followed by a space.**

---

## 📘 **输入示例（中文 / English）**

```
4 5
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
```

![](https://kamacoder.com/upload/kamacoder.com/image/20240412/20240412144356_69900.png)
---

## 📗 **输出示例（中文 / English）**

```
1 1 0 0 0
1 1 0 0 0
0 0 0 0 0
0 0 0 1 1
```
![](https://kamacoder.com/upload/kamacoder.com/image/20240412/20240412144445_89755.png)
---

## 📌 **解释（中英文）**

**中文：**  
矩阵中心的岛屿不接触边缘，因此属于孤岛，需要被沉没（变为 0）。

**English:**  
The island located in the center does not touch any boundary, so it is an enclaved island and must be sunk (converted to 0).

---

## 📏 **数据范围（中英文）**

```
1 ≤ N, M ≤ 50
```

---

## Solution Notes:  

- Solution Reflection:
    - Similar to problem Total Area of Isolated Islands
    - 步骤一：深搜或者广搜将地图周边的 1 （陆地）全部改成 2 （特殊标记）
    - 步骤二：将水域中间 1 （陆地）全部改成 水域（0）
    - 步骤三：将之前标记的 2 改为 1 （陆地）

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes:
```Java
// Optimize：

import java.util.Scanner;

public class Main {
    static int[][] dir = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} }; // 保存四个方向

    public static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 2;
        for (int[] d : dir) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            // 超过边界
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;
            // 不符合条件，不继续遍历
            if (grid[nextX][nextY] == 0 || grid[nextX][nextY] == 2) continue;
            dfs(grid, nextX, nextY);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 步骤一：
        // 从左侧边，和右侧边 向中间遍历
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][m - 1] == 1) dfs(grid, i, m - 1);
        }

        // 从上边和下边 向中间遍历
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[n - 1][j] == 1) dfs(grid, n - 1, j);
        }

        // 步骤二、步骤三
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) grid[i][j] = 0;
                if (grid[i][j] == 2) grid[i][j] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}


// DFS
import java.util.Scanner;
public class Main {
    public static int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int res = 0;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0, 2);
            if (grid[i][M - 1] == 1) dfs(grid, i, M - 1, 2);;
        }
        for (int j = 0; j < M; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j, 2);
            if (grid[N - 1][j] == 1) dfs(grid, N - 1, j, 2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, 0);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }

    public static void dfs (int[][] grid, int x, int y, int target) {
        grid[x][y] = target;
        for (int[] d: dir) {
            int nextX = x + d[1];
            int nextY = y + d[0];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }

            if (grid[nextX][nextY] == 1) {
                dfs(grid, nextX, nextY, target);
            }
        }
    }
}

```

```Python
# BFS
from collections import deque

n, m = list(map(int, input().split()))
g = []
for _ in range(n):
    row = list(map(int,input().split()))
    g.append(row)
    
directions = [(1,0),(-1,0),(0,1),(0,-1)]
count = 0

def bfs(r,c,mode):
    global count 
    q = deque()
    q.append((r,c))
    count += 1
    
    while q:
        r, c = q.popleft()
        if mode:
            g[r][c] = 2
            
        for di in directions:
            next_r = r + di[0]
            next_c = c + di[1]
            if next_c < 0 or next_c >= m or next_r < 0 or next_r >= n:
                continue
            if g[next_r][next_c] == 1:
                q.append((next_r,next_c))
                if mode:
                    g[r][c] = 2
                    
                count += 1
    

for i in range(n):
    if g[i][0] == 1: bfs(i,0,True)
    if g[i][m-1] == 1: bfs(i, m-1,True)
    
for j in range(m):
    if g[0][j] == 1: bfs(0,j,1)
    if g[n-1][j] == 1: bfs(n-1,j,1)

for i in range(n):
    for j in range(m):
        if g[i][j] == 2:
            g[i][j] = 1
        else:
            g[i][j] = 0
            
for row in g:
    print(" ".join(map(str, row)))

```