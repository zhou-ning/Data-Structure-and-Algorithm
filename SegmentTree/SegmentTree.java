package SegmentTree;

/**
 * 线段树，叶子节点为数的值，非叶子节点为叶子节点的累加 SegmentTree
 */
public class SegmentTree {

    /*** tree为构建后的数组 */
    int[] tree;
    /** nums为原数组 */
    int[] nums;
    /*** n为数组长度 */
    int n;

    /****
     * 构造函数，通过一个数组进行构造
     * 
     * @param nums
     */
    public SegmentTree(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    /***
     * 建树，使用迭代的方式
     * 
     * @param nums
     */
    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            // tree[i] = tree[i * 2] + tree[i * 2 + 1];
            Pushup(i);
    }

    /***
     * 递归法建树
     * 
     * @param k tree数组下标
     * @param l 左子树下标
     * @param r 右子树下标
     */
    private void buildTree(int k, int l, int r) {
        if (l == r) {
            tree[k] = nums[l];
        } else {
            // m则为中间点，左儿子的结点区间为[l,m],右儿子的结点区间为[m+1,r]
            int m = l + ((r - l) >> 1);
            // l = fa*2 （左子树下标为父亲下标的两倍）
            buildTree(k << 1, l, m); // 递归构造左儿子结点
            // r = fa*2+1（右子树下标为父亲下标的两倍+1）
            buildTree(k << 1 | 1, m + 1, r); // 递归构造右儿子结点
            Pushup(k);    //更新父节点
        }
    }

    void Pushup(int k) {
        // 更新函数，这里是实现区间和 ，同理可以变成，最小值，最小值等
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
    }

    /****
     * 更新某个位置的值
     * 
     * @param pos
     * @param val
     */
    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    /***
     * 统计 l到r之间的值的和
     * 
     * @param l
     * @param r
     * @return
     */
    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

}