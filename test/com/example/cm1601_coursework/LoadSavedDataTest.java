package com.example.cm1601_coursework;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoadSavedDataTest {

    @Test
    @Order(1)
    void loadDataIncorrectPath() {
        LoadSavedData loadSavedData = new LoadSavedData();
        String expected = "Error: File not found";
        String actual = loadSavedData.loadSavedData("incorrectPath");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void loadDataCorrectPath() {
        LoadSavedData loadSavedData = new LoadSavedData();
        String expected = "Data loaded successfully.";
        String actual = loadSavedData.loadSavedData("src/Driver_details.txt");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void loadDataAlreadyLoaded() {
        LoadSavedData loadSavedData = new LoadSavedData();
        String expected = "Error: Data already loaded";
        String actual = loadSavedData.loadSavedData("src/Driver_details.txt");
        assertEquals(expected, actual);
    }
}