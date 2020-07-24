package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traverse {
    
    /***
     * 深度优先遍历
     * @param node
     * @param visited
     */
    public void dfs(GraphNode node, List<GraphNode> visited) {
		// 判断是否遍历过
		if (visited.contains(node)) {
			return;
		}
		
		visited.add(node);
		System.out.println("节点：" + node.val);
		for (int i = 0; i < node.neighbors.size(); i++) {
			dfs(node.neighbors.get(i), visited);
		}
    }
    
    /***
     * 广度优先遍历
     * @param node
     */
    public void bfs(GraphNode node){

        List<GraphNode> visited = new ArrayList<GraphNode>(); // 已经被访问过的元素
		Queue<GraphNode> q = new LinkedList<GraphNode>(); // 用队列存放依次要遍历的元素
		q.offer(node);
		
		while (!q.isEmpty()) {
			GraphNode currNode = q.poll();
			if (!visited.contains(currNode)) {
				visited.add(currNode);
				System.out.println("节点：" + currNode.val);
				for (int i = 0; i < currNode.neighbors.size(); i++) {
					q.offer(currNode.neighbors.get(i));
                }
            }
        }	
    }

    public static void main(String[] args) {
        GraphNode v0 = new GraphNode(0); 
        GraphNode v1 = new GraphNode(1); 
        GraphNode v2 = new GraphNode(2); 
        GraphNode v3 = new GraphNode(3); 
        GraphNode v4 = new GraphNode(4); 
        GraphNode v5 = new GraphNode(5); 
        GraphNode v6 = new GraphNode(6); 
        GraphNode v7 = new GraphNode(7); 
        v0.addNeighbor(v1);v0.addNeighbor(v2);
        v1.addNeighbor(v0);v1.addNeighbor(v3);v1.addNeighbor(v4);
        v2.addNeighbor(v0);v2.addNeighbor(v5);v2.addNeighbor(v6);
        v3.addNeighbor(v1);v3.addNeighbor(v7);
        v4.addNeighbor(v1);v4.addNeighbor(v7);
        v5.addNeighbor(v2);v5.addNeighbor(v6);
        v6.addNeighbor(v2);v5.addNeighbor(v5);
        v7.addNeighbor(v3);v5.addNeighbor(v4);
        Traverse t =new Traverse();
        System.out.println("深度优先遍历");
        List<GraphNode> visited = new ArrayList<GraphNode>();
        t.dfs(v0, visited);
        System.out.println("广度优先遍历");
        t.bfs(v0);
    }
}