package controller;

import domain.ColasNListas.ArrayStack;
import domain.ColasNListas.LinkedQueue;
import domain.Exceptions.QueueException;
import domain.Exceptions.StackException;
import domain.Objetos.Climate;
import domain.Objetos.Person;
import domain.Objetos.Place;
import domain.Objetos.Weather;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class QueueStackController {
    @javafx.fxml.FXML
    private TextField placeTxtField;
    @javafx.fxml.FXML
    private ComboBox WeatherComboBox;
    @javafx.fxml.FXML
    private TableView<List<String>> tblViewQueue;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> tblColumnPlace;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> tblColumnWeatherCondition;
    @javafx.fxml.FXML
    private TableView tblViewSTACK;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> tblColumnPlaceSTACK;
    @javafx.fxml.FXML
    private TableColumn  <List<String>, String> tblColumnWeatherConditionSTACK;
    private LinkedQueue linkedQueue;
    private ArrayStack arrayStack;
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        this.linkedQueue = util.Utility.getLinkedQueue();
        this.arrayStack = util.Utility.getArrayStack();
        this.WeatherComboBox.setItems(weatherOL);
        //falta setear el array
        this.tblColumnPlace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
            }
        });
        this.tblColumnWeatherCondition.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }
        });
        this.tblColumnPlaceSTACK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> dato) {
                return new ReadOnlyObjectWrapper<>(dato.getValue().get(0));
            }
        });
        this.tblColumnWeatherConditionSTACK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> dato) {
                return new ReadOnlyObjectWrapper<>(dato.getValue().get(1));
            }
        });
    }
    private ObservableList<List<String>> getData() throws QueueException {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        for (int i = 1; i <= linkedQueue.size(); i++) {
            Climate climate = (Climate) linkedQueue.deQueue();
            List<String> arrayList = new ArrayList<>();
            arrayList.add((climate.getPlace().getNombre()));
            arrayList.add((climate.getWeather().getName()));
            data.add(arrayList);
            linkedQueue.enQueue(climate);

        }
        return data;

    }

    ObservableList<String> weatherOL = FXCollections.observableArrayList(
            "Rainy", "ThunderStorm", "Sunny", "Cloudy", "Foggy"
    );

    @FXML
    void btnAutoEnQueueOnAction(ActionEvent event) {
        if (this.linkedQueue != null) {
            try {
                this.linkedQueue.enQueue(new Climate(new Place(util.Utility.getPlace()), new Weather(util.Utility.getWeather())));
                for (int i = 0; i < 19; i++) {
                    Object ob = new Climate(new Place(util.Utility.getPlace()), new Weather(util.Utility.getWeather()));
                    if (!this.linkedQueue.contains(ob)){
                    this.linkedQueue.enQueue(ob);
                    }else {
                        i=i-1;
                    }
                }
                alert.setContentText("The lists have been successfully self-filled!");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                Utility.setLinkedQueue(this.linkedQueue);
                alert.showAndWait();
                this.tblViewQueue.setItems(getData());
            } catch (QueueException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.placeTxtField.clear();//limpiamos el text field
        WeatherComboBox.getSelectionModel().clearSelection();
        //***************************************************//
        this.linkedQueue.clear();//reseteamos la variable de Queue
        Utility.setLinkedQueue(linkedQueue);//Reseteamos la lista
        this.tblViewQueue.getItems().clear();//limpiamos el table View de Queue


        //***************************************************//
        //this.arrayStack.clear();//reseteamos la variable de stack
        Utility.setArrayStack(arrayStack);
        //falta resetear el array en el util.Utility
        this.tblViewSTACK.getItems().clear();//limpiamos el table view de Stack
    }

    @FXML
    void btnEnQueueOnAction(ActionEvent event) {
        try {
            if (isValid()){
                if (this.linkedQueue.isEmpty()) {
                    this.linkedQueue.enQueue(new Climate(new Place(placeTxtField.getText()), new Weather((String) WeatherComboBox.getValue())));
                    alert.setContentText("The weather has been added successfully!");
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    Utility.setLinkedQueue(this.linkedQueue);
                    alert.showAndWait();
                    this.tblViewQueue.setItems(getData());
                    this.tblViewQueue.refresh();
                    this.placeTxtField.clear();//limpiamos el text field
                    WeatherComboBox.getSelectionModel().clearSelection();
                }else if (!this.linkedQueue.contains(new Climate(new Place(placeTxtField.getText()), new Weather((String) WeatherComboBox.getValue()))) && isValid()) {
                    this.linkedQueue.enQueue(new Climate(new Place(placeTxtField.getText()), new Weather((String) WeatherComboBox.getValue())));
                    alert.setContentText("The weather has been added successfully!");
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    Utility.setLinkedQueue(this.linkedQueue);
                    alert.showAndWait();
                    this.tblViewQueue.setItems(getData());
                    this.tblViewQueue.refresh();
                    this.placeTxtField.clear();//limpiamos el text field
                    WeatherComboBox.getSelectionModel().clearSelection();
                }else if (this.linkedQueue.contains(new Climate(new Place(placeTxtField.getText()), new Weather((String) WeatherComboBox.getValue()))) && isValid()){
                    alert.setContentText("The weather has already been added to the list before");
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.showAndWait();
            }

        }else{
                alert.setContentText("Fill in all the requested spaces");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();}
            } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }

    }
    @FXML
    void btnPushAllOnAction(ActionEvent event) {
        if (this.linkedQueue != null) {
         this.arrayStack = new ArrayStack(linkedQueue.size());
            try {
                while (!this.linkedQueue.isEmpty()) {
                    arrayStack.push(this.linkedQueue.deQueue());
                }
                alert.setContentText("The lists have been successfully changed!");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                Utility.setArrayStack(this.arrayStack);
                alert.showAndWait();
                this.tblViewSTACK.setItems(getData2());
                this.placeTxtField.clear();//limpiamos el text field
                WeatherComboBox.getSelectionModel().clearSelection();

            } catch (QueueException e) {
                throw new RuntimeException(e);
            } catch (StackException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private ObservableList<List<String>> getData2() throws QueueException, StackException {
        ObservableList<List<String>> dato = FXCollections.observableArrayList();
        ArrayStack aux = new ArrayStack(arrayStack.size());
        while (!arrayStack.isEmpty()) {
                Climate climate = (Climate) arrayStack.top();
                List<String> arrayList = new ArrayList<>();
                arrayList.add((climate.getPlace().getNombre()));
                arrayList.add((climate.getWeather().getName()));
                dato.add(arrayList);
                aux.push(arrayStack.pop());
        }
        while (!aux.isEmpty()){
            arrayStack.push(aux.pop());
        }
        return dato;
    }
    public boolean isValid(){return !this.placeTxtField.getText().isEmpty() && !this.WeatherComboBox.getSelectionModel().isEmpty();}


}
