package be.kokw.controllers.magazines.search;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.Subscribed;
import be.kokw.repositories.magazines.MagazineRepo;
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


@Component
public class FindMagBySubscribed {
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
    private MagazineRepo repo;
    private SubscribedRepo subRepo;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setSubRepo(@Qualifier("subscribedRepo") SubscribedRepo subRepo) {
        this.subRepo = subRepo;
    }

    @FXML
    public void initialize() {
        ObservableList<Magazine> list = observableArrayList(repo.findMagazinesBySubscribedIsTrue());
        if (list.isEmpty()) {
            Warning.alert("No Magazines found!", "Er werden geen magazines gevonden waar de KOKW op geabonneerd is.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        } else {
            MagazineTable.init(table, id, issn, name, topic, publisher, nr, year, pages, period, copies, illustrated, list);

            table.setRowFactory(tv -> {
                TableRow<Magazine> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                                && event.getClickCount() == 2) {
                            Magazine clickedRow = row.getItem();
                            Subscribed subscribed = subRepo.findOne(clickedRow.geId());
                        if (subscribed != null) {
                            try {
                                window = NewStage.getStage("RijDetails!", "/fxml/magazines/search/views/subscriptionDetailsView.fxml");
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
    private void closeSubDetails() {
        window.close();
        detailsId.clear();
        magId.clear();
        title.clear();
        org.clear();
        expected.clear();
        street.clear();
        houseNr.clear();
        zip.clear();
        city.clear();
        telephone.clear();
        mail.clear();
    }
}



