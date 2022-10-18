package eu.xdarqus.mwdesign.controllers;

import eu.xdarqus.mwdesign.MWDesign;
import eu.xdarqus.mwdesign.calls.TreeViewCallImpl;
import eu.xdarqus.mwdesign.epstwod.EPS2DGenerator;
import eu.xdarqus.mwdesign.models.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseController implements Initializable {

    public TableView<FurnitureModel> modelList;
    public ComboBox<Type> comboBoxModel;
    public TextField nameModel;
    public TextField valueA;
    public TextField valueB;
    public TextField valueC;
    public TextField valueD;
    public TextField valueE;
    public TextField valueF;
    public TextField valueG;
    public TextField valueH;
    public TextField valueI;
    public TextField valueJ;
    public TextField valueK;
    public TextField valueL;

    @FXML
    private TreeView<String> modelsTreeView;

    @FXML
    private TextField A, B, C, D, E, F, G, H, I, J, K, L, rozszerz, count;

    @FXML
    private Text ModelAndType;

    @FXML
    private TableView<FurnitureModel> generateList;

    @FXML
    private Button add, chooseFile, generate;

    @FXML
    private TableColumn<FurnitureModel, String> Model, Typ, A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1,
    columnModel, columnName, columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH, columnI,
            columnJ, columnK, columnL;

    @FXML
    private CheckBox przetnijCheckBox;

    private File file;
    public String name;
    public Type model;
    ObservableList<FurnitureModel> generateObservableList = FXCollections.observableArrayList();

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
                FurnitureModel generate = new FurnitureModel(model.toString(), name, A.getText(), B.getText(), C.getText(), D.getText(), E.getText(), F.getText(), G.getText(), H.getText(), I.getText(), J.getText(), K.getText(), L.getText(), rozszerz1, przetnijCheckBox.isSelected());
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
                    for (FurnitureModel gg : generateList.getItems()) {
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

        generateList.setRowFactory(new Callback<TableView<FurnitureModel>, TableRow<FurnitureModel>>() {
            @Override
            public TableRow<FurnitureModel> call(TableView<FurnitureModel> tableView) {
                final TableRow<FurnitureModel> row = new TableRow<>();
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
        modelList.setRowFactory(new Callback<TableView<FurnitureModel>, TableRow<FurnitureModel>>() {
            @Override
            public TableRow<FurnitureModel> call(TableView<FurnitureModel> tableView) {
                final TableRow<FurnitureModel> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Usuń");
                final MenuItem editMenuItem = new MenuItem("Edytuj");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        modelList.getItems().remove(row.getItem());
                    }
                });
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        editModelButton.setDisable(false);
                        updateModelValuesToEdit(row.getItem());
                        rowIndex = row.getIndex();
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                contextMenu.getItems().add(editMenuItem);
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
        comboBoxModel.setItems(FXCollections.observableArrayList(Arrays.asList(Type.values())));
        setValueTextFieldsListener();
        setCellValueFactoryOfModelList();
    }

    ObservableList<FurnitureModel> modelObservableList = FXCollections.observableArrayList();
    public void addNewModel(ActionEvent event) {
        FurnitureModel furnitureModel = getFurnitureModelFromForm();
        modelObservableList.add(furnitureModel);
        updateModelList();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(MWDesign.title);
        alert.setHeaderText("Dodano nowy model");
        alert.setContentText(furnitureModel.modelProperty().getValue() +
                " | " + furnitureModel.typProperty().getValue());
        alert.show();
    }

    public void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Model List (*.mll)", "*.mll");
        fileChooser.getExtensionFilters().add(extFilter);
        File fileToSaveModelList = fileChooser.showSaveDialog(MWDesign.stage);

        if(fileToSaveModelList == null) return;
        else{
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileToSaveModelList);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                for(FurnitureModel furnitureModel : modelObservableList){
                    FurnitureModelSplitter splitter = new FurnitureModelSplitter(furnitureModel);
                    bufferedWriter.write(splitter.getSplittedFurnitureModel());
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(MWDesign.title);
        alert.setHeaderText("Zapisano model do pliku");
        alert.setContentText(fileToSaveModelList.getName());
        alert.show();
    }

    public void loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Model List (*.mll)", "*.mll");
        fileChooser.getExtensionFilters().add(extFilter);
        File fileToLoadModelList = fileChooser.showOpenDialog(MWDesign.stage);

        if(fileToLoadModelList == null) return;
        else{
            modelObservableList.clear();
            FileReader reader = null;
            try {
                reader = new FileReader(fileToLoadModelList);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                FurnitureModelSplitter splitter = new FurnitureModelSplitter();

                while ((line = bufferedReader.readLine()) != null)
                    modelObservableList.add(splitter.fuseSplittedFurnitureModel(line));

                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        updateModelList();
        loadCorners(getCornerListToUpdate());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(MWDesign.title);
        alert.setHeaderText("Wczytano model z pliku");
        alert.setContentText(fileToLoadModelList.getName());
        alert.show();
    }

    private void updateModelList(){
        modelList.setItems(modelObservableList);
    }
    private FurnitureModel getFurnitureModelFromForm(){
        String model = null;
        String name = null;

        if(comboBoxModel.getValue() != null)
            model = comboBoxModel.getValue().name();

        if(!nameModel.getText().isEmpty())
            name = nameModel.getText();


        String a = valueA.getText();
        String b = valueB.getText();
        String c = valueC.getText();
        String d = valueD.getText();
        String e = valueE.getText();
        String f = valueF.getText();
        String g = valueG.getText();
        String h = valueH.getText();
        String i = valueI.getText();
        String j = valueJ.getText();
        String k = valueK.getText();
        String l = valueL.getText();

        FurnitureModel furnitureModel = new FurnitureModel(
                model,
                name,
                a,
                b,
                c,
                d,
                e,
                f,
                g,
                h,
                i,
                j,
                k,
                l
        );

        return furnitureModel;
    }

    private void setValueTextFieldsListener(){
        valueA.textProperty().addListener(new ValueChangeListener(6));
        valueB.textProperty().addListener(new ValueChangeListener(6));
        valueC.textProperty().addListener(new ValueChangeListener(6));
        valueD.textProperty().addListener(new ValueChangeListener(6));
        valueE.textProperty().addListener(new ValueChangeListener(6));
        valueF.textProperty().addListener(new ValueChangeListener(6));
        valueG.textProperty().addListener(new ValueChangeListener(6));
        valueH.textProperty().addListener(new ValueChangeListener(6));
        valueI.textProperty().addListener(new ValueChangeListener(6));
        valueJ.textProperty().addListener(new ValueChangeListener(6));
        valueK.textProperty().addListener(new ValueChangeListener(6));
        valueL.textProperty().addListener(new ValueChangeListener(6));
    }

    private void setCellValueFactoryOfModelList(){
        columnModel.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        columnName.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        columnA.setCellValueFactory(cellData -> cellData.getValue().a1Property());
        columnB.setCellValueFactory(cellData -> cellData.getValue().b1Property());
        columnC.setCellValueFactory(cellData -> cellData.getValue().c1Property());
        columnD.setCellValueFactory(cellData -> cellData.getValue().d1Property());
        columnE.setCellValueFactory(cellData -> cellData.getValue().e1Property());
        columnF.setCellValueFactory(cellData -> cellData.getValue().f1Property());
        columnG.setCellValueFactory(cellData -> cellData.getValue().g1Property());
        columnH.setCellValueFactory(cellData -> cellData.getValue().h1Property());
        columnI.setCellValueFactory(cellData -> cellData.getValue().i1Property());
        columnJ.setCellValueFactory(cellData -> cellData.getValue().j1Property());
        columnK.setCellValueFactory(cellData -> cellData.getValue().k1Property());
        columnL.setCellValueFactory(cellData -> cellData.getValue().l1Property());
    }

    public List<Corner> getCornerList(){
        List<Corner> corners = new ArrayList<>();
        if(!modelObservableList.isEmpty())
            for(FurnitureModel furnitureModel : modelObservableList){
                corners.add(new Corner(furnitureModel.typProperty().getValue(), Type.valueOf(furnitureModel.modelProperty().getValue())));
            }
        return corners;
    }
    public TreeItem<String> getCornerListToUpdate(){
        TreeItem<String> rootNode = new TreeItem("Modele");// ??
        rootNode.setExpanded(true);
        List<Corner> corners = getCornerList();
        for (Corner corner : corners) {
            TreeItem<String> empLeaf = new TreeItem(corner.getName());
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
        return rootNode;
    }

    public void updateModelValues(String model, String type){
        FurnitureModel selected = modelObservableList.stream().
                filter(item -> item.typProperty().getValue().equals(type)
                && item.modelProperty().getValue().equals(model))
                .findFirst().orElse(null);
        updateModelValues(selected);
    }

    public void updateModelValues(FurnitureModel furnitureModel){
        if(furnitureModel != null){
            A.setText(furnitureModel.a1Property().getValue());
            B.setText(furnitureModel.b1Property().getValue());
            C.setText(furnitureModel.c1Property().getValue());
            D.setText(furnitureModel.d1Property().getValue());
            E.setText(furnitureModel.e1Property().getValue());
            F.setText(furnitureModel.f1Property().getValue());
            G.setText(furnitureModel.g1Property().getValue());
            H.setText(furnitureModel.h1Property().getValue());
            I.setText(furnitureModel.i1Property().getValue());
            J.setText(furnitureModel.j1Property().getValue());
            K.setText(furnitureModel.k1Property().getValue());
            L.setText(furnitureModel.l1Property().getValue());
        }else{
            A.setText("-1");
            B.setText("-1");
            C.setText("-1");
            D.setText("-1");
            E.setText("-1");
            F.setText("-1");
            G.setText("-1");
            H.setText("-1");
            I.setText("-1");
            J.setText("-1");
            K.setText("-1");
            L.setText("-1");
        }
    }
    public void updateModelValuesToEdit(FurnitureModel furnitureModel){
        if(furnitureModel != null){
            valueA.setText(furnitureModel.a1Property().getValue());
            valueB.setText(furnitureModel.b1Property().getValue());
            valueC.setText(furnitureModel.c1Property().getValue());
            valueD.setText(furnitureModel.d1Property().getValue());
            valueE.setText(furnitureModel.e1Property().getValue());
            valueF.setText(furnitureModel.f1Property().getValue());
            valueG.setText(furnitureModel.g1Property().getValue());
            valueH.setText(furnitureModel.h1Property().getValue());
            valueI.setText(furnitureModel.i1Property().getValue());
            valueJ.setText(furnitureModel.j1Property().getValue());
            valueK.setText(furnitureModel.k1Property().getValue());
            valueL.setText(furnitureModel.l1Property().getValue());
            nameModel.setText(furnitureModel.typProperty().getValue());
            comboBoxModel.setValue(Type.valueOf(furnitureModel.modelProperty().getValue()));
        }else{
            valueA.setText("-1");
            valueB.setText("-1");
            valueC.setText("-1");
            valueD.setText("-1");
            valueE.setText("-1");
            valueF.setText("-1");
            valueG.setText("-1");
            valueH.setText("-1");
            valueI.setText("-1");
            valueJ.setText("-1");
            valueK.setText("-1");
            valueL.setText("-1");
        }
    }
    int rowIndex = -1;
    public Button editModelButton;
    public void editModel(ActionEvent event) {
        FurnitureModel furnitureModel = getFurnitureModelFromForm();
        modelObservableList.set(rowIndex,furnitureModel);
        editModelButton.setDisable(true);
        updateModelList();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(MWDesign.title);
        alert.setHeaderText("Edytowano model");
        alert.setContentText(furnitureModel.modelProperty().getValue() +
                " | " + furnitureModel.typProperty().getValue());
        alert.show();
    }
}
