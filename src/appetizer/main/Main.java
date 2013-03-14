package appetizer.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import appetizer.ast.Program;
import appetizer.codegen.AssemblyWriter;
import appetizer.codegen.MipsAssemblyWriter;
import appetizer.syntactic.Parser;
import appetizer.translate.Translator;

public class Main {

	public static String pathOf(String filename) {
		return Main.class.getResource(filename).getPath();
	}

	private static void compile(String filename) throws IOException {
		InputStream inp = new FileInputStream(filename);
		Parser parser = new Parser(inp);
		java_cup.runtime.Symbol parseTree = null;
		try {
			parseTree = parser.parse();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.toString());
		} finally {
			inp.close();
		}

		Program program = (Program) parseTree.value;
		Translator translator = new Translator();
		AssemblyWriter asm = new MipsAssemblyWriter();
		translator.translate(program, asm);
		System.out.println(asm);
	}

	public static void main(String argv[]) throws IOException {
		compile(pathOf("example1.c"));
		compile(pathOf("example2.c"));
		compile(pathOf("example3.c"));
	}
}
