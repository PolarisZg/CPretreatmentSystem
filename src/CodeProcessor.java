import java.util.ArrayList;

class CodeProcessor {
    data_code dataCode;

    CodeProcessor(data_code dataCode) {
        this.dataCode = dataCode;
    }

    void startCodeProcessor() {
        for (int i = 0; i < dataCode.allCode.size(); i++) {
            ArrayList arrayList = dataCode.allCode.get(i);
            for (int j = 0; j < arrayList.size(); i++) {
                if(arrayList.get(j) == "#define"){

                }
            }
        }
    }
}
