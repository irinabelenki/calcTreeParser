package calcTreeParser;

public class Leaf extends Node {
	private int number;
	
	public Leaf(int number) {
		this.number = number;
	}

	int evaluate() {
		return number;
	}
}