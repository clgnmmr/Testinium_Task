package utilities;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadToText {

    public static String readText(String dataFile) {
        String id = "";

        try {


            //identify file location
            FileReader fileReader = new FileReader(dataFile);


            //Read the records of the file in given location
            BufferedReader br = new BufferedReader(fileReader);


            String line = br.readLine();//856-45-6789,


            while (line != null) {


                String readId = line.split(",")[0];
                id = readId;
                line = br.readLine();


            }


        } catch (Exception e) {


            e.printStackTrace();


        }
        return id;
    }
}
