package calcTreeParser;

import calcTreeParser.Expression.OPERATION;

public class Analyzator {

	public enum STATE {
		WAITING_FOR_LEFT_OPERAND, WAITING_FOR_RIGHT_OPERAND, WAITING_FOR_OPERATION, BEGIN_ANALYZATOR, END_ANALYZATOR
	};

	public static int buildTree(Parser parser) throws Exception {

		STATE state = STATE.WAITING_FOR_LEFT_OPERAND;
		Expression.OPERATION lastOperation = OPERATION.ILLEGAL_OPERATION;
		Expression root = new Expression(OPERATION.ILLEGAL_OPERATION);
		Leaf left = new Leaf();

		while (parser.hasMoreTokens()) {
			String token = parser.nextToken();
			try {
				int operand = Integer.parseInt(token);
				if (state == STATE.WAITING_FOR_LEFT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					addLeftOperand(operand, left);
					continue;
				} else if (state == STATE.WAITING_FOR_RIGHT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					root = addRightOperand(operand, lastOperation, root);
					continue;
				} else {
					throw new Exception("Illegal string input");
				}
			} catch (NumberFormatException nfe) {
				// System.out.println("Not an operand");
			}

			if (token.equals("(")) {
				int result = buildTree(parser);
				System.out.println("buildTree returns: " + result);
				if (state == STATE.WAITING_FOR_LEFT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					addLeftOperand(result, left);
					continue;
				} else if (state == STATE.WAITING_FOR_RIGHT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					root = addRightOperand(result, lastOperation, root);
					continue;
				}
			} else if (token.equals(")")) {
				break;
			}

			lastOperation = getOperation(token);
			if (state == STATE.WAITING_FOR_OPERATION) {
				state = STATE.WAITING_FOR_RIGHT_OPERAND;
				root = addOperation(lastOperation, root, left);
				continue;
			} else {
				throw new Exception("Illegal string input");
			}
		}// while
		if(root.getLeft() == null) {
			return left.evaluate();
		}
		return root.evaluate();
	}

	private static Expression.OPERATION getOperation(String operation) {
		if (operation.equals("+")) {
			return Expression.OPERATION.ADD;
		} else if (operation.equals("-")) {
			return Expression.OPERATION.SUBTRACT;
		} else if (operation.equals("*")) {
			return Expression.OPERATION.MULTIPLY;
		} else if (operation.equals("/")) {
			return Expression.OPERATION.DIVIDE;
		}
		return Expression.OPERATION.ILLEGAL_OPERATION;
	}

	private static void addLeftOperand(int operand, Leaf left) {
		left.setNumber(operand);
	}

	private static Expression addRightOperand(int operand,
			Expression.OPERATION lastOperation, Expression root) {
		if (lastOperation == Expression.OPERATION.ADD
				|| lastOperation == Expression.OPERATION.SUBTRACT) {
			Leaf right = new Leaf(operand);
			root.setRight(right);
		} else if (lastOperation == Expression.OPERATION.MULTIPLY
				|| lastOperation == Expression.OPERATION.DIVIDE) {
			Node mostRight = root;
			while (mostRight.getRight() != null) {
				mostRight = mostRight.getRight();
			}
			mostRight.setRight(new Leaf(operand));
		}
		return root;
	}

	private static Expression addOperation(Expression.OPERATION operation,
			Expression root, Leaf left) {
		if (root.getOperation() == OPERATION.ILLEGAL_OPERATION) {
			root.setOperation(operation);
			if (left != null) {
				root.setLeft(left);
			}
		} else {
			if (operation == Expression.OPERATION.ADD
					|| operation == Expression.OPERATION.SUBTRACT) {
				Expression newRoot = new Expression(operation);
				newRoot.setLeft(root);
				root = newRoot;
			} else if (operation == Expression.OPERATION.MULTIPLY
					|| operation == Expression.OPERATION.DIVIDE) {
				Expression newRight = new Expression(operation);
				newRight.setLeft(root.getRight());
				root.setRight(newRight);
			}
		}
		return root;
	}

}
