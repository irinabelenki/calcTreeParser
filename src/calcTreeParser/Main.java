package calcTreeParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter line in regular format");
		
		try {
			String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
			int result = Analyzator.buildTree(new Parser(line));
			System.out.println("Result: " + result);
		} 
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
