package com.test.rpn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RPNCalculatorImpl implements RPNCalculator {

    private Stack<Double> result = new Stack<>();
    private Stack<Double> resultCopy = new Stack<>();
    private Stack<Double> resultCopyForDoubleUndo = new Stack<>();
    private DecimalFormat formatter = new DecimalFormat("#.##########");


    public void performAction(final String inputVal) {

        List<String> dataList = new ArrayList<>(Arrays.asList(inputVal.split(RPNCalculator.separator)));

        for (int position = 0; position < dataList.size(); ) {
                String data = dataList.get(position);
                Double value1 = null;
                try {
                    if ( ifDataInAnyOperationTakeCopyOfResultAndPopFirstElement(data)) {
                        position++;
                        resultCopyForDoubleUndo = (Stack<Double>) resultCopy.clone();
                        resultCopy = (Stack<Double>) result.clone();
                        value1 = result.pop();
                    }
                    switch (data) {
                        case "+":
                            result.push(result.pop() + value1);
                            break;
                        case "-":
                            result.push(result.pop() - value1);
                            break;
                        case "*":
                            result.push(result.pop() * value1);
                            break;
                        case "/":
                            result.push(result.pop() / value1);
                            break;
                        case "sqrt":
                            result.push(Math.sqrt(value1));
                            break;
                        case "clear":
                            result.clear();
                            break;
                        case "undo":
                            position = performUndoCheck(dataList, position);
                            break;
                        default:
                            position++;
                            result.push(Double.parseDouble(data));
                    }

                } catch ( NumberFormatException nfe) {
                    System.err.println("Operator " + data + "( position: " + position +"): insufficient parameters");
                    return;
                }
                catch (Exception e) {
                    System.err.println("Operator " + data + "( position: " + position +"): insufficient parameters");
                    result = resultCopy;
                    return;
                }
            }
    }

    private int performUndoCheck(List<String> dataList, int i) {
        i++;
        if (i < dataList.size() && dataList.get(i).equals("undo") && !resultCopyForDoubleUndo.isEmpty()) {
            i++;
            copy(resultCopyForDoubleUndo);
        } else if (resultCopy.size() != 0) {
            copy(resultCopy);
        } else {
            result.pop();
        }
        return i;
    }

    private boolean ifDataInAnyOperationTakeCopyOfResultAndPopFirstElement(String data) {
        return data.equals(RPNCalculator.ADD) || data.equals(RPNCalculator.DIVIDE)
                || data.equals(RPNCalculator.MULTIPLY) || data.equals(RPNCalculator.SUBTRACT)
                || data.equals(RPNCalculator.SQUAREROOT) || data.equals(RPNCalculator.CLEAR);
    }

    public List<String> getResult() {
        return result.stream().map(doubleVal -> formatter.format(doubleVal)).collect(Collectors.toList());
    }

    private void copy(Stack<Double> numbersCopy) {
        result = (Stack<Double>)numbersCopy.clone();
        numbersCopy.clear();
    }
}
