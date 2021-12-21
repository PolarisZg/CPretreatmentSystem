package com.csystem;

import java.util.ArrayList;
import java.util.HashMap;

class data_SaveWord {
    HashMap<String, Object> data = new HashMap<>();

    public Object dataSearch(String s) {
        return data.get(s);
    }

    public void dataWrite(String s, Object o) {
        data.put(s,o);
    }

    public void dataDel(String s) {
        data.remove(s);
    }
}

class data_defineData {
    HashMap<String, data_object> data = new HashMap<>();

    public data_object dataSearch(String s) {
        return data.get(s);
    }

    public void dataWrite(String s, data_object o) {
        data.put(s,o);
    }

    public void dataDel(String s) {
        data.remove(s);
    }
}

class data_operator {
    HashMap<String, String>data = new HashMap<>();
    data_operator(){
        ini();
    }

    public String dataSearch(String s) {
        return data.get(s);
    }

    public void dataWrite(String s, Object o) {
        data.put(s , (String)o);
    }

    public void dataDel(String s) {
        data.remove(s);
    }

    private void ini(){
        String[] iniData = {"+", "-" , "*" , "/", "%", "(", ")",
                            "<", "<=", "==", ">=", ">", "&&", "||", "!",
                            "<<", ">>", "~", "|", "^", "&",
                            "=", "+=", "=+", "-=", "=-", "++", "--",
                            "?", ":",
                            ",",
                            "[", "]", "[]",
                            "{", "}", ";", " ", "\n", "\r", "\t", String.valueOf((char)9)};
        /*
        * 这里添加的各个符号是为了正确分割输入的代码字符串, 最后那个 (char)9 是横向制表符 Tab 键
        * */
        for (String iniDatum : iniData) {
            dataWrite(iniDatum, iniDatum);
        }
    }
}
class data_code{
    ArrayList<ArrayList> allCode = new ArrayList<>();
    private ArrayList<String> thisLine = null;
    data_code(){
        nextLine();
    }
    void nextLine(){
        thisLine = new ArrayList<>();
        allCode.add(thisLine);
    }

    void addWord(String s){
        thisLine.add(s);
    }

    String getBottomWord(){
        if(thisLine.size() == 0)
            return "@";                         // 在这里返回 @ 是因为如果此行还没代码的话size == 0 后面get会报错，所以添加了一个肯定不会出现在Operate里面的字符'@'
        return thisLine.get(thisLine.size() - 1);
    }

    void replaceBottomWord(String s){
        thisLine.set(thisLine.size() - 1, getBottomWord() + s);
    }

    void batterCode(){
        for(int i = 0 ; i < allCode.size() ; i++){
            ArrayList arrayList = allCode.get(i);
            if(arrayList.size() == 0){
                allCode.remove(i);
                i--;
                continue;
            }
            for(int j = 1 ; j < arrayList.size() ; j++){
                if(arrayList.get(j).equals(" ") && arrayList.get(j-1).equals(" ")){
                    arrayList.remove(j);
                    j--;
                }
            }
        }
    }
}

class data_object{
    String object_type;
    int object_valueInt;
    double object_valueDou;
    String object_valueStr;
    boolean object_isOperate;
    data_object(String name, int value){
        object_type = name;
        object_valueInt = value;
        object_isOperate = true;
    }
    data_object(String name, double value){
        object_type = name;
        object_valueDou = value;
        object_isOperate = true;
    }
    data_object(String name, String value){
        object_type = name;
        object_valueStr = value;
    }
    String get_data_object(){
        return object_type;
    }
}