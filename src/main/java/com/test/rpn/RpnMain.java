package com.test.rpn;

import java.util.*;

public class RpnMain {
    public static void main(String s[]) {
        Scanner sc = new Scanner(System.in);
        String data = "";
        RPNCalculator calculator = new RPNCalculatorImpl();

        System.out.println(" enter the calculator command, c to Close this application");
        data = sc.nextLine();

        while (!data.equals("c")) {
            calculator.performAction(data);
            System.out.println("Stack :" +  calculator.getResult());
            data = sc.nextLine();
        }
    }
}
