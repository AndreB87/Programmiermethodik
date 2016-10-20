/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 1
* Verwendete Quellen:--
*/

package aufgabenblatt1;

import java.util.Comparator;

/**
 * Vergleicht zwei Stundenten anhand des Nachnamens und, falls erforderlich, anhand des Vornamens.
 * 
 * @author Andre
 *
 */
public class StudentenComparator implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		int hilfszahl = student1.getNachname().compareTo(student2.getNachname());
		if (hilfszahl == 0){
			hilfszahl = student1.getVorname().compareTo(student2.getVorname());
		}
		return hilfszahl;
	}
}
