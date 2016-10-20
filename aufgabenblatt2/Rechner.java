package aufgabenblatt2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Rechner {

	public enum Operation { ADDITION, SUBTRAKTION, MULTIPLIKATION, DIVISION }
	
	private Map<Operation, BinaryOperator<Double>> operator;
	
	private BinaryOperator<Double> add = (op1, op2) -> op1 + op2;
	private BinaryOperator<Double> sub = (op1, op2) -> op1 - op2;
	private BinaryOperator<Double> mult = (op1, op2) -> op1 * op2;
	private BinaryOperator<Double> div = (op1, op2) -> op1 / op2;
	
	public Rechner() {
		operator = new HashMap<Operation, BinaryOperator<Double>>();
		operator.put(Operation.ADDITION, add);
		operator.put(Operation.SUBTRAKTION, sub);
		operator.put(Operation.MULTIPLIKATION, mult);
		operator.put(Operation.DIVISION, div);
	}

	public double berechne(Operation operation, double op1, double op2) {
		BinaryOperator<Double> oper = operator.get(operation);
		return oper.apply(op1, op2); 
	}
	
	public static void main(String[] args) {
		Rechner rechner = new Rechner();
		System.out.println(rechner.berechne(Operation.ADDITION, 20, 22));
		System.out.println(rechner.berechne(Operation.SUBTRAKTION, 36, 13));
		System.out.println(rechner.berechne(Operation.MULTIPLIKATION, 21, 2));
		System.out.println(rechner.berechne(Operation.DIVISION, 46, 2));
		DoubleDoubleZuDouble ddzd = (op1, op2) -> op1 * op2;
		System.out.println(ddzd.werteAus(21, 2));
		ddzd = (op1, op2) -> (0-op2)/op1;
		System.out.println(ddzd.werteAus(3, -69));
	}

}
