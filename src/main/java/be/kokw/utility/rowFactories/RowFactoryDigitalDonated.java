package be.kokw.utility.rowFactories;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.repositories.digital.DigitalDonateRepo;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

@SuppressWarnings("unchecked")
public interface RowFactoryDigitalDonated {

    static void setFactory(TableView table, DigitalDonateRepo donateRepo, TextField firstName, TextField lastName, DatePicker date){
        table.setRowFactory(tv -> {
            TableRow<Digital> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Digital clickedRow = row.getItem();
                    if (clickedRow != null) {
                        DigitalDonated donated = donateRepo.findByDigital(clickedRow);
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
                        AllDetailWindow.create(firstName, lastName, date);
                    }
                }
            });
            return row;
        });
    }
}
