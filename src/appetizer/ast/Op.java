package appetizer.ast;

public class Op extends Expr {

	public Op(Expr l, OpType assign, Expr r) {
		// TODO Auto-generated constructor stub
	}

	public static enum OpType {
		PLUS, MINUS, TIMES, DIVIDE, EQ, NE, LT, GT, LE, GE, ASSIGN
	}
}
