package com.example.cm1601_coursework;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;
public class SaveCurrentDataController {
    @FXML
    Button appendButton;

    @FXML
    Button overwriteButton;

    @FXML
    Label successLabel;

    @FXML
    ProgressBar progressBar;


    @FXML
    boolean saveAllowed = false;
    public void appendData() {
        if(dataRepository.size() == 0) {
            successLabel.setTextFill(javafx.scene.paint.Color.RED);
            successLabel.setText("No data to append.");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successLabel.setText("")));
            timeline.play();
        }else {
            saveAllowed = true;
        }

        if (saveAllowed) {
            try {
                FileWriter writer = new FileWriter(("src/Driver_details.txt"), true);
                double progressStep = 1.0 / dataRepository.size();
                double progress = 0.0;
                PauseTransition pause = new PauseTransition(Duration.seconds(0.1));

                for (ArrayList<Object> item : dataRepository) {
                    String line = item.get(0) + "," + item.get(1) + "," + item.get(2) + "," + item.get(3) + "," + item.get(4) + "\n";
                    writer.write(line);

                    pause.playFromStart();
                    progress += progressStep;
                    double finalProgress = progress;
                    pause.setOnFinished(e -> progressBar.setProgress(finalProgress));


                }
                successLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                successLabel.setText("Successfully appended data.");
                writer.close();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> {
                    successLabel.setText("");
                    progressBar.setProgress(0.0);
                }));
                timeline.play();

            } catch (IOException e) {
                Window owner = appendButton.getScene().getWindow();
                AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Error in appending data.");

                throw new RuntimeException(e);
            }
        }
    }

    public void overwriteData() {
        if(dataRepository.size() == 0) {
            successLabel.setTextFill(javafx.scene.paint.Color.RED);
            successLabel.setText("No data to overwrite.");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> successLabel.setText("")));
            timeline.play();
        }else {
            saveAllowed = true;
        }

        if (saveAllowed) {
            try {
                FileWriter writer = new FileWriter(("src/Driver_details.txt"), false);
                double progressStep = 1.0 / dataRepository.size();
                double progress = 0.0;
                PauseTransition pause = new PauseTransition(Duration.seconds(0.1));

                for (ArrayList<Object> item : dataRepository) {
                    String line = item.get(0) + "," + item.get(1) + "," + item.get(2) + "," + item.get(3) + "," + item.get(4) + "\n";
                    writer.write(line);

                    pause.playFromStart();
                    progress += progressStep;
                    double finalProgress = progress;
                    pause.setOnFinished(event  -> progressBar.setProgress(finalProgress));

                }
                successLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                successLabel.setText("Successfully overwrote data.");
                writer.close();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ae -> {
                    successLabel.setText("");
                    progressBar.setProgress(0.0);
                }));
                timeline.play();

            } catch (IOException e) {
                Window owner = overwriteButton.getScene().getWindow();
                AddDriverDetailsController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Error in overwriting data.");

                throw new RuntimeException(e);
            }
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
