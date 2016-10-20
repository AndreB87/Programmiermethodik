/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 2
* Verwendete Quellen:--
*/

package aufgabenblatt1;

public class NotenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String beschreibung;
	
	NotenException(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	@Override
	public String toString() {
		return beschreibung;
	}
}
