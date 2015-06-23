package calcTreeParser;

public class Analyzator {

	public enum OPERATION { 
		ADD(0), SUBTRACT(1), MULTIPLY(2), DIVIDE(3), ILLEGAL_OPERATION(4);
		
		private final int value;
	    
		private OPERATION(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	};
	
	public enum STATE { WAITING_FOR_LEFT_OPERAND,
						WAITING_FOR_RIGHT_OPERAND,
						WAITING_FOR_OPERATION };
	
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
			
			OPERATION operation = getOperation(token);
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
	
	private static OPERATION getOperation(String operation) {
		if(operation.equals("+")) {
			return OPERATION.ADD;
		}
		else if(operation.equals("-")) {
			return OPERATION.SUBTRACT;
		}
		else if(operation.equals("*")) {
			return OPERATION.MULTIPLY;
		}
		else if(operation.equals("/")) {
			return OPERATION.DIVIDE;
		}
		return OPERATION.ILLEGAL_OPERATION;
	}
}
