package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {

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

        System.out.println("HelloWorld");

    }
}
