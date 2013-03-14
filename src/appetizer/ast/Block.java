package appetizer.ast;

public class Block extends Stmt {

	public DeclList decls;
	public StmtList stmts;

	public Block(DeclList d, StmtList s) {
		decls = d;
		stmts = s;
	}
}
