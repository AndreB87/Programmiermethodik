package aufgabenblatt1;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class StudentTest {
	@Test
	public void testCompareTo() {
		Student student1 = new Student("Achim", "Abert", 11111);
		Student student2 = new Student("Bodo", "Bbert", 11112);
		int erwartet = -1;
		assertEquals(student1.compareTo(student2), erwartet);
	}
	
	@Test
	public void testComparator() {
		Student student1 = new Student("Achim", "Abert", 11111);
		Student student2 = new Student("Bodo", "Bbert", 11110);
		StudentenComparator comparator = new StudentenComparator();
		int erwartetTest1 = -1;
		assertEquals(comparator.compare(student1, student2), erwartetTest1);
		int erwartetTest2 = 1;
		assertEquals(comparator.compare(student2, student1), erwartetTest2);
		int erwartetTest3 = 0;
		assertEquals(comparator.compare(student1, student1), erwartetTest3);
	}
	
	@Test
	public void testPruefungsleistungEntfernen() {
		try {
		Student student = new Student("Aaa", "Bbb", 12345);
		student.pruefungsleistungHinzufuegen("test1", 13);
		student.pruefungsleistungHinzufuegen("test2", 10);
		student.pruefungsleistungEntfernen("test2", 10);
		List<Pruefungsleistung> erwartet = new LinkedList<Pruefungsleistung>();
		erwartet.add(new Pruefungsleistung("test1", 13));
		assertEquals(student.getPruefungsleistungen(), erwartet);
		} catch (Exception e) {
			System.out.println("Fehler bei PrüfungsleistungEntfernen");
		}
	}
	
	
}
