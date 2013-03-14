package appetizer.ast;

import appetizer.symbol.Symbol;

public class Var extends Expr {

	public Symbol sym;

	public Var(Symbol symbol) {
		sym = symbol;
	}
}
