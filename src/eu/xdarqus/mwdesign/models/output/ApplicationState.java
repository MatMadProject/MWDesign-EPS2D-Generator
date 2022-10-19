package eu.xdarqus.mwdesign.models.output;

import eu.xdarqus.mwdesign.models.FurnitureModel;
import eu.xdarqus.mwdesign.models.FurnitureModelSplitter;
import javafx.collections.ObservableList;

import java.io.*;

public class ApplicationState {

    private ApplicationRunningPath applicationRunningPath;
    private final String PREFIX_FILE = "\\state.mll";

    public ApplicationState() {
        applicationRunningPath = new ApplicationRunningPath();
    }

    public void saveApplicationState(ObservableList<FurnitureModel> modelObservableList){
        File fileToSaveModelList = new File(applicationRunningPath.getSuffixPath()+PREFIX_FILE);

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

    public void loadApplicationState(ObservableList<FurnitureModel> modelObservableList){
        File fileToLoadModelList = new File(applicationRunningPath.getSuffixPath()+PREFIX_FILE);
        modelObservableList.clear();
        FileReader reader = null;
        if(fileToLoadModelList.exists()){
            try {
                reader = new FileReader(fileToLoadModelList);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                FurnitureModelSplitter splitter = new FurnitureModelSplitter();

                while ((line = bufferedReader.readLine()) != null)
                    modelObservableList.add(splitter.fuseSplittedFurnitureModel(line));

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
