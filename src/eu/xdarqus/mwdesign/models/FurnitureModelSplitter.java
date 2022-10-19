package eu.xdarqus.mwdesign.models;

import com.sun.istack.internal.NotNull;

public class FurnitureModelSplitter {
    private FurnitureModel furnitureModel;
    private final String SPLITTER = ";";
    public FurnitureModelSplitter(@NotNull FurnitureModel furnitureModel) {
        this.furnitureModel = furnitureModel;
    }
    public FurnitureModelSplitter() {
    }

    public String getSplittedFurnitureModel(){
        String stringBuilder = furnitureModel.modelProperty().getValue() + SPLITTER +
                furnitureModel.typProperty().getValue() + SPLITTER +
                furnitureModel.a1Property().getValue() + SPLITTER +
                furnitureModel.b1Property().getValue() + SPLITTER +
                furnitureModel.c1Property().getValue() + SPLITTER +
                furnitureModel.d1Property().getValue() + SPLITTER +
                furnitureModel.e1Property().getValue() + SPLITTER +
                furnitureModel.f1Property().getValue() + SPLITTER +
                furnitureModel.g1Property().getValue() + SPLITTER +
                furnitureModel.h1Property().getValue() + SPLITTER +
                furnitureModel.i1Property().getValue() + SPLITTER +
                furnitureModel.j1Property().getValue() + SPLITTER +
                furnitureModel.k1Property().getValue() + SPLITTER +
                furnitureModel.l1Property().getValue() + SPLITTER +
                furnitureModel.extendProperty().getValue() + SPLITTER +
                furnitureModel.amountProperty().getValue() + SPLITTER;
        return stringBuilder;
    }
    public FurnitureModel fuseSplittedFurnitureModel(String splittedFurnitureModel){
        String [] tab = splittedFurnitureModel.split(SPLITTER);
        if(tab.length < 15)
            return new FurnitureModel(
                tab[0],
                    tab[1],
                    tab[2],
                    tab[3],
                    tab[4],
                    tab[5],
                    tab[6],
                    tab[7],
                    tab[8],
                    tab[9],
                    tab[10],
                    tab[11],
                    tab[12],
                    tab[13],
                    "0",
                    "1"
            );
        else
            return new FurnitureModel(
                    tab[0],
                    tab[1],
                    tab[2],
                    tab[3],
                    tab[4],
                    tab[5],
                    tab[6],
                    tab[7],
                    tab[8],
                    tab[9],
                    tab[10],
                    tab[11],
                    tab[12],
                    tab[13],
                    tab[14],
                    tab[15]
            );
    }
}
