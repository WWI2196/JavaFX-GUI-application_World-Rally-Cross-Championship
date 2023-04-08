package com.example.cm1601_coursework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class UpdateDriverDetails {
    private static int index;
    public List<Object> checkName(String deleteName) {
        for (ArrayList<Object>item : data_Repository) { // loop through array list
            if (deleteName.toUpperCase().equals(item.get(0).toString())) { // check if name exists
                index = data_Repository.indexOf(item); // get index of name

                String name = item.get(0).toString(); // get name
                Integer age = (Integer) item.get(1); // get age
                String team = item.get(2).toString();
                String model = item.get(3).toString();
                Integer point = (Integer) item.get(4);

                List<Object> driverInfo = new ArrayList<>();
                driverInfo.add(name); // add data to array list
                driverInfo.add(age);
                driverInfo.add(team);
                driverInfo.add(model);
                driverInfo.add(point);

                return driverInfo; // return array list

            }
        }
        return Collections.singletonList("Not found"); // return not found if name does not exist
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

    public List<Object> updateDriverDetails(String age, String team, String model, String points) { // update driver details
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

            data_Repository.get(index).set(1, Integer.parseInt(age));
            data_Repository.get(index).set(2, team);
            data_Repository.get(index).set(3, model);
            data_Repository.get(index).set(4, Integer.parseInt(points));

            return data_Repository.get(index); // return updated data

        } catch (IllegalArgumentException e) { // if age or points is not an integer number, show error message
            return Collections.singletonList("Not updated");
        }
    }

}
