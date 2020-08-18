# 并查集
参考：[https://blog.csdn.net/liujian20150808/article/details/50848646](https://blog.csdn.net/liujian20150808/article/details/50848646)

并查集对于图的连通性问题有着比较好的解决方法。
需要注意的是并查集父节点的指向，有的是指向一个负数(例如并查集.pdf中所说)，有的是指向自己(0->0,说明0是根节点)，这里采用的是指向自己为根节点