package appetizer.codegen;

import appetizer.translate.Label;
import appetizer.translate.Temp;

public interface AssemblyWriter {

	public void returnValue(Temp src);

	public void store(Temp dest, int src);

	public void move(Temp dest, Temp src);

	public void add(Temp dest, Temp src1, Temp src2);

	public void sub(Temp dest, Temp src1, Temp src2);

	public void mul(Temp dest, Temp src1, Temp src2);

	public void div(Temp dest, Temp src1, Temp src2);

	public void seq(Temp dest, Temp src1, Temp src2);

	public void sne(Temp dest, Temp src1, Temp src2);

	public void slt(Temp dest, Temp src1, Temp src2);

	public void sgt(Temp dest, Temp src1, Temp src2);

	public void sle(Temp dest, Temp src1, Temp src2);

	public void sge(Temp dest, Temp src1, Temp src2);

	public void emit(Label label);

	public void jump(Label label);

	public void jz(Temp cond, Label label);
}
