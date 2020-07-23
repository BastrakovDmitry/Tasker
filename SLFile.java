import java.io.*;
import java.util.Scanner;

public class SLFile {
    public static void save(String[] adressTaskData) throws IOException {
        String adress = adressTaskData[0];
        String task = adressTaskData[1];
        String data = adressTaskData[2];

        File file = new File(adress);
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(task + ";\n");
        fileWriter.write(data);
        fileWriter.flush();
    }

    public static String[] load(String adress) throws FileNotFoundException {
        String result = "";
        File file = new File(adress);
        FileReader fileReader = new FileReader(file);

        Scanner scan = new Scanner(fileReader);

        while (scan.hasNextLine()) {

            if (result == "") {
                result = scan.nextLine() + "\n";

            } else {
                result = result + scan.nextLine() + "\n";
            }
        }

        return result.substring(0, result.length() - 1).split(";\n");
    }
}
