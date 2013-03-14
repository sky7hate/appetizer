package appetizer.syntactic;

import java.io.*;

import appetizer.main.Main;

import com.google.gson.Gson;

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
		Gson gson = new Gson();
		System.out.println(gson.toJson(parseTree.value));
	}

	public static void main(String argv[]) throws IOException {
		parse(Main.pathOf("example1.c"));
		parse(Main.pathOf("example2.c"));
		parse(Main.pathOf("example3.c"));
	}
}
