package be.kokw.utility;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.repositories.digital.DigitalDonateRepo;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
public interface RowFactoryDigitalDonated {

    static void setFactory(TableView table, DigitalDonateRepo donateRepo, TextField firstName, TextField lastName, DatePicker date, Stage window){
        table.setRowFactory(tv -> {
            TableRow<Digital> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Digital clickedRow = row.getItem();
                    if (clickedRow.isDonated()) {
                        DigitalDonated donated = donateRepo.findByDigital(clickedRow);
                        window.show();
                        String[] fullName = donated.getName().split(" ");
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < fullName.length; i++) {
                            if (i == 0) {
                                firstName.setText(fullName[i]);
                            } else {
                                sb.append(fullName[i]);
                                sb.append(" ");
                            }
                        }
                        lastName.setText(sb.toString());
                        date.setValue(donated.getGiftedOn());
                    }
                }
            });
            return row;
        });
    }
}
