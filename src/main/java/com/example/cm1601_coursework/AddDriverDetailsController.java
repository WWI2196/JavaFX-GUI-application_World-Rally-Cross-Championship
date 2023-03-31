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
        for (ArrayList<Object> item : dataRepository) {
            if (nameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                nameTextError.setText("Error: Name already exists");
            } else {
                nameTextError.setText("");
            }
        }
    }

    @FXML
    public void checkAgeIsNumber() {
        try {
            Integer.parseInt(ageField.getText());
            ageTextError.setText("");
        } catch (NumberFormatException error01) {
            ageTextError.setText("Error: Age must be a integer number");
        }
    }

    @FXML
    public void checkPointsIsNumber() {
        try {
            Integer.parseInt(pointsField.getText());
            pointTextError.setText("");
        } catch (NumberFormatException error02) {
            pointTextError.setText("Error: Points must be a integer number");
        }
    }
    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {
            try {
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageField.getText());
                String team = teamTextField.getText();
                String carModel = carTextField.getText();
                int points = Integer.parseInt(pointsField.getText());

                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Error: Name cannot be empty.");
                }

                if (ageField.getText().isEmpty()) {
                    throw new NumberFormatException("Error: Age cannot be empty.");
                }

                try {
                    Integer.parseInt(ageField.getText());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Error: Age must be a number");
                }

                for (ArrayList<Object> item : dataRepository) {
                    if (name.toUpperCase().equals(item.get(0).toString())) {
                        throw new IllegalArgumentException("Error: Name already exists");
                    }
                }

                ArrayList<Object> dataCompile = new ArrayList<>();
                dataCompile.add(name.toUpperCase());
                dataCompile.add(age);
                dataCompile.add(team);
                dataCompile.add(carModel);
                dataCompile.add(points);
                dataRepository.add(new ArrayList<>(dataCompile));

                successText.setText("Driver details added successfully!");
                nameTextField.clear();
                ageField.clear();
                teamTextField.clear();
                carTextField.clear();
                pointsField.clear();


                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successText.setText("")));
                timeline.play();

            } catch (IllegalArgumentException e) {
                Window owner = submitButton.getScene().getWindow();
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Invalid input. Please try again.");
                throw new RuntimeException();
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

