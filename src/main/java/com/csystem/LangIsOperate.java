package com.csystem;

import java.util.ArrayList;
import java.util.List;

public class LangIsOperate {
    private static boolean isOperate(List arrayList, data_defineData dataDefineData){
        if(arrayList == null || arrayList.size() == 0){
            return false;
        }
        if(dataDefineData.dataSearch((String)arrayList.get(0)) != null && dataDefineData.dataSearch((String)arrayList.get(0)).object_isOperate)    // s->num
            return true;
        if(!etc.typeString((String) arrayList.get(0)).equals("String")){ // s->s
            return true;
        }
        if(arrayList.size() >= 3) {
            String string1 = (String) arrayList.get(1);
            String string2 = (String) arrayList.get(2);
            String stringEnd = (String) arrayList.get(arrayList.size() - 1);
            int a1 = arrayList.indexOf("?");
            int a2 = arrayList.indexOf(":");
            if (string1.equals("(") && stringEnd.equals(")")) {    // s->(s)
                return isOperate(arrayList.subList(2, arrayList.size() - 1), dataDefineData);
            }
            else if (dataDictionary.unaryPreOperatorMap.get(string1) != null) {   // s->op s
                return isOperate(arrayList.subList(2, arrayList.size()), dataDefineData);
            }
            else if (dataDictionary.unaryAftOperatorMap.get(stringEnd) != null) {  // s->s op
                return isOperate(arrayList.subList(1, arrayList.size() - 1), dataDefineData);
            }
            else if (dataDictionary.binaryOperatorMap.get(string2) != null){    // s ->s op s
                return isOperate(arrayList.subList(1,2),dataDefineData)
                        &&
                        isOperate(arrayList.subList(3,arrayList.size()),dataDefineData);
            }
            else if(a1 != -1 && a2 != -1){  // s -> s ? s : s
                return isOperate(arrayList.subList(0,a1),dataDefineData)
                        &&
                        isOperate(arrayList.subList(a1+1,a2),dataDefineData)
                        &&
                        isOperate(arrayList.subList(a2 + 1, arrayList.size()),dataDefineData);
            }
        }
        return false;
    }

    static void defineIsOperate(data_code dataCode, data_defineData dataDefineData){
        for(int i = 0 ; i < dataCode.allCode.size(); i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            if(arrayList.get(0).equals("#define")){
                data_object dataObject = dataDefineData.dataSearch((String) arrayList.get(1));
                dataObject.object_isOperate = isOperate(arrayList.subList(1,arrayList.size()),dataDefineData);
                dataDefineData.dataWrite((String) arrayList.get(1),dataObject);
            }
        }
    }
}

