package calcTreeParser;

public class Leaf extends Node {
	private int number;
	private boolean initialized = false;
	
	public Leaf() {
		
	}
	
	public Leaf(int number) {
		this.number = number;
		initialized = true;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void setNumber(int number) {
		this.number = number;
		initialized = true;
	}

	int evaluate() {
		return number;
	}
}