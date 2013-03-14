package appetizer.syntactic;

import java.io.*;

final class ParserTest {

	private static void parse(String filename) throws IOException {
		InputStream inp = new FileInputStream(filename);
		Parser parser = new Parser(new Yylex(inp));
		java_cup.runtime.Symbol parseTree = null;
		try {
			parseTree = parser.parse();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.toString());
		} finally {
			inp.close();
		}
		System.out.println(parseTree.value);
	}

	private static String pathOf(String filename) {
		return ParserTest.class.getResource(filename).getPath();
	}

	public static void main(String argv[]) throws IOException {
		parse(pathOf("example1.c"));
		parse(pathOf("example2.c"));
		parse(pathOf("example3.c"));
	}
}
