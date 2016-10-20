/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 1
* Verwendete Quellen:--
*/

package aufgabenblatt1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Beinhaltet Informationen über eine/n Studentin/ Studenten und ihre/ seine Pruefungsleistungen.
 * 
 * @author Andre
 *
 */
public class Student implements Comparable<Student> {
	/**
	 * Vorname der Stundentin/ des Studenten
	 */
	private String vorname;
	
	/**
	 * Nachname der Stundentin/ des Studenten
	 */
	private String nachname;
	
	/**
	 * Matrikelnummer der Stundentin/ des Studenten
	 */
	private int matrikelnummer;
	
	/**
	 * Liste der erbrachten Pruefungsleistungen
	 */
	private List<Pruefungsleistung> pruefungsleistungen;
	
	Student(String vorname, String nachname, int matrikelnummer) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.matrikelnummer = matrikelnummer;
		pruefungsleistungen = new LinkedList<Pruefungsleistung>();
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public List<Pruefungsleistung> getPruefungsleistungen() {
		return pruefungsleistungen;
	}
	
	/**
	 * Fuegt eine neue Pruefungsleistung zur Liste hinzu.
	 * 
	 * @param modulname Name des Moduls
	 * @param note Note, die erreicht wurde.
	 */
	public void pruefungsleistungHinzufuegen(String modulname, int note) {
		try {
			pruefungsleistungen.add(new Pruefungsleistung(modulname, note));
		} catch (NotenException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Entfernt eine vorhandene Pruefungsleistung aus der Liste.
	 * 
	 * @param pruefungsleistung Pruefungsleistung, die entfernt werden soll.
	 */
	public void pruefungsleistungEntfernen(String modulname, int note) {
		try {
			Pruefungsleistung pruefungsleistung = new Pruefungsleistung(modulname, note);
			for (Pruefungsleistung element : pruefungsleistungen) {
				if (element.equals(pruefungsleistung)) {
					pruefungsleistungen.remove(element);
				}
			}
		} catch (NotenException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Gibt die Informationen des Studenten/ der Studentin auf der Konsole aus.
	 */
	public void ausgeben() {
		System.out.println(this);
		Iterator<Pruefungsleistung> iter = pruefungsleistungen.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Student) {
			Student anderer = (Student) object;
			if (this.matrikelnummer == anderer.matrikelnummer) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return matrikelnummer;
	}

	@Override
	public int compareTo(Student anderer) {
		if (this.matrikelnummer == anderer.matrikelnummer) {
			return 0;
		}
		if (this.matrikelnummer < anderer.matrikelnummer) {
			return -1;
		}
		return 1;
	}
	
	@Override
	public String toString() {
		String string = nachname + ", " + vorname + " Matrikelnummer: " + matrikelnummer;
		return string;
	}
	
	public static void main(String[] args) {
		Student student = new Student("Harald", "Heiter", 121314);
		student.pruefungsleistungHinzufuegen("Modul1", 12);
		student.pruefungsleistungHinzufuegen("Modul2", 6);
		student.pruefungsleistungHinzufuegen("Modul3", 2);
		student.pruefungsleistungHinzufuegen("Modul3", 8);
		student.pruefungsleistungHinzufuegen("Fehler", 30);
		student.ausgeben();
		student.pruefungsleistungEntfernen("Modul3", 2);
		student.ausgeben();
	}
}
