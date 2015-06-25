package calcTreeParser;

public class Expression extends Node {
	public enum OPERATION { ADD, 
							SUBTRACT, 
							MULTIPLY, 
							DIVIDE, 
							ILLEGAL_OPERATION	
						  };
	
	private OPERATION operation;
	
	public Expression(OPERATION operation) {
		this.operation = operation;
	}
	
	public OPERATION getOperation() {
		return operation;
	}
	
	public void setOperation(OPERATION operation) {
		this.operation = operation;
	}
	
	int evaluate() {
		if (operation == OPERATION.ADD) {
			return left.evaluate() + right.evaluate();
		}
		else if (operation == OPERATION.SUBTRACT) {
			return left.evaluate() - right.evaluate();
		}
		else if (operation == OPERATION.MULTIPLY) {
			return left.evaluate() * right.evaluate();
		}
		else if (operation == OPERATION.DIVIDE) {
			return left.evaluate() / right.evaluate();
		}
		else return 0;  
	}
}

