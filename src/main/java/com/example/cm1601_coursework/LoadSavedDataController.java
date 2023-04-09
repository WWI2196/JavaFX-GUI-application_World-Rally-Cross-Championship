package com.example.cm1601_coursework;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class LoadSavedDataController implements Initializable {

    @FXML
    private Label numberOfDriversLabel;

    @FXML
    private Label successTextLabel;

    @FXML
    private Button loadSavedDataButton;

    @FXML
    private Button backToMenu;

    @FXML
    private static boolean loadSavedData = true;

    private static final String PATH = "src/Driver_details.txt"; // path to file

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) { // method to count number of lines in file
        countNumberOfLines(); // call method to count number of lines in file
    }

    public void loadSavedData() {
        if (loadSavedData) {
            try {
                File file = new File(PATH);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) { // read data from file
                    String[] data = scanner.nextLine().split(","); // split data by comma

                    dataRepository.add(new AddDriverDetailsController.DriverDetails(data[0],
                            Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4])));
                     // add data to dataRepository
                }
                scanner.close();

                successTextLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                successTextLabel.setText("Data loaded successfully.");
                loadSavedData = false;

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successTextLabel.setText(null)));
                timeline.play(); // set timer to clear successTextLabel


            } catch (FileNotFoundException e) { // catch exception if file not found
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("No data to load.");

            } catch (NumberFormatException e) { // catch exception if data is corrupted
                successTextLabel.setTextFill(javafx.scene.paint.Color.RED);
                successTextLabel.setText("Data is corrupted.");

            }
        }else {
            Platform.runLater(() -> { // run alert on JavaFX Application Thread
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "To avoid data duplication, loading Driver details again is prohibited.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("System Alert");
                alert.initOwner(backToMenu.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) { // if OK button is pressed
                    backToMenu.fire(); // fire backToMenu button
                }
            });
        }
    }

    public void countNumberOfLines() { // method to count number of lines in file
        try {
            File file = new File(PATH);
            Scanner scanner01 = new Scanner(file);
            int count = 0;
            while (scanner01.hasNextLine()) {  // while there is a next line
                scanner01.nextLine();  // read next line
                count++; // increment count
            }
            numberOfDriversLabel.setText(String.valueOf(count));
            scanner01.close();
        } catch (FileNotFoundException e) {
            Window owner = loadSavedDataButton.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "No data to load.");
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}

