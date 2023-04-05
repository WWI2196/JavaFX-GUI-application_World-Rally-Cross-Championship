package com.example.cm1601_coursework;

import java.util.ArrayList;

public class AddDriverDetails {
    public static ArrayList<ArrayList<Object>> data_Repository = new ArrayList<>();

    public String checkName(String name) {
        try {
            for (ArrayList<Object> item : data_Repository) {
                if (name.toUpperCase().equals(item.get(0).toString())) {
                    throw new Exception();
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return "Error: Name already exists";
        }
        return null;
    }

    public String checkAgeIsNumber(String age) {
        try {
            Integer.parseInt(age);
            return null;
        } catch (Exception e) {
            return "Error: Age must be a number";
        }
    }

    public String checkPointsIsNumber(String points) {
        try {
            Integer.parseInt(points);
            return null;
        } catch (Exception e) {
            return "Error: Points must a integer";
        }
    }

    public String checkAgeIsBetween15And99(String age) {
        try {
            Integer.parseInt(age);
            if (Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99) {
                throw new NumberFormatException();
            }return null;

        } catch (NumberFormatException e) {
            return "Error: Enter a valid age";
        }
    }

    public String checkNameIsNotEmpty(String name) {
        try {
            if (name.isEmpty()) {
                throw new Exception();
            }return null;
        } catch (Exception e) {
            return "Error: Name cannot be empty";
        }
    }

}
