package aufgabenblatt3;


public class Simulation implements Runnable {
	private Rangierbahnhof rbhf;

	public Simulation(Rangierbahnhof rbhf) {
		this.rbhf = rbhf;
	}

	public Rangierbahnhof getBahnhof() {
		return rbhf;
	}
	
	private void lokfuehrerAnlegen() throws InterruptedException {
		Lokfuehrer lf = new Lokfuehrer(rbhf); 
		lf.start();
		Thread.sleep(500);
	}

	@Override
	public void run() {
		while (true) {
			try {
				lokfuehrerAnlegen();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int anzahlGleise = 3;
		Thread simThread = new Thread(new Simulation(new Rangierbahnhof(anzahlGleise)));
		simThread.start();
	}
}
