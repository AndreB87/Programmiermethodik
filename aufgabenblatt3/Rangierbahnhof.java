package aufgabenblatt3;

import java.util.Observable;

public class Rangierbahnhof extends Observable {
	private Zug[] abstellGleis;

	public Rangierbahnhof(int anzahlGleise) {
		this.abstellGleis = new Zug[anzahlGleise];
	}

	public int getAnzahlGleise() {
		return abstellGleis.length;
	}

	public synchronized void einfahren(int gleis) throws InterruptedException {
		if(abstellGleis[gleis] != null) {
			wait();
		}
		abstellGleis[gleis] = new Zug();
		setChanged();
		notifyObservers(abstellGleis[gleis]);
		notifyAll();
	}

	public synchronized void ausfahren(int gleis) throws InterruptedException {
		if(abstellGleis[gleis] == null) {
			wait();
		}
		abstellGleis[gleis] = null;
		setChanged();
		notifyObservers(abstellGleis[gleis]);
		notifyAll();
	}
	
	
	public Zug[] getGleise() {
		return abstellGleis;
	}
}