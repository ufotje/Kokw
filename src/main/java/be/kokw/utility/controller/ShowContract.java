package be.kokw.utility.controller;

import be.kokw.utility.converting.PdfToImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowContract {

    public static void show(File file){
        if (!Desktop.isDesktopSupported()) {
            System.setProperty("java.awt.headless", "true");
            if (FilenameUtils.getExtension(file.getAbsolutePath()).equalsIgnoreCase("pdf")) {
                try {
                    List<BufferedImage> bufferedImage = PdfToImage.convert(file);
                    showImage(bufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<BufferedImage> bufferedImage = new ArrayList<>();
                    bufferedImage.add(ImageIO.read(file));
                    showImage(bufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void showImage(List<BufferedImage> bufferedImage) {
        javafx.scene.control.ScrollPane pane = new ScrollPane();
        VBox vBox = new VBox();
        vBox.setMaxHeight(500.0);
        vBox.setAlignment(Pos.CENTER);
        pane.setHmax(500.0);
        for (BufferedImage bi : bufferedImage) {
            Image image = SwingFXUtils.toFXImage(bi, null);
            ImageView imageV = new ImageView();
            imageV.setImage(image);
            imageV.setPreserveRatio(true);
            imageV.setSmooth(true);
            imageV.setCache(true);
            vBox.getChildren().add(imageV);
        }
        Button b = new Button("Sluiten");
        b.setAlignment(Pos.CENTER);
        b.setFont(Font.font(24));
        Scene scene = new Scene(pane);
        Stage window = new Stage();
        b.setOnAction((event) -> window.close());
        vBox.getChildren().add(b);
        pane.setContent(vBox);
        window.setTitle("Selected Contract!");
        window.setScene(scene);
        window.show();
    }
}
