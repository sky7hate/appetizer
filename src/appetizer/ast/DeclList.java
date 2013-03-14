package appetizer.ast;

public class DeclList {

	public Decl head;
	public DeclList tail;

	public DeclList(Decl d, DeclList x) {
		head = d;
		tail = x;
	}
}
