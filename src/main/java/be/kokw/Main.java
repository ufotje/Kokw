package be.kokw;

import be.kokw.repositories.books.interfaces.BookRepo;
import be.kokw.repositories.books.interfaces.MemberRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Main extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("KOKW-AdminApp");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        BookRepo bookRepo = springContext.getBean("bookRepo", BookRepo.class);
        MemberRepo memberRepo = springContext.getBean("memberRepo",MemberRepo.class);
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}
