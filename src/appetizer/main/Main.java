package appetizer.main;

public class Main {

	public static String pathOf(String filename) {
		return Main.class.getResource(filename).getPath();
	}
}
