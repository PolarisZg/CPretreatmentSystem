package com.csystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class etc {
    static String typeString(String s){
        try {
            if(s.charAt(0) == '0' && s.charAt(1) == 'x'){
                int i = Integer.parseInt(s.substring(2) , 16);
                return "intHex";
            }
            int i = Integer.parseInt(s);
            return "int";
        } catch (NumberFormatException e0){
            try {
                double j = Float.parseFloat(s);
                return "double";
            }
            catch (NumberFormatException e1){
                return "String";
            }
        }
    }
}
