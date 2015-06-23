package calcTreeParser;

import java.util.StringTokenizer;

public class Parser {

	StringTokenizer elementsList = null;
	
	public Parser(String line) {
		elementsList = new StringTokenizer(line);
	}
	
	public boolean hasMoreTokens() {
		return elementsList.hasMoreTokens();
	}
	
	public String nextToken() {
		return elementsList.nextToken();
	}
}
