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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;


@Component
public class FindMagBySubscriptionNotFulfilled {
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

    @FXML
    public void initialize() {
        ObservableList<MagazineCount> fulfilledList = observableArrayList(countRepo.findByExpectedIsGreaterThanReceived());
        ObservableList<Magazine> list = observableArrayList();
        for (MagazineCount m :fulfilledList) {
            list.add(m.getMagazine());
        }
        if (list.isEmpty()) {
            Warning.alert("No Magazines found!", "Er werden geen magazines gevonden waar we niet alle editities van gekregen hebben.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
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
                            try {
                                MagazineCount mc = countRepo.findByName(clickedRow.getName());
                                window = NewStage.getStage("RijDetails!", "/fxml/magazines/search/views/subscriptionNotFulfilledDetailsView.fxml");
                                window.show();
                                detailsId.setText("" + subscribed.getId());
                                magId.setText("" + clickedRow.getId());
                                title.setText(subscribed.getNameMag());
                                org.setText(subscribed.getPublisher());
                                expected.setText("" + mc.getExpected());
                                String[] parts = subscribed.getContactInfo().split("[\\n ]");
                                street.setText(parts[0]);
                                houseNr.setText(parts[1]);
                                zip.setText(parts[2]);
                                city.setText(parts[3]);
                                telephone.setText(subscribed.getTel());
                                mail.setText(subscribed.getEmail());

                            } catch (Exception e) {
                                Warning.alert("Error!", "Er ging iets fout tijdens het openen van de rijdetails.");
                                e.printStackTrace();
                            }
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



