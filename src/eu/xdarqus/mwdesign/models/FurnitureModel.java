package eu.xdarqus.mwdesign.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

import java.util.Objects;

public class FurnitureModel extends TreeItem<FurnitureModel> {

    @FXML
    private final StringProperty Model, Typ, A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1;
    @FXML
    private StringProperty extend, amount;

    boolean przetnij = false;

    public FurnitureModel(String Model, String Typ, String A1, String B1, String C1, String D1, String E1, String F1, String G1, String H1, String I1, String J1, String K1, String L1, int rozszerz, boolean przetnij) {
        this(Model,Typ,A1,B1,C1,D1,E1,F1,G1,H1,I1,J1,K1,L1);
        this.przetnij = przetnij;
    }
    public FurnitureModel(String Model, String Typ, String A1, String B1, String C1, String D1, String E1, String F1,
                          String G1, String H1, String I1, String J1, String K1, String L1, String rozszerz, String ilosc) {
        this(Model,Typ,A1,B1,C1,D1,E1,F1,G1,H1,I1,J1,K1,L1);
        this.extend = new SimpleStringProperty(rozszerz);
        this.amount = new SimpleStringProperty(ilosc);
    }

    public FurnitureModel(String Model, String Typ, String A1, String B1, String C1, String D1, String E1, String F1, String G1, String H1, String I1, String J1, String K1, String L1) {
        this.Model = new SimpleStringProperty(Model);
        this.Typ = new SimpleStringProperty(Typ);
        this.A1 = new SimpleStringProperty(String.valueOf(Integer.parseInt(A1)));
        this.B1 = new SimpleStringProperty(String.valueOf(Integer.parseInt(B1)));
        this.C1 = new SimpleStringProperty(String.valueOf(Integer.parseInt(C1)));
        this.D1 = new SimpleStringProperty(String.valueOf(Integer.parseInt(D1)));
        this.E1 = new SimpleStringProperty(E1);
        this.F1 = new SimpleStringProperty(F1);
        this.G1 = new SimpleStringProperty(G1);
        this.H1 = new SimpleStringProperty(H1);
        this.I1 = new SimpleStringProperty(I1);
        this.J1 = new SimpleStringProperty(J1);
        this.K1 = new SimpleStringProperty(K1);
        this.L1 = new SimpleStringProperty(L1);
    }

    public StringProperty modelProperty() {
        return Model;
    }

    public StringProperty typProperty() {
        return Typ;
    }

    public StringProperty a1Property() {
        return A1;
    }

    public StringProperty b1Property() {
        return B1;
    }

    public StringProperty c1Property() {
        return C1;
    }

    public StringProperty d1Property() {
        return D1;
    }

    public StringProperty e1Property() {
        return E1;
    }

    public StringProperty f1Property() {
        return F1;
    }

    public StringProperty g1Property() {
        return G1;
    }

    public StringProperty h1Property() {
        return H1;
    }

    public StringProperty i1Property() {
        return I1;
    }

    public StringProperty j1Property() {
        return J1;
    }

    public StringProperty k1Property() {
        return K1;
    }

    public StringProperty l1Property() {
        return L1;
    }

    public StringProperty extendProperty() {
        return extend;
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public boolean getPrzetnij() {
        return this.przetnij;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FurnitureModel)) return false;
        FurnitureModel that = (FurnitureModel) o;
        return Model.equals(that.Model) && Typ.equals(that.Typ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Model, Typ);
    }
}
