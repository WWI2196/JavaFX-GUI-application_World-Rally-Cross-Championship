package com.example.cm1601_coursework;

import java.util.ArrayList;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class UpdateDriverDetails {
    static int index;
    public List<Object> checkName(String deleteName) {
        for (ArrayList<Object>item : data_Repository) {
            if (deleteName.toUpperCase().equals(item.get(0).toString())) {
                index = data_Repository.indexOf(item);

                String name = item.get(0).toString();
                Integer age = (Integer) item.get(1);
                String team = item.get(2).toString();
                String model = item.get(3).toString();
                Integer point = (Integer) item.get(4);

                List<Object> driverInfo = new ArrayList<>();
                driverInfo.add(name);
                driverInfo.add(age);
                driverInfo.add(team);
                driverInfo.add(model);
                driverInfo.add(point);

                return driverInfo;

            }
        }
        return null;
    }

    public String checkAgeIsNumber(String age) {
        try {
            Integer.parseInt(age);
            if(Integer.parseInt(age) < 15 || Integer.parseInt(age) > 99 || age.isEmpty()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return "Error: Enter a valid age";
        }
        return null;
    }

    public String checkPointsIsNumber(String points) {
        try {
            Integer.parseInt(points);
            return null;
        } catch (Exception e) {
            return "Error: Points must an integer";
        }
    }

    public List<Object> updateDriverDetails(String age, String team, String model, String points) {
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

            return data_Repository.get(index);

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
