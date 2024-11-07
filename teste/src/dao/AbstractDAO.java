package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractDAO {

    public void writeObjectInFile(String local, String object) throws IOException {
        OutputStream os = new FileOutputStream(local, true);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        br.write(object + "\n");
        br.close();
    }

    public ArrayList<String> readFile(String local) throws IOException {
        File file = new File(local);
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            list.add(data);
        }

        scanner.close();
        return list;
    }

    public void clearFile(String local) throws IOException {
        FileWriter fwOb = new FileWriter(local, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

}
