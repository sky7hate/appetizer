package appetizer.ast;

public class StmtList {

	public Stmt head;
	public StmtList tail;

	public StmtList(Stmt s, StmtList x) {
		head = s;
		tail = x;
	}
}
