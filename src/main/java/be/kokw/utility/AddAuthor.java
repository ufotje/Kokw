package be.kokw.utility;

import javafx.scene.control.TextField;

public interface AddAuthor {
    static StringBuilder add(TextField firstNameAuthor, TextField lastNameAuthor){
        StringBuilder sb = new StringBuilder();
        if (Validation.validate("author", firstNameAuthor.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("author", lastNameAuthor.getText(), "[a-zA-Z \\-]+")) {
                sb.append(firstNameAuthor.getText());
                sb.append(" ");
                sb.append(lastNameAuthor.getText());
                sb.append("\n");
            } else {
                Warning.alert("Wrong Input!", "Verkeerde invoer voor achternaam Auteur.");
            }
        } else {
            Warning.alert("Wrong Input!", "Verkeerde invoer voor voornaam Auteur.");
        }
        return sb;
    }
}
