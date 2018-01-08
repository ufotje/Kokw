package be.kokw.controllers.books.search.donatedAgainst;

import be.kokw.bean.books.GiftedFor;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.utility.NewStage;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByGiftedForOnAll {
    @FXML
    private TableView<GiftedFor> table;
    @FXML
    private TableColumn<GiftedFor, Integer> idCol;
    @FXML
    private TableColumn<GiftedFor, String> isbnCol;
    @FXML
    private TableColumn<GiftedFor, String> bookIdCol;
    @FXML
    private TableColumn<GiftedFor, String> depotCol;
    @FXML
    private TableColumn<GiftedFor, String> conNameCol;
    @FXML
    private TableColumn<GiftedFor, File> conCol;
    @FXML
    private TableColumn<GiftedFor, LocalDate> conDateCol;
    @FXML
    private TableColumn<GiftedFor, String> conNrCol;
    @FXML
    private TableColumn<GiftedFor, String> titleCol;
    @FXML
    private TableColumn<GiftedFor, String> authorCol;

    private GiftedForRepo repo;

    @Autowired
    private void setBookRepo(@Qualifier("giftedForRepo") GiftedForRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize() {
        ObservableList<GiftedFor> bookList = observableArrayList(repo.findAll());
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die op werden gedoneerd met tegenprestatie.");
        } else {
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("issbn"));
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            conNrCol.setCellValueFactory(new PropertyValueFactory<>("contract_number"));
            conDateCol.setCellValueFactory(new PropertyValueFactory<>("contract_date"));
            conNameCol.setCellValueFactory(new PropertyValueFactory<>("contractor"));
            conCol.setCellValueFactory(new PropertyValueFactory<>("contract"));
            table.setItems(bookList);

            table.setRowFactory(tv -> {
                TableRow<GiftedFor> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                            && event.getClickCount() == 2) {
                        GiftedFor clickedRow = row.getItem();
                        File file = clickedRow.getContract();

                        if (!Desktop.isDesktopSupported()) {
                            System.out.println("Desktop is not supported");
                            System.setProperty("java.awt.headless", "true");
                            try {
                                BufferedImage bufferedImage = ImageIO.read(file);
                                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                                ImageView imageV = new ImageView();
                                imageV.setImage(image);
                                AnchorPane pane = new AnchorPane();
                                pane.getChildren().add(imageV);
                                Scene scene = new Scene(pane);
                                Stage window = new Stage();
                                window.setTitle("Selected Contract!");
                                window.setScene(scene);
                                window.show();
                            } catch (Exception z) {
                                z.printStackTrace(System.err);
                            }
                            //return;
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
                });
                return row;
            });
        }
    }
}
