/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 2
* Verwendete Quellen:--
*/

package aufgabenblatt1;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Liest eine XML-Datei ein und erstellt daraus ein Sensor-Objekt
 * @author Andre
 *
 */
public class SensorXmlEinlesen {

	/**
	 * Legt anhand einer uebergebenen Datei einen neuen Sensor an
	 * und fuegt bereits durchgefuehrte und abgespeicherte Messungen dem Objekt hinzu 
	 *
	 * @param file	 	XML-Datei des Sensors
	 * @return		 	Sensor, der aus der XML-Datei erstellt wurde
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Sensor sensorAnlegen(File file) 
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		Element element = document.getDocumentElement();
		System.out.println("--" + element.getAttribute("id"));
		Sensor sensor = new Sensor(element.getAttribute("id"));
		messungEinlesen(sensor, element);
		return sensor;
	}

	/**
	 * Liest Messungen der XML-Datei ein
	 * @param sensor 		Sensor, der anhand der XML-Datei erstellt wurde
	 * @param element		Sensor-Element, der XML-Datei
	 */
	private static void messungEinlesen(Sensor sensor, Element element) {
		NodeList kindElemente = element.getChildNodes();
		for (int i = 0; i < kindElemente.getLength(); i++) {
			Node kindElement = kindElemente.item(i);
			if (kindElement.getNodeName().equals("Messung")) {
				messungAnlegen(sensor, kindElement);
			}
		}
	}

	/**
	 * Liest die Attribute der Messung ein und uebergibt sie an das Sensor-Objekt
	 * @param sensor
	 * @param kindElement
	 */
	private static void messungAnlegen(Sensor sensor, Node kindElement) {
		NamedNodeMap attribute = kindElement.getAttributes();
		Double messwert = null;
		LocalDateTime zeit = null;
		for (int j = 0; j < attribute.getLength(); j++) {
			Node attribut = attribute.item(j);
			if (attribut.getNodeName().equals("Wert")) {
				messwert = Double.parseDouble(attribut.getNodeValue().replace(',', '.'));
			}
			if (attribut.getNodeName().equals("Zeitstempel")) {
				zeit = LocalDateTime.parse(attribut.getNodeValue());
			}
		}
		if (messwert != null && zeit != null) {
			sensor.messungHinzufuegen(new Messung(messwert, zeit));
		} else {
			System.out.println("Keine Informationen zu Messungen gefunden");
		}
	}

}