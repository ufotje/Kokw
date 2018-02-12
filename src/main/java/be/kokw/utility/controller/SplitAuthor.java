package be.kokw.utility.controller;

import java.util.List;

public interface SplitAuthor {
    static List<String> split(String author, List<String> authorsList) {
        String[] authorsSplitted = author.split("\n");
        for (String s : authorsSplitted) {
            if (!authorsList.contains(s)) {
                authorsList.add(s);
            }
        }
        return authorsList;
    }
}
