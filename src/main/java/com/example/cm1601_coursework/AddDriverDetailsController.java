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
    public static ArrayList<DriverDetails> dataRepository = new ArrayList<>(); // create an array list to store driver data

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
    public void checkName() { // method to check if name already exists
        try {
            for (DriverDetails item : dataRepository) {
                if (nameTextField.getText().toUpperCase().equals(item.getName())) {
                    // check if the name already exists
                    throw new Exception(); // if name exists, throw exception
                } else {
                    nameTextError.setText(null);
                }
            }
        } catch (Exception e) { // if the name exists, show an error message
            nameTextError.setText("Error: Name already exists");
        }
    }

    @FXML
    public void checkAgeIsNumber() { // method to check if age is an integer number
        try {
            Integer.parseInt(ageField.getText()); // check if age is an integer number
            ageTextError.setText(null);
            if (Integer.parseInt(ageField.getText()) < 15 && Integer.parseInt(ageField.getText()) > 99) {
                // check if the age is between 15 and 99
                throw new NumberFormatException(); // if age is not between 15 and 99, throw exception
            }
        } catch (NumberFormatException e) { //catch exception
            ageTextError.setText("Error: Enter a valid age"); // if age is not between 15 and 99, show an error message
        }
    }

    @FXML
    public void checkPointsIsNumber() { // method to check if points is an integer number
        try {
            Integer.parseInt(pointsField.getText());// check if points is an integer number
            pointTextError.setText(null);
        } catch (NumberFormatException e) { // if points is not an integer number, show an error message
            pointTextError.setText("Points must be a integer");
        }
    }

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {// when the submitted button is clicked
            try {
                if (nameTextField.getText().isEmpty()) { // check if the name field is empty
                    throw new IllegalArgumentException("Name cannot be empty."); // if the name field is empty, throw exception
                }

                for (DriverDetails item : dataRepository) { // check if the name already exists
                    if (nameTextField.getText().toUpperCase().equals(item.getName())) {
                        throw new IllegalArgumentException("Name already exists.");
                    }
                }

                try {
                    Integer.parseInt(ageField.getText());
                    if (ageField.getText().isEmpty() || Integer.parseInt(ageField.getText()) < 15
                            || Integer.parseInt(ageField.getText()) > 99) { // check if the age is between 15 and 99
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Enter a valid age.");
                }

                try {
                    Integer.parseInt(pointsField.getText());
                } catch (NumberFormatException e) { // check if points is an integer number
                    throw new IllegalArgumentException("Points must be a integer.");
                }

                String name = nameTextField.getText().toUpperCase(); // get name from text field
                int age = Integer.parseInt(ageField.getText()); // get age from text field
                String team = teamTextField.getText(); // get team from text field
                String carModel = carTextField.getText(); // get a car model from text field
                int points = Integer.parseInt(pointsField.getText()); // get points from text field

                dataRepository.add(new DriverDetails(name, age, team, carModel, points)); // add driver details to data repository

                successText.setText("Driver details added successfully");
                nameTextField.clear();
                ageField.clear();
                teamTextField.clear();
                carTextField.clear();
                pointsField.clear();


                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successText.setText(null)));
                timeline.play();

            } catch (IllegalArgumentException e) { // if the fields are empty or invalid, show an error message
                Window owner = submitButton.getScene().getWindow(); // get window
                MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "Invalid input. "+e.getMessage());
            }
        });
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event); // switch to a menu
    }

    public static class DriverDetails implements java.io.Serializable {
        // class to store driver details
        private String name; // name
        private int age; // age
        private String team; // team
        private String carModel; // car model
        private int points; // points

        public DriverDetails(String name, int age, String team, String carModel, int points) {
            this.name = name;
            this.age = age;
            this.team = team;
            this.carModel = carModel;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getTeam() {
            return team;
        }

        public String getCarModel() {
            return carModel;
        }

        public int getPoints() {
            return points;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

}

