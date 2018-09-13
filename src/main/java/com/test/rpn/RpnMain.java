package com.test.rpn;

import java.util.*;

public class RpnMain {
    public static void main(String s[]) {
        Scanner sc = new Scanner(System.in);
        String data = "";
        RPNCalculator calculator = new RPNCalculatorImpl();

        while (!data.equals("c")) {
            data = sc.nextLine();
            calculator.performAction(data);
            System.out.println("Stack :" +  calculator.getResult());
        }
    }
}
