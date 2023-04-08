package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddDriverDetailsTest {
    @BeforeAll
    static void setUp() { // set up data repository
        ArrayList<Object> driver = new ArrayList<>();
        driver.add("LEWIS HAMILTON");
        driver.add(35);
        driver.add("MERCEDES");
        driver.add("W11");
        driver.add(94);
        data_Repository.add(driver);
    }

    @Test
    @Order(1)
    void checkNameIsNotEmpty() { // check if name is empty
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name cannot be empty";
        String actual = addDriverDetails.checkNameIsNotEmpty("");
        assertEquals(expected, actual);
    }
    @Test
    @Order(2)
    void checkExitingName() { // check if name exists
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name already exists";
        String actual = addDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void checkNewName() { // check if name is new
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkName("MAX VERSTAPPEN");
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void checkAgeIsNotNumber() { // check if age is not a number
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Age must be an integer";
        String actual = addDriverDetails.checkAgeIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    void checkAgeIsNumber() { // check if age is a number
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkAgeIsNumber("21");
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    void checkAgeIsHigher() { // check if age is between 15 and 99
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Enter a valid age";
        String actual = addDriverDetails.checkAgeIsBetween15And99("101");
        assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    void checkAgeIsLower() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Enter a valid age";
        String actual = addDriverDetails.checkAgeIsBetween15And99("12");
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    void checkPointsIsNotNumber() { // check if points is not a number
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Points must be an integer";
        String actual = addDriverDetails.checkPointsIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(9)
    void checkPointsIsNumber() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkPointsIsNumber("96");
        assertEquals(expected, actual);
    }

    @Test
    @Order(10)
    void addDriverDetails() { // check if driver details are added
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        List<Object> expected = Arrays.asList("MAX VERSTAPPEN", 21, "RED BULL", "RB16B", 96);
        List<Object> actual = addDriverDetails.addDriverDetails("MAX VERSTAPPEN", "21", "RED BULL", "RB16B", "96");
        assertEquals(expected, actual);
    }



}