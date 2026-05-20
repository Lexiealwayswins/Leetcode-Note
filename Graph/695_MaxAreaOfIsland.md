# 695. Max Area of Island - Medium

Practice ACM:
https://kamacoder.com/problempage.php?pid=1172

## Problem Statement:  

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.


### Example 1:  
![](https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg)

**Input:** grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
**Output:** 6
**Explanation:** The answer is not 11, because the island must be connected 4-directionally.

### Example 2:  

**Input:** grid = [[0,0,0,0,0,0,0,0]]
**Output:** 0


### Constraints:

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 50
- grid[i][j] is either 0 or 1.

## Solution Notes:  

- Solution Reflection:
    - When an unvisited land node is encountered
    - Then marking all land nodes around that can be visited by that unvisited land node
    - Marked land nodes and ocean nodes are skipped. 
    - Then counts the area of each islands and got the max.

- Solution 1: DFS backtracking
- Solution 2: BFS

## Codes:
```TypeScript
// Solution 1: Optimized prunning traversal
function maxAreaOfIsland(grid: number[][]): number {
    let res: number = 0;
    let count: number = 0;
    const visited: boolean[][] = Array.from({length: grid.length}, () => new Array(grid[0].length).fill(false));
    const direction: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]]; // up, right, down, left
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (grid[i][j] === 1 && visited[i][j] === false) {
                visited[i][j] = true;
                count = 1;
                dfs(grid, i, j);
                res = Math.max(res, count);
            }
        }
    }
    
    function dfs (grid: number[][], x: number, y: number): void {
        for (const arr of direction) {
            let next_x: number = x + arr[1];
            let next_y: number = y + arr[0];

            if (next_x < 0 || next_x >= grid.length || next_y < 0 || next_y >= grid[0].length) continue;

            if (!visited[next_x][next_y] && grid[next_x][next_y] === 1) {
                visited[next_x][next_y] = true;
                count++;
                dfs(grid, next_x, next_y);
            }
        }
    }
    return res;
};

// Solution 2:
function maxAreaOfIsland(grid: number[][]): number {
    let res: number = 0;
    let count: number = 0;
    const visited: boolean[][] = Array.from({length: grid.length}, () => new Array(grid[0].length).fill(false));
    const direction: number[][] = [[0, 1], [1, 0], [0, -1], [-1, 0]]; // up, right, down, left
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (grid[i][j] === 1 && visited[i][j] === false) {
                visited[i][j] = true;
                count = 1;
                bfs(grid, i, j);
                res = Math.max(res, count);
            }
        }
    }
    
    function bfs (grid: number[][], x: number, y: number): void {
        const queue: number[][] = [];
        queue.push([x, y]);
        while (queue.length) {
            const [curr_x, curr_y] = queue.shift();
            for (const arr of direction) {
                let next_x: number = curr_x + arr[1];
                let next_y: number = curr_y + arr[0];

                if (next_x < 0 || next_x >= grid.length || next_y < 0 || next_y >= grid[0].length) continue;

                if (!visited[next_x][next_y] && grid[next_x][next_y] === 1) {
                    visited[next_x][next_y] = true;
                    count++;
                    queue.push([next_x, next_y]);
                }
            }
        }
    }
    return res;
};
```

ACM Version：https://kamacoder.com/problempage.php?pid=1172

```JavaScript
// DFS

// 深搜版

const r1 = require('readline').createInterface({ input: process.stdin });
// 创建readline接口
let iter = r1[Symbol.asyncIterator]();
// 创建异步迭代器
const readline = async () => (await iter.next()).value;

let graph // 地图
let N, M // 地图大小
let visited // 访问过的节点
let result = 0 // 最大岛屿面积
let count = 0 // 岛屿内节点数
const dir = [[0, 1], [1, 0], [0, -1], [-1, 0]] //方向

// 读取输入，初始化地图
const initGraph = async () => {
  let line = await readline();
  [N, M] = line.split(' ').map(Number);
  graph = new Array(N).fill(0).map(() => new Array(M).fill(0))
  visited = new Array(N).fill(false).map(() => new Array(M).fill(false))

  for (let i = 0; i < N; i++) {
    line = await readline()
    line = line.split(' ').map(Number)
    for (let j = 0; j < M; j++) {
      graph[i][j] = line[j]
    }
  }
}

/**
 * @description: 从(x, y)开始深度优先遍历
 * @param {*} graph 地图
 * @param {*} visited 访问过的节点
 * @param {*} x 开始搜索节点的下标
 * @param {*} y 开始搜索节点的下标
 * @return {*}
 */
const dfs = (graph, visited, x, y) => {
  for (let i = 0; i < 4; i++) {
    let nextx = x + dir[i][0]
    let nexty = y + dir[i][1]
    if(nextx < 0 || nextx >= N || nexty < 0 || nexty >= M) continue
    if(!visited[nextx][nexty] && graph[nextx][nexty] === 1){
      count++
      visited[nextx][nexty] = true
      dfs(graph, visited, nextx, nexty)
    }
  }
}

(async function () {

  // 读取输入，初始化地图
  await initGraph()

  // 统计最大岛屿面积
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (!visited[i][j] && graph[i][j] === 1) { //遇到没有访问过的陆地
        // 重新计算面积
        count = 1
        visited[i][j] = true

        // 深度优先遍历，统计岛屿内节点数，并将岛屿标记为已访问
        dfs(graph, visited, i, j)

        // 更新最大岛屿面积
        result = Math.max(result, count)
      }
    }
  }
  console.log(result);
})()

// BFS
// 广搜版

const r1 = require('readline').createInterface({ input: process.stdin });
// 创建readline接口
let iter = r1[Symbol.asyncIterator]();
// 创建异步迭代器
const readline = async () => (await iter.next()).value;

let graph // 地图
let N, M // 地图大小
let visited // 访问过的节点
let result = 0 // 最大岛屿面积
let count = 0 // 岛屿内节点数
const dir = [[0, 1], [1, 0], [0, -1], [-1, 0]] //方向


// 读取输入，初始化地图
const initGraph = async () => {
  let line = await readline();
  [N, M] = line.split(' ').map(Number);
  graph = new Array(N).fill(0).map(() => new Array(M).fill(0))
  visited = new Array(N).fill(false).map(() => new Array(M).fill(false))

  for (let i = 0; i < N; i++) {
    line = await readline()
    line = line.split(' ').map(Number)
    for (let j = 0; j < M; j++) {
      graph[i][j] = line[j]
    }
  }
}


/**
 * @description: 从(x, y)开始广度优先遍历
 * @param {*} graph 地图
 * @param {*} visited 访问过的节点
 * @param {*} x 开始搜索节点的下标
 * @param {*} y 开始搜索节点的下标
 * @return {*}
 */
const bfs = (graph, visited, x, y) => {
  let queue = []
  queue.push([x, y])
  count++
  visited[x][y] = true  //只要加入队列就立刻标记为访问过

  while (queue.length) {
    let [xx, yy] = queue.shift()
    for (let i = 0; i < 4; i++) {
      let nextx = xx + dir[i][0]
      let nexty = yy + dir[i][1]
      if(nextx < 0 || nextx >= N || nexty < 0 || nexty >= M) continue
      if(!visited[nextx][nexty] && graph[nextx][nexty] === 1){
        queue.push([nextx, nexty])
        count++
        visited[nextx][nexty] = true
      }
    }
  }

}

(async function () {

  // 读取输入，初始化地图
  await initGraph()

  // 统计最大岛屿面积
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (!visited[i][j] && graph[i][j] === 1) { //遇到没有访问过的陆地
        // 重新计算面积
        count = 0

        // 广度优先遍历，统计岛屿内节点数，并将岛屿标记为已访问
        bfs(graph, visited, i, j)

        // 更新最大岛屿面积
        result = Math.max(result, count)
      }
    }
  }
  console.log(result);
})()

```

```Python
# DFS:
# 四个方向
position = [[0, 1], [1, 0], [0, -1], [-1, 0]]
count = 0


def dfs(grid, visited, x, y):
    """
    深度优先搜索，对一整块陆地进行标记
    """
    global count  # 定义全局变量，便于传递count值
    for i, j in position:
        cur_x = x + i
        cur_y = y + j
        # 下标越界，跳过
        if cur_x < 0 or cur_x >= len(grid) or cur_y < 0 or cur_y >= len(grid[0]):
            continue
        if not visited[cur_x][cur_y] and grid[cur_x][cur_y] == 1:
            visited[cur_x][cur_y] = True
            count += 1
            dfs(grid, visited, cur_x, cur_y)


n, m = map(int, input().split())
# 邻接矩阵
grid = []
for i in range(n):
    grid.append(list(map(int, input().split())))
# 访问表
visited = [[False] * m for _ in range(n)]

result = 0  # 记录最终结果
for i in range(n):
    for j in range(m):
        if grid[i][j] == 1 and not visited[i][j]:
            count = 1
            visited[i][j] = True
            dfs(grid, visited, i, j)
            result = max(count, result)

print(result)

# BFS
from collections import deque

position = [[0, 1], [1, 0], [0, -1], [-1, 0]]  # 四个方向
count = 0


def bfs(grid, visited, x, y):
    """
    广度优先搜索对陆地进行标记
    """
    global count  # 声明全局变量
    que = deque()
    que.append([x, y])
    while que:
        cur_x, cur_y = que.popleft()
        for i, j in position:
            next_x = cur_x + i
            next_y = cur_y + j
            # 下标越界，跳过
            if next_x < 0 or next_x >= len(grid) or next_y < 0 or next_y >= len(grid[0]):
                continue
            if grid[next_x][next_y] == 1 and not visited[next_x][next_y]:
                visited[next_x][next_y] = True
                count += 1
                que.append([next_x, next_y])


n, m = map(int, input().split())
# 邻接矩阵
grid = []
for i in range(n):
    grid.append(list(map(int, input().split())))
visited = [[False] * m for _ in range(n)]  # 访问表

result = 0  # 记录最终结果
for i in range(n):
    for j in range(m):
        if grid[i][j] == 1 and not visited[i][j]:
            count = 1
            visited[i][j] = True
            bfs(grid, visited, i, j)
            res = max(result, count)

print(result)
```

```Java

import java.util.*;
import java.math.*;

/**
 * DFS版
 */
public class Main {

    static final int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
    static int result=0;
    static int count=0;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j]=scanner.nextInt();
            }
        }
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j]&&map[i][j]==1){
                    count=0;
                    dfs(map,visited,i,j);
                    result= Math.max(count, result);
                }
            }
        }
        System.out.println(result);
    }

    static void dfs(int[][] map,boolean[][] visited,int x,int y){
                count++;
                visited[x][y]=true;
                for (int i = 0; i < 4; i++) {
                    int nextX=x+dir[i][0];
                    int nextY=y+dir[i][1];
                    //水或者已经访问过的跳过
                    if(nextX<0||nextY<0
                    ||nextX>=map.length||nextY>=map[0].length
                    ||visited[nextX][nextY]||map[nextX][nextY]==0)continue;
                    
                    dfs(map,visited,nextX,nextY);
                }
            }
}

/**
 * BFS版
 */
public class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int result = 0;
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    count = 0;
                    bfs(map, visited, i, j);
                    result = Math.max(count, result);

                }
            }
        }
        System.out.println(result);
    }

    static void bfs(int[][] map, boolean[][] visited, int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        count++;
        while (!q.isEmpty()) {
            Node node = q.remove();
            for (int i = 0; i < 4; i++) {
                int nextX = node.x + dir[i][0];
                int nextY = node.y + dir[i][1];
                if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length || visited[nextX][nextY] || map[nextX][nextY] == 0)
                    continue;
                q.add(new Node(nextX, nextY));
                visited[nextX][nextY] = true;
                count++;
            }
        }
    }
}
```
