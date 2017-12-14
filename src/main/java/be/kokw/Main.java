package be.kokw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "be.kokw.repositories")
public class Main extends Application {
    public static Stage stage;
    public static ConfigurableApplicationContext springContext;
    private Parent root;

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        stage.setTitle("KOKW-AdminApp");
        Image icon = new Image(getClass().getResourceAsStream("/images/logoKOKW.jpg"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
    }

    @Override
    public void stop(){
        springContext.close();
    }
}

