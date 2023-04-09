package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateDriverDetailsTest {
    @BeforeAll // run before all tests
    static void setUp() { // set up data repository

        data_Repository.add(new AddDriverDetails.driver_Details("ADRIEN FOURMAUX", 26, "Team M-Sport", "Fiesta WRC", 80));

        data_Repository.add(new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "WRC-15", 94));

    }

    @Test
    @Order(1)
    void checkNonExistingName() { // check if name exists
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();

        AddDriverDetails.driver_Details expected = null;
        AddDriverDetails.driver_Details actual = updateDriverDetails.checkName("OTT TANAK");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void checkExistingName() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();

        AddDriverDetails.driver_Details expected = new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "WRC-15", 94);
        AddDriverDetails.driver_Details actual = updateDriverDetails.checkName("LEWIS HAMILTON");

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getTeam(), actual.getTeam());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getPoints(), actual.getPoints());
    }

    @Test
    @Order(3)
    void checkUpdatedAgeNotNumber() { // check if age is a number
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "Error: Enter a valid age";
        String actual = updateDriverDetails.checkAgeIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void checkUpdatedAgeIsNumber() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "22";
        String actual = updateDriverDetails.checkAgeIsNumber("22");
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    void checkUpdatedPointsIsNotNumber() { // check if points is a number
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "99";
        String actual = updateDriverDetails.checkPointsIsNumber("99");
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    void checkUpdatedPointsIsNumber() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "Error: Points must an integer";
        String actual = updateDriverDetails.checkPointsIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    void updateDriverDetails() { // check whether driver details get updated
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        AddDriverDetails.driver_Details expected = new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "WX-12", 99);
        AddDriverDetails.driver_Details actual = updateDriverDetails.updateDriverDetails("35", "MERCEDES", "WX-12", "99");

        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getTeam(), actual.getTeam());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getPoints(), actual.getPoints());
    }
}