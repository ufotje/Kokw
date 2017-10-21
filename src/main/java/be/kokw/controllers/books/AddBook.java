package be.kokw.controllers.books;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import be.kokw.bean.Book;

/**
 * Created by ufotje on 20/10/2017.
 */
public class AddBook {
  //  private final Configuration cfg = new Configuration().configure("sample/hibernate.cfg.xml");
    //private final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
    //private final SessionFactory sf = cfg.configure().buildSessionFactory(serviceRegistry);
    private TextField title, topic, firstName, lastName, publisher, place, year, pages;
    private Stage window;

    public AddBook() {
        init();
    }

    public void init() {
        title = new TextField();
        title.setTooltip(new Tooltip("Enter title"));
        title.setPromptText("Title");
        title.setMaxWidth(200);

        topic = new TextField();
        topic.setTooltip(new Tooltip("Enter topic"));
        topic.setPromptText("Topic");
        topic.setMaxWidth(200);

        firstName = new TextField();
        firstName.setTooltip(new Tooltip("Enter First Name Author"));
        firstName.setPromptText("First Name");
        firstName.setMaxWidth(200);

        lastName = new TextField();
        lastName.setTooltip(new Tooltip("Enter Last Name Author"));
        lastName.setPromptText("Last Name");
        lastName.setMaxWidth(200);

        publisher = new TextField();
        publisher.setTooltip(new Tooltip("Enter Publisher"));
        publisher.setPromptText("Publisher");
        publisher.setMaxWidth(200);

        place = new TextField();
        place.setTooltip(new Tooltip("Enter Published in"));
        place.setPromptText("City");
        place.setMaxWidth(200);

        year = new TextField();
        year.setTooltip(new Tooltip("Enter Year Published"));
        year.setPromptText("Year");
        year.setMaxWidth(200);

        pages = new TextField();
        pages.setTooltip(new Tooltip("Enter Nr of Pages"));
        pages.setPromptText("Pages");
        pages.setMaxWidth(200);

        Button savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save"));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(title, topic, firstName, lastName, publisher, place, year, pages, savebtn);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox);

        savebtn.setOnAction(event -> save());

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Book");
        window.setScene(scene);
        window.show();
    }

    void save() {
        //Session session = sf.openSession();
        //session.beginTransaction();
        Book book = new Book(title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
        System.out.println(book.getAuthorFirstName());
        //session.save(book);
        //session.getTransaction().commit();
        //session.close();

        window.close();
    }
}
