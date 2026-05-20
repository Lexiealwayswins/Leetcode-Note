# 797. All Paths From Source to Target - Medium

## Problem Statement:  

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).


### Example 1:  
![](https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg)

**Input:** graph = [[1,2],[3],[3],[]]
**Output:** [[0,1,3],[0,2,3]]
**Explanation:** There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/09/28/all_2.jpg)

**Input:** graph = [[4,3,1],[3,2,4],[3],[4],[]]
**Output:** [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


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

