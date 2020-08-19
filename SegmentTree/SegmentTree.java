package SegmentTree;

import java.util.Arrays;

/**
 * 线段树，叶子节点为数的值，非叶子节点为叶子节点的累加 SegmentTree
 */
public class SegmentTree {

    // 线段树节点
    class Node {
        int l, r;
        int data;

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return String.valueOf(data);
        }
    };

    /*** tree为构建后的数组 */
    Node[] tree;
    /** nums为原数组 */
    int[] nums;
    /*** n为数组长度 */
    int n;

    /****
     * 构造函数，通过一个数组进行构造
     * 
     * @param nums
     */
    public SegmentTree(int[] arr) {
        if (arr.length > 0) {
            n = arr.length;
            // 大小为n+1，下标从1开始
            // 下标从1开始，左节点为 fa*2，右节点为 fa*2+1，方便计算
            // 下标从1开始，0代表区间长度为0，也具有了实际意义
            nums = new int[n + 1];
            // 需要四倍大小
            tree = new Node[n << 2];
            for (int i = 0; i < tree.length; i++) {
                tree[i] = new Node();
            }
            for (int i = 0; i < n; i++) {
                nums[i + 1] = arr[i];
            }
            // 迭代建树
            build(1, 1, n);
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(tree));
    }

    /***
     * 递归法建树
     * 
     * @param k tree数组下标
     * @param l 左子树下标
     * @param r 右子树下标
     */
    private void build(int k, int l, int r) {
        tree[k].l = l;
        tree[k].r = r;
        if (l == r) {
            tree[k].data = nums[l];
        } else {
            // m则为中间点，左儿子的结点区间为[l,m],右儿子的结点区间为[m+1,r]
            int m = l + ((r - l) >> 1);
            // l = fa*2 （左子树下标为父亲下标的两倍）
            build(k << 1, l, m); // 递归构造左儿子结点
            // r = fa*2+1（右子树下标为父亲下标的两倍+1）
            build(k << 1 | 1, m + 1, r); // 递归构造右儿子结点
            // 进行 &运算
            // tree[k].data = tree[k << 1].data + tree[k << 1 | 1].data;
            Pushup(k);
        }
    }

    void Pushup(int k) {
        // 更新函数，这里是实现区间和 ，同理可以变成，最小值，最小值等
        tree[k].data = tree[k << 1].data + tree[k << 1 | 1].data;
    }

    /****
     * 更新某个位置的值
     * 
     * @param pos
     * @param val
     */
    public void update(int pos, int val) {
        update(1, val, 1, n, pos);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(tree));
    }

    /***
     * 递归进行修改
     * 
     * @param k   tree的下标
     * @param val 需要修改的值
     * @param l   左边界
     * @param r   右边界
     * @param pos nums的下标
     */
    void update(int k, int val, int l, int r, int pos) {
        if (l == r) {
            tree[k].data = val;
            nums[pos] = val;
        } else {
            int m = l + ((r - l) >> 1);
            if (pos <= m) {
                // 更新左半边pos
                update(k << 1, val, l, m, pos);
            } else {
                update(k << 1 | 1, val, m + 1, r, pos);
            }
            Pushup(k);
        }

    }

    // 查询,u为需要查询的下标,l,r为范围
    int query(int k, int l, int r) {
        if (l <= tree[k].l && r >= tree[k].r)
            return tree[k].data;
        int mid = tree[k].l + tree[k].r >> 1;
        int ans = (1 << 30) - 1;
        if (l <= mid)
            ans = ans + (query(k << 1, l, r));
        if (r > mid)
            ans = ans + (query(k << 1 | 1, l, r));
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9 };
        SegmentTree tree = new SegmentTree(arr);
        // 查询 位置1：9
        System.out.println(tree.query(1, 1, 5));
        tree.update(3, 4);
        System.out.println(tree.query(1, 1, 5));

    }

}