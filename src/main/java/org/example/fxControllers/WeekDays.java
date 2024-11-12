package org.example.fxControllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.enums.HasSelectedProperty;
import org.example.utils.DataPopulator;
import org.example.utils.DataTransfer;
import org.example.utils.WeekDayHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class WeekDays implements Initializable {
    @FXML
        public ListView weekDayList;

    WeekDayHolder weekDayHolder = WeekDayHolder.getInstance();
    DataTransfer dataTransfer = new DataTransfer();
    DataPopulator dataPopulator = new DataPopulator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWeekDayList();
    }

    public void setWeekDayList() {
        dataPopulator.fillTableWithEnums(weekDayList, org.example.enums.WeekDays.class);
        setListCellFactory(weekDayList, org.example.enums.WeekDays.class);
    }

    public <E extends Enum<E> & HasSelectedProperty> void setListCellFactory(ListView<E> listView, Class<E> enumType) {
        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<E, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(E item) {
                BooleanProperty observable = item.selectedProperty();
                observable.addListener((obs,wasSelected, selected) -> {
                    if(selected && !weekDayHolder.getSelectedWeekDays().contains(item)){
                        weekDayHolder.getSelectedWeekDays().add(item);
                    } else {
                        weekDayHolder.getSelectedWeekDays().remove(item);
                    }
                });
                return observable;
            }
        }));
    }

    public void sendInfo(){
        Stage stage = (Stage) weekDayList.getScene().getWindow();
        stage.close();
    }

    public void exitWeekDayChooser(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
