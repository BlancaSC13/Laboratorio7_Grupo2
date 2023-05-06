package controller;

import domain.ColasNListas.ArrayStack;
import domain.ColasNListas.LinkedQueue;
import domain.ColasNListas.PriorityLinkedQueue;
import domain.Exceptions.QueueException;
import domain.Objetos.Climate;
import domain.Objetos.Node;
import domain.Objetos.Person;
import domain.Objetos.Testeoo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueController {

    @javafx.fxml.FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> moodComboBox;
    @FXML
    private ComboBox<String> priorityComboBox;
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
        this.moodComboBox.setItems(moodOl);
        this.priorityComboBox.setItems(priorityOl);
        this.tblColumnName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> dato) {
                return new ReadOnlyObjectWrapper<>(dato.getValue().get(0));
            }
        });
        this.tblColumnMood.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> dato) {
                return new ReadOnlyObjectWrapper<>(dato.getValue().get(1));
            }
        });
        this.tblColumnPriority.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> dato) {
                return new ReadOnlyObjectWrapper<>(dato.getValue().get(2));
            }
        });


    }
    private ObservableList<List<String>> getDato() throws QueueException {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        for (int i = 1; i <= priorityLinkedQueue.size(); i++) {
            Person person = (Person) priorityLinkedQueue.deQueue();
            Integer prioridad = (Integer) priorityLinkedQueue.getPriorityC().deQueue();
            List<String> arrayList = new ArrayList<>();
            arrayList.add(person.getName());
            arrayList.add(person.getMood());
            arrayList.add(util.Utility.priorityToInteger(prioridad));
            data.add(arrayList);
            priorityLinkedQueue.enQueue(person);
            priorityLinkedQueue.getPriorityC().enQueue(prioridad);

        }
        return data;

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
        try {
            if (priorityLinkedQueue.isEmpty()) {
                Person person = new Person(util.Utility.getFirstName(), util.Utility.mood());
                priorityLinkedQueue.enQueue2(person, util.Utility.priorityToInteger(util.Utility.random(1,3)), priorityLinkedQueue.getPriorityC());
                for (int i = 0; i < 19 ; i++) {
                    Person person2 = new Person(util.Utility.getFirstName(), util.Utility.mood());
                    if (!priorityLinkedQueue.contains(new Person(util.Utility.getFirstName(), util.Utility.mood()))){
                        priorityLinkedQueue.enQueue2(person2, util.Utility.priorityToInteger(util.Utility.random(1,3)), priorityLinkedQueue.getPriorityC());
                    }else {
                        i=i-1;
                        System.out.println("Si se usa");
                    }
                }
            }else {
                for (int i = 0; i < 20 ; i++) {
                    Person person = new Person(util.Utility.getFirstName(), util.Utility.mood());
                    if (!priorityLinkedQueue.contains(new Person(util.Utility.getFirstName(), util.Utility.mood()))){
                        priorityLinkedQueue.enQueue2(person, util.Utility.priorityToInteger(util.Utility.random(1,3)), priorityLinkedQueue.getPriorityC());
                    }else {
                        i=i-1;
                        System.out.println("Se usa");

                    }
                }
            }
            util.Utility.setPriorityLinkedQueue(priorityLinkedQueue);
            System.out.println(priorityLinkedQueue);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Person added correctly!");
            System.out.println(priorityLinkedQueue.size());
            PriorityTableView.setItems(getDato());
            priorityComboBox.getSelectionModel().clearSelection();
            moodComboBox.getSelectionModel().clearSelection();
            this.txtName.clear();
        } catch (QueueException e) {
                    throw new RuntimeException(e);
                }
        }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.txtName.clear();//limpiamos el text field
        priorityLinkedQueue.clear();//reseteamos la variable de Linked Queue
        this.priorityComboBox.getSelectionModel().clearSelection();
        this.moodComboBox.getSelectionModel().clearSelection();
        util.Utility.setPriorityLinkedQueue(priorityLinkedQueue);//resetemos la cola
        this.PriorityTableView.getItems().clear();//limpiamos el table view
    }

    @FXML
    void btnEnQueueOnAction(ActionEvent event) {
        try {
            if (isValid()) {
                if (priorityLinkedQueue.isEmpty()) {
                    Person person = new Person(this.txtName.textProperty().getValue(), moodComboBox.getValue());
                    priorityLinkedQueue.enQueue2(person, priorityComboBox.getValue(), priorityLinkedQueue.getPriorityC());
                    util.Utility.setPriorityLinkedQueue(priorityLinkedQueue);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Person added correctly!");

                } else if (!priorityLinkedQueue.contains(new Person(this.txtName.textProperty().getValue(), moodComboBox.getValue()))) {
                        Person person = new Person(this.txtName.textProperty().getValue(), moodComboBox.getValue());
                        priorityLinkedQueue.enQueue2(person, priorityComboBox.getValue(),priorityLinkedQueue.getPriorityC());
                        util.Utility.setPriorityLinkedQueue(priorityLinkedQueue);
                        System.out.println(priorityLinkedQueue);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("Person added correctly!");

                    } else {
                        alert.setContentText("The Person has already been added to the list before");
                        alert.setAlertType(Alert.AlertType.ERROR);

                    }
                }else{
                    alert.setContentText("Fill in all the requested spaces");
                    alert.setAlertType(Alert.AlertType.ERROR);

                }
                PriorityTableView.setItems(getDato());
                priorityComboBox.getSelectionModel().clearSelection();
                moodComboBox.getSelectionModel().clearSelection();
                this.txtName.clear();
            alert.showAndWait();
            } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }
    }
    public boolean isValid(){return !this.txtName.getText().isEmpty() && !this.priorityComboBox.getSelectionModel().isEmpty() && !this.moodComboBox.getSelectionModel().isEmpty();}

}