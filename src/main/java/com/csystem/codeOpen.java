package com.csystem;

import java.util.ArrayList;

public class codeOpen {
    static void replace(ArrayList arrayList1, int location , ArrayList arrayList2){
        int size2 = arrayList2.size();
        for(int i = 0 ; i < size2 ; i++){
            arrayList1.add(i + location, arrayList2.get(i));
        }
        arrayList1.remove(location - 1);
    }
    static void CodeOpen(data_code dataCode, data_defineData dataDefineData){
        for(int i = 0 ; i < dataCode.allCode.size(); i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            if(arrayList.get(0).equals("#define")){
                String key = (String) arrayList.get(1);
                if(dataDefineData.dataSearch(key).object_type.equals("String")){
                    for(int j = 2 ; j < arrayList.size() ; j++){
                        String key1 = (String) arrayList.get(j);
                        if(dataDefineData.dataSearch(key1) != null){
                            replace(arrayList,j + 1, dataDefineData.dataSearch(key1).object_valueStr);
                        }
                    }
                    dataDefineData.dataSearch(key).mustDo(arrayList);
                }
            }
        }
    }
}
