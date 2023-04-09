package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeleteDriverDetailsTest {
    @BeforeAll
    static void setUp() { // set up data repository

        data_Repository.add(new AddDriverDetails.driver_Details("OTT TANAK", 31, "TOYOTA", "YARIS WRC", 96));

        data_Repository.add(new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "W11", 94));

    }

    @Test
    @Order(1)
    void checkExistingName() { // check if name exists
        DeleteDriverDetails deleteDriverDetails = new DeleteDriverDetails();

        AddDriverDetails.driver_Details expected = new AddDriverDetails.driver_Details("LEWIS HAMILTON", 35, "MERCEDES", "W11", 94);
        AddDriverDetails.driver_Details actual = deleteDriverDetails.checkName("LEWIS HAMILTON");

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getTeam(), actual.getTeam());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getPoints(), actual.getPoints());
    }

    @Test
    @Order(2)
    void checkNonExistingName() {
        DeleteDriverDetails deleteDriverDetails = new DeleteDriverDetails();

        AddDriverDetails.driver_Details expected = null;
        AddDriverDetails.driver_Details actual = deleteDriverDetails.checkName("MAX VERSTAPPEN");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void deleteDriver() { // delete driver
        DeleteDriverDetails deleteDriverDetails = new DeleteDriverDetails();

        String expected = "Driver deleted";
        String actual = deleteDriverDetails.deleteDriver("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

}