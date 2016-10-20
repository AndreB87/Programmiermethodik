package aufgabenblatt1;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;
=======
import org.junit.Test;
import static org.junit.Assert.*;
>>>>>>> 7b80d1931d2316f8594142e676e133510964922a

public class ArrayListenTest {
	
	@Test
	public void testHinzufuegen() {
		ArrayListe<Student> studenten = new ArrayListe<Student>();
		Student student = new Student("Berthold", "Bertram", 12345);
		studenten.hinzufuegen(student);
		try {
			assertEquals(studenten.get(0), student);
		} catch (ArrayListenException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testEntferneAnIndex() {
		ArrayListe<Student> studenten = new ArrayListe<Student>();
		Student student = new Student("Aaaa", "Bbbb", 11111);
		studenten.hinzufuegen(new Student("Berthold", "Bertram", 12345));
		studenten.hinzufuegen(student);
		try {
			studenten.entferneElementAnIndex(0);
			assertEquals(studenten.get(0), student);
		} catch (ArrayListenException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testEntfernen() {
		ArrayListe<Student> studenten = new ArrayListe<Student>();
		Student student1 = new Student("Aaaa", "Bbbb", 11111);
		Student student2 = new Student("Berthold", "Bertram", 12345);
		studenten.hinzufuegen(student2);
		studenten.hinzufuegen(student1);
		try {
			studenten.entfernen(student2);
			assertEquals(studenten.get(0), student1);
		} catch (ArrayListenException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testGetKleinstes() {
		ArrayListe<Student> studenten = new ArrayListe<Student>();
		Student student1 = new Student("Aaaa", "Bbbb", 11111);
		Student student2 = new Student("Berthold", "Bertram", 12345);
		Student kleinerKarl = new Student("Kleiner", "Karl", 00001);
		studenten.hinzufuegen(student2);
		studenten.hinzufuegen(student1);
		studenten.hinzufuegen(kleinerKarl);
		try {
			Student kleinster = studenten.getKleinstesElement();
			System.out.println(kleinster);
			assertEquals(kleinster, kleinerKarl);
		} catch (ArrayListenException e) {
			System.out.println(e);
		}
	}
}
