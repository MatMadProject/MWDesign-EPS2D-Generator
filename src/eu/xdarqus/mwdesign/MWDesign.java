package eu.xdarqus.mwdesign;

import eu.xdarqus.mwdesign.controllers.ChooseController;
import eu.xdarqus.mwdesign.models.Corner;
import eu.xdarqus.mwdesign.models.Type;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MWDesign extends Application {

    public static String title = "MWDesign v1.0.0-SNAPSHOT";
    List<Corner> cornerList = Arrays.asList(
            new Corner("Oparcie", Type.NK1),
            new Corner("Siedzisko", Type.NK1),
            new Corner("Front", Type.NK1),
            new Corner("Noga", Type.NK1),
            new Corner("Noga srodkowa", Type.NK1),

            new Corner("Oparcie", Type.NK2),
            new Corner("Siedzisko", Type.NK2),
            new Corner("Pufa", Type.NK2),
            new Corner("Pasek", Type.NK2), // 450 na 430

            new Corner("Oparcie", Type.NK4),
            new Corner("Siedzisko", Type.NK4),
            new Corner("Pufa", Type.NK4),

            new Corner("Oparcie", Type.NK5),
            new Corner("Siedzisko", Type.NK5),
            new Corner("Pufa", Type.NK5),
            new Corner("25x25", Type.NK5),

            //zmienic nk6 na nk14

            new Corner("Oparcie", Type.NK6), // szerokosc 430 obliczyc kat?
            new Corner("Siedzisko", Type.NK6),
            new Corner("Front", Type.NK6),
            new Corner("Noga", Type.NK6),

            new Corner("Oparcie", Type.NK6_NEW),
            new Corner("Siedzisko", Type.NK6_NEW),
            new Corner("Pufa", Type.NK6_NEW),

            new Corner("Siedzisko", Type.NK8),
            new Corner("Oparcie", Type.NK8), //  wysokosc z nk10 na 350

            new Corner("Noga pasek", Type.NK10), //1630 na 1800
            new Corner("Noga", Type.NK10), //515 na 508, 570 na 620
            new Corner("Siedzisko", Type.NK10),
            new Corner("Oparcie", Type.NK10),
            new Corner("Front", Type.NK10), // podmienic z nk1

            new Corner("Siedzisko", Type.NK11),
            new Corner("Oparcie", Type.NK11),
            new Corner("Pufa", Type.NK11),

            new Corner("Taboret", Type.OTHER),
            new Corner("Niestandardowy", Type.OTHER),
            new Corner("Ławka Ł2/T6 34,5", Type.OTHER),
            new Corner("Ławka Ł10/T14 35", Type.OTHER), // dodac do listenera
            new Corner("Ławka Ł4/T8 35", Type.OTHER),
            new Corner("Noga SK2", Type.OTHER) // 610x730 *2 dodac
            //new Corner("Noga środkowa", Type.OTHER)
    );
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

        TreeItem<String> rootNode = new TreeItem<String>("Modele");
        rootNode.setExpanded(true);
        for (Corner corner : cornerList) {
            TreeItem<String> empLeaf = new TreeItem<String>(corner.getName());
            empLeaf.setExpanded(false);
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(corner.getType())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem depNode = new TreeItem(corner.getType());
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }
        controller.loadCorners(rootNode);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
