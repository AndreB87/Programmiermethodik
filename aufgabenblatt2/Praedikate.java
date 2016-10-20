package aufgabenblatt2;

public class Praedikate {
	public static int MAX_STRING_LENGTH = 8;
	
	public static boolean isNotNull(Object obj) {
		if (obj != null) {
			return true;
		}
		return false;
	}
	
	public static String stringKuerzen(String string) {
		if (string.length() > MAX_STRING_LENGTH) {
			string = string.substring(0, MAX_STRING_LENGTH - 1);
		}
		return string;
	}
}
