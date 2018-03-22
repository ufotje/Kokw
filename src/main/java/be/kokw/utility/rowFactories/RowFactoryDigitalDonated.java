package be.kokw.utility.rowFactories;

import be.kokw.Main;
import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.controllers.books.search.donated.DonateDetails;
import be.kokw.repositories.digital.DigitalDonateRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;

@SuppressWarnings("unchecked")
public interface RowFactoryDigitalDonated {

    static void setFactory(TableView table, DigitalDonateRepo donateRepo) {
        table.setRowFactory(tv -> {
            TableRow<Digital> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Digital clickedRow = row.getItem();
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/books/found/gifted/giftedAllDetails.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DonateDetails controller = loader.getController();
                    Scene scene = new Scene(root);
                    stage.setTitle("DonatieDetails van digitale drager: '" + clickedRow.getTitle() + "'");
                    stage.setScene(scene);
                    DigitalDonated donated = donateRepo.findByDigital(clickedRow);
                    String [] name = donated.getName().split(" ");
                    controller.setFirstName(name[0]);
                    StringBuilder sb = new StringBuilder();
                    for(int i = 1; i < name.length; i++){
                        sb.append(name[i]);
                        sb.append(" ");
                    }
                    controller.setLastName(sb.toString());
                    controller.setDateValue(donated.getGiftedOn());
                    stage.show();
                }

            });
            return row;
        });
    }
}

