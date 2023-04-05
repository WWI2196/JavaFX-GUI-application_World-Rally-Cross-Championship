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

    private boolean deleteAllowed = false;
    private int deleteIndex;
    public void checkName() {
        for (ArrayList<Object> item : dataRepository) {
            if (deleteNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                deleteIndex = dataRepository.indexOf(item);

                errorDeleteText.setTextFill(javafx.scene.paint.Color.GREEN);
                errorDeleteText.setText("Driver found");

                nameLabel.setText(item.get(0).toString());
                ageLabel.setText(item.get(1).toString());
                teamLabel.setText(item.get(2).toString());
                carLabel.setText(item.get(3).toString());
                pointsLabel.setText(item.get(4).toString());

                deleteAllowed = true;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> errorDeleteText.setText(null)));
                timeline.play();

                break;

            }else {
                errorDeleteText.setTextFill(javafx.scene.paint.Color.RED);
                errorDeleteText.setText("No data found.");

                deleteAllowed = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> errorDeleteText.setText(null)));
                timeline.play();
            }
        }
    }

    @FXML
    public void initiate(){
        if (deleteAllowed) {
            try {
                dataRepository.remove(dataRepository.get(deleteIndex));

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
        }else {
            Window owner = deleteButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Driver not found. Please search again.");
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}

