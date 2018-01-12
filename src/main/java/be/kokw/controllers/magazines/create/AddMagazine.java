package be.kokw.controllers.magazines.create;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.MagazineCount;
import be.kokw.bean.magazines.Subscribed;
import be.kokw.bean.magazines.Trade;
import be.kokw.repositories.magazines.MagazineCountRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.NewStage;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
public class AddMagazine {
    @FXML
    private TextField name;
    @FXML
    private TextField title;
    @FXML
    private TextField issn;
    @FXML
    private TextField copies;
    @FXML
    private TextField pages;
    @FXML
    private TextField publisher;
    @FXML
    private TextField period;
    @FXML
    private TextField year;
    @FXML
    private TextField nr;
    @FXML
    private TextField theme;
    @FXML
    private RadioButton illustrated;
    @FXML
    private RadioButton subscription;
    @FXML
    private RadioButton traded;
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
    private TextField email;
    @FXML
    private TextField telephone;

    private Stage window;
    private MagazineCountRepo countRepo;
    private Magazine magazine;
    private StringBuilder sb = new StringBuilder();


    @Autowired
    private void setCountRepo(@Qualifier("magCountRepo") MagazineCountRepo countRepo) {
        this.countRepo = countRepo;
    }

    @FXML
    private void save() {
        if (saveMag()) {
            try {
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void more() {
        if (saveMag()) {
            clear();
        }
    }

    private boolean saveMag() {
        if (validated()) {
            magazine = new Magazine(issn.getText(), Integer.parseInt(copies.getText()), name.getText(), title.getText(), publisher.getText(), period.getText(), year.getText(), Integer.parseInt(nr.getText()), Integer.parseInt(pages.getText()), theme.getText());
            MagazineCount count = countRepo.findByName(magazine.getName());
            if (!illustrated.isSelected()) {
                magazine.setIllustrated(false);
            }
            if (traded.isSelected() && subscription.isSelected()) {
                Warning.alert("Selection error", "Een magazine kan niet zowel geruild als deel van een abonnement zijn.\nGelieve 1 iets te selecteren");
            } else if (traded.isSelected()) {
                magazine.setTraded(true);
                if (count != null) {
                    count.setReceived(count.getReceived() + 1);
                    countRepo.saveAndFlush(count);
                } else {
                    try {
                        window = NewStage.getStage("Ruilabonnement Info", "/fxml/magazines/create/tradeDetails.fxml");
                        publisher.setText(magazine.getPublisher());
                        name.setText(magazine.getName());
                        window.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (subscription.isSelected()) {
                magazine.setSubscribed(true);
                if (count != null) {
                    count.setReceived(count.getReceived() + 1);
                    countRepo.saveAndFlush(count);
                } else {
                    try {
                        window = NewStage.getStage("AbonnementInfo!", "/fxml/magazines/create/subsciptionDetails.fxml");
                        window.show();
                        publisher.setText(magazine.getPublisher());
                        name.setText(magazine.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!traded.isSelected() && !subscription.isSelected()) {
                    if (count != null) {
                        count.setReceived(count.getReceived() + 1);
                        countRepo.saveAndFlush(count);
                    } else {
                        count = new MagazineCount(name.getText(), Integer.parseInt(expected.getText()));
                        count.setMagazine(magazine);
                    }
                    if (countRepo.save(count) != null) {
                        Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
                    } else {
                        Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
                    }
                }
            }
        }
        return true;
    }

    @FXML
    private void saveTradeDetail() {
        if (validateDetail()) {
            Trade trade = new Trade(magazine, publisher.getText(), Integer.parseInt(expected.getText()), sb.toString(), email.getText(), telephone.getText());
            MagazineCount mc = new MagazineCount(name.getText(), Integer.parseInt(expected.getText()));
            mc.setTraded(true);
            mc.setTrade(trade);
            MagazineCount mCount = countRepo.save(mc);
            window.close();
            clear();
            if (mCount != null) {
                Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
            } else {
                Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
            }
        }

    }

    @FXML
    private void saveSubscriptionDetail() {
        if (validateDetail()) {
            Subscribed subscribed = new Subscribed(magazine, sb.toString(), email.getText(), telephone.getText());
            MagazineCount mc = new MagazineCount(name.getText(), Integer.parseInt(expected.getText()));
            mc.setSubscribed(true);
            mc.setSubscribtion(subscribed);
            MagazineCount mCount = countRepo.save(mc);
            window.close();
            clear();
            if (mCount != null) {
                Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
            } else {
                Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
            }
        }
    }

    private void clear() {
        name.clear();
        theme.clear();
        nr.clear();
        year.clear();
        period.clear();
        publisher.clear();
        pages.clear();
        copies.clear();
        issn.clear();
        illustrated.setSelected(true);
        traded.setSelected(false);
        subscription.setSelected(false);
        sb = new StringBuilder();
        email.clear();
        expected.clear();
        houseNr.clear();
        street.clear();
        zip.clear();
        city.clear();
        telephone.clear();
    }

    private boolean validated() {
        boolean validation = false;
        if (Validation.validate("name", name.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("theme", theme.getText(), "[a-zA-Z \\-]+")) {
                if (Validation.validate("nr", nr.getText(), "[0-9]+")) {
                    if (Validation.validate("year", year.getText(), "[0-9]+")) {
                        if (Validation.validate("period", period.getText(), "[a-zA-Z \\-]+")) {
                            if (Validation.validate("publisher", publisher.getText(), "[a-zA-Z \\-\\!\\.]+")) {
                                if (Validation.validate("pages", pages.getText(), "[0-9]+")) {
                                    if (Validation.validate("copies", copies.getText(), "[0-9]+")) {
                                        validation = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return validation;
    }

    private boolean validateDetail() {
        boolean validation = false;
        if (Validation.validate("expected", expected.getText(), "[0-9]+")) {
            if (Validation.validate("houseNr", houseNr.getText(), "[0-9]+") || houseNr.getText().isEmpty()) {
                if (!houseNr.getText().isEmpty()) {
                    if (Validation.validate("street", street.getText(), "[a-zA-Z //-]+")) {
                        if (Validation.validate("zip", zip.getText(), "[0-9]+")) {
                            if (Validation.validate("city", city.getText(), "[a-zA-Z //-]+")) {
                                if (Validation.validate("email", email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)") || email.getText().isEmpty()) {
                                    if (Validation.validate("tel", telephone.getText(), "[0-9 /////-]+") || telephone.getText().isEmpty()) {
                                        if (!street.getText().isEmpty()) {
                                            sb.append(street.getText());
                                            sb.append(" ");
                                            sb.append(houseNr.getText());
                                            sb.append("\n");
                                            sb.append(zip.getText());
                                            sb.append(" ");
                                            sb.append(city.getText());
                                        }
                                        validation = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return validation;
    }
}
