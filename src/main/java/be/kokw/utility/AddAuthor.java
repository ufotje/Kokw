package be.kokw.utility;

import javafx.scene.control.TextField;

public interface AddAuthor {
    static StringBuilder add(TextField author) {
        StringBuilder sb = new StringBuilder();
        if (Validation.validate("author", author.getText(), "[a-zA-Z \\-]+")) {
            sb.append(author.getText());
            sb.append("\n");
        } else {
            Warning.alert("Wrong Input!", "Verkeerde invoer voor naam Auteur.");
        }
        return sb;
    }
}
