/*
Get the random node in binary search tree
*/

class Tree {
	TreeNode root = null;
	
	public TreeNode getRandomNode() {
		if (root == null) return null;
		Random random =  new Random();
		int i = random.nextInt(root.size());
		return root.getIthNode(i);
	}
	
	public void insert(int d) {
		if (root == null) {
			root = new TreeNode(d);
		} else {
			root.insertInOrder(d);
		}
	}
}

class TreeNode {
	private int data;
	public TreeNode left;
	public TreeNode right;
	private int size = 0; // the size of tree whose root is current tree node
	
	public TreeNode(int d) {
		data = d;
		size = 1;
	}
	
	public int size() {
		return size;
	}
	
	public int data() {
		return data;
	}
	
	public void insertInOrder(int d) {
		if (d <= data) {
			if (left == null) {
				left = new TreeNode(d);
			} else {
				left.insertInOrder(d);
			}
		} else {
			if (right == null) {
				right = new TreeNode(d);
			} else {
				right.insertInOrder(d);
			}
		}
		size++;
	}
	
	public TreeNode find(int d) {
		if (data == d) {
			return this;
		} else if (d < data) {
			return left != null ? left.find(d) : null; 
		} else {
			return right != null ? right.find(d) : null;
		}
	}
	
	public TreeNode getIthNode(int i) {
		int leftSize = left == null ? 0 : left.size();
		if (i < leftSize) {
			return left.getIthNode(i);
		} else if (i == leftSize){
			return this;
		} else {
			// Skipping leftSize + 1 nodes on the left subtree plus current node
			return right.getIthNode(i - (leftSize + 1));
		}
	}
}
