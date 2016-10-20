/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 2
* Verwendete Quellen:--
*/

package aufgabenblatt1;

/**
 * Pruefungsleistung beinhaltet den Modulnamen und die Note.
 * 
 * @author Andre
 *
 */
public class Pruefungsleistung {
	/**
	 * Name des Moduls
	 */
	private String modulname;
	
	/**
	 * Note, die erreicht wurde
	 */
	private int note;
	
	Pruefungsleistung(String modulname, int note) throws NotenException {
		this.modulname = modulname;
		if (note < 0 || note > 15) {
			throw new NotenException("Note muss zwischen 0 und 15 liegen.");
		}
		this.note = note;
	}
	
	@Override
	public String toString() {
		String string = "Modul: ";
		string += modulname;
		string += " Note: " + note;
		return string;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Pruefungsleistung) {
			Pruefungsleistung anderes = (Pruefungsleistung) object;
			return (anderes.modulname.equals(this.modulname) && anderes.note == this.note);
		}
		return false;
	}
}
