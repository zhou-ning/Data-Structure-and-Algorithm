package RBTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RBTree
 */
public class RBTree {
    private RBNode root;

    public RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    public Boolean colorOf(RBNode node) {
        if (node != null) {
            return node.color;
        }
        return null;
    }

    public void setParent(RBNode node, RBNode parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    public void setColor(RBNode node, Boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    public Boolean isRed(RBNode node) {
        return (node != null && node.color == RBNode.RED) ? true : false;

    }

    public Boolean isBlack(RBNode node) {
        return !isRed(node);

    }

    public void setRed(RBNode node) {
        if (node != null) {
            node.color = RBNode.RED;
        }
    }

    public void setBlack(RBNode node) {
        if (node != null) {
            node.color = RBNode.BLACK;
        }
    }

    /**
     * 寻找key的节点
     */
    public RBNode search(Integer key, RBNode node) {
        if (node != null) {
            int com = key.compareTo(node.key);
            if (com < 0) {
                return search(key, node.left);
            } else if (com > 0) {
                return search(key, node.right);
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 根据 key 获取数据
     */
    public Integer get(Integer key) {
        RBNode node = search(key, root);
        return node == null ? null : node.data;
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)： px px / / x y / \ --(左旋)-. / \ # lx y x ry / \ / \ ly ry lx
     * ly
     *
     *
     */
    private void leftRotate(RBNode x) {
        // 设置x的右孩子为y
        RBNode y = x.right;

        // 将 “y的左孩子” 设为 “x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        // 将 “x的父亲” 设为 “y的父亲”
        y.parent = x.parent;

        if (x.parent == null) {
            // 如果 “x的父亲” 是空节点，则将y设为根节点
            this.root = y;
        } else {
            if (x.parent.left == x) {
                // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
                x.parent.left = y;
            } else {
                // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
                x.parent.right = y;
            }
        }

        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }

    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)： py py / / y x / \ --(右旋)-. / \ # x ry lx y / \ / \ # lx rx
     * rx ry
     * 
     */
    private void rightRotate(RBNode y) {
        // 设置x是当前节点的左孩子。
        RBNode x = y.left;

        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;

        }

        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;

        if (y.parent == null) {
            // 如果 “y的父亲” 是空节点，则将x设为根节点
            this.root = x;
        } else {
            if (y == y.parent.right) {
                // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
                y.parent.right = x;
            } else {
                // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
                y.parent.left = x;
            }
        }
        // 将 “y” 设为 “x的右孩子”
        x.right = y;
        // 将 “y的父节点” 设为 “x”
        y.parent = x;
    }

    /*
     * 将结点插入到红黑树中
     *
     * 参数说明： node 插入的结点 // 对应《算法导论》中的node
     */
    private void insert(RBNode node) {
        int cmp;
        RBNode y = null;
        RBNode x = this.root;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;

            }
        }

        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }

        // 2. 设置节点的颜色为红色
        node.color = RBNode.RED;

        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    /*
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明： key 插入结点的键值
     */
    public void insert(Integer key, Integer data) {
        RBNode node = new RBNode();
        node.key = key;
        node.data = data;
        // 如果新建结点失败，则返回。
        if (node != null) {
            insert(node);
        }
    }

    /*
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数； 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明： node 插入的结点 // 对应《算法导论》中的z
     */
    private void insertFixUp(RBNode node) {
        RBNode parent, gparent;

        // 若“父节点存在，并且父节点的颜色是红色”
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);

            // 若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBNode uncle = gparent.right;
                if (isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;

                } else {
                    // Case 2条件：叔叔是黑色，且当前节点是右孩子
                    if (parent.right == node) {
                        RBNode tmp;
                        leftRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    }

                    // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                    setBlack(parent);
                    setRed(gparent);
                    rightRotate(gparent);
                }

            } else {
                // 若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBNode uncle = gparent.left;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                } else {
                    // Case 2条件：叔叔是黑色，且当前节点是左孩子
                    if (parent.left == node) {
                        RBNode tmp;
                        rightRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    }

                    // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                    setBlack(parent);
                    setRed(gparent);
                    leftRotate(gparent);
                }
            }
        }

        // 将根节点设为黑色
        setBlack(this.root);
    }

    /*
     * 删除结点(node)，并返回被删除的结点
     *
     * 参数说明： node 删除的结点
     */
    private void remove(RBNode node) {
        RBNode child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ((node.left != null) && (node.right != null)) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBNode replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null) {
                replace = replace.left;
            }

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node) != null) {
                if (parentOf(node).left == node) {
                    parentOf(node).left = replace;
                } else {
                    parentOf(node).right = replace;
                }
            } else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child != null) {
                    setParent(child, parent);
                }
                // 不用管右边，因为找的就是左边
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == RBNode.BLACK) {
                removeFixUp(child, parent);

            }

            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child != null) {
            child.parent = parent;
        }

        // "node节点"不是根节点
        if (parent != null) {
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;

            }
        } else {
            this.root = child;
        }

        if (color == RBNode.BLACK) {
            removeFixUp(child, parent);
        }
        node = null;
    }

    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明： tree 红黑树的根结点 z 删除的结点
     */
    public void remove(Integer key) {
        RBNode node;

        if ((node = search(key, root)) != null) {
            remove(node);
        }
    }

    /*
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数； 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明： node 待修正的节点
     */
    private void removeFixUp(RBNode node, RBNode parent) {
        RBNode other;

        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right == null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node != null) {
            setBlack(node);
        }
    }

    /**
     * 为了让输出更有结构感，在元素前拼接一些空格，对齐
     *
     * @param size
     * @param index
     * @return
     */
    public String makeSpace2(int size, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1 << (size - index); i++) {
            builder.append("  ");
        }
        return builder.toString();
    }

    /**
     * 打印出整棵树的层级结构，为了方便跟踪旋转的过程
     *
     */
    public void printTreeLevel() {

        System.out.println("开始输出树的层级结构");
        ConcurrentHashMap<Integer, List<RBNode>> map = showTree();
        int size = map.size();

        for (int i = 0; i < map.size(); i++) {
            System.out.println();
            for (int j = 0; j < map.get(i).size(); j++) {
                System.out.print(makeSpace2(size, i)
                        + (map.get(i).get(j).key == null ? " "
                                : (map.get(i).get(j).key) + (map.get(i).get(j).color ? "(黑)" : "(红)"))
                        + makeSpace2(size, i));

            }
            System.out.println();
        }
        System.out.println("结束输出树的层级结构");
    }

    public void printTreeLevel2() {

        System.out.println("开始输出树的Graphviz结构");
        ConcurrentHashMap<Integer, List<RBNode>> map = showTree();
        // int size = map.size();
        System.out.println("digraph kunghsu{");
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {

                if (map.get(i).get(j).key != null) {
                    System.out.println(map.get(i).get(j).key + " [color="
                            + (map.get(i).get(j).color == RBNode.RED ? "red" : "black")
                            + " style=filled fontcolor=white] ");
                }
            }
        }

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                // String content = "";

                if (map.get(i).get(j).key != null) {
                    if (map.get(i).get(j).left != null) {
                        System.out.println(map.get(i).get(j).key + "->" + map.get(i).get(j).left.key + "[label=left]");
                    }
                    if (map.get(i).get(j).right != null) {
                        System.out
                                .println(map.get(i).get(j).key + "->" + map.get(i).get(j).right.key + "[label=right]");
                    }
                }
            }
        }
        System.out.println("}");

        System.out.println("结束输出树的Graphviz结构");
    }

    public ConcurrentHashMap<Integer, List<RBNode>> showTree() {

        ConcurrentHashMap<Integer, List<RBNode>> map = new ConcurrentHashMap<>();
        showTree(root, 0, map);
        return map;
    }

    public void showTree(RBNode root, int count, ConcurrentHashMap<Integer, List<RBNode>> map) {

        if (map.get(count) == null) {
            map.put(count, new ArrayList<>());
        }
        map.get(count).add(root);

        if (root.left != null) {
            showTree(root.left, count + 1, map);
        } else {
            // 假如为空，也添加到map中，因为我要做格式化控制，空的，我也要知道它这个位置是空的
            if (map.get(count + 1) == null) {
                map.put(count + 1, new ArrayList<>());
            }
            map.get(count + 1).add(new RBNode(false, null, null, null, null, null));
        }
        if (root.right != null) {
            showTree(root.right, count + 1, map);
        } else {
            if (map.get(count + 1) == null) {
                map.put(count + 1, new ArrayList<>());
            }
            map.get(count + 1).add(new RBNode(false, null, null, null, null, null));
        }
    }

    // 前序遍历
    public void preOrder(RBNode node) {
        if (node != null) {

            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);

        }

    }

    public void preOrder() {
        preOrder(this.root);

    }

    // 中序遍历
    public void inOrder(RBNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);

        }

    }

    public void inOrder() {
        inOrder(this.root);

    }

    // 后序遍历
    public void postOrder(RBNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");

        }

    }

    public void postOrder() {
        postOrder(this.root);

    }

    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        int[] array = { 10, 40, 30, 60, 90, 70, 20, 50, 80 };
        for (int i = 0; i < array.length; i++) {
            rbTree.insert(array[i], i);
        }

        rbTree.printTreeLevel2();
        rbTree.preOrder();
        System.out.println();
        rbTree.inOrder();
        System.out.println();
        rbTree.postOrder();
        System.out.println();
        System.out.println(rbTree.get(10));
    }

}