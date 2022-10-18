package eu.xdarqus.mwdesign.calls;

import eu.xdarqus.mwdesign.MWDesign;
import eu.xdarqus.mwdesign.controllers.ChooseController;
import eu.xdarqus.mwdesign.models.Type;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;

public class TreeViewCallImpl extends TreeCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty)
            setText(null);
        else
            setText(item);

    }
    public TreeViewCallImpl() {
        super();

        setOnMouseClicked(event -> {
            TreeItem<String> ti = getTreeItem();
            if (ti == null || event.getClickCount() < 2)
                return;
            if (ti.getParent() == null) return;
            Type type = null;
            try{
                type = Type.valueOf(ti.getParent().getValue());
            }catch (IllegalArgumentException ex) {}
            if (type == null) return;
            String name = ti.getValue();

            MWDesign.controller.setModelAndType(type, name);
            MWDesign.controller.updateModelValues(type.name(),name);
        });
    }
}
