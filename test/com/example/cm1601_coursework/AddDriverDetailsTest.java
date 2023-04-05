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
    @Order(1)
    void checkNameIsNotEmpty() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name cannot be empty";
        String actual = addDriverDetails.checkNameIsNotEmpty("");
        assertEquals(expected, actual);
    }
    @Test
    @Order(2)
    void checkExitingName() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Name already exists";
        String actual = addDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void checkNewName() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkName("MAX VERSTAPPEN");
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void checkAgeIsNotNumber() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = "Error: Age must be an integer";
        String actual = addDriverDetails.checkAgeIsNumber("abc");
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    void checkAgeIsNumber() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        String expected = null;
        String actual = addDriverDetails.checkAgeIsNumber("21");
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    void checkAgeIsHigher() {
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
    void checkPointsIsNotNumber() {
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
    void addDriverDetails() {
        AddDriverDetails addDriverDetails = new AddDriverDetails();
        List<Object> expected = Arrays.asList("MAX VERSTAPPEN", 21, "RED BULL", "RB16B", 96);
        List<Object> actual = addDriverDetails.addDriverDetails("MAX VERSTAPPEN", "21", "RED BULL", "RB16B", "96");
        assertEquals(expected, actual);
    }



}