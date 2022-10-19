package eu.xdarqus.mwdesign;

import eu.xdarqus.mwdesign.controllers.ChooseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MWDesign extends Application {

    public static String title = "MWDesign v1.1.0";

    private TabPane mainLayout;
    public static ChooseController controller;
    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        MWDesign.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MWDesign.class.getResource("Choose.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);

        controller = loader.<ChooseController>getController();
        controller.loadListeners();
        controller.loadLastState();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((event) -> {
            controller.saveLastState();
        });
    }
}
