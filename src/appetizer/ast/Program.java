package appetizer.ast;

import appetizer.symbol.Symbol;

public class Program {

	public Symbol sym;
	public Block body;

	public Program(Symbol symbol, Block b) {
		sym = symbol;
		body = b;
	}
}
