package be.kokw.utility.controller;

import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;

public interface AddAuthor {
    static StringBuilder add(String author) {
        StringBuilder sb = new StringBuilder();
        if (Validation.validate("author", author, "[a-zA-Z \\-]+")) {
            sb.append(author);
            sb.append("\n");
        } else {
            Warning.alert("Wrong Input!", "Verkeerde invoer voor naam Auteur.");
        }
        return sb;
    }
}
