package be.kokw.utility.controller.tables;

import be.kokw.bean.books.Book;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


@SuppressWarnings("unchecked")
public interface BookTable {
    static void init(TableView table, TableColumn idCol, TableColumn isbnCol, TableColumn depotCol, TableColumn titleCol,
                     TableColumn editionCol, TableColumn volumeCol, TableColumn topicCol, TableColumn authorCol,
                     TableColumn subTitleCol, TableColumn publisherCol, TableColumn yearCol, TableColumn pagesCol,
                     TableColumn illusCol, ObservableList<Book> bookList){
        table.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        subTitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitles"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("nrOfPages"));
        illusCol.setCellValueFactory(new PropertyValueFactory<>("illustrated"));
        table.setItems(bookList);
    }
}
