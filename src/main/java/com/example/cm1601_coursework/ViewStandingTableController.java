package com.example.cm1601_coursework;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class ViewStandingTableController implements Initializable {

    @FXML
    Button backToMenuButton;
    @FXML
    private TableColumn<sortedDriverData, String> carModelColumn;

    @FXML
    private TableColumn<sortedDriverData, String> driverNameColumn;

    @FXML
    private TableColumn<sortedDriverData, String> driverTeamColumn;

    @FXML
    private TableColumn<sortedDriverData, Integer> pointsColumn;

    @FXML
    private TableColumn<sortedDriverData, Integer> positionColumn;

    @FXML
    private TableView<sortedDriverData> standingTable;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (dataRepository.size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No data found.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.initOwner(backToMenuButton.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    backToMenuButton.fire();
                }
            }else {
                try {
                    ArrayList<ArrayList<Object>> dataRepository = AddDriverDetailsController.dataRepository;
                    ArrayList<ArrayList<Object>> sortedData = new ArrayList<>(dataRepository);

                    sortedData.sort((o1, o2) -> {
                        int points1 = (int) o1.get(4);
                        int points2 = (int) o2.get(4);
                        return Integer.compare(points2, points1);
                    });

                    int rank = 1;
                    List<sortedDriverData> data = new ArrayList<>();
                    for (ArrayList<Object> item : sortedData) {
                        int position = rank++;
                        String name = (String) item.get(0);
                        String team = (String) item.get(2);
                        String car = (String) item.get(3);
                        int points = (int) item.get(4);
                        data.add(new sortedDriverData(position, name, team, car, points));
                    }

                    standingTable.setStyle("-fx-font-family: 'Arial';-fx-font-size: 13.5pt;");

                    positionColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPosition()).asObject());
                    driverNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
                    driverTeamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeam()));
                    carModelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCar()));
                    pointsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPoints()).asObject());

                    standingTable.getItems().addAll(data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static class sortedDriverData {
        private final SimpleIntegerProperty position;
        private final SimpleStringProperty name;
        private final SimpleStringProperty team;
        private final SimpleStringProperty car;
        private final SimpleIntegerProperty points;

        public sortedDriverData(int position, String name, String team, String car, int points) {
            this.position = new SimpleIntegerProperty(position);
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleStringProperty(team);
            this.car = new SimpleStringProperty(car);
            this.points = new SimpleIntegerProperty(points);
        }

        public int getPosition() {
            return position.get();
        }

        public String getName() {
            return name.get();
        }

        public String getTeam() {
            return team.get();
        }

        public String getCar() {
            return car.get();
        }

        public int getPoints() {
            return points.get();
        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}
