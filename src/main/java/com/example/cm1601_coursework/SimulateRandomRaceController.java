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
import javafx.stage.Window;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

import static com.example.cm1601_coursework.AddDriverDetailsController.dataRepository; // import dataRepository

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
    private TableColumn<sortedStimulationDriverData, String> simulationCar; // table columns

    @FXML
    private TableColumn<sortedStimulationDriverData, String> simulationDriver;

    @FXML
    private TableColumn<sortedStimulationDriverData, Integer> simulationPoints;

    @FXML
    private TableColumn<sortedStimulationDriverData, Integer> simulationPosition;

    @FXML
    private TableView<sortedStimulationDriverData> simulationStandingTable; // table view

    @FXML
    private TableColumn<sortedStimulationDriverData, String> simulationTeam;

    @FXML
    private static final int[] POINTS = {10, 7, 5}; // points for 1st, 2nd and 3rd place
    private static final String RACE_LOCATIONS_FILE_PATH = "src/Race_stimulation_details_locations.txt";
    private static final String RACE_DATES_FILE_PATH = "src/Race_stimulation_details_raceDates.txt"; // path to file

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (dataRepository.size() < 3) { // if there are less than 3 drivers in the dataRepository
                Alert alert = new Alert(Alert.AlertType.ERROR, "A minimum of three players are required to stimulate a race.", ButtonType.OK); // show the error message
                alert.setHeaderText(null);
                alert.initOwner(progressBar.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) { // if ok is pressed
                    backToMenuButton.fire(); // go back to the menu
                }
            } else { // if there are more than 3 drivers in the dataRepository
                try {
                    loadRaceDetails(); // call method for loading race details
                    setProgressBar(); // call method to set progress bar
                } catch (Exception e) {
                    Window owner = progressBar.getScene().getWindow();
                    MainController.AlertHelper.showAlert(Alert.AlertType.ERROR,owner,"Error","Error while loading race details");
                }
            }
        });
    }

    private static class sortedStimulationDriverData { // class to store sorted data for table view
        private final SimpleIntegerProperty position; // properties for table view
        private final SimpleStringProperty name;
        private final SimpleStringProperty team;
        private final SimpleStringProperty car;
        private final SimpleIntegerProperty points;

        public sortedStimulationDriverData(int position, String name, String team, String car, int points) { // constructor
            this.position = new SimpleIntegerProperty(position);
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleStringProperty(team);
            this.car = new SimpleStringProperty(car);
            this.points = new SimpleIntegerProperty(points);
        }

        public int getStimulationPosition() {
            return position.get();
        } // getters

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

    public void setProgressBar() { // method to set progress bar
        progressBar.setProgress(0); // set progress bar to 0
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100); // sleep for 100ms
                    updateProgress(i + 1, 100); // update progress bar
                }
                return null;
            }
        };
        task.setOnSucceeded(event -> loadStimulatedRace()); // on completion of progress bar load the race

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    public void loadRaceDetails() { //method to load race details
        try{
            File file01 = new File(RACE_LOCATIONS_FILE_PATH);
            File file02 = new File(RACE_DATES_FILE_PATH);

            List<String> lines = Files.readAllLines(file01.toPath()); // read lines from file
            String raceLocation = lines.get(new Random().nextInt(lines.size())); // get random line from file

            List<String> lines2 = Files.readAllLines(file02.toPath());
            String raceDate = lines2.get(new Random().nextInt(lines2.size()));

            Thread.sleep(1000);
            raceLocationLabel.setText(raceLocation); // set text for labels
            raceDateLabel.setText(raceDate);
        } catch (IOException | InterruptedException e) { // catch exceptions
            Window owner = progressBar.getScene().getWindow();
            MainController.AlertHelper.showAlert(Alert.AlertType.ERROR,owner,"Error","Race details could not be loaded."); // show an error message
        }

    }

    private void loadStimulatedRace() { // method for loading race
        ArrayList<AddDriverDetailsController.DriverDetails> dataRepository = AddDriverDetailsController.dataRepository;
        ArrayList<AddDriverDetailsController.DriverDetails> temporaryData = SerializationUtils.clone(dataRepository); // clone dataRepository

        Collections.shuffle(temporaryData); // shuffle temporaryData
        List<sortedStimulationDriverData> data = new ArrayList<>(); // list to store data for table view

        Random random = new Random();
        Set<Integer> chosenIndexes = new HashSet<>(); // set to store chosen indexes
        for (int mark = 0; mark < 3; mark++) { // loop to add points to drivers
            int randomIndex;
            do {
                randomIndex = random.nextInt(temporaryData.size()); // generate random index
            } while (chosenIndexes.contains(randomIndex)); // if index is already chosen, generate new index
            chosenIndexes.add(randomIndex); // add index to chosenIndexes
            int fourth = temporaryData.get(randomIndex).getPoints(); // get points of driver
            temporaryData.get(randomIndex).setPoints(fourth + POINTS[mark]); // add points to the driver
        }

        temporaryData.sort((o1, o2) -> { // sort temporaryData
            int points1 = o1.getPoints();
            int points2 = o2.getPoints();
            return Integer.compare(points2, points1);
        });
        for (int count = 0; count < temporaryData.size(); count++) { // loop to add data to data list
            data.add(new sortedStimulationDriverData(count + 1, temporaryData.get(count).getName(),
                    temporaryData.get(count).getTeam(), temporaryData.get(count).getCarModel(),
                    temporaryData.get(count).getPoints()));
        }
        simulationStandingTable.setStyle("-fx-font-family: 'Arial';-fx-font-size: 10.5pt;");

        simulationPosition.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getStimulationPosition()).asObject()); // set cell values
        simulationDriver.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStimulationName()));
        simulationTeam.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStimulationTeam()));
        simulationCar.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStimulationCar()));
        simulationPoints.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getStimulationPoints()).asObject());

        simulationStandingTable.getItems().setAll(data);

    }
    public void switchToMenu(ActionEvent event) throws IOException {
        MainController.switchToMenu(event);
    }
}
