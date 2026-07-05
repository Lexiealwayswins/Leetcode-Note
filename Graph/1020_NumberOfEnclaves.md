# 1020. Number of Enclaves - Medium

## Problem Statement:  

You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

### Example 1:
![](https://assets.leetcode.com/uploads/2021/02/18/enclaves1.jpg)

**Input:** grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
**Output:** 3
**Explanation:** There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

### Example 2:
![](https://assets.leetcode.com/uploads/2021/02/18/enclaves2.jpg)

**Input:** grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
**Output:** 0
**Explanation:** All 1s are either on the boundary or can reach the boundary.
 

### Constraints:

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 500
- grid[i][j] is either 0 or 1.


## Solution Notes:  

- Solution Reflection:
    - 本题要求找到不靠边的陆地面积
    - 那么我们只要从周边找到陆地然后 通过 dfs或者bfs 将周边靠陆地且相邻的陆地都变成海洋
    - 然后再去重新遍历地图 统计此时还剩下的陆地就可以了

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes
```TypeScript
function numEnclaves(grid: number[][]): number {
    const n = grid.length;
    const m = grid[0].length;
    const dir: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    let res = 0;

    function dfs (grid: number[][], x: number, y: number): void {
        grid[x][y] = 0;
        for (const d of dir) {
            const nextX = x + d[1];
            const nextY = y + d[0];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
            if (grid[nextX][nextY] === 1)  {
                dfs(grid, nextX, nextY);
            };
        }
    }

    for (let i = 0; i < n; i++) {
        if (grid[i][0] === 1) dfs(grid, i, 0);
        if (grid[i][m - 1] === 1) dfs(grid, i, m - 1);
    }

    for (let j = 0; j < m; j++) {
        if (grid[0][j] === 1) dfs(grid, 0, j);
        if (grid[n - 1][j] === 1) dfs(grid, n - 1, j);
    }


    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (grid[i][j] === 1) res++;
        }
    }

    return res;
};

```



---

Practice ACM:
https://kamacoder.com/problempage.php?pid=1173

# Total Area of Isolated Island - Medium

## Problem Statement:  

当然可以，我给你 **中英文对照版本**，格式清晰、专业，适合写题解、面试、提交平台描述。

---

# **101. 孤岛的总面积**  
**101. Total Area of Isolated Islands**

---

## 📝 **题目描述（中文）**

给定一个由 `1`（陆地）和 `0`（水）组成的矩阵。  
岛屿指的是由水平或垂直方向相邻的陆地单元格组成的区域。  
如果一个岛屿的所有陆地单元格都 **不接触矩阵边缘**，则称为 **孤岛**。

请计算所有孤岛的总面积。  
岛屿面积定义为岛屿中陆地单元格的数量。

---

## 📝 **Problem Description (English)**

You are given a matrix consisting of `1` (land) and `0` (water).  
An **island** is a group of land cells connected horizontally or vertically.  
An island is considered an **enclaved island** if **none of its land cells touches the boundary** of the matrix.

Your task is to compute the **total area** of all enclaved islands.  
The area of an island is defined as the **number of land cells** it contains.

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

输出一个整数，表示所有孤岛的总面积。  
如果不存在孤岛，则输出 `0`。

---

## 📤 **Output Description (English)**

Output a single integer — the total area of all enclaved islands.  
If no enclaved island exists, output `0`.

---

## 📘 **输入示例（中文）**

```
4 5
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
```
![]()

## 📘 **Example Input (English)**

```
4 5
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
```

---

## 📗 **输出示例（中文）**

```
1
```

## 📗 **Example Output (English)**

```
1
```

---

## 📌 **解释（中英文）**

**中文：**  
矩阵中心的岛屿没有任何单元格接触边缘，因此是孤岛，面积为 `1`。

**English:**  
The island located in the center of the matrix does not touch any boundary, so it is an enclaved island.  
Its area is `1`.

---

## 📏 **数据范围（中英文）**

```
1 ≤ N, M ≤ 50
```

---

## Solution Notes:  

- Solution Reflection:
    - 本题要求找到不靠边的陆地面积
    - 那么我们只要从周边找到陆地然后 通过 dfs或者bfs 将周边靠陆地且相邻的陆地都变成海洋
    - 然后再去重新遍历地图 统计此时还剩下的陆地就可以了

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes:
```Java
// DFS
import java.util.Scanner;
public class Main {
    public static int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int res = 0;
    public static void main(String[] args) {
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
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][M - 1] == 1) dfs(grid, i, M - 1);
        }

        for (int j = 0; j < M; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[N - 1][j] == 1) dfs(grid, N - 1, j);
        }

        res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) dfs(grid, i, j);
            }
        }
        
        System.out.println(res);

        sc.close();

    }

    public static void dfs (int[][] grid, int x, int y) {
        grid[x][y] = 0;
        res++;
        for (int[] d: dir) {
            int nextX = x + d[1];
            int nextY = y + d[0];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }

            if (grid[nextX][nextY] == 1) {
                dfs(grid, nextX, nextY);
            }
        }

    }
}

// BFS

import java.util.*;

public class Main {
    private static int count = 0;
    private static final int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 四个方向

    private static void bfs(int[][] grid, int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, y});
        grid[x][y] = 0; // 只要加入队列，立刻标记
        count++;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1];
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue; // 越界了，直接跳过
                if (grid[nextx][nexty] == 1) {
                    que.add(new int[]{nextx, nexty});
                    count++;
                    grid[nextx][nexty] = 0; // 只要加入队列立刻标记
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];
        
        // 读取网格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        
        // 从左侧边，和右侧边向中间遍历
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) bfs(grid, i, 0);
            if (grid[i][m - 1] == 1) bfs(grid, i, m - 1);
        }
        
        // 从上边和下边向中间遍历
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) bfs(grid, 0, j);
            if (grid[n - 1][j] == 1) bfs(grid, n - 1, j);
        }
        
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) bfs(grid, i, j);
            }
        }

        System.out.println(count);

        scanner.close();
    }
}
```