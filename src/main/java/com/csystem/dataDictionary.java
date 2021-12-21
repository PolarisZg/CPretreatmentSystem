package com.csystem;

import java.util.HashMap;

public class dataDictionary {
    static HashMap<String, Integer> binaryOperatorMap = new HashMap<>();
    private static void iniBinaryOperatorMap(){
        binaryOperatorMap.put("*", 0);
        binaryOperatorMap.put("/", 1);
        binaryOperatorMap.put("%", 2);
        binaryOperatorMap.put("+", 3);
        binaryOperatorMap.put("-", 4);

        binaryOperatorMap.put(">", 5);
        binaryOperatorMap.put("<", 6);
        binaryOperatorMap.put(">=", 7);
        binaryOperatorMap.put("<=", 8);
        binaryOperatorMap.put("==", 9);
        binaryOperatorMap.put("!=", 10);

        binaryOperatorMap.put("&&", 11);
        binaryOperatorMap.put("||", 12);

        binaryOperatorMap.put("<<", 13);
        binaryOperatorMap.put(">>", 14);
        binaryOperatorMap.put("|", 15);
        binaryOperatorMap.put("&", 16);
        binaryOperatorMap.put("^", 17);

        binaryOperatorMap.put(",", 18);
    }

    static int[][] priorityTab = {
                        /*  *   /   %   +   -   >   <   >=  <=  ==  !=  &&  ||  <<  >>  |   &   ^   ,   !   ~   */
            /*  *   */  {   0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, -1, -1},
            /*  /   */  {   0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, -1, -1},
            /*  %   */  {   0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, -1, -1},
            /*  +   */  {  -1, -1, -1,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, -1, -1},
            /*  -   */  {  -1, -1, -1,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, -1, -1},
            /*  >   */  {  -1, -1, -1, -1, -1,  0,  0,  0,  0,  1,  1,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /*  <   */  {  -1, -1, -1, -1, -1,  0,  0,  0,  0,  1,  1,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /* >=   */  {  -1, -1, -1, -1, -1,  0,  0,  0,  0,  1,  1,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /* <=   */  {  -1, -1, -1, -1, -1,  0,  0,  0,  0,  1,  1,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /* ==   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /* !=   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0,  1,  1, -1, -1,  1,  1,  1,  1, -1, -1},
            /* &&   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  1, -1, -1, -1, -1, -1,  1, -1, -1},
            /* ||   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1,  1, -1, -1},
            /* <<   */  {  -1, -1, -1, -1, -1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0,  1,  1,  1,  1, -1, -1},
            /* <<   */  {  -1, -1, -1, -1, -1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0,  1,  1,  1,  1, -1, -1},
            /*  |   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, -1, -1,  0, -1, -1,  1, -1, -1},
            /*  &   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, -1, -1,  1,  0,  1,  1, -1, -1},
            /*  ^   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, -1, -1,  1, -1,  0,  1, -1, -1},
            /*  ,   */  {  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            /*  !   */  {   1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
            /*  ~   */  {   1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0}

    };

    static HashMap<String, Integer> unaryPreOperatorMap = new HashMap<>();
    private static void iniUnaryPreOperatorMap(){
        unaryPreOperatorMap.put("!", 19);
        unaryPreOperatorMap.put("~", 20);
    }

    static HashMap<String,Integer> unaryAftOperatorMap = new HashMap<>();
    private static void iniUnaryAftOperatorMap(){
        unaryAftOperatorMap.put("++", 1);
        unaryAftOperatorMap.put("--", 2);
    }

    static int searchOperate(String s){
        int i = -1;
        if(binaryOperatorMap.get(s) != null)
            i = binaryOperatorMap.get(s);
        else if(unaryPreOperatorMap.get(s) != null){
            i = unaryPreOperatorMap.get(s);
        }
        return i;
    }
    static void ini(){
        iniBinaryOperatorMap();
        iniUnaryAftOperatorMap();
        iniUnaryPreOperatorMap();
    }
}
