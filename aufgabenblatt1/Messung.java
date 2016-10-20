/**
* Praktikum TI PM2, WS 2016
* Gruppe: Andre Brand (andre.brand@haw-hamburg.de),
* 
* Aufgabe: Aufgabenblatt 1, Aufgabe 2
* Verwendete Quellen:--
*/

package aufgabenblatt1;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.*;

/**
 * Repraesentiert die Messung eines Sensors, welche den Messwert, 
 * sowie einen Zeitstempel der Messung beinhaltet.
 * @author Andre
 *
 */
@XmlRootElement (name = "Messung")
public class Messung {
	/**
	 * Wert, der gemessen wurde
	 */
	private double wert;
	
	/**
	 * Zeitpunkt der Messung
	 */
	private LocalDateTime zeitstempel;
	
	public Messung(double wert, LocalDateTime zeitstempel) {
		this.wert = wert;
		this.zeitstempel = zeitstempel;
	}
	
	public Messung(double wert) {
		this(wert, LocalDateTime.now());
	}
	
	public Messung() {
		this(Math.random() * 50.0);
	}
	
	@XmlAttribute (name = "Wert")
	public double getWert() {
		return wert;
	}
	@XmlAttribute (name = "Zeitstempel")
	public LocalDateTime getZeit() {
		return zeitstempel;
	}
	
	public String toString() {
		String string = String.format("Wert: %.1f°C gemessen am: ", wert) + zeitstempel.toString();
		return string;
	}
}