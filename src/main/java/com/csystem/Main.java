package com.csystem;

import com.googlecode.aviator.AviatorEvaluator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {

        dataDictionary.ini();
        Reader reader = new FileReader("src/main/java/test.txt");
        data_operator dataOperator = new data_operator();
        data_code dataCode = new data_code();
        FileProcessor fileProcessor = new FileProcessor(reader , dataOperator , dataCode);
        data_defineData dataDefineData = new data_defineData();

//        for(int i = 0 ; i < dataCode.allCode.size() ; i++){
//            ArrayList arrayList = dataCode.allCode.get(i);
//            for (Object o : arrayList) {
//                System.out.print(o);
//            }
//            System.out.println();
//        }

        code_processor codeProcessor = new code_processor(dataCode, dataDefineData);
        codeOpen.CodeOpen(dataCode,dataDefineData);

        LangIsOperate.defineIsOperate(dataCode,dataDefineData);
        OperateAnswer.getCodeOperate(dataCode,dataDefineData);
        OperateAnswer.defineEndAnswer(dataDefineData);
        theEndStep.end(dataCode,dataDefineData);
//        Long result = (Long) AviatorEvaluator.execute("1+2*(2+6>>1)+3");
//
//        System.out.println(result);
        System.out.println("Happy");
    }
}
