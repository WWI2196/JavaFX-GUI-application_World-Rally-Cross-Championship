package com.example.cm1601_coursework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class LoadSavedData { // class to load saved data
    private static boolean dataLoaded = true; // boolean to check if data is loaded
    public String loadSavedData(String path) {
        if (dataLoaded) {
            try {
                File file = new File(path); // get file
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) { // while there is a next line
                    String[] data = scanner.nextLine().split(","); // split data by comma
                    ArrayList<Object> driverData = new ArrayList<>();
                    driverData.add(data[0]);
                    driverData.add(Integer.parseInt(data[1]));
                    driverData.add(data[2]);
                    driverData.add(data[3]);
                    driverData.add(Integer.parseInt(data[4]));
                    data_Repository.add(driverData); // add data to array list
                }
                scanner.close();
                dataLoaded = false; // set data loaded to false

                return "Data loaded successfully.";

            } catch (IOException e) { // catch exception
                return "Error: File not found";
            }
        } else {
            return "Error: Data already loaded";
        }
    }
}
