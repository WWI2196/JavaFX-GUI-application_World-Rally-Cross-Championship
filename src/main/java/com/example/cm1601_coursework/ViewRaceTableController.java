package com.example.cm1601_coursework;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Button backToMenu;

    @FXML
    private TableColumn<Race, String> circuitColumn; // table columns

    @FXML
    private TableColumn<Race, LocalDate> dateColumn;

    @FXML
    private TableView<Race> raceTable; // table view

    private static final String PATH_TO_DETAILS = "src/Race_settings.txt"; // path to file

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) { // initialise table
        raceTable.setStyle("-fx-font-family: 'Arial';-fx-font-size: 14pt;"); // set font size and type

        circuitColumn.setCellValueFactory(new PropertyValueFactory<>("circuit")); // set cell value factory
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        LocalDate currentDate = LocalDate.now(); // get current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // format date
        String formattedDate = currentDate.format(formatter);
        currentDateLabel.setText(formattedDate); // set current date label

        try {
            File file = new File(PATH_TO_DETAILS); // create file
            Scanner scanner = new Scanner(file);
            List<Race> races = new ArrayList<>();

            while (scanner.hasNextLine()) { // read file
                String[] line = scanner.nextLine().split(","); // split line
                String circuit = line[0]; // get circuit
                LocalDate date = LocalDate.parse(line[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")); // get date
                races.add(new Race( circuit, date)); // add to list
            }

            races.sort(Comparator.comparing(Race::getDate)); // sort by date
            raceTable.getItems().addAll(races); // add to table

        } catch (IOException e) { // catch exception
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR,"No data found.", ButtonType.OK); // show error message
                alert.setHeaderText(null);
                alert.initOwner(backToMenu.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) { // if ok is pressed
                    backToMenu.fire(); // go back to menu
                }
            });
        }
    }
    public static class Race{ // Race class
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