package be.kokw.utility.controller.tables;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("unchecked")
public interface DigitalTable {
    static void init(TableView table, TableColumn idCol, TableColumn depotCol, TableColumn titleCol, TableColumn volumeCol,
                     TableColumn topicCol, TableColumn authorCol, TableColumn subTitleCol, TableColumn publisherCol,
                     TableColumn yearCol, ObservableList bookList){

        table.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        subTitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitles"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        table.setItems(bookList);
    }
}
