package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateDriverDetailsTest {
    @BeforeAll
    static void setUp() {
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
        driver2.add("W11");
        driver2.add(94);
        data_Repository.add(driver2);

    }

    @Test
    @Order(1)
    void checkName() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();

        List<Object> expected = Arrays.asList("LEWIS HAMILTON", 35, "MERCEDES", "W11", 94);
        List<Object> actual = updateDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void checkAgeIsNumber() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "Error: Enter a valid age";
        String actual = updateDriverDetails.checkAgeIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void checkPointsIsNumber() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        String expected = "Error: Points must an integer";
        String actual = updateDriverDetails.checkPointsIsNumber("abc");
        assertEquals(expected, actual);
    }


    @Test
    @Order(4)
    void updateDriverDetails() {
        UpdateDriverDetails updateDriverDetails = new UpdateDriverDetails();
        List<Object> expected = Arrays.asList("LEWIS HAMILTON", 35, "MERCEDES", "WX-11", 99);
        List<Object> actual = updateDriverDetails.updateDriverDetails("35", "MERCEDES", "WX-11", "99");
        assertEquals(expected, actual);
    }
}