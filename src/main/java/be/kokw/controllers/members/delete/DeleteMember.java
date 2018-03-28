package be.kokw.controllers.members.delete;

import be.kokw.bean.Member;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DeleteMember {
    @FXML
    private TextField id;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo){
        this.repo = repo;
    }

    @FXML
    private void delete(){
        Member member = repo.getOne(Long.parseLong(id.getText()));
        int result = repo.deleteById(Long.parseLong(id.getText()));
        if(result > 0){
            Warning.alert("Member Removed!", "Het lid: " + member.getFirstName() + " " + member.getLastName() + " werd succesvol werwijderd.");
        } else{
            Warning.alert("Member Not Found!", "Het lid met id: " + id.getText() + " werd niet teruggevonden");
        }
    }
}
