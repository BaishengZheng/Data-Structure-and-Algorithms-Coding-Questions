/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in 
a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another 
computer environment.
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string 
can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(node == null ? "null" : node.val);
            if (node != null) {
                q.add(node.left);
                q.add(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> nodes = deserializeHelper(data);
        Queue<TreeNode> kids = queueClone(nodes); // Copy TreeNode references
        
        TreeNode root = kids.size() > 0 ? kids.poll() : null;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            if (node != null) {
                if (!kids.isEmpty()) {
                    node.left = kids.poll();
                }
                if (!kids.isEmpty()) {
                    node.right = kids.poll();
                } 
            }
        }
        return root;
    }
    
    private Queue<TreeNode> queueClone(Queue<TreeNode> q) {
        // Just clone object references
        Queue<TreeNode> res = new LinkedList<TreeNode>();
        int size = q.size();
        while (size > 0) {
            TreeNode node = q.poll();
            res.add(node);
            q.add(node);
            size--;
        }
        return res;
    }
    
    private Queue<TreeNode> deserializeHelper(String data) {
        if (data == null) {
            return (Queue<TreeNode>) new LinkedList<TreeNode>();
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        String[] strs = data.split(",");
        for (String str : strs) {
            if (str.equals("null")) {
                q.add(null);
            } else {
                int val = Integer.parseInt(str);
                q.add(new TreeNode(val));
            }
        }
        return q;
    }
}
