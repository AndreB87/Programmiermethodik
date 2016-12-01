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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	/**
	 * Simulation, die in der Anwendung dargestellt wird
	 */
	private BVSimulation sim;
	
	/**
	 * Welt, in der sich die Vehikel bewegen
	 */
	private BVCanvas canvas;
	
	/**
	 * Pane, in der die Simulation abgelegt wird
	 */
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

		// Knopf und Checkbox initialisieren
		Button knopf = new Button("Simuliere!");
		CheckBox checkBox = new CheckBox("Simuliere");
		checkBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (checkBox.isSelected()) {
					sim.setInterrupted(!checkBox.isSelected());
					Thread thread = new Thread(sim);
					thread.start();
				} else {
					sim.setInterrupted(!checkBox.isSelected());
				}
			}
		});
		knopf.setOnAction(event -> sim.simulationsSchritt());
		wurzel = new BorderPane();
		wurzel.setCenter(canvas);

		// GridPane für die Steuerung der Vehikel erstellen
		GridPane rightPane = new GridPane();
		wurzel.setRight(rightPane);
		rightPane.add(knopf, 0, 0);
		rightPane.add(checkBox, 0, 1);
		for (int i = 0; i < sim.getAnzahlVehike(); i++) {
			rightPane.add(new Label(sim.getVehikel(i).getName()), 0, i + 2);
			VehikelMitBox vehikel = new VehikelMitBox(sim.getVehikel(i));
			rightPane.add(vehikel.getComboBox(), 1, i + 2);
		}
	}

	/**
	 * Erzeugt eine Simulationsszene zum Testen.
	 */
	public BVSimulation erzeugeSimulationsszene() {
		BraitenbergVehikel vehikel1 = new BraitenbergVehikel
				("Susi", new BVBewegungAttraktion());
		BraitenbergVehikel vehikel2 = new BraitenbergVehikel
				("Mike", new BVBewegungAbstossung(), new Vektor2(-100, 100),
				new Vektor2(1, 0));

		BVSimulation sim = new BVSimulation();
		sim.vehikelHinzufuegen(vehikel1);
		sim.vehikelHinzufuegen(vehikel2);
		return sim;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Bindet eine ComboBox an ein Vehikel
	 * 
	 * @author andre
	 *
	 */
	public class VehikelMitBox {
		/**
		 * BraitenbergVehikel, an dem die ComboBox gebunden wird
		 */
		private BraitenbergVehikel vehikel;

		/**
		 * ComboBox des Vehikels
		 */
		private ComboBox<String> comboBox;

		public VehikelMitBox(BraitenbergVehikel vehikel) {
			this.vehikel = vehikel;
			comboBox = new ComboBox<String>
				(FXCollections.observableArrayList("Abstossung", "Attraktion"));
			if (vehikel.getBewegung() instanceof BVBewegungAbstossung) {
				comboBox.setValue("Abstossung");
			} else {
				comboBox.setValue("Attraktion");
			}
			comboBox.setOnAction(event -> comboBoxAction());
		}

		/**
		 * Umstellung der Bewegungsart der Vehikel anhand der Auswahl der
		 * ComboBox
		 */
		public void comboBoxAction() {
			if (comboBox.getValue().equals("Abstossung")) {
				vehikel.setBewegung(new BVBewegungAbstossung());
			} else {
				vehikel.setBewegung(new BVBewegungAttraktion());
			}
		}

		public BraitenbergVehikel getVehikel() {
			return vehikel;
		}

		public ComboBox<String> getComboBox() {
			return comboBox;
		}
	}
}
