package com.example.cm1601_coursework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class LoadSavedData {
    private static boolean dataLoaded = true;
    public String loadSavedData(String path) {
        if (dataLoaded) {
            try {
                File file = new File(path);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    ArrayList<Object> driverData = new ArrayList<>();
                    driverData.add(data[0]);
                    driverData.add(Integer.parseInt(data[1]));
                    driverData.add(data[2]);
                    driverData.add(data[3]);
                    driverData.add(Integer.parseInt(data[4]));
                    data_Repository.add(driverData);
                }
                scanner.close();
                dataLoaded = false;

                return "Data loaded successfully.";

            } catch (IOException e) {
                return "Error: File not found";
            }
        } else {
            return "Error: Data already loaded";
        }
    }
}
