package be.kokw.utility;

import be.kokw.Main;
import be.kokw.bean.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.bean.TimeStamp;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.repositories.TimeStampRepo;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class AutomatedTasks {
    private CheckOutRepo checkOutRepo;
    private TimeStampRepo timeStampRepo;
    private MemberRepo memberRepo;

    @Autowired
    private void setCheckOutRepo(@Qualifier("checkOutRepo") CheckOutRepo checkOutRepo) {
        this.checkOutRepo = checkOutRepo;
    }

    @Autowired
    private void setTimeStampRepo(@Qualifier("stampRepo")TimeStampRepo timeStampRepo){
        this.timeStampRepo = timeStampRepo;
    }

    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo){
        this.memberRepo = memberRepo;
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    public void mailOverDue() {
        List<CheckedOut> list = checkOutRepo.findByReturnDateBeforeAndReturnedIsFalse(LocalDate.now());
        if (list != null) {
            for (CheckedOut c : list) {
                Member m = c.getMember();
                Book b = c.getBook();
                String name = m.getFirstName();
                String title = b.getTitle();
                String text = "Geachte " + name + "\n \nHet boek: '" + title + "' geschreven door '" + "' werd te laat terug gebracht.\nGelieve zo spoedig mogelijk het boek in te leveren.\n \nMet vriendelijke groeten \n \n \nHet KOKW-Team";
                Mail.sendMail(m.getEmail(), "Boek Te Laat!", text);
            }
        } else {
            Warning.alert("No Items Found", "Er zijn geen boeken gevonden die te laat zijn.");
        }
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    public void mailBDay() {
        TimeStamp stamp = timeStampRepo.findOne(1);
        LocalDate latest = stamp.getLast();
        LocalDate now = LocalDate.now();
        List<Member> list = memberRepo.findByBDayBetween(latest, now);
        String topic = "Happy Birthday";
        String wens = "\n \nWij, van de KOKW, willen u laten weten dat ook bij ons uw verjaardag niet ongemerkt is gebleven en willen je van harte feliciteren op deze speciale dag.";
        String text;
        if (list.isEmpty()) {
            Warning.alert("No Members Found", "Er werden geen leden gevonden die jarig zijn!");
        } else {
            for (Member m : list) {
                LocalDate bDay = m.getbDay();
                if (bDay.equals(now)) {
                    text = "Beste " + m.getFirstName() + wens + "\n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                    Mail.sendMail(m.getEmail(), topic, text);
                } else {
                    Date d1 = Date.from(bDay.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    Date d2 = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    long difference = d2.getTime() - d1.getTime();
                    text = "Beste " + m.getFirstName() + wens + "\nOok al zijn we " + difference + " dag(en) te hopen wij dat je toch genoten hebt van je verjaardag en wensen we je nog vele jaren. \n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                    Mail.sendMail(m.getEmail(), topic, text);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    private void mailTwoDaysNotice() {
        LocalDate returnDate = LocalDate.now().plusDays(2);
        List<CheckedOut> list = checkOutRepo.findByReturnDateAndReturnedIsFalse(returnDate);
        if (list.isEmpty()) {
            Warning.alert("No Items Found", "Er werden geen boeken gevonden die binnen de 2 dagen dienen worden terug gebracht!");
        } else {
            for (CheckedOut c : list) {
                String name = c.getFullName();
                String title = c.getTitle();
                Member m = c.getMember();
                String topic = "Uw uitleenbeurt vervalt binnen 2 dagen...";
                String text = "Geachte " + name + "\n \nUw uitleenbeurt voor het boek '" + title + "' vervalt binnen 2 dagen!\nVergeet niet tijdig het boek binnen te brengen.\n \nMet vriendelijke groeten\n \n \n \nHet KOKW-Team";
                Mail.sendMail(m.getEmail(), topic, text);
            }
        }
    }
}
