package be.kokw.controllers.magazines.search;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.MagazineCount;
import be.kokw.bean.magazines.Subscribed;
import be.kokw.repositories.magazines.MagazineCountRepo;
import be.kokw.repositories.magazines.SubscribedRepo;
import be.kokw.utility.controller.tables.MagazineTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class FindMagBySubscriptionFulfilled {
    @FXML
    private TableView<Magazine> table;
    @FXML
    private TableColumn<Magazine, String> id;
    @FXML
    private TableColumn<Magazine, String> issn;
    @FXML
    private TableColumn<Magazine, String> name;
    @FXML
    private TableColumn<Magazine, String> topic;
    @FXML
    private TableColumn<Magazine, String> publisher;
    @FXML
    private TableColumn<Magazine, String> nr;
    @FXML
    private TableColumn<Magazine, String> year;
    @FXML
    private TableColumn<Magazine, String> pages;
    @FXML
    private TableColumn<Magazine, String> period;
    @FXML
    private TableColumn<Magazine, String> copies;
    @FXML
    private TableColumn<Magazine, Boolean> illustrated;
    @FXML
    private TextField detailsId;
    @FXML
    private TextField magId;
    @FXML
    private TextField title;
    @FXML
    private TextField org;
    @FXML
    private TextField expected;
    @FXML
    private TextField street;
    @FXML
    private TextField houseNr;
    @FXML
    private TextField zip;
    @FXML
    private TextField city;
    @FXML
    private TextField telephone;
    @FXML
    private TextField mail;
    private Stage window;
    private SubscribedRepo subRepo;
    private MagazineCountRepo countRepo;

    @Autowired
    private void setSubRepo(@Qualifier("subscribedRepo") SubscribedRepo subRepo) {
        this.subRepo = subRepo;
    }

    @Autowired
    private void setCountRepo(@Qualifier("magCountRepo") MagazineCountRepo countRepo){
        this.countRepo = countRepo;
    }

    /**
     * Shows a table with all the magazines of which the subscription is fulfilled
     * When clicked on a row it show the subscription details
     */
    @FXML
    public void initialize() {
        ObservableList<MagazineCount> fulfilledList = observableArrayList(countRepo.findByExpectedEqualsReceived());
        ObservableList<Magazine> list = observableArrayList();
        for (MagazineCount m :fulfilledList) {
            list.add(m.getMagazine());
        }
        if (list.isEmpty()) {
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            Warning.alert("No Magazines found!", "Er werden geen magazines gevonden waar we alle editities van gekregen hebben.");
        } else {
            MagazineTable.init(table, id, issn, name, topic, publisher, nr, year, pages, period, copies, illustrated, list);

            table.setRowFactory(tv -> {
                TableRow<Magazine> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                                && event.getClickCount() == 2) {
                            Magazine clickedRow = row.getItem();
                            Subscribed subscribed = subRepo.findOne(clickedRow.getId());
                        if (subscribed != null) {
                                window = NewStage.getStage("RijDetails!", "/fxml/magazines/search/views/subscriptionFulfilledDetailView.fxml");
                                window.show();
                                detailsId.setText("" + subscribed.getId());
                                magId.setText("" + clickedRow.getId());
                                title.setText(subscribed.getNameMag());
                                org.setText(subscribed.getPublisher());
                                expected.setText("" );
                                String[] parts = subscribed.getContactInfo().split("[\\n ]");
                                street.setText(parts[0]);
                                houseNr.setText(parts[1]);
                                zip.setText(parts[2]);
                                city.setText(parts[3]);
                                telephone.setText(subscribed.getTel());
                                mail.setText(subscribed.getEmail());
                        }
                    }
                });
                return row;
            });
        }
    }

    @FXML
    private void closeDetails() {
        window.close();
    }
}



