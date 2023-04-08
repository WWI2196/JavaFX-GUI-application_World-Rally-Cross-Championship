package com.example.cm1601_coursework;

import java.util.ArrayList;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class DeleteDriverDetails {
    public List<Object> checkName(String deleteName) {
        for (ArrayList<Object>item : data_Repository) {
            if (deleteName.toUpperCase().equals(item.get(0).toString())) { // check if name already exists to delete

                String name = item.get(0).toString(); // get name

                Integer age = (Integer) item.get(1); // get age

                String team = item.get(2).toString();   // get team

                String model = item.get(3).toString();  // get model

                Integer point = (Integer) item.get(4); // get points

                List<Object> driverInfo = new ArrayList<>();
                driverInfo.add(name);
                driverInfo.add(age);
                driverInfo.add(team);
                driverInfo.add(model);
                driverInfo.add(point);
                return driverInfo; // return driver info

            }
        }
        return null; // if name not found, return null
    }

    public String deleteDriver(String deleteName) {
        for (ArrayList<Object>item_ : data_Repository) {
            if (deleteName.toUpperCase().equals(item_.get(0).toString())) { // check if name already exists to delete
                data_Repository.remove(item_); // remove driver from array list
                return "Driver deleted";
            }
        }
        return "Driver not found";
    }


}
