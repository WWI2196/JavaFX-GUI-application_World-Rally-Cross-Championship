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
    TextField nameTextField;


    @FXML
    TextField ageField;

    @FXML
    TextField teamTextField;

    @FXML
    TextField carTextField;

    @FXML
    TextField pointsField;

    @FXML
    Button submitButton;

    @FXML
    Button backToMenuButton;

    @FXML
    Label successText;

    @FXML
    Label nameTextError;

    @FXML
    Label ageTextError;

    @FXML
    Label pointTextError;
    @FXML
    public void checkName() {
        try {
            for (ArrayList<Object> item : dataRepository) {
                if (nameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                    throw new Exception();
                } else {
                    nameTextError.setText(null);
                }
            }
        } catch (Exception e) {
            nameTextError.setText("Error: Name already exists");
        }
    }

    @FXML
    public void checkAgeIsNumber() {
        try {
            Integer.parseInt(ageField.getText());
            ageTextError.setText(null);
            if (Integer.parseInt(ageField.getText()) < 15 && Integer.parseInt(ageField.getText()) > 99) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            ageTextError.setText("Error: Enter a valid age");
        }
    }

    @FXML
    public void checkPointsIsNumber() {
        try {
            Integer.parseInt(pointsField.getText());
            pointTextError.setText(null);
        } catch (NumberFormatException e) {
            pointTextError.setText("Points must be a integer");
        }
    }

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {
            try {
                if (nameTextField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }

                for (ArrayList<Object> item : dataRepository) {
                    if (nameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                        throw new IllegalArgumentException("Name already exists.");
                    }
                }

                String name = nameTextField.getText();

                try {
                    Integer.parseInt(ageField.getText());
                    if (ageField.getText().isEmpty() || Integer.parseInt(ageField.getText()) < 15 || Integer.parseInt(ageField.getText()) > 99) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Enter a valid age.");
                }

                int age = Integer.parseInt(ageField.getText());
                String team = teamTextField.getText();
                String carModel = carTextField.getText();

                try {
                    Integer.parseInt(pointsField.getText());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Points must be a integer.");
                }

                int points = Integer.parseInt(pointsField.getText());

                ArrayList<Object> dataCompile = new ArrayList<>();
                dataCompile.add(name.toUpperCase());
                dataCompile.add(age);
                dataCompile.add(team);
                dataCompile.add(carModel);
                dataCompile.add(points);
                dataRepository.add(new ArrayList<>(dataCompile));

                successText.setText("Driver details added successfully");
                nameTextField.clear();
                ageField.clear();
                teamTextField.clear();
                carTextField.clear();
                pointsField.clear();


                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successText.setText(null)));
                timeline.play();

            } catch (IllegalArgumentException e) {
                Window owner = submitButton.getScene().getWindow();
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "Invalid input. "+e.getMessage());
            }
        });
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
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

