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

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class DeleteDriverDetailsController {
    @FXML
    private TextField deleteNameTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label teamLabel;

    @FXML
    private Label carLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label successDeleteText;

    @FXML
    private Label errorDeleteText;

    @FXML

    private boolean deleteAllowed = false; // boolean to check if delete is allowed
    private int deleteIndex; // index of driver to delete
    public void checkName() {
        for (AddDriverDetailsController.DriverDetails item : dataRepository) {
            if (deleteNameTextField.getText().toUpperCase().equals(item.getName())) {
                deleteIndex = dataRepository.indexOf(item); // get index of driver to delete

                errorDeleteText.setTextFill(javafx.scene.paint.Color.GREEN);
                errorDeleteText.setText("Driver found");

                nameLabel.setText(item.getName()); // set labels to driver details
                ageLabel.setText(String.valueOf(item.getAge()));
                teamLabel.setText(item.getTeam());
                carLabel.setText(item.getCarModel());
                pointsLabel.setText(String.valueOf(item.getPoints()));

                deleteAllowed = true; // allow delete

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> errorDeleteText.setText(null)));
                timeline.play();

                break;

            }else {
                errorDeleteText.setTextFill(javafx.scene.paint.Color.RED);
                errorDeleteText.setText("No data found."); // if no data found, set labels to null

                deleteAllowed = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> errorDeleteText.setText(null)));
                timeline.play();
            }
        }
    }

    @FXML
    public void initiate(){
        if (deleteAllowed) { // if delete is allowed, delete driver
            try {
                dataRepository.remove(dataRepository.get(deleteIndex)); // remove driver from dataRepository

                successDeleteText.setTextFill(javafx.scene.paint.Color.GREEN);
                successDeleteText.setText("Successfully deleted " + nameLabel.getText().toUpperCase());

                nameLabel.setText(null);
                ageLabel.setText(null);
                teamLabel.setText(null);
                carLabel.setText(null);
                pointsLabel.setText(null);
                errorDeleteText.setText(null);
                deleteNameTextField.setText(null);

                deleteAllowed = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successDeleteText.setText(null)));
                timeline.play();


            } catch (IllegalAccessError ignored) {
            }
        }else { // if delete is not allowed, show error
            Window owner = deleteButton.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Driver not found. Please search again.");
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}

