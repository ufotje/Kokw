package be.kokw.controllers.magazines.create;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.Trade;
import be.kokw.repositories.magazines.MagazineRepo;
import be.kokw.repositories.magazines.TradeRepo;
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
    private MagazineRepo repo;
    private Stage window;
    private TradeRepo tr;
    private Magazine magazine;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setTr(@Qualifier("tradeRepo") TradeRepo tr) {
        this.tr = tr;
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
            magazine = new Magazine(issn.getText(), Integer.parseInt(copies.getText()), name.getText(), publisher.getText(), period.getText(), year.getText(), Integer.parseInt(nr.getText()), Integer.parseInt(pages.getText()), theme.getText());
            if (traded.isSelected() && subscription.isSelected()) {
                Warning.alert("Selection error", "Een magazine kan niet zowel geruild als deel van een abonnement zijn.\nGelieve 1 iets te selecteren");
            } else if (traded.isSelected()) {
                magazine.setTraded(true);
                try {
                    window = NewStage.getStage("Ruilabonnement Info", "/fxml/magazines/create/tradeDetails.fxml");
                    publisher.setText(magazine.getPublisher());
                    name.setText(magazine.getName());
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (subscription.isSelected()) {
                magazine.setSubscribed(true);
            } else if (!illustrated.isSelected()) {
                magazine.setIllustrated(false);
            }
            if (!traded.isSelected() && !subscription.isSelected()) {
                Magazine mag = repo.save(magazine);
                if (mag != null) {
                    Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
                } else {
                    Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
                }
            }
        }
        return true;
    }

    @FXML
    private void saveTradeDetail() {
        if (Validation.validate("expected", expected.getText(), "[0-9]+")) {
            if (Validation.validate("houseNr", houseNr.getText(), "[0-9]+") || houseNr.getText().isEmpty()) {
                if (!houseNr.getText().isEmpty()) {
                    if (Validation.validate("street", street.getText(), "[a-zA-Z //-]+")) {
                        if (Validation.validate("zip", zip.getText(), "[0-9]+")) {
                            if (Validation.validate("city", city.getText(), "[a-zA-Z //-]+")) {
                                if (Validation.validate("email", email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)") || email.getText().isEmpty()) {
                                    if (Validation.validate("tel", telephone.getText(), "[0-9 /////-]+") || telephone.getText().isEmpty()) {
                                        StringBuilder sb = new StringBuilder();
                                        if (!street.getText().isEmpty()) {
                                            sb.append(street.getText());
                                            sb.append(" ");
                                            sb.append(houseNr.getText());
                                            sb.append("\n");
                                            sb.append(zip.getText());
                                            sb.append(" ");
                                            sb.append(city.getText());
                                        }
                                        Trade trade = new Trade(magazine, publisher.getText(), Integer.parseInt(expected.getText()), sb.toString(), email.getText(), telephone.getText());
                                        Trade t = tr.save(trade);
                                        window.close();
                                        if (t != null) {
                                            Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
                                        } else {
                                            Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
                                        }
                                    } else {
                                        if (!telephone.getText().isEmpty()) {
                                            Warning.alert("Wrong input", "Verkeerd invoer voeer het veld telefoonnummer.");
                                        }
                                    }
                                } else {
                                    Warning.alert("Wrong Input", "Verkeerd invoer voor het e-mailveld");
                                }
                            } else {
                                Warning.alert("Wrong input", "Steden kunnen enkel letters, een koppelteken of een spatie bevatten.");
                            }
                        } else {
                            Warning.alert("Wrong input", "postcodes kunnen enkel uit cijfers bestaan.");
                        }
                    } else {
                        Warning.alert("Wrong input", "Straatnamen kunnen enkel letters, een koppelteken of een spatie bevatten.");
                    }
                }

            } else {
                if (!houseNr.getText().isEmpty()) {
                    Warning.alert("Wrong input", "Huisnummers kunnen enkel uit cijfers bestaan.");
                }
            }
        } else {
            Warning.alert("Wrong Input", "Dit veld is een verplicht veld en kan enkel cijfers bevatten.");
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
}
