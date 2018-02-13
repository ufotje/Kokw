package be.kokw.utility.rowFactories;


import be.kokw.bean.digital.DigitalTraded;
import be.kokw.utility.controller.ShowContract;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.File;

public interface RowFactoryDigitalTraded {
    static void set(TableView table) {
        table.setRowFactory(tv -> {
            TableRow<DigitalTraded> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    DigitalTraded clickedRow = row.getItem();
                    File file = clickedRow.getContract();
                    ShowContract.show(file);

                }
            });
            return row;
        });
    }
}
