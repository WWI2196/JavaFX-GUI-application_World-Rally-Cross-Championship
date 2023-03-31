package com.example.cm1601_coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToAddDriverDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddDriverDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image03.jpg");
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
    public void switchToDeleteDriverDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteDriverDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image04.jpg");
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
    public void switchToLoadSavedData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoadSavedData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image07.jpg");
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
    public void switchToSaveCurrentData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SaveCurrentData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image06.jpg");
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
    public void switchToStimulateRandomRace(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SimulateRandomRace.fxml"));
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
    public void switchToUpdateDriverDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateDriverDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image05.jpg");
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
    public void switchToViewRaceTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewRaceTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        File file01 = new File("src/image03.jpg");
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
    public void switchToViewStandingTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewStandingTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgramme() {
        System.exit(0);
    }
}