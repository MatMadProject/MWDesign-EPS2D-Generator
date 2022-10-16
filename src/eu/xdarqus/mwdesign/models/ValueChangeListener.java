package eu.xdarqus.mwdesign.models;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ValueChangeListener implements ChangeListener {
    private final int MAX_LENGTH;

    public ValueChangeListener(int maxLength) {
        this.MAX_LENGTH = maxLength;
    }

    public ValueChangeListener() {
        this.MAX_LENGTH = Integer.MAX_VALUE;
    }

        @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        String oldValue = ((String) o).replaceAll(" ","");
        String newValue = ((String) t1).replaceAll(" ","");
        if(!newValue.isEmpty()){
            if(isNewValueLengthThanMaxLength(newValue.length())){
                setOldValue((StringProperty)observableValue, oldValue);
                return;
            }

            int lastIndex = newValue.length()-1;
            char lastCharacter = newValue.charAt(lastIndex);
//            if(oldValue.length() < newValue.length())
                if(lastCharacter >= 48 && lastCharacter <= 57)
                    ((StringProperty)observableValue).setValue(newValue);
                else
                    setOldValue((StringProperty)observableValue, oldValue);
        }
    }
    private boolean isNewValueLengthThanMaxLength(int newValueLength){
        return newValueLength > MAX_LENGTH;
    }
    private void setOldValue(StringProperty stringProperty, String oldValue){
        if(!oldValue.isEmpty())
            stringProperty.setValue(oldValue);
        else
            stringProperty.setValue("");
    }
}
