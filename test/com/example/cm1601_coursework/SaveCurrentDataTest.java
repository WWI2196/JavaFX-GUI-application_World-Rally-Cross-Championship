package com.example.cm1601_coursework;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SaveCurrentDataTest {
    @Test
    @Order(1)
    void noDataToAppendOrOverwrite() { // check if data exists
        SaveCurrentData saveCurrentData = new SaveCurrentData();
        String expected = "Error: No data to append.";
        String actual = saveCurrentData.appendTheData("src/Driver_details_test.txt");
        assertEquals(expected, actual);

        SaveCurrentData saveCurrentData2 = new SaveCurrentData();
        String expected2 = "Error: No data to overwrite.";
        String actual2 = saveCurrentData2.overwriteTheData("src/Driver_details_test.txt");
        assertEquals(expected2, actual2);
    }

    @Test
    @Order(2)
    public void addDataToAppendOrOverwrite() { // add data to data repository
        data_Repository.add(new ArrayList<Object>());
        data_Repository.get(0).add("LEWIS HAMILTON");
        data_Repository.get(0).add(35);
        data_Repository.get(0).add("MERCEDES");
        data_Repository.get(0).add("MI11-EU");
        data_Repository.get(0).add(93);
    }
    @Test
    @Order(3)
    void dataToAppendOrOverwrite() {
        SaveCurrentData saveCurrentData = new SaveCurrentData();
        String expected = "Data appended successfully.";
        String actual = saveCurrentData.appendTheData("src/Driver_details_test.txt");
        assertEquals(expected, actual);

        SaveCurrentData saveCurrentData2 = new SaveCurrentData();
        String expected2 = "Data overwritten successfully.";
        String actual2 = saveCurrentData2.overwriteTheData("src/Driver_details_test.txt");
        assertEquals(expected2, actual2);
    }
}