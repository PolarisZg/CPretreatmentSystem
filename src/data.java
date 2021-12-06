import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

interface data {
    String dataType();
    Object dataSearch(String s);
    void dataWrite(String s, Object o);
    void dataDel(String s);
}
class data_SaveWord implements data{
    HashMap<String, Object> data = new HashMap<>();
    @Override
    public String dataType() {
        return "saveWord";
    }

    @Override
    public Object dataSearch(String s) {
        return data.get(s);
    }

    @Override
    public void dataWrite(String s, Object o) {
        data.put(s,o);
    }

    @Override
    public void dataDel(String s) {
        data.remove(s);
    }
}
class data_defineData implements data{
    HashMap<String, String> data = new HashMap<>();
    @Override
    public String dataType() {
        return "changeWord";
    }

    @Override
    public String dataSearch(String s) {
        return data.get(s);
    }

    @Override
    public void dataWrite(String s, Object o) {
        data.put(s,(String)o);
    }

    @Override
    public void dataDel(String s) {
        data.remove(s);
    }
}
class data_operator implements data{
    HashMap<String, String>data = new HashMap<>();
    data_operator(){
        ini();
    }
    @Override
    public String dataType() {
        return "operator";
    }

    @Override
    public String dataSearch(String s) {
        return data.get(s);
    }

    @Override
    public void dataWrite(String s, Object o) {
        data.put(s , (String)o);
    }

    @Override
    public void dataDel(String s) {
        data.remove(s);
    }

    private void ini(){
        String[] iniData = {"+", "-" , "*" , "/", "%", "(", ")",
                            "<", "<=", "==", ">=", ">",
                            "<<", ">>", "~", "|", "^", "&",
                            "=", "+=", "=+", "-=", "=-",
                            "?", ":",
                            ",",
                            "[", "]",
                            "{", "}", ";", " ", "\n", "\r", "\t"};
        int length = iniData.length;
        for (String iniDatum : iniData) {
            dataWrite(iniDatum, iniDatum);
        }
    }
}
class data_code{
    ArrayList<ArrayList> allCode = new ArrayList<>();
    ArrayList<String> thisLine = null;
    data_code(){
        nextLine();
    }
    void nextLine(){
        thisLine = new ArrayList<>();
        allCode.add(thisLine);
    }

    void addWord(String s){
        thisLine.add(s);
    }

    void batterCode(){
        for(int i = 0 ; i < allCode.size() ; i++){
            ArrayList arrayList = allCode.get(i);
            if(arrayList.size() == 0){
                allCode.remove(i);
                i--;
                continue;
            }
            for(int j = 1 ; j < arrayList.size() ; j++){
                if(arrayList.get(j).equals(" ") && arrayList.get(j-1).equals(" ")){
                    arrayList.remove(j);
                    j--;
                }
            }
        }
    }
}