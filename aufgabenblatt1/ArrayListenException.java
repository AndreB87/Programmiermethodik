package aufgabenblatt1;

/**
 * Exception, die in der ArrayListe auftreten kann
 * @author Andre
 *
 */
public class ArrayListenException extends Exception {
	/**
	 * Default-Serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Uebergebene Fehlermeldung
	 */
	String message;
	
	public ArrayListenException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
