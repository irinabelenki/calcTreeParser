package calcTreeParser;

class Leaf extends Node {
	public Leaf(int number) {
		this.number = number;
	}

	int evaluate() {
		return number;
	}
}