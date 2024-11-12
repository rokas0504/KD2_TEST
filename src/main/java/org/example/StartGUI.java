package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGUI extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("/KD2_TEST/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Darbuotojas Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void weekDayChooserWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("/KD2_TEST/weekdays.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Choose Working Days");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public static void main(String[] args) { launch();}

}
