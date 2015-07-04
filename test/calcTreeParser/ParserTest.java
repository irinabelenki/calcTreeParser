package calcTreeParser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ParserTest {

	@Test
	public void test() {
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
