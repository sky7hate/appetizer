package appetizer.translate;

/**
 * In a real project, you may need to add typing information here.
 */
public class VarInfo {

	public Temp loc;

	public VarInfo(int offset) {
		loc = new Temp(offset);
	}
}
