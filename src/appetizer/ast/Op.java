package appetizer.ast;

public class Op extends Expr {

	public Expr left, right;
	public OpType opType;

	public Op(Expr l, OpType t, Expr r) {
		left = l;
		right = r;
		opType = t;
	}

	public static enum OpType {
		PLUS, MINUS, TIMES, DIVIDE, EQ, NE, LT, GT, LE, GE, ASSIGN
	}
}
