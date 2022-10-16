package eu.xdarqus.mwdesign.models;

import javafx.beans.property.SimpleStringProperty;

public class Corner {

    private SimpleStringProperty name;
    private SimpleStringProperty type;

    public Corner(String name, Type type) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type.toString());
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return this.type.get();
    }

    public void setType(Type type) {
        this.type.set(type.toString());
    }
}
