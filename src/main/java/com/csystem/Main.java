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

        //new NoteBookMachine();
        // 上一行注释掉的代码代表图形界面，取消上一行的注释并注释掉下方所有代码即可运行图形界面

        Reader reader = new FileReader("src/main/java/test.txt");
        data_operator dataOperator = new data_operator();
        data_code dataCode = new data_code();
        FileProcessor fileProcessor = new FileProcessor(reader , dataOperator , dataCode);
        data_defineData dataDefineData = new data_defineData();

        code_processor codeProcessor = new code_processor(dataCode, dataDefineData);
        codeOpen.CodeOpen(dataCode,dataDefineData);

        LangIsOperate.defineIsOperate(dataCode,dataDefineData);
        OperateAnswer.getCodeOperate(dataCode,dataDefineData);
        OperateAnswer.defineEndAnswer(dataDefineData);
        theEndStep.end(dataCode,dataDefineData);
        System.out.println("Happy");
    }
}
