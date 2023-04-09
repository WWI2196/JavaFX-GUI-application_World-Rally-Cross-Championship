package com.example.cm1601_coursework;

import java.io.FileWriter;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
public class SaveCurrentData {
    public String appendTheData(String FILE) { // method to append data
        if(data_Repository.size() == 0) {
            return "Error: No data to append."; // if no data to append, return error
        }else {
            try {
                FileWriter writer = new FileWriter((FILE), true); // create file writer
                for (AddDriverDetails.driver_Details item : data_Repository) {
                    String line = item.getName() + "," + item.getAge() + ","
                            + item.getTeam() + "," + item.getModel() + "," + item.getPoints() + "\n";
                    writer.write(line); // write data to file
                }
                writer.close();
                return "Data appended successfully."; // return success message
            } catch (Exception e) { // catch exception
                return "Error: Data could not be appended.";
            }
        }
    }

    public String overwriteTheData(String FILE) {
        if(data_Repository.size() == 0) { // if no data to overwrite, return error
            return "Error: No data to overwrite.";
        }else {
            try {
                FileWriter writer = new FileWriter((FILE), false); // create file writer
                for (AddDriverDetails.driver_Details item : data_Repository) {
                    String line = item.getName() + "," + item.getAge() + "," + item.getTeam() + "," + item.getModel() + "," + item.getPoints() + "\n";
                    writer.write(line);
                }
                writer.close();
                return "Data overwritten successfully.";
            } catch (Exception e) {
                return "Error: Data could not be overwritten.";
            }
        }
    }


}
