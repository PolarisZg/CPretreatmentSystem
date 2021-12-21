package com.csystem;

import java.util.ArrayList;

public class theEndStep {
    static void end(data_code dataCode,data_defineData dataDefineData){
        for(int i = 0 ; i < dataCode.allCode.size(); i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            if(arrayList.get(0).equals("#define")){
                String value = (String) arrayList.get(1);
                data_object dataObject = dataDefineData.dataSearch(value);
                arrayList.clear();
                arrayList.add("#define");
                arrayList.add(value);
                if(dataObject.object_isOperate){
                    arrayList.add(String.valueOf(dataObject.operate.get(0)));
                }
                else{
                    StringBuffer stringBuffer = new StringBuffer();
                    for(int k = 0 ; k < dataObject.object_valueStr.size() ; k++)
                        stringBuffer.append(String.valueOf(dataObject.object_valueStr.get(k)));
                    arrayList.add(stringBuffer.toString());
                }
            }
            else{
                for(int j = 0 ; j < arrayList.size();j++){
                    String value = (String) arrayList.get(j);
                    if(dataDefineData.dataSearch(value) != null){
                        data_object dataObject = dataDefineData.dataSearch(value);
                        if(dataObject.object_isOperate){
                            arrayList.set(j,String.valueOf(dataObject.operate.get(0)));
                        }
                        else{
                            StringBuffer stringBuffer = new StringBuffer();
                            for(int k = 0 ; k < dataObject.object_valueStr.size() ; k++)
                                stringBuffer.append(String.valueOf(dataObject.object_valueStr.get(k)));
                            arrayList.set(j,stringBuffer.toString());
                        }
                    }
                }
            }
        }
    }
}
