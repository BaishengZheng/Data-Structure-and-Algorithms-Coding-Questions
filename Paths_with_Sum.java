/*
Given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm to count the
number of paths that sum to a given value. The path does not need to start or end at the root or a leaf, but it must go downwards 
(traveling only from parent nodes to child nodes).
*/

public class PathsWithSum {
	
	public int countPathsWithSum(TreeNode root, int targetSum) {
		return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
	}
	
	public int countPathsWithSum(TreeNode node, int targetSum, int sum, HashMap<Integer, Integer> pathSums) {
		if (node == null) return 0;
		
		sum += node.data;
		int partialSum = sum - targetSum;
		int totalPaths = pathSums.getOrDefault(partialSum, 0);
		
		// If sum equals to targetSum, there is a path starts from root fulfill requirement
		if (sum == targetSum) {
			totalPaths++;
		}
		
		incrementHashMap(pathSums, sum, 1);
		totalPaths += countPathsWithSum(node.left, targetSum, sum, pathSums);
		totalPaths += countPathsWithSum(node.right, targetSum, sum, pathSums);
		incrementHashMap(pathSums, sum, -1);
		return totalPaths;
	}
	
	public void incrementHashMap(HashMap<Integer, Integer> pathSums, int key, int delta) {
		int newCount = pathSums.getOrDefault(key, 0) + delta;
		if (newCount == 0) { // Remove when zero to reduce space usage
			pathSums.remove(key);
		} else {
			pathSums.put(key, newCount);
		}
	}
}

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	public TreeNode(int d) {
		data = d;
	}
}
