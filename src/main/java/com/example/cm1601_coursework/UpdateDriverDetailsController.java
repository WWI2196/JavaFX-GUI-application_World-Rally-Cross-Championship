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

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class UpdateDriverDetailsController {
    @FXML
    private TextField updateNameTextField;

    @FXML
    private TextField newDriverAgeText;

    @FXML
    private TextField newDriverTeamText;

    @FXML
    private TextField newDriverCarText;

    @FXML
    private TextField newDriverPointsText;

    @FXML
    private Button updateButton;


    @FXML
    private Label driverNameSearchLabel;

    @FXML
    private Label errorAgeLabel;

    @FXML
    private Label errorPointsLabel;

    @FXML
    private Label successUpdateText;

    @FXML
    private boolean updateAllowed = false; // boolean to check if update is allowed
    private int index; // index of driver to update
    public void checkName() {
        for (ArrayList<Object> item : dataRepository) {
            if (updateNameTextField.getText().toUpperCase().equals(item.get(0).toString())) { // check if name exists
                index = dataRepository.indexOf(item); // get index of driver to update

                newDriverAgeText.setText(item.get(1).toString()); // set text fields to current driver details
                newDriverTeamText.setText(item.get(2).toString());
                newDriverCarText.setText(item.get(3).toString());
                newDriverPointsText.setText(item.get(4).toString());

                driverNameSearchLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                driverNameSearchLabel.setText("Driver found.");

                updateAllowed = true; // allow update

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> driverNameSearchLabel.setText(null)));
                timeline.play();

                break;

            }else {
                driverNameSearchLabel.setTextFill(javafx.scene.paint.Color.RED);
                driverNameSearchLabel.setText("No data found.");

                updateAllowed = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> driverNameSearchLabel.setText(null)));
                timeline.play();

            }
        }
    }

    public void checkAgeIsNumber() {
        try { // check if age is a number
            Integer.parseInt(newDriverAgeText.getText());
            errorAgeLabel.setText(null);
            if (Integer.parseInt(newDriverAgeText.getText()) < 15
                    || Integer.parseInt(newDriverAgeText.getText()) > 99 || newDriverAgeText.getText().isEmpty()) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            errorAgeLabel.setText("Error: Enter a valid age");
        }
    }

    public void checkPointsIsNumber() {
        try { // check if points is a number
            Integer.parseInt(newDriverPointsText.getText());
            errorPointsLabel.setText(null);
        } catch (NumberFormatException e) {
            errorPointsLabel.setText("Error: Points must be an integer");
        }
    }

    public void updateDriverDetails() {
        if (updateAllowed){ // check if update is allowed
            try {
                try {
                    Integer.parseInt(newDriverAgeText.getText());
                    if (Integer.parseInt(newDriverAgeText.getText()) < 15
                            || Integer.parseInt(newDriverAgeText.getText()) > 99 || newDriverAgeText.getText().isEmpty()) {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Enter a valid age");
                }

                try {
                    Integer.parseInt(newDriverPointsText.getText());
                    errorPointsLabel.setText(null);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Points must be a integer");
                }

                updateDataRepository(index, Integer.parseInt(newDriverAgeText.getText()), newDriverTeamText.getText(),
                        newDriverCarText.getText(), Integer.parseInt(newDriverPointsText.getText())); // update data repository

                updateAllowed = false; // disallow update

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> successUpdateText.setText(null)));
                timeline.play();

            }catch (NumberFormatException e) { // catch any errors
                Window owner = updateButton.getScene().getWindow();
                MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "Invalid input. "+e.getMessage()); // display error message
            }
        }else { // if update is not allowed
            Window owner = updateButton.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                    "Driver not found. Please search again."); // display error message
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }

    private void updateDataRepository(int index, int age, String team, String car, int points) { // method to update data repository
        dataRepository.get(index).set(1, age);
        dataRepository.get(index).set(2, team);
        dataRepository.get(index).set(3, car);
        dataRepository.get(index).set(4, points);

        successUpdateText.setTextFill(javafx.scene.paint.Color.GREEN);
        successUpdateText.setText("Driver details updated successfully");
        driverNameSearchLabel.setText(null);
        updateNameTextField.setText(null);
        newDriverAgeText.clear();
        newDriverTeamText.clear();
        newDriverCarText.clear();
        newDriverPointsText.clear();

    }
}
