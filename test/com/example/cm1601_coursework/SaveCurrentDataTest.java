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
        data_Repository.add(new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "W11", 94));
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