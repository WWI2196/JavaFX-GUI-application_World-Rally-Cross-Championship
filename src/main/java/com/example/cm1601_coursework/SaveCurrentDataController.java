package com.example.cm1601_coursework;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;
public class SaveCurrentDataController {
    @FXML
    private Button appendButton;

    @FXML
    private Button overwriteButton;

    @FXML
    private Label successLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private static final String FILE_PATH = "src/Driver_details.txt"; // file path to write to

    public void appendData() { // append data to file
        if(dataRepository.size() == 0) { // if no data to append
            Window owner = appendButton.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                    "No data to append."); // show error message

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successLabel.setText(null)));
            timeline.play();
        }else {
            try {
                writeToFile(true); // write data to file

                successLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                successLabel.setText("Successfully appended data."); // show success message

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> {
                    successLabel.setText(null);
                    progressBar.setProgress(0.0);
                })); // reset success message and progress bar after 3 seconds
                timeline.play();

            } catch (IOException e) { // catch any errors
                Window owner = appendButton.getScene().getWindow();
                MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Error in appending data.");
            }
        }
    }

    public void overwriteData() { // overwrite data in file
        if(dataRepository.size() == 0) {
            Window owner = appendButton.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                    "No data to overwrite.");

        }else {
            try {
                writeToFile(false); // write data to file

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> {
                    successLabel.setText(null);
                    progressBar.setProgress(0.0);
                }));
                timeline.play();

            } catch (IOException e) {
                Window owner = overwriteButton.getScene().getWindow();
                MainController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Error in overwriting data.");
            }
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }

    private void writeToFile(boolean mode) throws IOException {
        FileWriter writer = new FileWriter((SaveCurrentDataController.FILE_PATH), mode); //open file in append or overwrite mode
        double progressStep = 2.0 / dataRepository.size(); // calculate progress step
        double progress = 0.0; // initialise progress
        PauseTransition pause = new PauseTransition(Duration.seconds(0.1)); // set pause time to 0.1 seconds

        for (ArrayList<Object> item : dataRepository) { // loop through data repository
            String line = item.get(0) + "," + item.get(1) + "," + item.get(2) + "," + item.get(3) + "," + item.get(4) + "\n";
            writer.write(line); // write line to file

            pause.playFromStart();
            progress += progressStep; // update progress
            double finalProgress = progress;
            pause.setOnFinished(event  -> progressBar.setProgress(finalProgress)); // update progress bar

        }
        writer.close();
    }

}
