package UFSet;

/**
 * UFSet
 */
public class UFSet {

    int[] parent;
    int size;

    public UFSet(int size) {
        parent = new int[size];
        this.size = size;
        // 指向自己的为父节点
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int Find(int x) {
        // 寻找根
        int r = x;
        while (parent[r] != r) {
            r = parent[r];
        }
        // 路径压缩,将路径上的点都指向r，方便查询
        int i = x, j;
        while (i != r) {
            j = parent[i];
            // 将parent[i]指向r
            parent[i] = r;
            i = j;
        }
        return r;
    }

    public void join(int root1, int root2) {
        int fx = Find(root1);
        int fy = Find(root2);
        // 分支合并
        if (fx != fy) {
            parent[fx] = fy;
        }
    }

    public static void main(String[] args) {
        UFSet set = new UFSet(6);
        set.join(0, 1);
        set.join(2, 3);
        set.join(4, 5);
        int a, b;
        a = set.Find(1);
        b = set.Find(5);
        if (a != b) {
            System.out.println("1和5不相通");
        } else {
            System.out.println("1和5相通");
        }
    }
}