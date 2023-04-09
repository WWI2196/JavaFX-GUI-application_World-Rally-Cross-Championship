package com.example.cm1601_coursework;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class DeleteDriverDetails {
    public AddDriverDetails.driver_Details checkName(String deleteName) {
        for (AddDriverDetails.driver_Details item : data_Repository) {
            if (deleteName.toUpperCase().equals(item.getName())) { // check if name already exists to delete

                String name = item.getName(); // get name

                int age = item.getAge(); // get age

                String team = item.getTeam();   // get team

                String model = item.getModel();  // get model

                int point = item.getPoints(); // get points

                return new AddDriverDetails.driver_Details(name,age,team,model,point);// return driver info

            }
        }
        return null; // if name not found, return null
    }

    public String deleteDriver(String deleteName) {
        for (AddDriverDetails.driver_Details item_ : data_Repository) {
            if (deleteName.toUpperCase().equals(item_.getName())) { // check if name already exists to delete
                data_Repository.remove(item_); // remove driver from array list
                return "Driver deleted";
            }
        }
        return "Driver not found";
    }


}
