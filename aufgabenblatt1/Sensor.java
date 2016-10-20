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
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

@XmlRootElement(name = "Sensor")
public class Sensor implements AutoCloseable {
	/**
	 * Beschreibung des Sensors
	 */
	private String id;
	
	/**
	 * Messungen, die mit dem Sensor durchgefuehrt wurden
	 */
	private List<Messung> messungen;

	public Sensor(String id) {
		this.id = id;
		this.messungen = new ArrayList<Messung>();
	}

	
	@XmlAttribute(name = "id")
	public String getID() {
		return id;
	}

	@XmlElement(name = "Messung")
	public List<Messung> getMessungen() {
		return messungen;
	}

	/**
	 * Fuegt dem Sensor eine neue Messung hinzu
	 * @param messung
	 */
	public void messungHinzufuegen(Messung messung) {
		messungen.add(messung);
	}

	public String toString() {
		return id;
	}

	/**
	 * Gibt den Zustand des Sensors mit all seinen Messungen aus
	 */
	public void ausgeben() {
		System.out.println(this);
		if (messungen != null) {
			for (Messung element : messungen) {
				System.out.println(element);
			}
		}
	}

	/**
	 * Beendet die Verbindung zum Sensor
	 * @throws IOException
	 */
	public void verbindungBeenden() throws IOException {
		if (Math.random() < 0.3) {
			throw new IOException();
		}
	}
	
	@Override
	public void close() throws IOException {
		verbindungBeenden();
	}

	public static void main(String[] args) {
		try (Sensor sensor = SensorXmlEinlesen.sensorAnlegen(new File("src\\aufgabenblatt1\\Sensor.xml"))) {
			sensor.messungHinzufuegen(new Messung());
			sensor.messungHinzufuegen(new Messung());
			sensor.ausgeben();
			SensorXmlSchreiben.sensorXmlSchreiben(sensor, new File("src\\aufgabenblatt1\\SensorNeu.xml"));
		} catch (IOException e) {
			System.out.println("Zugriffsfehler am Sensor.");
		} catch (ParserConfigurationException e) {
			System.out.println("Fehler beim Lesen der Datei.");
		} catch (SAXException e) {
			System.out.println("Fehler beim Lesen der Datei.");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}