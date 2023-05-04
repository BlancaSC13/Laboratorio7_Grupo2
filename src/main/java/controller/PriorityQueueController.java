package controller;

import domain.PriorityLinkedQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PriorityQueueController {
    @javafx.fxml.FXML
    private TextField txtName;
    @FXML
    private ChoiceBox<String> moodChoiceBox;
    @FXML
    private ChoiceBox<String> priorityChoiceBox;
    @javafx.fxml.FXML
    private TableView<List<String>> PriorityTableView;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> tblColumnName;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> tblColumnMood;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> tblColumnPriority;
    private PriorityLinkedQueue priorityLinkedQueue;
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        this.priorityLinkedQueue = util.Utility.getPriorityLinkedQueue();//Cargamos la cola con prioridad
        //llenamos los choiceBox
        this.moodChoiceBox.setItems(moodOl);
        this.priorityChoiceBox.setItems(priorityOl);
    }

    ObservableList<String> moodOl = FXCollections.observableArrayList(
            "Happiness", "Sadness", "Anger", "Sickness",
            "Cheerful", "Reflective", "Gloomy", "Romantic", "Calm", "Hopeful", "Fearful", "Tense",
            "Lonely"
    );
    ObservableList<String> priorityOl = FXCollections.observableArrayList(
            "Low", "Medium", "High"
    );

    @FXML
    void btnAutoEnQueueOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnEnQueueOnAction(ActionEvent event) {

    }

}