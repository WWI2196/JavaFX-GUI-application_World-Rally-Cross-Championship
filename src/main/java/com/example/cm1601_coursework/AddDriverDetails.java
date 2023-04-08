package com.example.cm1601_coursework;

import java.util.ArrayList;
import java.util.List;

public class AddDriverDetails {
    public static ArrayList<ArrayList<Object>> data_Repository = new ArrayList<>();

    public String checkName(String name) {
        try {
            for (ArrayList<Object> item : data_Repository) {
                if (name.toUpperCase().equals(item.get(0).toString())) { // check if name already exists
                    throw new Exception(); // if name exists, throw exception
                } else {
                    return null; // if name does not exist, return null
                }
            }
        } catch (Exception e) { // if name exists, catch exception
            return "Error: Name already exists"; // if name exists, return error message
        }
        return null; // if name does not exist, return null
    }

    public String checkAgeIsNumber(String age) { // check if age is a number
        try {
            Integer.parseInt(age);
            return null;
        } catch (Exception e) { // if age is not a number, catch exception
            return "Error: Age must be an integer";
        }
    }

    public String checkPointsIsNumber(String points) { // check if points is a number
        try {
            Integer.parseInt(points);
            return null;
        } catch (Exception e) { // if points is not a number, catch exception
            return "Error: Points must be an integer";
        }
    }

    public String checkAgeIsBetween15And99(String age) { // check if age is between 15 and 99
        try {
            Integer.parseInt(age);
            if (Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99) {
                throw new NumberFormatException(); // if age is not between 15 and 99, throw exception
            }return null;

        } catch (NumberFormatException e) { // if age is not between 15 and 99, catch exception
            return "Error: Enter a valid age";
        }
    }

    public String checkNameIsNotEmpty(String name) { // check if name is not empty
        try {
            if (name.isEmpty()) {
                throw new Exception(); // if name is empty, throw exception
            }return null;
        } catch (Exception e) { // if name is empty, catch exception
            return "Error: Name cannot be empty";
        }
    }

    public List<Object> addDriverDetails(String name, String age,String team,String model, String points) {
        try {
            if (name.isEmpty()) {
                throw new Exception();
            }
            for (ArrayList<Object> item : data_Repository) {
                if (name.toUpperCase().equals(item.get(0).toString())) {
                    throw new Exception();
                }
            }
            if (age.isEmpty() || Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99) {
                throw new Exception();
            }
            if (points.isEmpty()) {
                throw new Exception();
            }


            ArrayList<Object> driverDetails = new ArrayList<>();
            driverDetails.add(name);
            driverDetails.add(Integer.parseInt(age));
            driverDetails.add(team);
            driverDetails.add(model);
            driverDetails.add(Integer.parseInt(points));
            data_Repository.add(driverDetails); // add driver details to data repository

            return driverDetails; // return driver details

        } catch (Exception e) {
            return null; // if any of the above conditions are not met, return null
        }
    }

}
