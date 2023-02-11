## miroris遍历 MorrisTraversal
遍历二叉树的时候，时间复杂度O(n),空间复杂度脱化到h(1)
流程：
当前节点cur,一开始来到整棵树的头指针
1. cur无左树，cur = cur.right
2. cur有左树，找到左子树的最右节点，记为mostright
    2.1 mostright的右指针指向问null,mostright.right = cur,cur = cur.left
    2.2 mostright的右指针指向cur,mostright.right = null,cur = cur.right;
cur = null，流程停止
**任何右左孩子的树都去来到两次**
---
题目：
1. 使用Morris求出二叉树的最小高度  MinHeight