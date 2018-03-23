package be.kokw.controllers.magazines.search;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.Trade;
import be.kokw.repositories.magazines.MagazineRepo;
import be.kokw.repositories.magazines.TradeRepo;
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
public class FindMagByTraded {
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
    private MagazineRepo repo;
    private TradeRepo tradeRepo;
    private Stage window;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setTradeRepo(@Qualifier("tradeRepo") TradeRepo repo) {
        tradeRepo = repo;
    }

    /**
     * Shows a table with all the traded magazines
     * When clicked on a row it show the trade details
     */
    @FXML
    public void initialize() {
        ObservableList<Magazine> list = observableArrayList(repo.findMagazinesByTradedIsTrue());
        if (list.isEmpty()) {
            Warning.alert("No Magazines found!", "Er werden geen magazines gevonden waar er een ruilabonnement is.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        } else {
            MagazineTable.init(table, id, issn, name, topic, publisher, nr, year, pages, period, copies, illustrated, list);
        }
        table.setRowFactory(tv -> {
            TableRow<Magazine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Magazine clickedRow = row.getItem();
                    Trade trade = tradeRepo.findOne(clickedRow.getId());
                    if(trade != null){
                        try {
                            window = NewStage.getStage("RijDetails!", "/fxml/magazines/search/views/tradeDetailsView.fxml");
                            window.show();
                            detailsId.setText("" + trade.getId());
                            magId.setText("" + clickedRow.getId());
                            title.setText(trade.getNameMag());
                            org.setText(trade.getNameOrg());
                            expected.setText("" + trade.getExpected());
                            String[] parts = trade.getContactInfo().split("[\\n ]");
                            street.setText(parts[0]);
                            houseNr.setText(parts[1]);
                            zip.setText(parts[2]);
                            city.setText(parts[3]);
                            telephone.setText(trade.getTel());
                            mail.setText(trade.getEmail());

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

    @FXML
    private void closeDetails(){
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
        window.close();
    }
}
