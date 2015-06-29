package calcTreeParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	public void testParser() {
		String[] answers = {"(", "2", "+", "3", "+", "(", "44", "*", "1", ")", ")"};
		List<String> answersList = new ArrayList<String>(Arrays.asList(answers));
		
		Parser parser = new Parser("( 2+3+(44*1) )");
		List<String> tokensList = new ArrayList<String>();
		while(parser.hasMoreTokens()) {
			tokensList.add(parser.nextToken());
		}
		assertEquals(answersList, tokensList);
	}

}
