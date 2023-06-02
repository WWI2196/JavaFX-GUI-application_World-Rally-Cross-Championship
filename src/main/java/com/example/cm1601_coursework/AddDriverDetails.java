package com.example.cm1601_coursework;

import java.util.ArrayList;

public class AddDriverDetails {
    public static ArrayList<driver_Details> data_Repository = new ArrayList<>();

    public String checkName(String name) {
        try {
            for ( driver_Details item : data_Repository) {
                if (name.toUpperCase().equals(item.getName())) { // check if name already exists
                    throw new Exception(); // if name exists, throw exception
                } else {
                    return null; // if the name does not exist, return null
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

    public static driver_Details addDriverDetails(String name, String age, String team, String model, String points) {
        try {
            if (name.isEmpty()) {
                throw new Exception();
            }
            for (driver_Details item : data_Repository) {
                if (name.toUpperCase().equals(item.getName())) {
                    throw new Exception();
                }
            }
            if (age.isEmpty() || Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99) {
                throw new Exception();
            }
            if (points.isEmpty()) {
                throw new Exception();
            }



            driver_Details newDriver = new driver_Details(name, Integer.parseInt(age), team, model, Integer.parseInt(points));
            data_Repository.add(newDriver);
            return newDriver;// add new driver to data repository

             // return driver details

        } catch (Exception e) {
            return null; // if any of the above conditions are not met, return null
        }
    }

    public static class driver_Details {
        private String name;
        private int age;
        private String team;
        private String model;
        private int points;

        public driver_Details(String name, int age, String team, String model, int points) {
            this.name = name;
            this.age = age;
            this.team = team;
            this.model = model;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

}
