package aufgabenblatt4.view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import aufgabenblatt4.braitenbergvehikel.BVBewegungAbstossung;
import aufgabenblatt4.braitenbergvehikel.BVSimulation;
import aufgabenblatt4.braitenbergvehikel.BraitenbergVehikel;
import aufgabenblatt4.braitenbergvehikel.Vektor2;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Zeichenfläche für eine Braitenberg-Vehikle-Simulation.
 * 
 * @author Philipp Jenke
 */
public class BVCanvas extends Canvas implements Observer {
	/**
	 * Bild eines Vehikels. Achtung: Package mit dem Bild muss korrekt angegeben
	 * werden.
	 */
	private Image bvImage = new Image("/aufgabenblatt4/assets/braitenberg_vehikel.png");
	
	/**
	 * Bild mit der Darstellung fuer die Bewegungsart Abstossung
	 */
	private final Image abstossung = new Image("/aufgabenblatt4/assets/icon_abstossung.png");

	/**
	 * Bild mit der Darstellung fuer die Bewegungsart Attraktion
	 */
	private final Image attraktion = new Image("/aufgabenblatt4/assets/icon_attraktion.png");
	/**
	 * Referenz auf die Simulation.
	 */
	private final BVSimulation sim;

	public BVCanvas(int breite, int hoehe, BVSimulation sim) {
		super(breite, hoehe);
		this.sim = sim;
		this.sim.addObserver(this);
		for (int i = 0; i < sim.getAnzahlVehike(); i++) {
			sim.getVehikel(i).addObserver(this);
		}
		setzeSignalMitMaus();
	}

	// Setzt das Signal auf die Stelle, auf die mit der Maus geklickt wurde
	private void setzeSignalMitMaus() {
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Vektor2 koordinaten = new Vektor2(event.getX(), event.getY());
				Point point = bild2WeltKoordinaten(koordinaten);
				sim.setSignal(point.getX(), point.getY());
			}
		});
	}

	/**
	 * Zeichnet die gesamte Simulation neu.
	 */
	public void zeichneSimulation() {
		GraphicsContext gc = getGraphicsContext2D();
		// Alles löschen
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, getWidth(), getHeight());
		// Vehikel zeichnen
		for (int i = 0; i < sim.getAnzahlVehike(); i++) {
			zeichneVehikel(gc, sim.getVehikel(i));
		}
		// Signal zeichnen
		zeichneSignal(gc, sim.getSignal());
	}

	/**
	 * Rechnet von Welt- zu Bild-Koordinaten um.
	 */
	public Point welt2BildKoordinaten(Vektor2 position) {
		return new Point((int) (position.x() + getWidth() / 2), (int) (getHeight() / 2 - position.y()));
	}

	/**
	 * Rechnet von Bild- zu Welt-Koordinaten um.
	 */
	public Point bild2WeltKoordinaten(Vektor2 koordinaten) {
		return new Point((int) (koordinaten.x() - getWidth() / 2), (int) (getHeight() / 2 - koordinaten.y()));
	}

	/**
	 * Rotiert den aktuellen Grafik-Kontext (zum Zeichnen eines rotierten
	 * Bildes).
	 */
	private void rotieren(GraphicsContext gc, double winkel, double px, double py) {
		Rotate r = new Rotate(winkel, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	/**
	 * Zeichnet ein Bild gedreht.
	 */
	private void zeichneGedrehtesBild(GraphicsContext gc, Image image, Image bewegungsImage, String bvName,
			double winkel, double x, double y) {
		final int bewegungsImageKorrektur = 40;
		final int nameKorrektur = 65;
		// Zustand auf dem Stack sichern
		gc.save();
		rotieren(gc, winkel, x + image.getWidth() / 2, y + image.getHeight() / 2);
		gc.drawImage(image, x, y);
		gc.drawImage(bewegungsImage, x + bewegungsImageKorrektur, y);
		gc.setFill(Color.BLACK);
		gc.fillText(bvName, x, y + nameKorrektur);
		// Zustand wiederherstellen
		gc.restore();
	}

	/**
	 * Zeichnet ein Braitenberg-Vehikel.
	 */
	protected void zeichneVehikel(GraphicsContext gc, BraitenbergVehikel bv) {
		Point p = welt2BildKoordinaten(bv.getPosition());
		Image bewegungsImage;
		if (bv.getBewegung() instanceof BVBewegungAbstossung) {
			bewegungsImage = abstossung;
		} else {
			bewegungsImage = attraktion;
		}
		double winkelInGrad = bv.getRotationGradImUhrzeigersinn();
		int x = (int) (p.x - bv.getSeitenlaenge() / 2);
		int y = (int) (p.y - bv.getSeitenlaenge() / 2);
		zeichneGedrehtesBild(gc, bvImage, bewegungsImage, bv.getName(), winkelInGrad, x, y);
	}

	/**
	 * Zeichnet das Signal.
	 */
	private void zeichneSignal(GraphicsContext gc, Vektor2 signal) {
		int breite = 10;
		int offset = 2;
		Point p = welt2BildKoordinaten(signal);
		gc.setFill(Color.BLACK);
		gc.fillOval(p.x - breite / 2 - offset, p.y - breite / 2 - offset, breite + offset * 2, breite + offset * 2);
		gc.setFill(Color.YELLOW);
		gc.fillOval(p.x - breite / 2, p.y - breite / 2, breite, breite);
	}

	/**
	 * Update der Zeichenflaeche, sobald sich bei einem der Observables aendert.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Zeichenroutine wird im JavaFX-Thread aufgerufen.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				zeichneSimulation();
			}
		});
	}
}
