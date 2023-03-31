package com.example.cm1601_coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class ViewRaceTableController implements Initializable {

    @FXML
    Label currentDateLabel;

    @FXML
    private TableColumn<Race, String> circuitColumn;

    @FXML
    private TableColumn<Race, LocalDate> dateColumn;

    @FXML
    private TableView<Race> raceTable;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        raceTable.setStyle("-fx-font-family: 'Arial';-fx-font-size: 14pt;");

        circuitColumn.setCellValueFactory(new PropertyValueFactory<>("circuit"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        currentDateLabel.setText(formattedDate);

        try {
            File file = new File("src/Race_settings.txt");
            Scanner scanner = new Scanner(file);
            List<Race> races = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                String circuit = line[0];
                LocalDate date = LocalDate.parse(line[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                races.add(new Race( circuit, date));
            }

            races.sort(Comparator.comparing(Race::getDate));
            raceTable.getItems().addAll(races);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class Race{

        private final String circuit;
        private final LocalDate date;

        public Race( String circuit, LocalDate date) {
            this.circuit = circuit;
            this.date = date;
        }

        public String getCircuit() {
            return circuit;
        }

        public LocalDate getDate() {
            return date;
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