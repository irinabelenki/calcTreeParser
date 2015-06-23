package calcTreeParser;

public class Tree {
	Node root = null;
	Node left = null;
	
	public void addLeftOperand(int operand) {
		left = new Leaf(operand);
	}
	
	public void addRightOperand(int operand) {
		root.right = new Leaf(operand);
	}
	
	public void addOperation(Expression.OPERATION operation) {
		if (root == null) {
			root = new Expression(operation);
			if (left != null) {
				root.left = left;
			}
		}
		else {
			if (operation == Expression.OPERATION.ADD ||
				operation == Expression.OPERATION.SUBTRACT) {				
				Expression newRoot = new Expression(operation);
				newRoot.left = root;
				root = newRoot;
			}
			else if (operation == Expression.OPERATION.MULTIPLY ||
					operation == Expression.OPERATION.DIVIDE) {
				
			}
		}
	}
	
	public int evaluate() {
		return root.evaluate();
	}

}
