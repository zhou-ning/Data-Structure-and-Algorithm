package Graph;

import java.util.List;
import java.util.ArrayList;

/**
 * @author zhouning 
 * 图的节点
 */
public class GraphNode {
    // 节点的值和邻居
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }

    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphNode>();
    }

    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    public void addNeighbor(GraphNode edge) {
        neighbors.add(edge);
    }
}
