package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    void btnAutoEnQueueOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnEnQueueOnAction(ActionEvent event) {

    }

    @FXML
    void btnPushAllOnAction(ActionEvent event) {

    }

}