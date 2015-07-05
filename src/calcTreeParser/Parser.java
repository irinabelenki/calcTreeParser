package calcTreeParser;

import java.util.ArrayList;

// 1. Why not make it implements Iterable - JH?
// Whitespace may include tabulation, not only space; see Character.isWhitespace

import java.util.List;
import java.util.ListIterator;

public class Parser {
	private List<String> tokensList = new ArrayList<String>();
	private ListIterator<String> tokensListIt = null;
	private char DELIMITER = ' ';

	public Parser(String line) {
		parse(line.trim());
	}

	enum TOKEN_TYPE {
		OPERATION, DIGIT, UNKNOWN
	};

	private void parse(String line) {
		char[] array = line.toCharArray();

		char currChar;
		StringBuilder tokenBuilder = null;
		TOKEN_TYPE tokenType = TOKEN_TYPE.UNKNOWN;

		for (char ch : array) {
			currChar = ch;
			if (isOperation(currChar)) {
				if (tokenBuilder != null) {
					tokensList.add(tokenBuilder.toString());
				}
				tokenBuilder = new StringBuilder();
				tokenBuilder.append(ch);
				tokenType = TOKEN_TYPE.OPERATION;
				tokensList.add(tokenBuilder.toString());
				tokenBuilder = null;
			} else if (Character.isDigit(currChar)) {
				if (tokenBuilder != null) {
					if (tokenType == TOKEN_TYPE.DIGIT) {
						tokenBuilder.append(ch);
					} else {
						tokensList.add(tokenBuilder.toString());
						tokenBuilder = null;
					}
				} else {
					tokenBuilder = new StringBuilder();
					tokenBuilder.append(ch);
					tokenType = TOKEN_TYPE.DIGIT;
				}
			} else if (currChar == DELIMITER) {
				if (tokenBuilder != null) {
					tokensList.add(tokenBuilder.toString());
					tokenBuilder = null;
				}
			} else {
				if (tokenBuilder != null) {
					if (tokenType == TOKEN_TYPE.UNKNOWN) {
						tokenBuilder.append(ch);
					} else {
						tokensList.add(tokenBuilder.toString());
						tokenBuilder = null;
					}
				} else {
					tokenBuilder = new StringBuilder();
					tokenBuilder.append(ch);
					tokenType = TOKEN_TYPE.UNKNOWN;
				}
			}
		}// for
		if (tokenBuilder != null) {
			tokensList.add(tokenBuilder.toString());
		}
		tokensListIt = tokensList.listIterator();
	}

	private boolean isOperation(char ch) {
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '('
				|| ch == ')') {
			return true;
		}
		return false;
	}

	public boolean hasMoreTokens() {
		return tokensListIt.hasNext();
	}

	public String nextToken() {
		return tokensListIt.next();
	}

}
