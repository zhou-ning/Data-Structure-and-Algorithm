import java.util.LinkedList;
import java.util.Stack;


/**
 * BinaryTree
 * 
 * @author zhouning
 */
public class BinaryTree {
    // public BinTreeNode root;

    /**
     * 前序遍历
     */
    public void preOrder(BinTreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        this.preOrder(root.left);
        this.preOrder(root.right);
    }
    
    /**
     * 前序遍历（非递归）
     */
    public void preOrder2(BinTreeNode root){
        Stack<BinTreeNode> binStack = new Stack<>();
        binStack.add(root);

        while(!binStack.isEmpty()){
            BinTreeNode node = binStack.pop();
            if (node!=null) {
                System.out.println(node.data);
                binStack.add(node.right);       
                binStack.add(node.left);       
            }

        }
    
    }

    /**
     * 中序遍历
     */
    public void inOrder(BinTreeNode root){
        if(root==null){
            return;
        }
        this.inOrder(root.left);
        System.out.println(root.data);
        this.inOrder(root.right);
    }

    /**
     * 中序遍历（非递归）
     */
    public void inOrder2(BinTreeNode root){
        Stack<BinTreeNode> binStack = new Stack<>();
        BinTreeNode p =root;
        

        while (p!=null||!binStack.isEmpty()) {
            while (p!=null) {
                binStack.add(p);
                p=p.left;
            }    

            if (!binStack.isEmpty()) {
                p=binStack.pop();
                System.out.println(p.data);
                p=p.right;
            }

        }
    }

    /**
     * 后续遍历
     */
    public void postOrder(BinTreeNode root){
        if(root==null){
            return;
        }
        this.postOrder(root.left);
        this.postOrder(root.right);
        System.out.println(root.data);
    }

    public void levelOrder(BinTreeNode root){
        LinkedList<BinTreeNode> binTreeNodes = new LinkedList<>();
        binTreeNodes.add(root);

        while (!binTreeNodes.isEmpty()) {
            BinTreeNode node = binTreeNodes.pop();
            if (node!=null) {
                System.out.println(node.data);
                binTreeNodes.add(node.left);
                binTreeNodes.add(node.right);
            }
        }

    }


    public static void main(String[] args) {
        BinTreeNode root = new BinTreeNode();
        root.data = 1;


        BinTreeNode tree1 = new BinTreeNode();
        tree1.data = 2;
        BinTreeNode tree2 = new BinTreeNode();
        tree2.data = 3;
        
        BinTreeNode tree3 = new BinTreeNode();
        tree3.data = 4;

        BinTreeNode tree4 = new BinTreeNode();
        tree4.data = 5;

        BinTreeNode tree5 = new BinTreeNode();
        tree5.data = 6;

        BinTreeNode tree6 = new BinTreeNode();
        tree6.data = 7;

        root.left = tree1;
        root.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;
        tree2.left = tree5;
        tree2.right = tree6;
        BinaryTree binaryTree = new BinaryTree();
        // binaryTree.inOrder(root);
        // System.out.println();
        // binaryTree.inOrder2(root);
        binaryTree.levelOrder(root);

    }
}