package be.kokw;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("KOKW-AdminApp");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}
