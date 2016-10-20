package aufgabenblatt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamVerarbeitung {
	public static int MAX_STRING_LENGTH = 8;

	public static List<String> streamVerarbeiten(String... strings ) {
		Stream<String> stringStream = Arrays.stream(strings);
		stringStream = stringStream.filter(StreamVerarbeitung::isNotNull);
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
	
	private static boolean isNotNull(Object obj) {
		if (obj != null) {
			return true;
		}
		return false;
	}
	
	private static String stringKuerzen(String string) {
		if (string.length() > MAX_STRING_LENGTH) {
			string = string.substring(0, MAX_STRING_LENGTH - 1);
		}
		return string;
	}
	
	public static void main (String[] args) {
		try {
			List<String> testListe = StreamVerarbeitung.streamVerarbeiten
					("Haus", null, " Förster", "Straße", "Bäben", "Hobo-Der-Hobbit");
			testListe.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Schade, das hat leider nicht geklappt!");
		}
	}
}
