package be.kokw.utility.controller.tables;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("unchecked")
public interface MagazineTable {

    static void init(TableView table, TableColumn id, TableColumn issn, TableColumn name, TableColumn topic,
                     TableColumn publisher, TableColumn nr, TableColumn year, TableColumn pages, TableColumn period,
                     TableColumn copies, TableColumn illustrated, ObservableList list){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        issn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        topic.setCellValueFactory(new PropertyValueFactory<>("theme"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        pages.setCellValueFactory(new PropertyValueFactory<>("nrOfPages"));
        period.setCellValueFactory(new PropertyValueFactory<>("period"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        illustrated.setCellValueFactory(new PropertyValueFactory<>("illustrated"));
        table.setItems(list);
    }
}
