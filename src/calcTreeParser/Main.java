package calcTreeParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter line in regular format");
		try {
			String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
			Parser parser = new Parser(line);
			Tree tree = Analyzator.buildTree(parser);
			int result = tree.evaluate();
			System.out.println("Result: " + result);
		} 
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
