package com.example.cm1601_coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.util.Objects;

import javafx.scene.layout.Pane;

public class Main extends Application { 
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene scene = new Scene(root, 900, 750);

        File file = new File("src/image01.jpg");
        Image icon = new Image(file.toURI().toString());

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

        primaryStage.setTitle("World Rally Championship Management System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.show();



    }
}