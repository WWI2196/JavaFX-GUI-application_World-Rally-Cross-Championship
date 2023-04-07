package com.example.cm1601_coursework;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class AddDriverDetailsController {
    public static ArrayList<ArrayList<Object>> dataRepository = new ArrayList<>();

    @FXML
    private TextField nameTextField;


    @FXML
    private TextField ageField;

    @FXML
    private TextField teamTextField;

    @FXML
    private TextField carTextField;

    @FXML
    private TextField pointsField;

    @FXML
    private Button submitButton;


    @FXML
    private Label successText;

    @FXML
    private Label nameTextError;

    @FXML
    private Label ageTextError;

    @FXML
    private Label pointTextError;
    @FXML
    public void checkName() {
        try {
            for (ArrayList<Object> item : dataRepository) {
                if (nameTextField.getText().toUpperCase().equals(item.get(0).toString())) { // check if name already exists
                    throw new Exception(); // if name exists, throw exception
                } else {
                    nameTextError.setText(null);
                }
            }
        } catch (Exception e) { // if name exists, show error message
            nameTextError.setText("Error: Name already exists");
        }
    }

    @FXML
    public void checkAgeIsNumber() {
        try {
            Integer.parseInt(ageField.getText()); // check if age is an integer number
            ageTextError.setText(null);
            if (Integer.parseInt(ageField.getText()) < 15 && Integer.parseInt(ageField.getText()) > 99) { // check if age is between 15 and 99
                throw new NumberFormatException(); // if age is not between 15 and 99, throw exception
            }
        } catch (NumberFormatException e) { // if age is not an integer number, show error message
            ageTextError.setText("Error: Enter a valid age");
        }
    }

    @FXML
    public void checkPointsIsNumber() {
        try {
            Integer.parseInt(pointsField.getText());// check if points is an integer number
            pointTextError.setText(null);
        } catch (NumberFormatException e) { // if points is not an integer number, show error message
            pointTextError.setText("Points must be a integer");
        }
    }

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {// when submit button is clicked
            try {
                if (nameTextField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }

                for (ArrayList<Object> item : dataRepository) {
                    if (nameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                        throw new IllegalArgumentException("Name already exists.");
                    }
                }

                String name = nameTextField.getText(); // get name from text field

                try {
                    Integer.parseInt(ageField.getText());
                    if (ageField.getText().isEmpty() || Integer.parseInt(ageField.getText()) < 15 || Integer.parseInt(ageField.getText()) > 99) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Enter a valid age.");
                }

                int age = Integer.parseInt(ageField.getText()); // get age from text field
                String team = teamTextField.getText(); // get team from text field
                String carModel = carTextField.getText(); // get car model from text field

                try {
                    Integer.parseInt(pointsField.getText());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Points must be a integer.");
                }

                int points = Integer.parseInt(pointsField.getText()); // get points from text field

                ArrayList<Object> dataCompile = new ArrayList<>(); // create array list to store data
                dataCompile.add(name.toUpperCase()); // add name to array list
                dataCompile.add(age); // add age to array list
                dataCompile.add(team); // add team to array list
                dataCompile.add(carModel); // add car model to array list
                dataCompile.add(points);   // add points to array list
                dataRepository.add(new ArrayList<>(dataCompile)); // add array list to data repository

                successText.setText("Driver details added successfully");
                nameTextField.clear();
                ageField.clear();
                teamTextField.clear();
                carTextField.clear();
                pointsField.clear();


                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successText.setText(null)));
                timeline.play();

            } catch (IllegalArgumentException e) { // if the fields are empty or invalid, show error message
                Window owner = submitButton.getScene().getWindow();
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "Invalid input. "+e.getMessage());
            }
        });
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event); // switch to menu
    }

    public static class AlertHelper {

        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }
}

