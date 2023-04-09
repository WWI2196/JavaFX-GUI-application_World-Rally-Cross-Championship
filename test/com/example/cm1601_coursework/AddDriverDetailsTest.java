package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddDriverDetailsTest {
    @BeforeAll
    static void setUp() { // set up data repository
        data_Repository.add(new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "W11", 96));
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
    void checkPointsIsNumber() { // check if points is a number
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkPointsIsNumber("96");
        assertEquals(expected, actual);
    }

    @Test
    @Order(10)
    void addDriverDetails() { // check if driver details are added
        AddDriverDetails addDriverDetails = new AddDriverDetails();

        AddDriverDetails.driver_Details expected = new AddDriverDetails.driver_Details("MAX VERSTAPPEN", 21, "Team Toyota", "Yaris 139", 94);
        AddDriverDetails.driver_Details actual = AddDriverDetails.addDriverDetails("MAX VERSTAPPEN", "21", "Team Toyota", "Yaris 139", "94");

        // Compare the properties of the expected and actual driver details using their getter methods
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getTeam(), actual.getTeam());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getPoints(), actual.getPoints());
    }



}