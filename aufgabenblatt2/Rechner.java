package aufgabenblatt2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Rechner {

	public static enum Operation { ADDITION, SUBTRAKTION, MULTIPLIKATION, DIVISION }
	
	private static Map<Operation, BinaryOperator<Double>> operator;
	
	public static void init() {
		operator = new HashMap<Operation, BinaryOperator<Double>>();
		operator.put(Operation.ADDITION, (op1, op2) -> op1 + op2);
		operator.put(Operation.SUBTRAKTION, (op1, op2) -> op1 - op2);
		operator.put(Operation.MULTIPLIKATION, (op1, op2) -> op1 * op2);
		operator.put(Operation.DIVISION, (op1, op2) -> op1 / op2);
	}

	public static double berechne(Operation operation, double op1, double op2) {
		Rechner.init();
		BinaryOperator<Double> oper = operator.get(operation);
		return oper.apply(op1, op2); 
	}
	
	public static void main(String[] args) {
		System.out.println(Rechner.berechne(Operation.ADDITION, 20, 22));
		System.out.println(Rechner.berechne(Operation.SUBTRAKTION, 36, 13));
		System.out.println(Rechner.berechne(Operation.MULTIPLIKATION, 21, 2));
		System.out.println(Rechner.berechne(Operation.DIVISION, 46, 2));
		DoubleDoubleZuDouble ddzd = (op1, op2) -> op1 * op2;
		System.out.println(ddzd.werteAus(21, 2));
		ddzd = (op1, op2) -> (0-op2)/op1;
		System.out.println(ddzd.werteAus(3, -69));
	}

}
