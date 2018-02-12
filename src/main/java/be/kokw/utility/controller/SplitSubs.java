package be.kokw.utility.controller;

import java.util.List;

public interface SplitSubs {
    static List<String> split(String sub, List<String> subsList){
        String[] subsSplited = sub.split("\n");
        for(String s : subsSplited){
            if(!subsList.contains(s)){
                subsList.add(s);
            }
        }
        return subsList;
    }
}
