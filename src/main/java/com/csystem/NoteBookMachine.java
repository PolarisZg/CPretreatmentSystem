package com.csystem;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class NoteBookMachine {
    JFrame jFrame;
    JMenu jMenu;
    JMenuBar jMenuBar;
    JMenuItem jMenuItemOpen;
    JMenuItem jMenuItemSave;
    JMenuItem jMenuItemExit;
    JMenuItem jMenuItemClose;
    JTextArea jTextArea;
    data_code dataCode = new data_code();
    data_defineData dataDefineData = new data_defineData();

    NoteBookMachine(){
        jFrame = new JFrame("文本编辑器");
        jFrame.setJMenuBar(jMenuBar = new JMenuBar());
        jMenuBar.add(jMenu = new JMenu("文件"));
        (jMenuItemOpen = new JMenuItem("打开")).addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                try {
                    Reader reader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    jTextArea.setText("");

                    jTextArea.append("成功读入文件，正在分析代码结构" + System.lineSeparator());
                    data_operator dataOperator = new data_operator();
                    FileProcessor fileProcessor = new FileProcessor(reader , dataOperator , dataCode);
                    jTextArea.append("读取代码结构完成，输出代码:" + System.lineSeparator());
                    for(int i = 0 ; i < dataCode.allCode.size() ; i++){
                        ArrayList arrayList = dataCode.allCode.get(i);
                        for (Object o : arrayList) {
                            jTextArea.append((String) o);
                            jTextArea.append(" ");
                        }
                        jTextArea.append(System.lineSeparator());
                    }

                    FileProcessor.Hex2Dec(dataCode);
                    jTextArea.append("二次读取代码结构完成，输出代码:" + System.lineSeparator());
                    for(int i = 0 ; i < dataCode.allCode.size() ; i++){
                        ArrayList arrayList = dataCode.allCode.get(i);
                        for (Object o : arrayList) {
                            jTextArea.append((String) o);
                            jTextArea.append(" ");
                        }
                        jTextArea.append(System.lineSeparator());
                    }

                    jTextArea.append(System.lineSeparator() +
                            "开始分析宏语句，对宏语句进行词法分析" +
                            System.lineSeparator());
                    code_processor codeProcessor = new code_processor(dataCode, dataDefineData);
                    codeOpen.CodeOpen(dataCode,dataDefineData);
                    LangIsOperate.defineIsOperate(dataCode,dataDefineData);
                    jTextArea.append(System.lineSeparator() +
                            "宏语句词法/语法分析完成，第一次宏展开完成输出分析结果:" +
                            System.lineSeparator());
                    Iterator iterator = dataDefineData.data.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = (String) entry.getKey();
                        data_object value = (data_object) entry.getValue();
                        jTextArea.append(key + System.lineSeparator());
                        jTextArea.append(">>>>>>");
                        if(value.object_type.equals("int"))
                            jTextArea.append("      int:    " + String.valueOf(value.object_valueStr));
                        else if(value.object_type.equals("double"))
                            jTextArea.append("      double: " + String.valueOf(value.object_valueStr));
                        else if(value.object_isOperate)
                            jTextArea.append("      operate:    " + String.valueOf(value.object_valueStr));
                        else
                            jTextArea.append("      justString: " + value.object_valueStr);
                        jTextArea.append(System.lineSeparator());
                    }

                    jTextArea.append("宏语句开始语义分析，将表达式转变为逆波兰式:" + System.lineSeparator());
                    OperateAnswer.getCodeOperate(dataCode,dataDefineData);
                    iterator = dataDefineData.data.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = (String) entry.getKey();
                        data_object value = (data_object) entry.getValue();
                        if(value.object_isOperate) {
                            jTextArea.append(key + System.lineSeparator());
                            jTextArea.append(">>>>>>");
                            jTextArea.append("      operate:    " + String.valueOf(value.operate));
                        }
                        jTextArea.append(System.lineSeparator());
                    }

                    OperateAnswer.defineEndAnswer(dataDefineData);
                    jTextArea.append("逆波兰式计算完成，开始输出结果" + System.lineSeparator());
                    iterator = dataDefineData.data.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = (String) entry.getKey();
                        data_object value = (data_object) entry.getValue();
                        if(value.object_isOperate) {
                            jTextArea.append(key + System.lineSeparator());
                            jTextArea.append(">>>>>>");
                            jTextArea.append("      operate:    " + String.valueOf(value.operate));
                        }
                        jTextArea.append(System.lineSeparator());
                    }

                    theEndStep.end(dataCode,dataDefineData);
                    jTextArea.append("[=====================================]" + System.lineSeparator());
                    jTextArea.append("//语义分析完成，开始输出第二宏展开代码" + System.lineSeparator());
                    for(int i = 0 ; i < dataCode.allCode.size(); i++){
                        ArrayList arrayList = dataCode.allCode.get(i);
                        for(int j = 0 ; j < arrayList.size(); j++){
                            jTextArea.append(String.valueOf(arrayList.get(j)) + "  ");
                        }
                        jTextArea.append(System.lineSeparator());
                    }

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        (jMenuItemSave = new JMenuItem("保存")).addActionListener(e -> Save(jTextArea,dataCode));
        (jMenuItemExit = new JMenuItem("退出")).addActionListener(e -> {
            int selection = JOptionPane.showConfirmDialog(jFrame,"是否保存更改？");
            if(selection == JOptionPane.OK_OPTION){
                Save(jTextArea,dataCode);
            }
            System.exit(0);
        });
        (jMenuItemClose = new JMenuItem("关闭")).addActionListener(e -> System.exit(0));
        jMenu.add(jMenuItemOpen);
        jMenu.add(jMenuItemSave);
        jMenu.add(jMenuItemExit);
        jMenu.add(jMenuItemClose);
        JScrollPane jScrollPane = new JScrollPane(jTextArea = new JTextArea());
        jFrame.add(jScrollPane);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }

    static void Save(JTextArea jTextArea, data_code dataCode){
        JFileChooser jFileChooser = new JFileChooser();
        if(jFileChooser.showSaveDialog(jTextArea) == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            try {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                for (int i = 0 ; i < dataCode.allCode.size();i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    ArrayList arrayList = dataCode.allCode.get(i);
                    for(int j = 0 ; j < arrayList.size();j++)
                        stringBuffer.append(arrayList.get(j) + " ");
                    String value = stringBuffer.toString();
                    bufferedWriter.write(value);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String [] args){
        dataDictionary.ini();
        new NoteBookMachine();
    }
}
