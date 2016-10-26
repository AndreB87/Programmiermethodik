package aufgabenblatt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wandelt ein uebergebenes Array in einen Stream um, 
 * verarbeitet diesen und liefert das Ergebnis als List<T> zurueck
 * @author andre
 *
 */
public class StreamVerarbeitung {
	/**
	 * Maximale Laenge fuer die String-Kuerzung
	 */
	public static int MAX_STRING_LENGTH = 8;

	/**
	 * Verarbeitung des Streams
	 * 
	 * @param strings	String-VarArgs
	 * @return			Liste mit den verarbeiteten Strings
	 */
	public static List<String> streamVerarbeiten(String... strings ) {
		Stream<String> stringStream = Arrays.stream(strings);
		stringStream = stringStream.filter(Objects::nonNull);
		stringStream = stringStream.map(string -> string = string.trim().toUpperCase()
				.replaceAll("Ä", "AE")
				.replaceAll("Ö", "OE")
				.replaceAll("Ü", "UE")
				.replaceAll("ß", "SS"));
		stringStream = stringStream.map(StreamVerarbeitung::stringKuerzen);
		List<String> stringList = stringStream.collect(Collectors.toCollection(ArrayList::new));
		stringStream.close();
		return stringList;
	}
	
	/**
	 * Kuerzt einen String auf eine bestimmte Laenge
	 * 
	 * @param string	Zu kuerzender String
	 * @return			Gekuerzter String
	 */
	private static String stringKuerzen(String string) {
		if (string.length() > MAX_STRING_LENGTH) {
			string = string.substring(0, MAX_STRING_LENGTH);
		}
		return string;
	}
	
	public static void main (String[] args) {
		List<String> testListe = StreamVerarbeitung.streamVerarbeiten
				("Haus", null, " Förster", "Straße", "Bäben", "Hobo-Der-Hobbit");
		testListe.forEach(System.out::println);
	}
}
