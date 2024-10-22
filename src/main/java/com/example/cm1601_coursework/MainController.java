package com.example.cm1601_coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToAddDriverDetails(ActionEvent event) throws IOException { // switch to add the driver details scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddDriverDetails.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image03.jpg", root);

        stage.setScene(scene);
        stage.show();
    }


    public void switchToDeleteDriverDetails(ActionEvent event) throws IOException { // switch to delete the driver details scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteDriverDetails.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image04.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoadSavedData(ActionEvent event) throws IOException { // switch to load saved data scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoadSavedData.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image07.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSaveCurrentData(ActionEvent event) throws IOException { // switch to save current data scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SaveCurrentData.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image06.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToStimulateRandomRace(ActionEvent event) throws IOException { // to switch to Random race stimulator
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SimulateRandomRace.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image08.jpg");
        Image image01 = new Image(file01.toURI().toString());
        ImageView imageView01 = new ImageView(image01);
        imageView01.setFitHeight(750);
        imageView01.setFitWidth(180);
        imageView01.setLayoutX(0);
        imageView01.setLayoutY(0);

        File file02 = new File("src/image09.jpg");
        Image image02 = new Image(file02.toURI().toString());
        ImageView imageView02 = new ImageView(image02);
        imageView02.setFitHeight(750);
        imageView02.setFitWidth(180);
        imageView02.setLayoutX(740);
        imageView02.setLayoutY(0);

        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(imageView01);
        }

        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(imageView02);
        }

        stage.setScene(scene);
        stage.show();
    }

    public void switchToUpdateDriverDetails(ActionEvent event) throws IOException { // switch to update driver details scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UpdateDriverDetails.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image05.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToViewRaceTable(ActionEvent event) throws IOException { // switch to view race table
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewRaceTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        loadTheImage("src/image03.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public static void switchToMenu(ActionEvent event) throws IOException { // switch to main menu
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("MainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        loadTheImage("src/image02.jpg", root);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToViewStandingTable(ActionEvent event) throws IOException { // switch to view standing table
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewStandingTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void loadTheImage(String filename, Parent root){ // method to load the image
        File file01 = new File(filename);
        Image image01 = new Image(file01.toURI().toString());
        ImageView imageView01 = new ImageView(image01);
        imageView01.setFitHeight(750);
        imageView01.setFitWidth(400);
        imageView01.setLayoutX(0);
        imageView01.setLayoutY(0);

        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(imageView01);
        }
    }

    public static class AlertHelper { // class to show alert messages
        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }

    public void exitProgramme() { // method to exit program
        System.exit(0);
    } // exit programme
}