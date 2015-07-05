package calcTreeParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class ParserTest extends TestCase {
	// (new String[]{ "1", "+", "2" })

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "1+2", new String[] { "1", "+", "2" } },
				{ "( 2+3+(44*1) )", new String[] { "(", "2", "+", "3", "+", "(", "44", "*", "1", ")", ")" } },
				{ "( 2 + 3+( 44  *1) )", new String[] { "(", "2", "+", "3", "+", "(", "44", "*", "1", ")", ")" } },
				{ "123qwe", new String[] { "123", "qwe" } },
				{ "qwe123", new String[] { "qwe", "123" } },
				});
	}

	@Parameter
	public String source;

	@Parameter(value = 1)
	public String res[];

	@Test
	public void testParser() {
		
		Parser parser = new Parser(source);
		List<String> tokensList = new ArrayList<String>();
		while(parser.hasMoreTokens()) {
			tokensList.add(parser.nextToken());
		}
		assertEquals(Arrays.asList(res), tokensList);
	}
}
