package com.example.cm1601_coursework;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository;

public class SimulateRandomRaceController implements Initializable {

    @FXML
    private Button backToMenuButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text raceDateLabel;

    @FXML
    private Text raceLocationLabel;

    @FXML
    private TableColumn<sortedStimulationDriverData, String> simulationCar;

    @FXML
    private TableColumn<sortedStimulationDriverData, String> simulationDriver;

    @FXML
    private TableColumn<sortedStimulationDriverData, Integer> simulationPoints;

    @FXML
    private TableColumn<sortedStimulationDriverData, Integer> simulationPosition;

    @FXML
    private TableView<sortedStimulationDriverData> simulationStandingTable;

    @FXML
    private TableColumn<sortedStimulationDriverData, String> simulationTeam;

    @FXML
    private static final int[] POINTS = {10, 7, 5};
    private static final String RACE_LOCATIONS_FILE_PATH = "src/Race_stimulation_details_locations.txt";
    private static final String RACE_DATES_FILE_PATH = "src/Race_stimulation_details_raceDates.txt";

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (dataRepository.size() < 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "To stimulate a race, at least three players needed.", ButtonType.OK);
                alert.setHeaderText(null);
                alert.initOwner(progressBar.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    backToMenuButton.fire();
                }
            } else {
                try {
                    loadRaceDetails();
                    setProgressBar();
                } catch (Exception ignored) {
                }
            }
        });
    }

    public static class sortedStimulationDriverData {
        private final SimpleIntegerProperty position;
        private final SimpleStringProperty name;
        private final SimpleStringProperty team;
        private final SimpleStringProperty car;
        private final SimpleIntegerProperty points;

        public sortedStimulationDriverData(int position, String name, String team, String car, int points) {
            this.position = new SimpleIntegerProperty(position);
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleStringProperty(team);
            this.car = new SimpleStringProperty(car);
            this.points = new SimpleIntegerProperty(points);
        }

        public int getStimulationPosition() {
            return position.get();
        }

        public String getStimulationName() {
            return name.get();
        }

        public String getStimulationTeam() {
            return team.get();
        }

        public String getStimulationCar() {
            return car.get();
        }

        public int getStimulationPoints() {
            return points.get();
        }
    }

    public void setProgressBar() {
        progressBar.setProgress(0);
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    updateProgress(i + 1, 100);
                }
                return null;
            }
        };
        task.setOnSucceeded(event -> loadStimulatedRace());

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
    public void loadRaceDetails() throws IOException, InterruptedException {
        File file01 = new File(RACE_LOCATIONS_FILE_PATH);
        File file02 = new File(RACE_DATES_FILE_PATH);

        List<String> lines = Files.readAllLines(file01.toPath());
        String raceLocation = lines.get(new Random().nextInt(lines.size()));

        List<String> lines2 = Files.readAllLines(file02.toPath());
        String raceDate = lines2.get(new Random().nextInt(lines2.size()));

        Thread.sleep(1000);
        raceLocationLabel.setText(raceLocation);
        raceDateLabel.setText(raceDate);
    }

    public void loadStimulatedRace() {
        ArrayList<ArrayList<Object>> dataRepository = AddDriverDetailsController.dataRepository;
        ArrayList<ArrayList<Object>> temporaryData = SerializationUtils.clone(dataRepository);

        Collections.shuffle(temporaryData);
        List<sortedStimulationDriverData> data = new ArrayList<>();

        Random random = new Random();
        Set<Integer> chosenIndices = new HashSet<>();
        for (int mark = 0; mark < 3; mark++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(temporaryData.size());
            } while (chosenIndices.contains(randomIndex));
            chosenIndices.add(randomIndex);
            int fourth = (int) temporaryData.get(randomIndex).get(4);
            temporaryData.get(randomIndex).set(4, fourth + POINTS[mark]);
        }

        temporaryData.sort((o1, o2) -> {
            int points1 = (int) o1.get(4);
            int points2 = (int) o2.get(4);
            return Integer.compare(points2, points1);
        });
        for (int count = 0; count < temporaryData.size(); count++) {
            data.add(new sortedStimulationDriverData(count + 1, (String) temporaryData.get(count).get(0), (String) temporaryData.get(count).get(2), (String) temporaryData.get(count).get(3), (int) temporaryData.get(count).get(4)));
        }
        simulationStandingTable.setStyle("-fx-font-family: 'Arial';-fx-font-size: 10.5pt;");

        simulationPosition.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStimulationPosition()).asObject());
        simulationDriver.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStimulationName()));
        simulationTeam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStimulationTeam()));
        simulationCar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStimulationCar()));
        simulationPoints.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStimulationPoints()).asObject());

        simulationStandingTable.getItems().setAll(data);

    }
    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}
