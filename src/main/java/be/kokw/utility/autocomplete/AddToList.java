package be.kokw.utility.autocomplete;

import java.util.List;

@SuppressWarnings("unchecked")
public interface AddToList {

    static List add(List list, String s){
        if (!list.contains(s)) {
           list.add(s);
        }
        return list;
    }
}
