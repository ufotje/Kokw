package be.kokw.utility.autocomplete;

import be.kokw.bean.digital.DigitalTraded;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

public interface NameTextField {

    static void bind(List<DigitalTraded> list, TextField field){
        List<String> names = new ArrayList<>();
        for(DigitalTraded dt : list){
            if(!names.contains(dt.getName())){
                names.add(dt.getName());
            }
        }
        TextFields.bindAutoCompletion(field, names);
    }
}
