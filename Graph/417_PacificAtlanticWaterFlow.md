# 417. Pacific Atlantic Water Flow - Medium

## Problem Statement:  

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 

### Example 1:
![](https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg)

**Input:** heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
**Output:** [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
**Explanation:** The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.

### Example 2:

**Input:** heights = [[1]]
**Output:** [[0,0]]
**Explanation:** The water can flow from the only cell to the Pacific and Atlantic oceans.
 

### Constraints:

- m == heights.length
- n == heights[r].length
- 1 <= m, n <= 200
- 0 <= heights[r][c] <= 105

## Solution Notes:  

- Solution Reflection(反向流动):
    - 从太平洋边界上的节点 逆流而上，将遍历过的节点都标记上。
    - 同样从大西洋边界的边上节点 逆流而上，将遍历过的节点也标记上。
    - 然后两方都标记过的节点就是既可以流向太平洋边界也可以流向大西洋边界的节点。

- Solution: DFS backtracking

## Codes:
```TypeScript
function pacificAtlantic(heights: number[][]): number[][] {
    const n = heights.length;
    const m = heights[0].length;
    const dir = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    const pacific: boolean[][] = Array.from({length: n}, () => new Array(m).fill(false));
    const atlantic: boolean[][] = Array.from({length: n}, () => new Array(m).fill(false));

    function dfs (heights: number[][], visited: boolean[][], x: number, y: number) : void {
        if (visited[x][y]) return;
        visited[x][y] = true;

        for (const [j, i] of dir) {
            const nextX = x + i;
            const nextY = y + j;

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
            if (heights[x][y] > heights[nextX][nextY]) continue;

            dfs(heights, visited, nextX, nextY);
        }
    }

    for (let i = 0; i < n; i++) {
        dfs(heights, pacific, i, 0);
        dfs(heights, atlantic, i, m - 1);
    }

    for (let j = 0; j < m; j++) {
        dfs(heights, pacific, 0, j);
        dfs(heights, atlantic, n - 1, j);
    }

    const res: number[][] = [];
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (pacific[i][j] && atlantic[i][j]) res.push([i, j]);
        }
    }

    return res;
};
```

Practice ACM:
https://kamacoder.com/problempage.php?pid=1175

---

# **103. 高山流水**  
**103. Mountain–River Flow Reachability**

---

## 📝 **题目描述（中文）**

给定一个 `N × M` 的矩阵，每个单元格包含一个整数，表示该位置的相对高度。  
矩阵的 **左边界和上边界** 被视为 **第一组边界**；  
矩阵的 **右边界和下边界** 被视为 **第二组边界**。

当雨水落在矩阵上时，水会沿着地形从 **较高或等高** 的位置流向 **较低或等高** 且 **相邻（上下左右）** 的位置。

请找出所有这样的单元格：  
从这些单元格出发的水 **既能流到第一组边界，也能流到第二组边界**。

---

## 📝 **Problem Description (English)**

You are given an `N × M` matrix where each cell contains an integer representing its relative height.  
The **left** and **top** edges of the matrix are considered the **first boundary group**,  
while the **right** and **bottom** edges are considered the **second boundary group**.

When rain falls on the terrain, water flows from a cell to any **adjacent** cell (up, down, left, or right) whose height is **less than or equal to** the current cell.

Your task is to determine all cells from which water can flow to **both** boundary groups.

---

## 📥 **输入描述（中文）**

- 第一行包含两个整数 `N` 和 `M`，表示矩阵的行数和列数。  
- 接下来 `N` 行，每行包含 `M` 个整数，表示每个单元格的高度。

---

## 📥 **Input Description (English)**

- The first line contains two integers `N` and `M`, the number of rows and columns.  
- The next `N` lines each contain `M` integers representing the height of each cell.

---

## 📤 **输出描述（中文）**

输出若干行，每行包含两个整数，表示能够同时到达第一组边界和第二组边界的单元格坐标（行、列）。  
输出顺序任意。

---

## 📤 **Output Description (English)**

Output multiple lines, each containing two integers:  
the coordinates `(row, column)` of cells from which water can reach **both** boundary groups.  
The order of output does not matter.

---

## 📘 **输入示例（中文 / English）**

```
5 5
1 3 1 2 4
1 2 1 3 2
2 4 7 2 1
4 5 6 1 1
1 4 1 2 1
```

---

## 📗 **输出示例（中文 / English）**

```
0 4
1 3
2 2
3 0
3 1
3 2
4 0
4 1
```

---

## 📌 **解释（中英文）**
![](https://kamacoder.com/upload/kamacoder.com/image/20240418/20240418103946_19439.png)

**中文：**  
图中的蓝色方块表示雨水既能流向第一组边界，也能流向第二组边界，因此这些单元格的坐标构成最终答案。

**English:**  
The blue cells in the diagram represent positions from which water can reach both boundary groups.  
Thus, all such coordinates form the final output.

---

## 📏 **数据范围（中英文）**

```
1 ≤ N, M ≤ 100
```

---

```Java
import java.util.Scanner;
public class Main {
    public static int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
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

        boolean[][] first = new boolean[N][M];
        boolean[][] second = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            dfs(grid, first, i, 0);
            dfs(grid, second, i, M - 1);
        }

        for (int j = 0; j < M; j++) {
            dfs(grid, first, 0, j);
            dfs(grid, second, N - 1, j);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (first[i][j] && second[i][j]) {
                    System.out.print(i + " " + j);
                    System.out.println();
                }
            }
            
        }
        sc.close();

    }

    public static void dfs (int[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y]) return;
        visited[x][y] = true;

        for (int[] d: dir) {
            int nextX = x + d[1];
            int nextY = y + d[0];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }

            if (grid[x][y] > grid[nextX][nextY]) {
                continue;
            }

            dfs(grid, visited, nextX, nextY);
        }

    }
}
```