package controller;

import domain.ColasNListas.ArrayStack;
import domain.ColasNListas.LinkedQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class QueueStackController {
    @javafx.fxml.FXML
    private TextField placeTxtField;
    @javafx.fxml.FXML
    private ComboBox WeatherComboBox;
    @javafx.fxml.FXML
    private TableView tblViewQueue;
    @javafx.fxml.FXML
    private TableColumn tblColumnPlace;
    @javafx.fxml.FXML
    private TableColumn tblColumnWeatherCondition;
    @javafx.fxml.FXML
    private TableView tblViewSTACK;
    @javafx.fxml.FXML
    private TableColumn tblColumnPlaceSTACK;
    @javafx.fxml.FXML
    private TableColumn tblColumnWeatherConditionSTACK;
    private LinkedQueue linkedQueue;
    private ArrayStack arrayStack;
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        this.linkedQueue = util.Utility.getLinkedQueue();
        this.WeatherComboBox.setItems(weatherOL);
        //falta setear el array
    }

    ObservableList<String> weatherOL = FXCollections.observableArrayList(
            "Rainy", "ThunderStorm", "Sunny", "Cloudy", "Foggy"
    );

    @FXML
    void btnAutoEnQueueOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.placeTxtField.clear();//limpiamos el text field

        //***************************************************//
        this.linkedQueue.clear();//reseteamos la variable de Queue
        util.Utility.setLinkedQueue(linkedQueue);//Reseteamos la lista
        this.tblViewQueue.getItems().clear();//limpiamos el table View de Queue

        //***************************************************//
        this.arrayStack.clear();//reseteamos la variable de stack
        //falta resetear el array en el util.Utility
        this.tblViewSTACK.getItems().clear();//limpiamos el table view de Stack



    }

    @FXML
    void btnEnQueueOnAction(ActionEvent event) {

    }

    @FXML
    void btnPushAllOnAction(ActionEvent event) {

    }

}