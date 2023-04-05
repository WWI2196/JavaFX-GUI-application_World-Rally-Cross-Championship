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
    TextField updateNameTextField;

    @FXML
    TextField newDriverAgeText;

    @FXML
    TextField newDriverTeamText;

    @FXML
    TextField newDriverCarText;

    @FXML
    TextField newDriverPointsText;

    @FXML
    Button updateButton;

    @FXML
    Button searchButton;

    @FXML
    Label driverNameSearchLabel;

    @FXML
    Label errorAgeLabel;

    @FXML
    Label errorPointsLabel;

    @FXML
    Label successUpdateText;

    @FXML
    boolean updateAllowed = false;
    int index;
    public void checkName() {
        for (ArrayList<Object> item : dataRepository) {
            if (updateNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                index = dataRepository.indexOf(item);

                newDriverAgeText.setText(item.get(1).toString());
                newDriverTeamText.setText(item.get(2).toString());
                newDriverCarText.setText(item.get(3).toString());
                newDriverPointsText.setText(item.get(4).toString());

                driverNameSearchLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                driverNameSearchLabel.setText("Driver found.");

                updateAllowed = true;

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
        try {
            Integer.parseInt(newDriverAgeText.getText());
            errorAgeLabel.setText(null);
            if (Integer.parseInt(newDriverAgeText.getText()) < 15 || Integer.parseInt(newDriverAgeText.getText()) > 99 || newDriverAgeText.getText().isEmpty()) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            errorAgeLabel.setText("Error: Enter a valid age");
        }
    }

    public void checkPointsIsNumber() {
        try {
            Integer.parseInt(newDriverPointsText.getText());
            errorPointsLabel.setText(null);
        } catch (NumberFormatException e) {
            errorPointsLabel.setText("Error: Points must be an integer");
        }
    }

    public void updateDriverDetails() {
        if (updateAllowed){
            try {
                try {
                    Integer.parseInt(newDriverAgeText.getText());
                    if (Integer.parseInt(newDriverAgeText.getText()) < 15 || Integer.parseInt(newDriverAgeText.getText()) > 99 || newDriverAgeText.getText().isEmpty()) {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Enter a valid age");
                }

                dataRepository.get(index).set(1, Integer.parseInt(newDriverAgeText.getText()));
                dataRepository.get(index).set(2, newDriverTeamText.getText());
                dataRepository.get(index).set(3, newDriverCarText.getText());

                try {
                    Integer.parseInt(newDriverPointsText.getText());
                    errorPointsLabel.setText(null);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Points must be a integer");
                }
                dataRepository.get(index).set(4, Integer.parseInt(newDriverPointsText.getText()));

                successUpdateText.setTextFill(javafx.scene.paint.Color.GREEN);
                successUpdateText.setText("Driver details updated successfully");
                driverNameSearchLabel.setText(null);
                updateNameTextField.setText(null);
                newDriverAgeText.clear();
                newDriverTeamText.clear();
                newDriverCarText.clear();
                newDriverPointsText.clear();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> successUpdateText.setText(null)));
                timeline.play();

            }catch (NumberFormatException e) {
                Window owner = updateButton.getScene().getWindow();
                AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "Invalid input. "+e.getMessage());
            }
        }else {
            Window owner = updateButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                    "Driver not found. Please search again.");
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}
