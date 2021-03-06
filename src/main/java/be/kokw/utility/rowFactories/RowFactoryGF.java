package be.kokw.utility.rowFactories;

import be.kokw.bean.books.GiftedFor;
import be.kokw.utility.converting.ShowContract;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.File;

/**
 * Created By Demesmaecker Daniel
 */

@SuppressWarnings("unchecked")
public interface RowFactoryGF {
    static void set(TableView table) {
        table.setRowFactory(tv -> {
            TableRow<GiftedFor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    GiftedFor clickedRow = row.getItem();
                    File file = clickedRow.getContract();
                    ShowContract.show(file);

                }
            });
            return row;
        });
    }
}
