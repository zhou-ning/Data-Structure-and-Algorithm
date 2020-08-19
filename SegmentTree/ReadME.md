# 线段树
参考：
* https://leetcode-cn.com/problems/range-sum-query-mutable/solution/qu-yu-he-jian-suo-shu-zu-ke-xiu-gai-by-leetcode/
* https://www.cnblogs.com/xenny/p/9801703.html
* https://www.cnblogs.com/jason2003/p/9676729.html
* https://www.cnblogs.com/FengZeng666/p/11446827.html
* https://www.bilibili.com/video/av47331849/

线段树，对于区间问题是一个比较好的解决方法
个人感觉需要注意的点：

1.tree数组的大小

2.下标的起点：从0开始，还是从1开始

3.父亲节点以及孩子节点对应的值

目前实现了建树，查询，还还有修改
修改是普通的修改，还可以改成懒汉式修改，不过目前先不写