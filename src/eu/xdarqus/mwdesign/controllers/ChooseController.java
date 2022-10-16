package eu.xdarqus.mwdesign.controllers;

import eu.xdarqus.mwdesign.MWDesign;
import eu.xdarqus.mwdesign.calls.TreeViewCallImpl;
import eu.xdarqus.mwdesign.epstwod.EPS2DGenerator;
import eu.xdarqus.mwdesign.models.Generate;
import eu.xdarqus.mwdesign.models.Type;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseController implements Initializable {

    @FXML
    private TreeView<String> modelsTreeView;

    @FXML
    private TextField A, B, C, D, E, F, G, H, I, J, K, L, rozszerz, count;

    @FXML
    private Text ModelAndType;

    @FXML
    private TableView<Generate> generateList;

    @FXML
    private Button add, chooseFile, generate;

    @FXML
    private TableColumn<Generate, String> Model, Typ, A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1;

    @FXML
    private CheckBox przetnijCheckBox;

    private File file;
    public String name;
    public Type model;
    ObservableList<Generate> generateObservableList = FXCollections.observableArrayList();

    public void loadCorners(TreeItem<String> treeItem) {
        modelsTreeView.setRoot(treeItem);
        modelsTreeView.setShowRoot(false);
        generateList.setItems(generateObservableList);
        modelsTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TreeViewCallImpl();
            }
        });
    }

    public TreeView<String> getModelsTreeView() {
        return modelsTreeView;
    }

    public void setModelsTreeView(TreeView<String> modelsTreeView) {
        this.modelsTreeView = modelsTreeView;
    }

    public TextField getA() {
        return this.A;
    }

    public TextField getB() {
        return this.B;
    }

    public TextField getC() {
        return this.C;
    }

    public TextField getD() {
        return this.D;
    }

    public TextField getE() {
        return this.E;
    }

    public TextField getF() {
        return this.F;
    }

    public TextField getG() {
        return this.G;
    }

    public TextField getH() {
        return this.H;
    }

    public TextField getI() {
        return this.I;
    }

    public TextField getJ() {
        return this.J;
    }

    public TextField getK() {
        return this.K;
    }

    public TextField getL() {
        return this.L;
    }

    public TextField getRozszerz() {
        return this.rozszerz;
    }

    public TextField getCount(){
        return this.count;
    }

    public CheckBox getPrzetnijCheckBox() {
        return this.przetnijCheckBox;
    }

    public void setModelAndType(Type model, String name) {
        ModelAndType.setText(model.toString() + "\n" + name);
        this.model = model;
        this.name = name;
    }

    public void addCornerToGenerate(Type model, String name) {
        generateList.setEditable(false);
        try{

            if(count.getText().equals("") || rozszerz.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(MWDesign.title);
                alert.setHeaderText("Sprawdź pola ilości i rozszerzenia");
                alert.setContentText("Wartość nie mogą być puste!");
                alert.show();
                return;
            }

            int count1 = Integer.parseInt(count.getText());
            int rozszerz1 = Integer.parseInt(rozszerz.getText());
            if(count1 < 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(MWDesign.title);
                alert.setHeaderText("Sprawdź ilość do wygenerowania");
                alert.setContentText("Wartość musi być większa od 0");
                alert.show();
                return;

            }else if(rozszerz1 < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(MWDesign.title);
                alert.setHeaderText("Sprawdź ilość do wygenerowania");
                alert.setContentText("Wartość musi być większa od 0");
                alert.show();
            }
            for(int i = 0; i < count1; i++) {
                Generate generate = new Generate(model.toString(), name, A.getText(), B.getText(), C.getText(), D.getText(), E.getText(), F.getText(), G.getText(), H.getText(), I.getText(), J.getText(), K.getText(), L.getText(), rozszerz1, przetnijCheckBox.isSelected());
                generateObservableList.add(generate);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(MWDesign.title);
            alert.setHeaderText("Dodano");
            alert.setContentText("Model: " + model.toString() + " Typ: " + name + ", Ilość wektorów: " + count1);
            alert.show();
        }catch (NullPointerException ex) {

        }
    }

    public void loadListeners() {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MWDesign.controller.addCornerToGenerate(model, name);
            }
        });
        chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Encapsulated PostScript (*.eps)", "*.eps");
                fileChooser.getExtensionFilters().add(extFilter);
                file = fileChooser.showSaveDialog(MWDesign.stage);
                if(file == null) return;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(MWDesign.title);
                alert.setHeaderText("Plik " + file.getName());
                alert.setContentText("Zostal wybrany");
                alert.show();
            }
        });
        generate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (file == null) return;
                try {
                    EPS2DGenerator eps2DGenerator = new EPS2DGenerator(new FileOutputStream(file));
                    for (Generate gg : generateList.getItems()) {
                        float a,b,c,d,e,f,g,h,i,j,k,l = 0f;
                        float addC = 0f;
                        float addD = 0f;
                        Type model = Type.valueOf(gg.modelProperty().get());
                        String typ = gg.typProperty().get();
                        switch (model) {
                            case NK1:
                            case NK6:
                            case NK8:
                            case NK10:
                                if(typ.equals("Oparcie")) {
                                    addC = 130;
                                    addD = 160;
                                }
                                break;
                        }
                        a = Float.parseFloat(gg.a1Property().get());
                        b = Float.parseFloat(gg.b1Property().get());
                        c = Float.parseFloat(gg.c1Property().get());
                        d = Float.parseFloat(gg.d1Property().get());
                        e = Float.parseFloat(gg.e1Property().get());
                        f = Float.parseFloat(gg.f1Property().get());
                        g = Float.parseFloat(gg.g1Property().get());
                        h = Float.parseFloat(gg.h1Property().get());
                        i = Float.parseFloat(gg.i1Property().get());
                        j = Float.parseFloat(gg.j1Property().get());
                        k = Float.parseFloat(gg.k1Property().get());
                        l = Float.parseFloat(gg.l1Property().get());
                        eps2DGenerator.generateCode(a, b, c, d, e, f, g, h, i, j, k, l, addC, addD, gg.getPrzetnij());
                    }
                    eps2DGenerator.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(MWDesign.title);
                    alert.setHeaderText("Plik " + file.getName());
                    alert.setContentText("Zostal wygenerowany, ilość wektorów: " + generateList.getItems().size());
                    alert.show();
                } catch (Exception ex) {}
                generateList.getItems().clear();
            }
        });

        generateList.setRowFactory(new Callback<TableView<Generate>, TableRow<Generate>>() {
            @Override
            public TableRow<Generate> call(TableView<Generate> tableView) {
                final TableRow<Generate> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Usun");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        generateList.getItems().remove(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );
                return row ;
            }
        });
        A.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    A.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        B.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    B.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        C.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    C.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(C.getText().isEmpty() || C.getText().equals("0") || model == null) return;
                switch (model) {
                    case NK2:
                        if(name.equals("Oparcie")) {
                            D.setText(String.valueOf(Integer.parseInt(C.getText()) - 40));
                        }
                        break;
                    case NK4:
                    case NK5:
                    case NK6_NEW:
                    case NK11:
                        if(name.equals("Oparcie")) {
                            D.setText(C.getText());
                        }
                        break;
                }
                if(name.equals("Siedzisko") || name.equals("Front") || name.equals("Ławka Ł9 35") || name.equals("Ławka Ł2 34,5")) {
                    D.setText(C.getText());
                }
            }
        });
        D.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    D.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(D.getText().isEmpty() || D.getText().equals("0") || model == null) return;
                switch (model) {
                    case NK1:
                    case NK3:
                    case NK6:
                    case NK8:
                    case NK10:
                        if(name.equals("Oparcie")) {
                            C.setText(String.valueOf(Integer.parseInt(D.getText()) + 30));
                        }
                        break;
                }
            }
        });
        E.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    E.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        F.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    F.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        G.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    G.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        H.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    H.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        J.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    J.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        K.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    K.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        L.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    L.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        rozszerz.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    L.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        count.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    L.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        Typ.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        A1.setCellValueFactory(cellData -> cellData.getValue().a1Property());
        B1.setCellValueFactory(cellData -> cellData.getValue().b1Property());
        C1.setCellValueFactory(cellData -> cellData.getValue().c1Property());
        D1.setCellValueFactory(cellData -> cellData.getValue().d1Property());
        E1.setCellValueFactory(cellData -> cellData.getValue().e1Property());
        F1.setCellValueFactory(cellData -> cellData.getValue().f1Property());
        G1.setCellValueFactory(cellData -> cellData.getValue().g1Property());
        H1.setCellValueFactory(cellData -> cellData.getValue().h1Property());
        I1.setCellValueFactory(cellData -> cellData.getValue().i1Property());
        J1.setCellValueFactory(cellData -> cellData.getValue().j1Property());
        K1.setCellValueFactory(cellData -> cellData.getValue().k1Property());
        L1.setCellValueFactory(cellData -> cellData.getValue().l1Property());
    }
}