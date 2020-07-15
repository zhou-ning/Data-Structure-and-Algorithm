
/**
 * @author zhouning
 */
public class BinTreeNode {
    public Integer data;
    public BinTreeNode left,right;

    public BinTreeNode(){
        this.data = null;
        this.left = null;
        this.right = null;
    };
    public BinTreeNode(Integer data,BinTreeNode left,BinTreeNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}