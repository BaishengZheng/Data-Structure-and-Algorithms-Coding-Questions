/*Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.*/

// Recursive solution
public class solution {
    public treenode upsidedownbinarytree(treenode root) {
      	return dfstoleft(root, null);
    }
    
    private treenode dfstoleft(treenode p, treenode parent) {
      	if (p == null) return parent;
      	treenode root = dfstoleft(p.left, p);
      	p.left = (parent == null) ? parent : parent.right;
      	p.right = parent;
      	return root;
    }
}

// Iterative solution
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
       	TreeNode node = root, parent = null, parentRight = null;
       	
       	while (node != null) {
       	    // Be careful of the order of updating node references.
       	    TreeNode nodeLeft = node.left;
       	    node.left = parentRight;
       	    parentRight = node.right;
       	    node.right = parent;
       	    parent = node;
       	    node = nodeLeft;
       	}
       	return parent;
    }
}