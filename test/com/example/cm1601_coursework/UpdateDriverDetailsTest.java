package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateDriverDetailsTest {
    @BeforeAll // run before all tests
    static void setUp() { // set up data repository
        ArrayList<Object> driver1 = new ArrayList<>();
        driver1.add("ADRIEN FOURMAUX");
        driver1.add(26);
        driver1.add("Team M-Sport");
        driver1.add("Fiesta WRC");
        driver1.add(80);
        data_Repository.add(driver1);

        ArrayList<Object> driver2 = new ArrayList<>();
        driver2.add("LEWIS HAMILTON");
        driver2.add(35);
        driver2.add("MERCEDES");
        driver2.add("WRC-15");
        driver2.add(94);
        data_Repository.add(driver2);

    }

    @Test
    @Order(1)
    void checkNonExistingName() { // check if name exists
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();

        List<Object> expected = Collections.singletonList("Not found");
        List<Object> actual = updateDriverDetails.checkName("OTT TANAK");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void checkExistingName() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();

        List<Object> expected = Arrays.asList("LEWIS HAMILTON", 35, "MERCEDES", "WRC-15", 94);
        List<Object> actual = updateDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
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
        List<Object> expected = Arrays.asList("LEWIS HAMILTON", 35, "MERCEDES", "WX-12", 99);
        List<Object> actual = updateDriverDetails.updateDriverDetails("35", "MERCEDES", "WX-12", "99");
        assertEquals(expected, actual);
    }
}