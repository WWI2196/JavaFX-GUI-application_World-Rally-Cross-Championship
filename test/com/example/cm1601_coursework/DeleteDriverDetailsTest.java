package com.example.cm1601_coursework;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cm1601_coursework.AddDriverDetails.data_Repository;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeleteDriverDetailsTest {
    @BeforeAll
    static void setUp() {
        ArrayList<Object> driver1 = new ArrayList<>();
        driver1.add("OTT TANAK");
        driver1.add(31);
        driver1.add("TOYOTA");
        driver1.add("YARIS WRC");
        driver1.add(96);
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
        DeleteDriverDetails deleteDriverDetails = new DeleteDriverDetails();

        List<Object> expected = Arrays.asList("LEWIS HAMILTON", 35, "MERCEDES", "W11", 94);
        List<Object> actual = deleteDriverDetails.checkName("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void deleteDriver() {
        DeleteDriverDetails deleteDriverDetails = new DeleteDriverDetails();

        String expected = "Driver deleted";
        String actual = deleteDriverDetails.deleteDriver("LEWIS HAMILTON");
        assertEquals(expected, actual);
    }

}