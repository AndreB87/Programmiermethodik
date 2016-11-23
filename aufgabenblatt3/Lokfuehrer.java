package aufgabenblatt3;

public class Lokfuehrer extends Thread {
	private Rangierbahnhof rbhf;
	
	public Lokfuehrer(Rangierbahnhof rbhf) {
		this.rbhf = rbhf;
	}

	public void run() {
		try {
			int gleis = (int)(Math.random() * rbhf.getAnzahlGleise());
			if (rbhf.getGleise()[gleis] == null) {
				rbhf.einfahren(gleis);
				System.out.printf(
						"Zug erfolgreich eingefahren auf Gleis %d.\n", gleis + 1);
			} else {
				rbhf.ausfahren(gleis);
				System.out.printf(
						"Zug erfolgreich ausgefahren von Gleis %d.\n", gleis + 1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
