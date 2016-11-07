package aufgabenblatt2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
/**
 * Repraesentiert einen Rechner, der die vier Grundrechenarten beherrscht.
 * 
 * @author andre
 *
 */
public class Rechner {
	/**
	 * Enum mit den vier Grundrechenarten.
	 * @author andre
	 *
	 */
	public static enum Operation { ADDITION, SUBTRAKTION, MULTIPLIKATION, DIVISION }
	
	/**
	 * Map, in der die Operationen mit den Enum-Werten als Schluessel gespeichert werden.
	 */
	private static Map<Operation, BinaryOperator<Double>> operator;
	
	/**
	 * Initialisieren der Map mit den Rechenarten.
	 */
	public static void init() {
		operator = new HashMap<Operation, BinaryOperator<Double>>();
		operator.put(Operation.ADDITION, (op1, op2) -> op1 + op2);
		operator.put(Operation.SUBTRAKTION, (op1, op2) -> op1 - op2);
		operator.put(Operation.MULTIPLIKATION, (op1, op2) -> op1 * op2);
		operator.put(Operation.DIVISION, (op1, op2) -> op1 / op2);
	}

	/**
	 * Wendet eine der vier Grundrechenarten auf die uebergebenen Zahlen an.
	 * 
	 * @param operation Enu-Wert der Rechenart
	 * @param op1		Erste Zahl fuer die Berechnung
	 * @param op2		Zweite Zahl fuer die Berechnung
	 * @return			Ergebnis der Berechnung
	 */
	public static double berechne(Operation operation, double op1, double op2) {
		Rechner.init();
		BinaryOperator<Double> oper = operator.get(operation);
		return oper.apply(op1, op2); 
	}
}
