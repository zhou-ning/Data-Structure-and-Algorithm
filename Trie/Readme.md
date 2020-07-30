# 字典树
转载自：
* https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
* https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/

## 介绍
Trie (发音为 "try") 或前缀树是一种树数据结构，用于检索字符串数据集中的键。这一高效的数据结构有多种应用：

1. 自动补全

![图 1. 谷歌的搜索建议](https://pic.leetcode-cn.com/963cd3fc83e9618aba9cb78365c8a5bf6b7cef8967da0d204dede7844f6738f2-file_1562596867150)

2. 拼写检查
   
![图2. 文字处理软件中的拼写检查](https://pic.leetcode-cn.com/4d18efbdd4d51ae3935b42cd59b11d66fb62f1586b9638f9499d2a18fa8919d0-image.png)

**问题:**既然还有其他的数据结构，如平衡树和哈希表，使我们能够在字符串数据集中搜索单词。为什么我们还需要 Trie 树呢？
尽管哈希表可以在 O(1)O(1) 时间内寻找键值，却无法高效的完成以下操作：

找到具有同一前缀的全部键值。
按词典序枚举字符串的数据集。
Trie 树优于哈希表的另一个理由是，随着哈希表大小增加，会出现大量的冲突，时间复杂度可能增加到 $O(n)$，其中 $n$ 是插入的键的数量。与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。此时 Trie 树只需要 $O(m)$ 的时间复杂度，其中 $m$ 为键长。而在平衡树中查找键值需要 $O(mlogn)$ 时间复杂度。

## 总结
通过以上介绍和代码实现我们可以总结出 Trie 的几点性质：

1. Trie 的形状和单词的插入或删除顺序无关，也就是说对于任意给定的一组单词，Trie 的形状都是唯一的。

2. 查找或插入一个长度为 L 的单词，访问 next 数组的次数最多为 L+1，和 Trie 中包含多少个单词无关。

3. Trie 的每个结点中都保留着一个字母表，这是很耗费空间的。如果 Trie 的高度为 n，字母表的大小为 m，最坏的情况是 Trie 中还不存在前缀相同的单词，那空间复杂度就为 O(m^n)。

最后，关于 Trie 希望你能记住 8 个字：一次建树，多次查询。

