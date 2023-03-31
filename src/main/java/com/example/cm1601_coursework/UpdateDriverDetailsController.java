package com.example.cm1601_coursework;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
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

    boolean allowUpdate = false;
    public void checkName() {
        for (ArrayList<Object> item : dataRepository) {
            if (updateNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                newDriverAgeText.setText(item.get(1).toString());
                newDriverTeamText.setText(item.get(2).toString());
                newDriverCarText.setText(item.get(3).toString());
                newDriverPointsText.setText(item.get(4).toString());

                driverNameSearchLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                driverNameSearchLabel.setText("Driver : " + item.get(0).toString() +" found.");

                allowUpdate = true;
                break;
            }else {
                driverNameSearchLabel.setTextFill(javafx.scene.paint.Color.RED);
                driverNameSearchLabel.setText("Driver Name: " + updateNameTextField.getText() + " does not exist.");

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> driverNameSearchLabel.setText("")));
                timeline.play();

            }
        }
    }

    public void checkAgeIsNumber() {
        try {
            Integer.parseInt(newDriverAgeText.getText());
            errorAgeLabel.setText("");
        } catch (NumberFormatException error01) {
            errorAgeLabel.setText("Error: Age must be a integer");
        }
    }

    public void checkPointsIsNumber() {
        try {
            Integer.parseInt(newDriverPointsText.getText());
            errorPointsLabel.setText("");
        } catch (NumberFormatException error02) {
            errorPointsLabel.setText("Error: Points must be a integer");
        }
    }

    public void updateDriverDetails() {
        if (allowUpdate) {
            for (ArrayList<Object> item : dataRepository) {
                if (updateNameTextField.getText().toUpperCase().equals(item.get(0).toString())) {
                    try {
                        item.set(1, Integer.parseInt(newDriverAgeText.getText()));
                        item.set(2, newDriverTeamText.getText());
                        item.set(3, newDriverCarText.getText());
                        item.set(4, Integer.parseInt(newDriverPointsText.getText()));

                        successUpdateText.setTextFill(javafx.scene.paint.Color.GREEN);
                        successUpdateText.setText("Driver details updated successfully");
                        driverNameSearchLabel.setText("");
                        updateNameTextField.setText("");
                        newDriverAgeText.setText("");
                        newDriverTeamText.setText("");
                        newDriverCarText.setText("");
                        newDriverPointsText.setText("");

                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> successUpdateText.setText("")));
                        timeline.play();
                    }catch (NumberFormatException error03) {
                        Window owner = updateButton.getScene().getWindow();
                        AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                                "Please enter a valid age and points");
                    }
                }
            }
        }else {
            Window owner = updateButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Driver details did not update.\nPlease check the driver name and try again.");
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        File file01 = new File("src/image02.jpg");
        Image image01 = new Image(file01.toURI().toString());
        ImageView imageView01 = new ImageView(image01);
        imageView01.setFitHeight(750);
        imageView01.setFitWidth(400);
        imageView01.setLayoutX(0);
        imageView01.setLayoutY(0);

        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(imageView01);
        }

        stage.setScene(scene);
        stage.show();
    }
}
