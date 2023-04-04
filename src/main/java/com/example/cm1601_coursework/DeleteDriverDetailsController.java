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

public class DeleteDriverDetailsController {
    @FXML
    TextField deleteNameTextField;

    @FXML
    Button deleteButton;

    @FXML
    Button searchButton;

    @FXML
    Label nameLabel;

    @FXML
    Label ageLabel;

    @FXML
    Label teamLabel;

    @FXML
    Label carLabel;

    @FXML
    Label pointsLabel;

    @FXML
    Label successDeleteText;

    @FXML
    Label errorDeleteText;

    @FXML

    boolean deleteAllowed = false;
    public void checkName() {
        for (ArrayList<Object> item : dataRepository) {
            if (deleteNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                errorDeleteText.setTextFill(javafx.scene.paint.Color.GREEN);
                errorDeleteText.setText("Driver found");

                nameLabel.setText(item.get(0).toString());
                ageLabel.setText(item.get(1).toString());
                teamLabel.setText(item.get(2).toString());
                carLabel.setText(item.get(3).toString());
                pointsLabel.setText(item.get(4).toString());

                deleteAllowed = true;
                break;
            }else {
                errorDeleteText.setTextFill(javafx.scene.paint.Color.RED);
                errorDeleteText.setText("Error: No data found.");
            }
        }
    }

    @FXML
    public void initiate(){
        if (deleteAllowed) {
            for (ArrayList<Object> item : dataRepository) {
                if (deleteNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                    try {
                        dataRepository.remove(item);
                        successDeleteText.setTextFill(javafx.scene.paint.Color.GREEN);
                        successDeleteText.setText("Successfully deleted " + deleteNameTextField.getText().toUpperCase());
                        nameLabel.setText(null);
                        ageLabel.setText(null);
                        teamLabel.setText(null);
                        carLabel.setText(null);
                        pointsLabel.setText(null);
                        errorDeleteText.setText(null);
                        deleteNameTextField.setText(null);


                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successDeleteText.setText(null)));
                        timeline.play();

                        break;

                    } catch (IllegalAccessError e) {
                        Window owner = deleteButton.getScene().getWindow();
                        AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                                "Invalid input.");
                    }
                }
            }

        }else{
            Window owner = deleteButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Driver not found. Please try again.");

        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}

