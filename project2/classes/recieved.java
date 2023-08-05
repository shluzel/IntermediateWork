package project2.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class recieved extends prize{
    private LocalDateTime dateAward;
   
    public recieved(int id, customer customer, toy toy, LocalDateTime dateAward) {
        super(id, customer, toy);
        this.dateAward = dateAward;
    }

    public LocalDateTime getDateAward() {
        return dateAward;
    }
    
    public String getDateAwardString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd.MM.yyyy HH:mm");
        return dateAward.format(formatter);
    }

    public void setDateAward(LocalDateTime dateAward) {
        this.dateAward = dateAward;
    }
   
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd.MM.yyyy HH:mm");
        return "PrizeAwarded: [id=" + super.getId() + ",\n" +
                "Customer=" + super.getCustomer().toString() + ",\n" +
                "Toy=" + super.getToy().toStringAsPrize() + ",\n" +
                "DateAward=" + dateAward.format(formatter) + 
                "]\n***************";
    }    

}
