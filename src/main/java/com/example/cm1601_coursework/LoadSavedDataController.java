package com.example.cm1601_coursework;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class LoadSavedDataController implements Initializable {

    @FXML
    Label numberOfDriversLabel;

    @FXML
    Label successTextLabel;

    @FXML
    Button loadSavedDataButton;

    @FXML
    public static boolean loadDataTime = true;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        countNumberOfLines();
    }

    public void loadSavedData() {
        if (loadDataTime) {
            try {
                File file = new File("src/Driver_details.txt");
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    ArrayList<Object> driverData = new ArrayList<>();
                    driverData.add(data[0]);
                    driverData.add(Integer.parseInt(data[1]));
                    driverData.add(data[2]);
                    driverData.add(data[3]);
                    driverData.add(Integer.parseInt(data[4]));
                    dataRepository.add(driverData);
                }
                scanner.close();

                successTextLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                successTextLabel.setText("Data loaded successfully.");
                loadDataTime = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successTextLabel.setText("")));
                timeline.play();


            } catch (FileNotFoundException e) {
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("No data to load.");
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("Data is corrupted.");
                throw new RuntimeException(e);
            }
        }else {
            Window owner = loadSavedDataButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "System Alert!",
                    "Loading driver details again is strictly prohibited \nto avoid duplication of data that has already been loaded.");
        }
    }

    public void countNumberOfLines() {
        try {
            File file = new File("src/Driver_details.txt");
            Scanner scanner01 = new Scanner(file);
            int count = 0;
            while (scanner01.hasNextLine()) {
                scanner01.nextLine();
                count++;
            }
            numberOfDriversLabel.setText(String.valueOf(count));
            scanner01.close();
        } catch (FileNotFoundException e) {
            Window owner = loadSavedDataButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "No data to load.");
            throw new RuntimeException(e);
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

