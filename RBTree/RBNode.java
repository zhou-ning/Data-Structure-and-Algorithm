package RBTree;

/**
 * 
 * @author zhouning
 */
public class RBNode implements Comparable<RBNode> {
    public static final boolean RED = false;
    public static final boolean BLACK = true;

    public Integer key;
    public Integer data;
    public Boolean color;
    public RBNode parent;
    public RBNode left;
    public RBNode right;

    public RBNode() {
    }

    public RBNode(Boolean col, Integer key, Integer data, RBNode parent, RBNode left, RBNode right) {
        this.color = col;
        this.key = key;
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;

    }

    @Override
    public int compareTo(RBNode rNode) {
        return Integer.compare(data, rNode.data);
    }

    @Override
    public java.lang.String toString() {
        return "RBNode{" + "key=" + key + ", data=" + data + ", color=" + color + ", parent=" + parent + ", left="
                + left + ", right=" + right + '}';
    }
}