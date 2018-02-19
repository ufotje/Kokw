package be.kokw.utility.autocomplete;

import be.kokw.bean.Member;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public class TextFieldsMembers {

    public static void autoCompleteAll(List<Member> list, TextField firstName, TextField lastName, TextField street, TextField city){
        List<String> firstNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        List<String> streetNames = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        for(Member m : list){
            if(!firstNames.contains(m.getFirstName())){
                firstNames.add(m.getFirstName());
            }
            if(!lastNames.contains(m.getLastName())){
                lastNames.add(m.getFirstName());
            }
            if(!streetNames.contains(m.getStreet())){
                streetNames.add(m.getStreet());
            }
            if(!cities.contains(m.getCity())){
                cities.add(m.getCity());
            }
        }
        TextFields.bindAutoCompletion(firstName, firstNames);
        TextFields.bindAutoCompletion(lastName, lastNames);
        TextFields.bindAutoCompletion(street, streetNames);
        TextFields.bindAutoCompletion(city, cities);
    }

    public static void autoCompletNames(List<Member> memberList, TextField firstName, TextField lastName){
        List<String> firstNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        for(Member m : memberList) {
            if (!firstNames.contains(m.getFirstName())) {
                firstNames.add(m.getFirstName());
            }
            if (!lastNames.contains(m.getLastName())) {
                lastNames.add(m.getFirstName());
            }
        }
        TextFields.bindAutoCompletion(firstName, firstNames);
        TextFields.bindAutoCompletion(lastName, lastNames);
    }

    public static void autoCompletCities(List<Member> memberList, TextField city){
        List<String> cities = new ArrayList<>();
        for(Member m : memberList) {
            if (!cities.contains(m.getCity())) {
                cities.add(m.getCity());
            }
        }
        TextFields.bindAutoCompletion(city, cities);
    }
}
