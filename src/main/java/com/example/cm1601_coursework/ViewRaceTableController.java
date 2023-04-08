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
    private TableColumn<Race, String> circuitColumn;

    @FXML
    private TableColumn<Race, LocalDate> dateColumn;

    @FXML
    private TableView<Race> raceTable;

    private static final String PATH_TO_DETAILS = "src/Race_settings.txt";

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
            File file = new File(PATH_TO_DETAILS);
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
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR,"No data found.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.initOwner(backToMenu.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    backToMenu.fire();
                }
            });
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