package calcTreeParser;

public class Tree {
	Element root = null;
	Element left = null;
	
	public void addLeftOperand(int operand) {
		left = new Leaf(operand);
	}
	
	public void addRightOperand(int operand) {
		root.right = new Leaf(operand);
	}
	
	public void addOperation(Analyzator.OPERATION operation) {
		if (root == null) {
			root = new Node(operation.getValue());
			if (left != null) {
				root.left = left;
			}
		}
		else {
			Node newRoot = new Node(operation.getValue());
			newRoot.left = root;
			root = newRoot;
		}
	}
	
	public int evaluate() {
		return root.evaluate();
	}

}
