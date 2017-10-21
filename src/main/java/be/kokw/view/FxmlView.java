package be.kokw.view;

import java.util.ResourceBundle;

public enum FxmlView {

    SAMPLE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("sample.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/sample.fxml";
        }
    }, 
    ADDMEMBER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("addMember.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/addMember.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
