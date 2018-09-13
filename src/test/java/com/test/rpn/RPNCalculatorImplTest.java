package com.test.rpn;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RPNCalculatorImplTest {
    private RPNCalculator calculator;

    private StringBuffer calculateList = new StringBuffer("12 3");

    private List<String> responseStack = new ArrayList<>();

    @Before
    public void setUp() {
        calculator = new RPNCalculatorImpl();
    }

    @org.junit.Test
    public void calculateDivision() {
        calculateList.append(" /");
        responseStack.add("4");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }
    @org.junit.Test
    public void calculateAddition() {
        calculateList.append(" +");
        responseStack.add("15");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }
    @org.junit.Test
    public void calculateSubtraction() {
        calculateList.append(" -");
        responseStack.add("9");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }
    @org.junit.Test
    public void calculateMultiplication() {
        calculateList.append(" *");
        responseStack.add("36");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }

    @org.junit.Test
    public void calculateMultipleOperation() {
        responseStack.add("0.7745966692");
        String calculateListMultiOperation1 = "4 1 3 2 - * - 5 / sqrt";
        calculator.performAction(calculateListMultiOperation1);
        assertEquals(calculator.getResult(),responseStack);
    }

    @org.junit.Test
    public void testEngineeringNotation() {
        responseStack.add("0.0000000051");
        String enggNotation = "4 777777777 /";
        calculator.performAction(enggNotation);
        assertEquals(calculator.getResult(),responseStack);

    }
    @org.junit.Test
    public void testUndo() {
        responseStack.add("36");
        String undoList = "12 3 undo undo 2 7 + 4 *";
        calculator.performAction(undoList);
        assertEquals(calculator.getResult(),responseStack);
        responseStack.add("76");
        undoList = "12 3 undo 7 + 4 *";
        calculator.performAction(undoList);
        assertEquals(calculator.getResult(),responseStack);

    }
    @org.junit.Test
    public void testClear() {
        calculateList.append(" clear");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }

    @org.junit.Test
    public void testExceptionCaseInvalidNumber() {
        responseStack.add("12");
        responseStack.add("3");
        calculateList.append(" &");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }

    @org.junit.Test
    public void testExceptionCaseInvalidOperand() {
        responseStack.add("36");
        calculateList.append(" * -");
        calculator.performAction(calculateList.toString());
        assertEquals(calculator.getResult(),responseStack);
    }

}