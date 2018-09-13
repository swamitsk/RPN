package com.test.rpn;

import java.util.List;

public interface RPNCalculator {

    String separator = " ";
    String ADD = "+";
    String SUBTRACT = "-";
    String DIVIDE = "/";
    String MULTIPLY = "*";
    String SQUAREROOT = "sqrt";
    String CLEAR = "clear";
    List<String> getResult();
    void performAction(String content);
}
