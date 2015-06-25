package calcTreeParser;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Parser {
	private List<String> tokensList = new ArrayList<String>();
	private ListIterator<String> tokensListIt = null;
		
	public Parser(String line) {
		parse(line.trim());
	}
	
	private void parse(String line) {
		char[] array = line.toCharArray();
		
		char currChar = ' ';
		char prevChar = ' ';
		StringBuilder tokenBuilder = null;
		
		for (char ch : array) {
		    currChar = ch;
		    if(currChar == '(' || currChar == ')') {
		    	if(tokenBuilder != null) {
		    		tokensList.add(tokenBuilder.toString());
		    	}
		    	tokenBuilder = new StringBuilder();
		    	tokenBuilder.append(ch);
		    	tokensList.add(tokenBuilder.toString());
		    	tokenBuilder = null;
		    }
		    else if ((Character.isDigit(currChar) && !Character.isDigit(prevChar)) ||
		    	(!Character.isDigit(currChar) && currChar != ' ' && Character.isDigit(prevChar)) ||
		    	prevChar == ' ') {
		    	if(tokenBuilder != null) {
		    		tokensList.add(tokenBuilder.toString());
		    	}
		    	tokenBuilder = new StringBuilder();
		    	tokenBuilder.append(ch);
		    }
		    else if ((Character.isDigit(currChar) && Character.isDigit(prevChar)) ||
		    		 (!Character.isDigit(currChar) && currChar != ' ' && !Character.isDigit(prevChar))) {
		    	if(tokenBuilder == null) {
		    		tokenBuilder = new StringBuilder();
		    		tokenBuilder.append(ch);
		    	}
		    }
		    else if (currChar == ' ') {
		    	tokensList.add(tokenBuilder.toString());
		    	tokenBuilder = null;
		    }
		    
		    prevChar = currChar;
		}//for
		if(tokenBuilder != null) {
    		tokensList.add(tokenBuilder.toString());
    	}
		tokensListIt = tokensList.listIterator();
	}
	
	public boolean hasMoreTokens() {
		return tokensListIt.hasNext();
	}
	
	public String nextToken() {
		return tokensListIt.next();
	}
	
}
