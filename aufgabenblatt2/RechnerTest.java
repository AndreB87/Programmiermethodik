package aufgabenblatt2;

import static org.junit.Assert.*;

import org.junit.Test;

import aufgabenblatt2.Rechner.Operation;

/**
 * Testet die Klasse Rechner
 * 
 * @author andre
 *
 */
public class RechnerTest {
	/**
	 * Testet die Berechnung mit dem BinaryOperator
	 */
	@Test
	public void testBinaryOp() {
		assertEquals(Rechner.berechne(Operation.MULTIPLIKATION, 140, 300000), 42000000.0, 0.00001);
		assertEquals(Rechner.berechne(Operation.ADDITION, 29, 13), 42.0, 0.00001);
		assertEquals(Rechner.berechne(Operation.DIVISION, 2.94, 7), 0.42, 0.00001);
		assertEquals(Rechner.berechne(Operation.SUBTRAKTION, 53, 11), 42.0, 0.00001);
	}
	
	/**
	 * Testet die Berechnung mit der eigenen Implementierung DoubleDoubleZuDouble
	 */
	public void testDoubleDoubleZuDouble() {
		DoubleDoubleZuDouble ddzd = (op1, op2) -> op1 * op2;
		assertEquals(ddzd.werteAus(280, 150000), 42000000.0, 0.00001);
		ddzd = (op1, op2) -> op1 / op2;
		assertEquals(ddzd.werteAus(84, 0.0002), 420000.0, 0.00001);
	}
}
