package com.maks.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Settings extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SettingsScreen.fxml")); // TODO: 15/06/2020 stworzyc layout ustawien: motyw ciemny/jasny

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.show();
    }

}
