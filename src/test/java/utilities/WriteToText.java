package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Set;

public class WriteToText {


    public static void writeToMethod(String T, String filepath) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);


            writer.append(T);
            writer.newLine();


            writer.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void writeToCollection(Set<String> T, String filepath) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (String element : T) {
                writer.append(element + System.lineSeparator());

            }


            writer.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
