package ru.vtb.learning.lesson4.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataReaderFromFiles implements DataReader{
    private final String dirPath;
    public DataReaderFromFiles(@Value("${datareader.path}")String dirPath) {
        this.dirPath = dirPath;
    }
    public List<PlainLogItem> getData() {
        List<PlainLogItem> plainLogList = new ArrayList<>();

        if (dirPath != null && !dirPath.isBlank()) {
            File dir = new File(dirPath);
            File[] fileList = dir.listFiles();

            if (fileList != null) {
                for (File file : fileList) {
                    getDataOneFile(file, plainLogList);
                }
            }
        }

        return plainLogList;
    }

    private void getDataOneFile(File file, List<PlainLogItem> plainLogList) {
        // Файл должен быть в кодировке UTF-8 без BOM-символов.
        String LineFromFile;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                LineFromFile = sc.nextLine();
                if (LineFromFile != null && !LineFromFile.isBlank()) {
                    plainLogList.add(new PlainLogItem(LineFromFile));
                }
            }
            sc.close();
        } catch (FileNotFoundException exc) {}
    }
}
