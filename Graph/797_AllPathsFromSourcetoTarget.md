# 797. All Paths From Source to Target - Medium

## Problem Statement:  

Practice ACM:
https://kamacoder.com/problempage.php?pid=1170

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).


### Example 1:  
![](https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg)

**Input:** graph = \[[1,2],[3],[3],[]]
**Output:** \[[0,1,3],[0,2,3]]
**Explanation:** There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/09/28/all_2.jpg)

**Input:** graph = \[[4,3,1],[3,2,4],[3],[4],[]]
**Output:** \[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


### Constraints:

- n == graph.length
- 2 <= n <= 15
- 0 <= graph[i][j] < n
- graph[i][j] != i (i.e., there will be no self-loops).
- All the elements of graph[i] are unique.
- The input graph is guaranteed to be a DAG.

## Solution Notes:  
- DFS backtracking

## Codes:
```TypeScript
function allPathsSourceTarget(graph: number[][]): number[][] {
    const res: number[][] = [];
    const path: number[] = [];
    path.push(0);

    function dfs (graph: number[][], idx: number): void {
        if (idx === graph.length - 1) {
            res.push(path.slice());
            return;
        }
        for (const i of graph[idx]) {
            path.push(i);
            dfs(graph, i);
            path.pop();
        }
    }

    dfs(graph, 0);
    return res;
};
```

```Python
class Solution:
    def __init__(self):
        self.res = []
        self.path = [0]

    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        self.dfs(graph, 0)
        return self.res
    
    def dfs (self, graph, idx) -> void:
        if idx == len(graph) - 1:
            self.res.append(self.path[:])
            return
        
        for i in graph[idx]:
            self.path.append(i)
            self.dfs(graph, i)
            self.path.pop()

```

```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0);
        dfs(graph, 0);
        return res;
    }

    public void dfs(int[][] graph, int idx) {
        if (idx == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i: graph[idx]) {
            path.add(i);
            dfs(graph, i);
            path.remove(path.size() - 1);
        }
    }
}
```

ACM Version：https://kamacoder.com/problempage.php?pid=1170

```Java
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    static ArrayList<Integer> path = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N + 1);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }   

        while (M-- > 0) {
            graph.get(sc.nextInt()).add(sc.nextInt());
        }

        path.add(1);
        dfs(graph, 1, N);
        
        if (res.isEmpty()) System.out.println(-1);
        for (ArrayList<Integer> p: res){
            for (int i = 0; i < p.size() - 1; i++) {
                System.out.print(p.get(i) + " ");
            }
            System.out.println(p.get(p.size() - 1));
        }
    }

    public static void dfs (ArrayList<ArrayList<Integer>> graph, int idx, int n) {
        if (idx == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i: graph.get(idx)) {
            path.add(i);
            dfs(graph, i, n);
            path.remove(path.size() - 1);
        }
    }
}
```