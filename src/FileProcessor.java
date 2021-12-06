import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

class FileProcessor {
    Reader reader = null;
    data_operator dataOperator = null;
    data_code dataCode = null;
    FileProcessor(Reader reader , data_operator dataOperator , data_code dataCode){
        this.reader = reader;
        this.dataOperator = dataOperator;
        this.dataCode = dataCode;
    }
    void first() {
        {
            try {
                int i = 0;
                while (true) {
                    i = reader.read();
                    StringBuffer temp = new StringBuffer();
                    while (dataOperator.dataSearch(String.valueOf((char) i)) == null) {
                        temp.append((char) i);
                        i = reader.read();
                        if (i == -1) {
                            break;
                        }
                    }
                    if (i == -1) break;
                    if (i == '\r' || i == '\n') {
                        if (temp.length() > 0)
                            dataCode.addWord(temp.toString());
                        dataCode.nextLine();
                    } else {
                        if (temp.length() > 0)
                            dataCode.addWord(temp.toString());
                        dataCode.addWord(String.valueOf((char) i));
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
}
