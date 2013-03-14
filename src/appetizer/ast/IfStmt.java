package appetizer.ast;

public class IfStmt extends Stmt {

	public Expr cond;
	public Stmt body;

	public IfStmt(Expr e, Stmt s) {
		cond = e;
		body = s;
	}
}
