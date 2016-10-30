package aufgabenblatt2;

import java.util.ArrayList;
import java.util.List;

import aufgabenblatt2.Flugzeug.Status;

/**
 * Simuliert einen Flughafen.
 * Der Flughafen hat eine bestimmte Anzahl von Flugzeugen, die sich in der Luft befinden.
 * Wenn ein Flugzeug eine bestimmte Dauer in der luft war, stellt es eine Landeanfrage
 * an den Flughafen. Wenn sich kein anderes Flugzeug in der Luft befindet, so kann es landen.
 * Befindet sich bereits ein anderes Flugzeug im Landeanflug, so muss das Flugzeug weiter kreisen.
 * Wenn ein Flugzeug gelandet ist, wird es geloescht und ein neues wird erzeugt.
 * @author andre
 *
 */
public class Flughafen extends Thread {
	/**
	 * Liste mit den Flugzeugen, die der Flughafen "hat".
	 */
	private List<Flugzeug> flugzeuge;
	
	/**
	 * Anzahl der Flugzeuge, um die sich der Flughafen kuemmert.
	 */
	private int anzahlFlugzeuge;
	
	/**
	 * Aktuelle Zeit des Flughafens
	 */
	private int zeit;

	public Flughafen(int anzahlFlugzeuge) {
		this.anzahlFlugzeuge = anzahlFlugzeuge;
		flugzeuge = new ArrayList<Flugzeug>();
	}

	/**
	 * Legt ein neues Flugzeug an und liefert dieses zurueck.
	 * 
	 * @return 	neu erstelltes Flugzeug
	 */
	private Flugzeug flugzeugAnlegen() {
		return new Flugzeug
				(this, String.format("Hansa-Air FL %d", (int)(Math.random() * 100) + flugzeuge.size()),
						(int) ((Math.random() * 20) + anzahlFlugzeuge), zeit);
	}

	/**
	 * Anfrage, die ein Flugzeug an den Flughafen stellt, um landen zu koennen.
	 * Schlüsselwort synchronized sorgt dafuer, dass immer nur genau ein Flugzeug zur
	 * Zeit landen kann.
	 * 
	 * @param flugzeug	Flugzeug, welches landen moechte
	 * @throws InterruptedException
	 */
	public synchronized void landen(Flugzeug flugzeug) throws InterruptedException {
		flugzeug.setStatus(Status.IM_LANDEANFLUG);
		Thread.sleep(1500);
		flugzeug.istGelandet();
		flugzeuge.remove(flugzeug);
	}

	/**
	 * Legt, falls notwendig, neue Flugzeuge an, startet sie und uebergibt
	 * die aktuelle Zeit weiter.
	 */
	@Override
	public void run() {
		try {
			while (!isInterrupted()) {
				while (anzahlFlugzeuge >= flugzeuge.size()) {
					flugzeuge.add(flugzeugAnlegen());
				}
				Thread.sleep(500);
				zeit++;
				System.out.printf("Zeit: %d\n", zeit);
				for (Flugzeug flugzeug : flugzeuge) {
					if (!flugzeug.isAlive()) {
						flugzeug.start();
					}
					flugzeug.setZeit(zeit);
					System.out.println(flugzeug);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Der Flughafen wurde aufgrund eines Streiks geschlossen.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Flughafen flughafen = new Flughafen(5);
		flughafen.start();
	}
}
