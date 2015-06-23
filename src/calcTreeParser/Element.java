package calcTreeParser;

public abstract class Element {
	int number = 0;
	Element left = null;
	Element right = null;
	int evaluate() {
		return 0;
	}
}

class Leaf extends Element {
	public Leaf(int number) {
		this.number = number;
	}

	int evaluate() {
		return number;
	}
} 

class Node extends Element {
	public Node(int number) {
		this.number = number;
	}
	
	int evaluate() {
		if (number == Analyzator.OPERATION.ADD.getValue()) {
			return left.evaluate() + right.evaluate();
		}
		else if (number == Analyzator.OPERATION.SUBTRACT.getValue()) {
			return left.evaluate() - right.evaluate();
		}
		else return 0;  
	}
}


