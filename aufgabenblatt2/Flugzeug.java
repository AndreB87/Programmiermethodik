package aufgabenblatt2;

/**
 * Simuliert ein Flugzeug, welches um einen Flughafen herum fliegt.
 * Es kann Landeanfragen an den Flughafen stellen.
 * 
 * @author andre
 *
 */
public class Flugzeug extends Thread {
	/**
	 * Enthaelt die Status, die ein Flugzeug einnehmen kann.
	 * @author andre
	 *
	 */
	public enum Status {
		IM_FLUG, IM_LANDEANFLUG, GELANDET, KREIST
	}
	/**
	 * Flughafen, an dem das Flugzeug eine Laneanfrage stellen kann.
	 */
	private Flughafen flughafen;
	
	/**
	 * Name des Flugzeugs
	 */
	private String id;
	
	/**
	 * Zeit, die das FLugzeug unterwegs ist.
	 */
	private int flugdauer;
	
	/**
	 * Zeitpunkt zu dem das FLugzeug gestartet ist.
	 */
	private int startzeit;
	
	/**
	 * Aktueller Status des FLugzeugs(z.B. IM_FLUG).
	 */
	private Status status;
	
	/**
	 * Aktuelle Zeit
	 */
	private int zeit;

	public Flugzeug(Flughafen flughafen, String id, int flugdauer, int startzeit) {
		this.flughafen = flughafen;
		this.id = id;
		this.flugdauer = flugdauer;
		this.startzeit = startzeit;
		this.zeit = startzeit;
		this.status = Status.IM_FLUG;
		this.start();
	}

	/**
	 * Rechnet die verbleibende Flugzeit des Flugzeugs aus.
	 * @return	Verbleibende Flugzeit
	 */
	private int getRestzeit() {
		int restzeit = startzeit + flugdauer - zeit;
		if (restzeit < 0) {
			return 0;
		}
		return restzeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Setzt den Status des Flugzeugs auf GELANDET und gibt eine Rueckmeldung auf der Konsole aus.
	 */
	public void istGelandet() {
		status = Status.GELANDET;
		System.out.println("-> " + id + " ist gelandet");
	}

	public boolean isGelandet() {
		return status == Status.GELANDET;
	}

	public String toString() {
		String string = "Flugzeug: ";
		string += id + " (";
		string += status;
		string += String.format(", Zeit bis Ziel: %d)", getRestzeit());
		return string;
	}

	/**
	 * Simuliert den Flug und stellt,
	 * sofern die Flugdauer erreicht ist, eine Landeanfrage an den FLughafen.
	 */
	@Override
	public void run() {
		try {
			System.out.println(" --> " + id + " gestartet");
			while (getRestzeit() > 0) {
				sleep(500);
			}
			status = Status.KREIST;
			flughafen.landen(this);
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception");
		}
	}

}
