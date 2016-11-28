/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package aufgabenblatt4;

import aufgabenblatt4.braitenbergvehikel.BVBewegungAbstossung;
import aufgabenblatt4.braitenbergvehikel.BVBewegungAttraktion;
import aufgabenblatt4.braitenbergvehikel.BVSimulation;
import aufgabenblatt4.braitenbergvehikel.BraitenbergVehikel;
import aufgabenblatt4.braitenbergvehikel.Vektor2;
import aufgabenblatt4.view.BVCanvas;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */
public class BVAnwendung extends Application {

	private final static int KORREKTUR_X = -25;
	private BVSimulation sim;
	private BVCanvas canvas;
	private BorderPane wurzel;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Braitenberg-Vehikel!");
		primaryStage.setScene(new Scene(wurzel, 850, 600));
		primaryStage.show();
	}

	/**
	 * Initialisiert de Bestandteile der Anwendung
	 */
	@Override
	public void init() {
		// Simulation erstellen
		sim = erzeugeSimulationsszene();

		// Canvas erstellen
		canvas = new BVCanvas(600, 600, sim);
		canvas.zeichneSimulation();

		//Knopf und Checkbox initialisieren
		Button knopf = new Button("Simuliere!");
		CheckBox checkBox = sim.getCheckBox();
		knopf.setOnAction(event -> sim.simulationsSchritt());
		wurzel = new BorderPane();
		wurzel.setCenter(canvas);
		
		//EventHandler im Center der BorderPane anmelden
		wurzel.getCenter().addEventHandler
			(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sim.setSignal
					(event.getSceneX() - (canvas.getWidth() / 2) + KORREKTUR_X, 
						(canvas.getHeight() / 2) - event.getSceneY());
			}
		});
		
		//GridPane für die Steuerung der Vehikel erstellen
		GridPane rightPane = new GridPane();
		wurzel.setRight(rightPane);
		rightPane.add(knopf, 0, 0);
		rightPane.add(checkBox, 0, 1);
		for (int i = 0; i < sim.getAnzahlVehike(); i++) {
			rightPane.add(new Label(sim.getVehikel(i).getName()), 0, i + 2);
			rightPane.add(sim.getVehikel(i).getComboBox(), 1, i + 2);
		}
	}

	/**
	 * Erzeugt eine Simulationsszene zum Testen.
	 */
	public BVSimulation erzeugeSimulationsszene() {
		BraitenbergVehikel vehikel1 = new BraitenbergVehikel("Susi", new BVBewegungAttraktion());
		BraitenbergVehikel vehikel2 = new BraitenbergVehikel("Mike", new BVBewegungAbstossung(), new Vektor2(-100, 100),
				new Vektor2(1, 0));

		BVSimulation sim = new BVSimulation();
		sim.vehikelHinzufuegen(vehikel1);
		sim.vehikelHinzufuegen(vehikel2);
		return sim;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
