package appetizer.translate;

import static appetizer.symbol.Symbol.symbol;

import appetizer.ast.*;
import appetizer.codegen.AssemblyWriter;
import appetizer.symbol.Table;

public class Translator {

	private Program prog;
	private AssemblyWriter asm;
	private Table vscope;
	private int offset;
	private Label exit;

	public void translate(Program program, AssemblyWriter assemblyWriter) {
		prog = program;
		asm = assemblyWriter;
		vscope = new Table();
		offset = 0;
		exit = new Label();
		translate(prog);
	}

	private void translate(Program prog) {
		if (prog.sym != symbol("main")) {
			throw new Error("Program name must be main.");
		}
		// TODO prologue
		translate(prog.body);
		asm.emit(exit);
		// TODO print return value
	}

	private void translate(Block block) {
		vscope.beginScope();
		translate(block.decls);
		translate(block.stmts);
		vscope.endScope();
	}

	private void translate(DeclList decls) {
		if (decls == null) {
			return;
		}
		translate(decls.head);
		translate(decls.tail);
	}

	private void translate(Decl decl) {
		offset -= 4;
		vscope.put(decl.sym, new VarInfo(offset));
	}

	private void translate(StmtList stmts) {
		if (stmts == null) {
			return;
		}
		translate(stmts.head);
		translate(stmts.tail);
	}

	private void translate(Stmt stmt) {
		if (stmt instanceof Block) {
			translate((Block) stmt);
		} else if (stmt instanceof Expr) {
			translate((Expr) stmt);
		} else if (stmt instanceof IfStmt) {
			translate((IfStmt) stmt);
		} else if (stmt instanceof ReturnStmt) {
			translate((ReturnStmt) stmt);
		}
	}

	private void translate(Expr expr) {
		if (expr instanceof Num) {
			translate((Num) expr);
		} else if (expr instanceof Op) {
			translate((Op) expr);
		} else if (expr instanceof Var) {
			translate((Var) expr);
		}
	}

	private void transleft(Expr expr) {
		if (expr instanceof Num) {
			throw new Error("Number is not a lvalue.");
		} else if (expr instanceof Op) {
			if (((Op) expr).isLvalue()) {
				translate((Op) expr);
			}
			throw new Error(((Op) expr).opType + " is not a lvalue.");
		} else if (expr instanceof Var) {
			translate((Var) expr);
		}
	}

	private Temp newTemp() {
		offset -= 4;
		return new Temp(offset);
	}

	private void translate(Num num) {
		num.loc = newTemp();
		asm.store(num.loc, num.value);
	}

	private void translate(Op op) {
		if (op.isLvalue()) {
			transleft(op.left);
			translate(op.right);
			op.loc = op.left.loc;
		} else {
			translate(op.left);
			translate(op.right);
			op.loc = newTemp();
		}

		switch (op.opType) {
		case PLUS:
			asm.add(op.loc, op.left.loc, op.right.loc);
			break;
		case MINUS:
			asm.sub(op.loc, op.left.loc, op.right.loc);
			break;
		case TIMES:
			asm.mul(op.loc, op.left.loc, op.right.loc);
			break;
		case DIVIDE:
			asm.div(op.loc, op.left.loc, op.right.loc);
			break;
		case EQ:
			asm.seq(op.loc, op.left.loc, op.right.loc);
			break;
		case NE:
			asm.sne(op.loc, op.left.loc, op.right.loc);
			break;
		case LT:
			asm.slt(op.loc, op.left.loc, op.right.loc);
			break;
		case GT:
			asm.sgt(op.loc, op.left.loc, op.right.loc);
			break;
		case LE:
			asm.sle(op.loc, op.left.loc, op.right.loc);
			break;
		case GE:
			asm.sge(op.loc, op.left.loc, op.right.loc);
			break;
		case ASSIGN:
			asm.move(op.loc, op.right.loc);
			break;
		}
	}

	private void translate(Var var) {
		VarInfo varInfo = (VarInfo) vscope.get(var.sym);
		if (varInfo == null) {
			throw new Error("Undefined variable: " + var.sym);
		}
		var.loc = varInfo.loc;
	}

	private void translate(IfStmt ifStmt) {
		Label next = new Label();
		translate(ifStmt.cond);
		asm.jz(ifStmt.cond.loc, next);
		translate(ifStmt.body);
		asm.emit(next);
	}

	private void translate(ReturnStmt returnStmt) {
		translate(returnStmt.expr);
		asm.returnValue(returnStmt.expr.loc);
		asm.jump(exit);
	}
}
