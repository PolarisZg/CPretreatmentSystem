package com.csystem;

import java.util.HashMap;
import java.util.Stack;

public class numOperate {
    static HashMap<String, Integer> mapOperate;
    boolean isInt;
    boolean isDouble;
    boolean isNaN = true;
    Stack<Integer> stackNum;
    Stack<String> stackOperate;

    static void iniMapOperate(){
        mapOperate = new HashMap<>();
        int class_0 = 0;
        mapOperate.put("*", class_0);
        mapOperate.put("/", class_0);
        mapOperate.put("%", class_0);

        mapOperate.put("+", class_0 + 1);
        mapOperate.put("-", class_0 + 1);

        mapOperate.put("<<", class_0 + 2);
        mapOperate.put(">>", class_0 + 2);

        mapOperate.put(">", class_0 + 3);
        mapOperate.put("<", class_0 + 3);
        mapOperate.put(">=", class_0 + 3);
        mapOperate.put("<=", class_0 + 3);

        mapOperate.put("==", class_0 + 4);
        mapOperate.put("!=", class_0 + 4);

        mapOperate.put("&", class_0 + 5);

        mapOperate.put("^", class_0 + 6);

        mapOperate.put("|", class_0 + 7);


    }
}
