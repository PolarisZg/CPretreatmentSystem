package main.java;

import java.util.ArrayList;

class code_processor {
    private data_code dataCode;
    private data_defineData dataDefineData;
    code_processor(data_code dataCode, data_defineData dataDefineData) {
        this.dataCode = dataCode;
        this.dataDefineData = dataDefineData;
        startCodeProcessor();
    }

    private void startCodeProcessor() {
        for (int i = 0; i < dataCode.allCode.size(); i++) {
            ArrayList arrayList = dataCode.allCode.get(i);
            for (int j = 0; j < arrayList.size(); j++) {
                if(arrayList.get(j).equals("#define")){
                    String key = (String) arrayList.get(j + 2);
                    j = j + 4;
                    StringBuffer stringBuffer = new StringBuffer();
                    for(;j < arrayList.size(); j++){
                        stringBuffer.append(arrayList.get(j));
                    }
                    String value = stringBuffer.toString();
                    data_object dataObject;
                    if(etc.typeString(value).equals("intHex")){
                        dataObject = new data_object("int", Integer.parseInt(value.substring(2) , 16));
                    }
                    else if(etc.typeString(value).equals("int")){
                        dataObject = new data_object("int", Integer.parseInt(value));
                    }
                    else if(etc.typeString(value).equals("double")){
                        dataObject = new data_object("double", Double.parseDouble(value));
                    }
                    else
                        dataObject = new data_object("String", value);
                    dataDefineData.dataWrite(key, dataObject);
                }
            }
        }
    }
}
