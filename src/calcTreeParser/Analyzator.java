package calcTreeParser;

public class Analyzator {

	public enum STATE { WAITING_FOR_LEFT_OPERAND,
						WAITING_FOR_RIGHT_OPERAND,
						WAITING_FOR_OPERATION 
					  };
	
	private static void buildTree(Parser parser) throws Exception {
		//Tree tree = new Tree();
		STATE state = STATE.WAITING_FOR_LEFT_OPERAND;
		
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
					addRightOperand(operand);
					continue;
				}
				else {
					throw new Exception("Illegal string input");
				}
			} 
			catch (NumberFormatException nfe) {
				System.out.println("Not an operand");
			}
			
			Expression.OPERATION operation = getOperation(token);
			if(state == STATE.WAITING_FOR_OPERATION) {
				state = STATE.WAITING_FOR_RIGHT_OPERAND;
				addOperation(operation);
				continue;
			}
			else {
				throw new Exception("Illegal string input");
			}
			
		}
		//return tree;
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
	
	static Node root = null;
	static Node left = null;
	
	public static void addLeftOperand(int operand) {
		left = new Leaf(operand);
	}
	
	public static void addRightOperand(int operand) {
		root.right = new Leaf(operand);
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
				
			}
		}
	}
	
	public static int evaluate(Parser parser) throws Exception {
		buildTree(parser);
		return root.evaluate();
	}
}
