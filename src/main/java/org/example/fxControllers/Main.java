package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.Darbuotojas;
import org.example.StartGUI;
import org.example.enums.WeekDays;
import org.example.hibernateController.GenericHibernate;
import org.example.utils.DataPopulator;
import org.example.utils.WeekDayHolder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class Main {
    @FXML
            private AnchorPane darbuotojasManagerPane;
    @FXML
            private ListView<Darbuotojas> darbuotojasListField;
    @FXML
            private TextField nameField;
    @FXML
            private TextField surnameField;
    @FXML
            private Label slowToggleLabelTrue;
    @FXML
            private Label slowToggleLabelFalse;
    boolean toggle = false;
    private Darbuotojas selectedDarbuotojas = null;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("courseworkKD2");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);
    DataPopulator dataPopulator = new DataPopulator();
    WeekDayHolder weekDayHolder = WeekDayHolder.getInstance();

    public void initialize() {
        dataPopulator.fillTableAll(darbuotojasListField, Darbuotojas.class);
    }

    public void loadDarbuotojasData() {
        Darbuotojas darbuotojas = darbuotojasListField.getSelectionModel().getSelectedItem();
        if(!toggle){
            nameField.setText(darbuotojas.getName());surnameField.setText(darbuotojas.getSurname());
        } else {
            Timeline timelineName = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                nameField.setText(darbuotojas.getName());
            }));
            timelineName.setCycleCount(1);
            timelineName.play();

            Timeline timelineSurname = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                surnameField.setText(darbuotojas.getSurname());
            }));
            timelineSurname.setCycleCount(1);
            timelineSurname.play();
        }
    }
    public void createNewDarbuotojas(){
        Darbuotojas darbuotojas = new Darbuotojas(nameField.getText(),surnameField.getText(), (List<WeekDays>) (List<?>) weekDayHolder.getSelectedWeekDays());
        hibernate.create(darbuotojas);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("New user created");
        alert.showAndWait();
        dataPopulator.fillTableAll(darbuotojasListField, Darbuotojas.class);
        resetAllFields();
    }

    public void openWeekDayChooser() throws IOException {
        loadDarbuotojasData();
        StartGUI.weekDayChooserWindow();
    }
    public void resetAllFields(){
        nameField.clear();
        surnameField.clear();
    }
    public boolean slowToggle(){
        if(toggle){
            slowToggleLabelTrue.setVisible(false);
            slowToggleLabelFalse.setVisible(true);
            toggle = false;
        } else {
            slowToggleLabelTrue.setVisible(true);
            slowToggleLabelFalse.setVisible(false);
            toggle = true;
        }
        return toggle;
    }
}
