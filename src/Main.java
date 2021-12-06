import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {
        Reader reader = new FileReader("src/test.txt");
        data_operator dataOperator = new data_operator();
        data_code dataCode = new data_code();
        FileProcessor fileProcessor = new FileProcessor(reader , dataOperator , dataCode);
        fileProcessor.first();
        for(int i = 0 ; i < dataCode.allCode.size() ; i++){
            ArrayList arrayList = dataCode.allCode.get(i);
            for(int j = 0 ; j < arrayList.size() ; j++){
                System.out.print(arrayList.get(j));
            }
            System.out.println();
        }
    }
}
