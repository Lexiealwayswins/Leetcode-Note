# 200. Number of Islands - Medium

Practice ACM:
https://kamacoder.com/problempage.php?pid=1171

## Problem Statement:  

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.


### Example 1:  
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

### Example 2:  
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


### Constraints:

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 300
- grid[i][j] is '0' or '1'.

## Solution Notes:  

- Solution Reflection:
    - When an unvisited land node is encountered
    - Then marking all land nodes around that can be visited by that unvisited land node
    - Marked land nodes and ocean nodes are skipped. 
    - The counter then represents the final number of islands.

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes:
```TypeScript
// Solution 1: Optimized prunning traversal
function numIslands(grid: string[][]): number {
    let res: number = 0;
    const visited: boolean[][] = Array.from({length: grid.length}, () => new Array(grid[0].length).fill(false));
    const direction: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]]; // up, right, down, left
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (grid[i][j] === "1" && visited[i][j] === false) {
                res++;
                visited[i][j] = true;
                dfs(grid, i, j);
            }
        }
    }
    
    function dfs (grid: string[][], x: number, y: number): void {
        for (const arr of direction) {
            let next_x: number = x + arr[1];
            let next_y: number = y + arr[0];

            if (next_x < 0 || next_x >= grid.length || next_y < 0 || next_y >= grid[0].length) continue;

            if (!visited[next_x][next_y] && grid[next_x][next_y] === "1") {
                visited[next_x][next_y] = true;
                dfs(grid, next_x, next_y);
            }
        }
    }
    return res;
};

// Solution 1: standard traveral all
function numIslands(grid: string[][]): number {
    let res: number = 0;
    const visited: boolean[][] = Array.from({length: grid.length}, () => new Array(grid[0].length).fill(false));
    const direction: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]]; // up, right, down, left
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (grid[i][j] === "1" && visited[i][j] === false) {
                res++;
                dfs(grid, i, j);
            }
        }
    }
    
    function dfs (grid: string[][], x: number, y: number): void {
        if (visited[x][y] === true || grid[x][y] === "0")  return;

        visited[x][y] = true;

        for (const arr of direction) {
            let next_x: number = x + arr[1];
            let next_y: number = y + arr[0];

            if (next_x < 0 || next_x >= grid.length || next_y < 0 || next_y >= grid[0].length) continue;

            dfs(grid, next_x, next_y);
        }
    }
    return res;
};

// Solution 2:
function numIslands(grid: string[][]): number {
    let res: number = 0;
    const visited: boolean[][] = Array.from({length: grid.length}, () => new Array(grid[0].length).fill(false));
    const direction: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]]; // up, right, down, left
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (grid[i][j] === "1" && visited[i][j] === false) {
                res++;
                visited[i][j] = true;
                bfs(grid, i, j);
            }
        }
    }
    
    function bfs (grid: string[][], x: number, y: number): void {
        const queue: number[][] = [];
        queue.push([x, y]);
        while (queue.length) {
            let [curr_x, curr_y] = queue.shift();
            for (const arr of direction) {
                let next_x: number = curr_x + arr[1];
                let next_y: number = curr_y + arr[0];

                if (next_x < 0 || next_x >= grid.length || next_y < 0 || next_y >= grid[0].length) continue;

                if (!visited[next_x][next_y] && grid[next_x][next_y] === "1") {
                    visited[next_x][next_y] = true;
                    queue.push([next_x, next_y]);
                }
            }
        }

    }
    return res;
};
```

```Java
// DFS
class Solution {
    public static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    res++;
                    visited[i][j] = true;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        for (int[] d: dir) {
            int nextX = x + d[1];
            int nextY = y + d[0];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;

            if (grid[nextX][nextY] == '1' && visited[nextX][nextY] == false) {
                visited[nextX][nextY] = true;
                dfs(grid, visited, nextX, nextY);
            }
        }
    }
}

// BFS
class Solution {
    public static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    res++;
                    visited[i][j] = true;
                    bfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    public void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            for (int[] d: dir) {
                int nextX = currX + d[1];
                int nextY = currY + d[0];
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;

                if (grid[nextX][nextY] == '1' && visited[nextX][nextY] == false) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }

        }

    }
}
```

ACM Version：https://kamacoder.com/problempage.php?pid=1171

```JavaScript
const r1 = require("readline").createInterface({ input: process.stdin });
const iter = r1[Symbol.asyncIterator]();
const readline = async () => (await iter.next()).value;

let graph;
let visited;
let N, M;
let res = 0;
const dir = [[0, 1], [1, 0], [0, -1], [-1, 0]];

const initGraph = async () => {
    let line = await readline();
    [N, M] = line.split(" ").map(Number);
    graph = Array.from({ length: N }, () => new Array(M).fill(0));
    visited = Array.from({ length: N }, () => new Array(M).fill(false));

    for (let i = 0; i < N; i++) {
        line = await readline();
        graph[i] = line.split(" ").map(Number);
    }
}

const dfs = (graph, visited, x, y) => {
    for (const [i, j] of dir) {
        const next_x = x + j;
        const next_y = y + i;

        if (next_x < 0 || next_x >= N || next_y < 0 || next_y >= M) continue;

        if (!visited[next_x][next_y] && graph[next_x][next_y] === 1) {
            visited[next_x][next_y] = true;
            dfs(graph, visited, next_x, next_y);
        }
    }
}

(async function () {
    await initGraph();

    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (visited[i][j] === false && graph[i][j] === 1) {
                res++;
                visited[i][j] = true;
                dfs(graph, visited, i, j);
            }
        }
    }
    console.log(res);
})()

```

```Python
direction = [[0, 1], [1, 0], [0, -1], [-1, 0]]  # 四个方向：上、右、下、左


def dfs(grid, visited, x, y):
    """
    对一块陆地进行深度优先遍历并标记
    """
    for i, j in direction:
        next_x = x + i
        next_y = y + j
        # 下标越界，跳过
        if next_x < 0 or next_x >= len(grid) or next_y < 0 or next_y >= len(grid[0]):
            continue
        # 未访问的陆地，标记并调用深度优先搜索
        if not visited[next_x][next_y] and grid[next_x][next_y] == 1:
            visited[next_x][next_y] = True
            dfs(grid, visited, next_x, next_y)


if __name__ == '__main__':  
    # 版本一
    n, m = map(int, input().split())
    
    # 邻接矩阵
    grid = []
    for i in range(n):
        grid.append(list(map(int, input().split())))
    
    # 访问表
    visited = [[False] * m for _ in range(n)]
    
    res = 0
    for i in range(n):
        for j in range(m):
            # 判断：如果当前节点是陆地，res+1并标记访问该节点，使用深度搜索标记相邻陆地。
            if grid[i][j] == 1 and not visited[i][j]:
                res += 1
                visited[i][j] = True
                dfs(grid, visited, i, j)
    
    print(res)
```

```Java
import java.util.Scanner;

public class Main {
    public static int[][] dir ={{0,1},{1,0},{-1,0},{0,-1}};
    public static void dfs(boolean[][] visited,int x,int y ,int [][]grid)
    {
        for (int i = 0; i < 4; i++) {
            int nextX=x+dir[i][0];
            int nextY=y+dir[i][1];
            if(nextY<0||nextX<0||nextX>= grid.length||nextY>=grid[0].length)
                continue;
            if(!visited[nextX][nextY]&&grid[nextX][nextY]==1)
            {
                visited[nextX][nextY]=true;
                dfs(visited,nextX,nextY,grid);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m= sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j]=sc.nextInt();
            }
        }
        boolean[][]visited =new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]&&grid[i][j]==1)
                {
                    ans++;
                    visited[i][j]=true;
                    dfs(visited,i,j,grid);
                }
            }
        }
        System.out.println(ans);
    }
}

```
