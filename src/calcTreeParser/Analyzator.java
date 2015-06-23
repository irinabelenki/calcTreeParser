package calcTreeParser;

public class Analyzator {

	public enum STATE { WAITING_FOR_LEFT_OPERAND,
						WAITING_FOR_RIGHT_OPERAND,
						WAITING_FOR_OPERATION 
					  };
	
	public static Tree buildTree(Parser parser) throws Exception {
		Tree tree = new Tree();
		STATE state = STATE.WAITING_FOR_LEFT_OPERAND;
		
		while (parser.hasMoreTokens()) {
			String token = parser.nextToken();
			try {
				int operand = Integer.parseInt(token);
				if (state == STATE.WAITING_FOR_LEFT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					tree.addLeftOperand(operand);
					continue;
				}
				else if (state == STATE.WAITING_FOR_RIGHT_OPERAND) {
					state = STATE.WAITING_FOR_OPERATION;
					tree.addRightOperand(operand);
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
				tree.addOperation(operation);
				continue;
			}
			else {
				throw new Exception("Illegal string input");
			}
			
		}
		return tree;
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
	
}
