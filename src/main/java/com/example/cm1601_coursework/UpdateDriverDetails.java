package com.example.cm1601_coursework;

import java.util.Collections;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class UpdateDriverDetails {
    private static int index;
    public AddDriverDetails.driver_Details checkName(String deleteName) {
        for (AddDriverDetails.driver_Details item : data_Repository) { // loop through array list
            if (deleteName.toUpperCase().equals(item.getName())) { // check if name exists
                index = data_Repository.indexOf(item); // get index of name

                String name = item.getName(); // get name
                int age = item.getAge(); // get age
                String team = item.getTeam();
                String model = item.getModel();
                int point = item.getPoints();

                return new AddDriverDetails.driver_Details(name,age,team,model,point); // return array list

            }
        }
        return (AddDriverDetails.driver_Details) Collections.singletonList("Not found."); // return not found if name does not exist
    }

    public String checkAgeIsNumber(String age) {
        try {
            Integer.parseInt(age);
            if(Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99 || age.isEmpty()) {
                throw new NumberFormatException();
            }
            return age;
        } catch (NumberFormatException e) {
            return "Error: Enter a valid age";
        }
    }

    public String checkPointsIsNumber(String points) {
        try {
            Integer.parseInt(points);
            return points;
        } catch (Exception e) {
            return "Error: Points must an integer";
        }
    }

    public AddDriverDetails.driver_Details updateDriverDetails(String age, String team, String model, String points) { // update driver details
        try {
            try {
                Integer.parseInt(age);
                if (Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99 || age.isEmpty()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
            try {
                Integer.parseInt(points);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }

            data_Repository.get(index).setAge(Integer.parseInt(age)); // update age
            data_Repository.get(index).setTeam(team);
            data_Repository.get(index).setModel(model);
            data_Repository.get(index).setPoints(Integer.parseInt(points));

            return data_Repository.get(index); // return updated data

        } catch (IllegalArgumentException e) { // if age or points is not an integer number, show error message
            return (AddDriverDetails.driver_Details) Collections.singletonList("Not updated");
        }
    }

}
