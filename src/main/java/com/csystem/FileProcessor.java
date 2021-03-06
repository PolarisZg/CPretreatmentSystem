package com.csystem;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

class FileProcessor {
    Reader reader;
    data_operator dataOperator;
    data_code dataCode;

    FileProcessor(Reader reader , data_operator dataOperator , data_code dataCode){
        this.reader = reader;
        this.dataOperator = dataOperator;
        this.dataCode = dataCode;
        startFileProcessor();
    }

    private void startFileProcessor() {
        {
            try {
                int i;
                while (true) {
                    i = reader.read();
                    StringBuilder temp = new StringBuilder();
                    while (dataOperator.dataSearch(String.valueOf((char) i)) == null) {
                        temp.append((char) i);
                        i = reader.read();
                        if (i == -1) {
                            break;
                        }
                    }
                    if (i == -1) break;
                    if (temp.length() > 0)
                        dataCode.addWord(temp.toString());
                    if(i == '\t' || i == ' '){
                        //dataCode.addWord(String.valueOf(' '));
                        continue;
                    }
                    if (i == '\r' || i == '\n') {
                        dataCode.nextLine();
                    } else {
                        if(dataOperator.dataSearch((dataCode.getBottomWord() + String.valueOf((char) i))) == null)
                            dataCode.addWord(String.valueOf((char) i));
                        else
                            dataCode.replaceBottomWord(String.valueOf((char) i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataCode.batterCode();
        }
    }

    static void Hex2Dec(data_code dataCode){
        for(int i = 0 ; i < dataCode.allCode.size() ; i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            for(int j = 0 ; j < arrayList.size() ; j++){
                String string = (String) arrayList.get(j);
                if(etc.typeString(string).equals("intHex")){
                    arrayList.set(j,String.valueOf(Integer.parseInt(string.substring(2), 16)));
                }
            }
        }
    }
}
