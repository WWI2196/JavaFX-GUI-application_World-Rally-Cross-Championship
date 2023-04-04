package com.example.cm1601_coursework;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successTextLabel.setText(null)));
                timeline.play();


            } catch (FileNotFoundException e) {
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("No data to load.");

            } catch (NumberFormatException e) {
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("Data is corrupted.");

            }
        }else {
            Window owner = loadSavedDataButton.getScene().getWindow();
            AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "System Alert!",
                    "Loading driver details again is strictly prohibited to avoid duplication of data that has already been loaded.");
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
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}

