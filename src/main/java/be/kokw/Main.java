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
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.*;
import java.time.LocalDate;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "be.kokw.repositories")
@EnableScheduling
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
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("KOKW-AdminApp");
        Image icon = new Image(getClass().getResourceAsStream("/images/logoKOKW.jpg"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
    }

    @Override
    public void stop() {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://db4free.net:3306/kokwapp?verifyServerCertificate=false&useSSL=true";
        final String USER = "kokwapp";
        final String PASS = "W817Matthew";
        Connection conn;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Date date = Date.valueOf(LocalDate.now());
            PreparedStatement statement = conn.prepareStatement("UPDATE time_stamp SET last = ? WHERE id = ?");
            statement.setDate(1,date);
            statement.setInt(2,1);
            int result = statement.executeUpdate();
            if(result > 0){
                System.out.println("Timestamp succes");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        springContext.close();
    }
}

