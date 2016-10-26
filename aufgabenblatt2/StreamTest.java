package aufgabenblatt2;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testet die Klasse StreamVerarbeitung
 * 
 * @author andre
 *
 */
public class StreamTest {
	/**
	 * Testet die Methode StreamVerarbeitung
	 */
	@Test
	public void streamVerarbeitungTest() {
		List<String> expected = Arrays.asList
				("EINGABE", "AEUSSERE", "STRASSEN", "EIN HAUS");
		List<String> testStream = StreamVerarbeitung.streamVerarbeiten
				("Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus");
		assertEquals(testStream, expected);
	}
}
