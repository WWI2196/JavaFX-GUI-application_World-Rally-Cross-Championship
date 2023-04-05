package com.example.cm1601_coursework;

import java.util.ArrayList;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

public class DeleteDriverDetails {
    public List<Object> checkName(String deleteName) {
        for (ArrayList<Object>item : data_Repository) {
            if (deleteName.toUpperCase().equals(item.get(0).toString())) {

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

    public String deleteDriver(String deleteName) {
        for (ArrayList<Object>item_ : data_Repository) {
            if (deleteName.toUpperCase().equals(item_.get(0).toString())) {
                data_Repository.remove(item_);
                return "Driver deleted";
            }
        }
        return "Driver not found";
    }


}
