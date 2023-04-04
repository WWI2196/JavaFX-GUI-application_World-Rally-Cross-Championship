package com.example.cm1601_coursework;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

class AddDriverDetailsControllerTest {
    @Test
    public void checkName() {
        ArrayList<Object> driver = new ArrayList<>();
        driver.add("Lewis Hamilton");
        driver.add(35);
        driver.add("Mercedes");
        driver.add("Mercedes");
        driver.add(93);
        dataRepository.add(driver);

        AddDriverDetailsController addDriverDetailsController = new AddDriverDetailsController();
        addDriverDetailsController.nameTextField = new TextField();
        addDriverDetailsController.nameTextField.setText("Lewis Hamilton");

        addDriverDetailsController.checkName();
        assertEquals("Error: Name already exists", addDriverDetailsController.nameTextError.getText());


    }

    @Test
    void checkAgeIsNumber() {
    }

    @Test
    void checkPointsIsNumber() {
    }

    @Test
    void initialize() {
    }

    @Test
    void switchToMenu() {
    }
}