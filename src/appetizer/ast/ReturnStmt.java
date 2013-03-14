package appetizer.ast;

public class ReturnStmt extends Stmt {

	public Expr expr;

	public ReturnStmt(Expr e) {
		expr = e;
	}
}
