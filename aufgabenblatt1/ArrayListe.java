/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
* Verwendete Quellen:--
*/

package aufgabenblatt1;

/**
 * Eigene Implementierung einer Array-List
 * @author Andre
 *
 * @param <T>	DatenTyp, der in der Liste gespeichert wird
 */
public class ArrayListe<T extends Comparable<T>> {
	private int anzahlElemente;
	Object[] elemente;
	
	public ArrayListe() {
		anzahlElemente = 0;
		elemente = new Object[1];
	}
	
	/**
	 * Fuegt der Liste ein neues Element hinzu
	 * @param element 	Element, welches hinzugefuegt werden soll
	 */
	public void hinzufuegen(T element) {
		if (anzahlElemente >= elemente.length) {
			Object[] kopieKasten = new Object[elemente.length * 2];
			for (int i = 0; i < elemente.length; i++) {
				kopieKasten[i] = elemente[i];
			}
			elemente = kopieKasten;
		}
		elemente[anzahlElemente] = element;
		anzahlElemente++;
	}
	
	/**
	 * Prueft die Liste auf leere Felder ab und ueberschreibt diese mit dem nachfolgenden Wert
	 */
	private void pruefeAufNull() {
		int j = 0;
		do {
			if (elemente[j] == null){
				elemente[j]= elemente[j +1];
				elemente[j + 1] = null;
			}
			j++;
		} while(j < (elemente.length - 1));
	}
	
	/**
	 * Entfernt, sofern vorhanden, das uebergebene Element aus der Liste
	 * @param zuEntfernen		Zu entfernendes Element
	 * @throws ArrayListenException
	 */
	public void entfernen(T zuEntfernen) throws ArrayListenException {
		int test = 0;
		for (int i = 0; i < anzahlElemente; i++) {
			if (elemente[i].equals(zuEntfernen)) {
				elemente[i] = null;
				test = 1;
			}
		}
		if (test != 1) {
			throw new ArrayListenException("Element nicht vorhanden.");
		}
		pruefeAufNull();
		anzahlElemente--;
	}
	
	/**
	 * Entfernt das Element aus der Liste, welches an der uebergebenen Position liegt 
	 * @param position		Position des zu entfernenden Elements
	 * @throws ArrayListenException
	 */
	public void entferneElementAnIndex(int position) throws ArrayListenException {
		if (position >= 0 && position < anzahlElemente) {
			elemente[position] = null;
			pruefeAufNull();
			anzahlElemente--;
		} else {
			throw new ArrayListenException("Position nicht belegt.");
		}
	}
	
	/**
	 * Liefert das Objekt, welches sich an der uebergebenen Position befindet, zurueck, sofern vorhanden
	 * @param position		Position des zurueckzuliefernden Objektes
	 * @return				Element an der uebergebenen Position
	 * @throws ArrayListenException
	 */
	public T get(int position) throws ArrayListenException {
		if (position >= 0 && position < anzahlElemente) {
			return(T) elemente[position];
		} else {
			throw new ArrayListenException("Position nicht belegt.");
		}
	}
	
	/**
	 * Liefert das kleinste Element zurueck
	 * @return 		kleinstes Element in der Liste
	 * @throws ArrayListenException
	 */
	public T getKleinstesElement() throws ArrayListenException {
		T kleinstes = (T)elemente[0];
		for (int i = 0; i < anzahlElemente; i++) {
			if (((T)elemente[i]).compareTo(kleinstes) < 0) {
				kleinstes = (T)elemente[i];
			}
		}
		return kleinstes;
	}
	
	public int getAnzahlElemente() {
		return anzahlElemente;
	}
	
	public String toString() {
		String string = "Inhalt:\n";
		for (int i = 0; i < anzahlElemente; i++) {
			string += String.format("Position %d: ", i);
			string += elemente[i].toString() + "\n";
		}
		return string;
	}
	
	public static void main(String args[]) {
		ArrayListe<Student> studenten = new ArrayListe<Student>();
		Student student = new Student("Berthold", "Bertram", 12345);
		studenten.hinzufuegen(student);
		System.out.println(studenten);
	}
}
