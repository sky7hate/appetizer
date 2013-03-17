package appetizer.ast;

import appetizer.translate.Temp;

public abstract class Expr extends Stmt {

	public transient Temp loc;
}
