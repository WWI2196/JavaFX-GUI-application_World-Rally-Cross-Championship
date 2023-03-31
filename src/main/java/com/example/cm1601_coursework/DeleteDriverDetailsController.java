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
import java.util.Objects;

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
                        nameLabel.setText("");
                        ageLabel.setText("");
                        teamLabel.setText("");
                        carLabel.setText("");
                        pointsLabel.setText("");
                        errorDeleteText.setText("");
                        deleteNameTextField.setText("");

                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event01 -> successDeleteText.setText("")));
                        timeline.play();

                    } catch (IllegalAccessError e) {
                        Window owner = deleteButton.getScene().getWindow();
                        AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                                "Invalid input.");
                        throw new RuntimeException(e.getMessage());
                    }
                }else {
                    AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, deleteButton.getScene().getWindow(), "Error!",
                            "No data found.");
                }
            }
        }else{
            Window owner = deleteButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Driver not found. Please check the name and try again.");

        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
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

