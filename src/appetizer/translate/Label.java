package appetizer.translate;

public class Label {

	private static int count = 0;

	private int i;

	public Label() {
		i = ++count;
	}

	public String toString() {
		return "L" + i;
	}
}
