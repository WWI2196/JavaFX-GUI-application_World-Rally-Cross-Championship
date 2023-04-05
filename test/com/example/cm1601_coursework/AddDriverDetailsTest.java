package com.example.cm1601_coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.*;

class AddDriverDetailsTest {
    @BeforeAll
    static void setUp() {
        ArrayList<Object> driver = new ArrayList<>();
        driver.add("LEWIS HAMILTON");
        driver.add(35);
        driver.add("MERCEDES");
        driver.add("W11");
        driver.add(94);
        data_Repository.add(driver);
    }

    @Test
    void checkName() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name already exists";
        String actual = addDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

    @Test
    void checkAgeIsNumber() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Age must be a number";
        String actual = addDriverDetails.checkAgeIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    void checkPointsIsNumber() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Points must be a number";
        String actual = addDriverDetails.checkPointsIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    void checkAgeIsBetween15And99() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Enter a valid age";
        String actual = addDriverDetails.checkAgeIsBetween15And99("100");
        assertEquals(expected, actual);
    }

    @Test
    void checkNameIsNotEmpty() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name cannot be empty";
        String actual = addDriverDetails.checkNameIsNotEmpty("");
        assertEquals(expected, actual);
    }

}