package be.kokw.utility.controller;

import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.scene.control.TextField;

public interface AddSubtitle {

    static StringBuilder addSubtitles(TextField subTitle){
        StringBuilder stringBuilder = new StringBuilder();
        if (Validation.emptyValidation("subTitle", subTitle.getText().isEmpty())) {
            stringBuilder.append(subTitle.getText());
            stringBuilder.append("\n");
        } else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }
        return stringBuilder;
    }
}
