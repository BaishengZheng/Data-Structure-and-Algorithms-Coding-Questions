/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node 
in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can 
be a descendant of itself according to the LCA definition.
*/

// This solution could handle even if p/q is not in the tree.
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Result res = helper(root, p, q);
        if (res.isAncester) {
            return res.node;
        }
        return null;
    }
    
    public Result helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new Result(null, false);
        
        if (root == p && root == q) {
            return new Result(root, true);
        }
        
        Result rx = helper(root.left, p, q);
        if (rx.isAncester) {
            return rx;
        }
        
        Result ry = helper(root.right, p, q);
        if (ry.isAncester) {
            return ry;
        }
        
        if (rx.node != null && ry.node != null) {
            return new Result(root, true);
        } else if (root == p || root == q) {
            boolean isAnc = rx.node != null || ry.node != null;
            return new Result(root, isAnc);
        } else {
            return new Result(rx.node != null ? rx.node : ry.node, false);
        }
    }
}

class Result {
    public TreeNode node;
    public boolean isAncester;
    public Result(TreeNode n, boolean isAnc) {
        node = n;
        isAncester = isAnc;
    }
}
