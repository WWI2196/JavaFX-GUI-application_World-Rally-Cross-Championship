package com.example.cm1601_coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class ViewRaceTableController implements Initializable {

    @FXML
    private Label currentDateLabel;

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

        } catch (IOException ignored) {
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
        MainController.switchToMenu(event);
    }
}