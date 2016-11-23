package aufgabenblatt3;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application implements Observer {
	private final static int MAX_HEIGHT = 50;
	private final static int MAX_WIDTH = 100;
	private final static int ANZAHL_GLEISE = 10;
	private Background green = new Background(
			new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
	private Background red = new Background(
			new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));

	private Thread simThread;
	private Simulation sim;
	private VBox root;
	private HBox[] gleise;
	private Rectangle[] zuege;

	@Override
	public void start(Stage primaryStage) {
		try {
			simThread = new Thread(sim);
			primaryStage.setTitle("Rangierbahnhof");
			primaryStage.setScene(new Scene(root, 450, 500));
			simThread.start();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		sim = new Simulation(new Rangierbahnhof(ANZAHL_GLEISE));
		sim.getBahnhof().addObserver(this);
		root = new VBox(5);
		gleise = new HBox[sim.getBahnhof().getAnzahlGleise()];
		for (int i = 0; i < gleise.length; i++) {
			gleise[i] = new HBox(5);
			gleise[i].setMinWidth(MAX_WIDTH + 10);
			gleise[i].setMinHeight(MAX_HEIGHT + 10);
			gleise[i].setMaxWidth(gleise[i].getMinWidth() * 2);
			gleise[i].setMaxHeight(gleise[i].getMinHeight() * 2);
			gleise[i].setBackground(green);
			gleise[i].setLayoutY(5);
			root.getChildren().add(gleise[i]);
		}
		zuege = new Rectangle[gleise.length];
		for (int i = 0; i < zuege.length; i++) {
			zuege[i] = new Rectangle();
			initZug(zuege[i], i);
			gleise[i].getChildren().add(
					new Label(String.format("Gleis Nr.: %3d", i + 1)));
			gleise[i].getChildren().add(zuege[i]);
			gleise[i].setBackground(red);
			gleise[i].setLayoutX(5);
		}
	}

	private void initZug(Rectangle zug, int i) {
		zug.setWidth(MAX_WIDTH);
		zug.setHeight(MAX_HEIGHT);
		zug.setFill(Color.TRANSPARENT);
		zug.setStroke(Color.BLACK);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void update(Observable o, Object arg) {
		Zug[] abstellGleise = sim.getBahnhof().getGleise();
		for (int i = 0; i < abstellGleise.length; i++) {
			if (abstellGleise[i] != null) {
				gleise[i].setBackground(red);
				zuege[i].setVisible(true);
			} else {
				gleise[i].setBackground(green);
				zuege[i].setVisible(false);
			}
		}
	}
}
