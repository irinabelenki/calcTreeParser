package calcTreeParser;

import calcTreeParser.Expression.OPERATION;

public class Analyzator {

	public enum STATE { WAITING_FOR_LEFT_OPERAND,
						WAITING_FOR_RIGHT_OPERAND,
						WAITING_FOR_OPERATION 
					  };
	
	private static void buildTree(Parser parser) throws Exception {
		//Tree tree = new Tree();
		STATE state = STATE.WAITING_FOR_LEFT_OPERAND;
		Expression.OPERATION lastOperation = OPERATION.ILLEGAL_OPERATION;
		
		while (parser.hasMoreTokens()) {
			String token = parser.nextToken();
			try {
				int operand = Integer.parseInt(token);
				if (state == STATE.WAITING_FOR_LEFT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					addLeftOperand(operand);
					continue;
				}
				else if (state == STATE.WAITING_FOR_RIGHT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					addRightOperand(operand, lastOperation);
					continue;
				}
				else {
					throw new Exception("Illegal string input");
				}
			} 
			catch (NumberFormatException nfe) {
				System.out.println("Not an operand");
			}
			
			lastOperation = getOperation(token);
			if(state == STATE.WAITING_FOR_OPERATION) {
				state = STATE.WAITING_FOR_RIGHT_OPERAND;
				addOperation(lastOperation);
				continue;
			}
			else {
				throw new Exception("Illegal string input");
			}
			
		}
	}
	
	private static Expression.OPERATION getOperation(String operation) {
		if(operation.equals("+")) {
			return Expression.OPERATION.ADD;
		}
		else if(operation.equals("-")) {
			return Expression.OPERATION.SUBTRACT;
		}
		else if(operation.equals("*")) {
			return Expression.OPERATION.MULTIPLY;
		}
		else if(operation.equals("/")) {
			return Expression.OPERATION.DIVIDE;
		}
		return Expression.OPERATION.ILLEGAL_OPERATION;
	}
	
	static Expression root = null;
	static Leaf left = null;
	static Leaf right = null;
	
	public static void addLeftOperand(int operand) {
		left = new Leaf(operand);
	}
	
	public static void addRightOperand(int operand, Expression.OPERATION lastOperation) {
		if (lastOperation == Expression.OPERATION.ADD ||
			lastOperation == Expression.OPERATION.SUBTRACT) {
			right = new Leaf(operand);
			root.right = right;
		}
		else if (lastOperation == Expression.OPERATION.MULTIPLY ||
				lastOperation == Expression.OPERATION.DIVIDE) {
			Node mostRight = root;
			while (mostRight.right != null) {
				mostRight = mostRight.right;
			}
			mostRight.right = new Leaf(operand);
		}
	}
	
	public static void addOperation(Expression.OPERATION operation) {
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
				Expression newRight = new Expression(operation);
				newRight.left = root.right;
				root.right = newRight;
			}
		}
	}
	
	public static int evaluate(Parser parser) throws Exception {
		buildTree(parser);
		return root.evaluate();
	}
}
