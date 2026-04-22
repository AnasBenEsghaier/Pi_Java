package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Always boot to Sign In first
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Users/SignIn.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Sign In — Soft UI Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
