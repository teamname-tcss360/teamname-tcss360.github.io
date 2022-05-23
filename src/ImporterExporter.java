package src;

/**
 * ImporterExporter class that can export or read
 * settings from a CSV file.
 *
 * @author  Trevor Tomlin
 * @version 1.0
 * @since   2022-05-18
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImporterExporter {

    /***
     * Exports row to file with given headers.
     * If file already exists, it appends the row to file.
     * @param filePath
     * @param headers
     * @param row
     * @throws ExportException
     * @throws IOException
     */
    public static void exportSettings(String fileName, String[] headers, String[] row) throws ExportException, IOException {

        if (headers.length != row.length) {

            throw new ExportException("Length of headers does not match length of settings.");

        }

        File file = new File(fileName);

        String userHomeFolder = System.getProperty("user.home")+"\\Desktop";
        File textFile = new File(userHomeFolder, row[0]+".txt");

        String headerLine = arrayToCSV(headers);
        String rowLine = arrayToCSV(row);

        System.out.println(headerLine);
        System.out.println(rowLine);

        boolean fileExists = file.isFile();
        boolean textFileExists = textFile.isFile();

        FileWriter writer = new FileWriter(file, fileExists);
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {
            if (!fileExists && !textFileExists){
                writer.write(headerLine + "\n");

                writer.write(rowLine + "\n");

            }
            out.write(rowLine +"\n");
            out.write(headerLine + "\n");
            out.close();
            writer.close();
        }

        catch (Exception e) {

            e.printStackTrace();

        }


    }

    /***
     * Reads settings from a CSV file.
     * Returns a List of Maps that map each header to the rows value.
     * @param filePath
     * @return ArrayList<Map<String, String>>
     */
    public static ArrayList<Map<String, String>> importSettings(String filePath) {

        ArrayList <Map<String, String>> result = new ArrayList<>();

        try {

            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            String[] headers = scanner.nextLine().split(",");

            while(scanner.hasNextLine()) {

                String[] row = scanner.nextLine().split(",");
                Map<String, String> map = new HashMap<>();

                for (int i = 0; i < row.length; i++) {

                    map.put(headers[i], row[i]);

                }
                
                result.add(map);

            }

            System.out.println(headers[0]);

            scanner.close();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /***
     * Takes a string array and converts it to a single string
     * where each value is seperated by a comma.
     * Does not currently check for invalid CSV characters.
     * @param array
     * @return String
     */
    private static String arrayToCSV(String[] array) {

        String result = "";

        for (int i = 0; i < array.length; i++) {

            result += array[i];

            if (i < array.length - 1) result += ",";

        }

        return result;

    }

}
