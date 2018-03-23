package be.kokw.utility.maillings;

import be.kokw.bean.books.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.bean.TimeStamp;
import be.kokw.repositories.books.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.repositories.TimeStampRepo;
import be.kokw.utility.validation.Warning;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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
    private void setTimeStampRepo(@Qualifier("stampRepo") TimeStampRepo timeStampRepo) {
        this.timeStampRepo = timeStampRepo;
    }

    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    public void mailOverDue() {
        Platform.runLater(() -> {
            List<CheckedOut> list = checkOutRepo.findByReturnDateBeforeAndReturnedIsFalse(LocalDate.now());
            if (!list.isEmpty()) {
                for (CheckedOut c : list) {
                    Member m = c.getMember();
                    Book b = c.getBook();
                    String name = m.getFirstName();
                    String title = b.getTitle();
                    String text = "Geachte " + name + "\n \nHet boek: '" + title + "' geschreven door '" + b.getAuthors() + "' werd te laat terug gebracht.\nGelieve zo spoedig mogelijk het boek in te leveren.\n \nMet vriendelijke groeten \n \n \nHet KOKW-Team";
                    SingleRecipient.sendMail(m.getEmail(), "Boek Te Laat!", text);
                }
            } else {
                Warning.alert("No Items Found", "Er zijn geen boeken gevonden die te laat zijn.");
            }
        });
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    public void mailBDay() {
        Platform.runLater(() -> {
            TimeStamp stamp = timeStampRepo.findOne(1);
            LocalDate n = LocalDate.now();
            List<Member> list = memberRepo.findByBDayBetween(stamp.getLast(), n);
            String topic = "Happy Birthday";
            String wens = "\n \nWij, van de KOKW, willen u laten weten dat ook bij ons uw verjaardag niet ongemerkt is gebleven en willen je van harte feliciteren op deze speciale dag.";
            String text;
            Date d2 = Date.from(n.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Calendar now = Calendar.getInstance();
            now.setTime(d2);
            if (list.isEmpty()) {
                Warning.alert("No Members Found", "Er werden geen leden gevonden die jarig zijn!");
            } else {
                for (Member m : list) {
                    LocalDate b = m.getbDay();
                    Date d1 = Date.from(b.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    Calendar bDay = Calendar.getInstance();
                    bDay.setTime(d1);
                    if (bDay.get(Calendar.MONTH) == now.get(Calendar.MONTH) && bDay.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
                        text = "Beste " + m.getFirstName() + wens + "\n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                        SingleRecipient.sendMail(m.getEmail(), topic, text);
                    } else {
                        int difference = now.get(Calendar.DAY_OF_MONTH) - bDay.get(Calendar.DAY_OF_MONTH);
                        text = "Beste " + m.getFirstName() + wens + "\nOok al zijn we " + difference + " dag(en) te laat, hopen wij dat je toch genoten hebt van je verjaardag en wensen we je nog vele jaren. \n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                        SingleRecipient.sendMail(m.getEmail(), topic, text);
                    }
                }
            }
        });
    }

    @Scheduled(cron = "0 0 10,16 * * *")
    private void mailTwoDaysNotice() {
        Platform.runLater(() -> {
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
                    SingleRecipient.sendMail(m.getEmail(), topic, text);
                }
            }
        });
    }
}
