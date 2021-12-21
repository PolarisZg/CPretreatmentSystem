package com.csystem;

import java.util.*;

public class OperateAnswer {
    private static ArrayList<String> getOperateAnswer(List arraylist, data_defineData dataDefineData) throws ArithmeticException{
        Stack<String> stackOperate = new Stack<>();
        Boolean isInt = true;
        Boolean isDouble = false;
        ArrayList<String> answer = new ArrayList<>();
        for(int i = 0 ; i < arraylist.size() ; i++){
            String s = (String) arraylist.get(i);
            if(dataDefineData.dataSearch(s) != null || !etc.typeString(s).equals("String")){
                answer.add(s);
            }
            else{
                if(s.equals("(")){
                    stackOperate.push("(");
                }
                else if(s.equals(")")){
                    for(String s1 = stackOperate.pop(); !s1.equals("("); s1 = stackOperate.pop()){
                        answer.add(s1);
                        if(stackOperate.empty())
                            throw new ArithmeticException();
                    }
                }
                else{
                    if(stackOperate.empty()){
                        stackOperate.push(s);
                    }
                    else{
                        String s1 = stackOperate.peek();
                        while (!stackOperate.empty()){
                            if(s1.equals("("))
                                break;
                            int pre = dataDictionary.searchOperate(s1);
                            int now = dataDictionary.searchOperate(s);
                            if(pre == -1 || now == -1)
                                throw new ArithmeticException();
                            if(dataDictionary.priorityTab[now][pre] == 1){
                                break;
                            }
                            else{
                                answer.add(s1);
                                stackOperate.pop();
                                if(stackOperate.empty())
                                    break;
                                s1 = stackOperate.peek();
                            }
                        }
                        stackOperate.push(s);
                    }
                }
            }
        }
        while (!stackOperate.empty()){
            answer.add(stackOperate.pop());
        }
        return answer;
    }

    static void getCodeOperate(data_code dataCode, data_defineData dataDefineData){
        for(int i = 0 ; i < dataCode.allCode.size() ; i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            if(arrayList.get(0).equals("#define")){
                String name = (String) arrayList.get(1);
                if(dataDefineData.dataSearch(name).object_isOperate){
                    try {
                        dataDefineData.dataSearch(name).operate = getOperateAnswer(arrayList.subList(2, arrayList.size()), dataDefineData);
                    }
                    catch(ArithmeticException a){
                        System.out.println("表达式错误");
                        break;
                    }
                }
            }
        }
    }


    static void defineEndAnswer(data_defineData dataDefineData){
        Iterator iterator = dataDefineData.data.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            data_object value = (data_object) entry.getValue();
            getTheEndAnswer(key, value.operate, dataDefineData);
        }
    }

    private static void getTheEndAnswer(String name, ArrayList<String> arrayList, data_defineData dataDefineData){
        data_object dataObject = dataDefineData.data.get(name);
        if(dataObject.object_type.equals("int") || dataObject.object_type.equals("double"))
            return;
        else{
            int intAnswer = 0;
            double doubleAnswer = 0;
            boolean isInt = true;
            for(int i = 0 ; i < arrayList.size(); i++){
                String now = arrayList.get(i);
                if(etc.typeString(now).equals("double")){
                    isInt = false;
                    break;
                }
            }
            for(int i = 0 ; i < arrayList.size(); i++){
                String now = arrayList.get(i);
                int op = dataDictionary.searchOperate(now);
                if(op!=-1){
                    if(isInt){
                        if(op < 19){
                            intAnswer = binaryAnswer(op,Integer.parseInt(arrayList.get(i - 2)),Integer.parseInt(arrayList.get(i - 1)));
                            arrayList.remove(i);
                            arrayList.remove(i - 1);
                            arrayList.set(i - 2, String.valueOf(intAnswer));
                            i = i - 2;
                            continue;
                        }
                        else {
                            intAnswer = unaryAnswer(op, Integer.parseInt(arrayList.get(i - 1)));
                            arrayList.remove(i);
                            arrayList.set(i - 1,String.valueOf(intAnswer));
                            i = i - 1;
                            continue;
                        }
                    }
                    else{
                        if(op < 19){
                            doubleAnswer = binaryAnswer(op,Double.parseDouble(arrayList.get(i - 2)),Double.parseDouble(arrayList.get(i - 1)));
                            arrayList.remove(i);
                            arrayList.remove(i - 1);
                            arrayList.set(i - 2, String.valueOf(doubleAnswer));
                            i = i - 2;
                            continue;
                        }
                        else {
                            doubleAnswer = unaryAnswer(op, Double.parseDouble(arrayList.get(i - 1)));
                            arrayList.remove(i);
                            arrayList.set(i - 1,String.valueOf(doubleAnswer));
                            i = i - 1;
                            continue;
                        }
                    }
                }
            }
            if(isInt){
                dataObject.object_type = "int";
                dataObject.object_valueInt = intAnswer;
                dataDefineData.dataWrite(name,dataObject);
                return;
            }
            else{
                dataObject.object_type = "double";
                dataObject.object_valueDou = doubleAnswer;
                dataDefineData.dataWrite(name,dataObject);
                return;
            }
        }
    }
    private static int binaryAnswer(int binary, int i1, int i2){
        switch (binary){
            case 0: return i1*i2;
            case 1: return i1/i2;
            case 2: return i1%i2;
            case 3: return i1+i2;
            case 4: return i1-i2;
            case 5: return i1>i2?1:0;
            case 6: return i1<i2?1:0;
            case 7: return i1>=i2?1:0;
            case 8: return i1<=i2?1:0;
            case 9: return i1==i2?1:0;
            case 10:return i1!=i2?1:0;
            case 11:return i1!=0&&i2!=0?1:0;
            case 12:return i1==0&&i2==0?0:1;
            case 13:return i1<<i2;
            case 14:return i1>>i2;
            case 15:return i1|i2;
            case 16:return i1&i2;
            case 17:return i1^i2;
            case 18:return i2;
        }
        throw new ArithmeticException();
    }

    private static double binaryAnswer(int binary, double i1, double i2){
        switch (binary){
            case 0: return i1*i2;
            case 1: return i1/i2;
            case 2: return i1%i2;
            case 3: return i1+i2;
            case 4: return i1-i2;
            case 5: return i1>i2?1:0;
            case 6: return i1<i2?1:0;
            case 7: return i1>=i2?1:0;
            case 8: return i1<=i2?1:0;
            case 9: return i1==i2?1:0;
            case 10:return i1!=i2?1:0;
            case 11:return i1!=0&&i2!=0?1:0;
            case 12:return i1==0&&i2==0?0:1;
            case 18:return i2;
        }
        System.out.println("可能出现了浮点数的位运算");
        throw new ArithmeticException();
    }

    private static int unaryAnswer(int unary, int i1){
        switch (unary){
            case 19: return i1==0?1:0;
            case 20: return ~i1;
        }
        throw new ArithmeticException();
    }

    private static double unaryAnswer(int unary, double i1){
        switch (unary){
            case 19: return i1==0?1:0;
        }
        System.out.println("可能出现了浮点数的位运算");
        throw new ArithmeticException();
    }
}