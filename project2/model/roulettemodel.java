package project2.model;

import project2.classes.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class roulettemodel {
    private String fnamePrizesToAward; 
    private String fnamePrizesAwarded; 
    DateTimeFormatter formatter;
    private roulette drw;

    public roulettemodel() {
        fnamePrizesToAward = "./project2/info/prizestoaward.csv";
        fnamePrizesAwarded = "./project2/info/prizesawarded.csv";
        drw = new roulette();
        formatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy HH:mm");
    }

    public boolean loadPrizesToAward() {
        List<prize> prizesToAward = new LinkedList<>();
        try (FileReader fr = new FileReader(fnamePrizesToAward)) {
            Scanner scanner = new Scanner(fr);
            int i = 0;
            while (scanner.hasNextLine()) {
                String curRow = scanner.nextLine();
                if (i > 0) {
                    String[] fields = curRow.split("\\|");
                    if (fields.length != 3) {
                        throw new Exception("There is error in file in row " + i 
                                    + ". The number of fields is not equal 3.");
                    }
                    int curId = Integer.parseInt(fields[0].trim());
                    String[] buFields = fields[1].trim().split(";");
                    customer curCustomer = new customer(Integer.parseInt(buFields[0].trim()),
                                                buFields[1].trim(),
                                                buFields[2].trim(),
                                                buFields[3].trim());
                    String[] toFields = fields[2].trim().split(";");
                    toy curToy = new toy(Integer.parseInt(toFields[0].trim()),
                                        toFields[1].trim(),
                                        0,
                                        Float.parseFloat(toFields[2].trim()),
                                        0);
                    prize curPrize = new prize(curId, curCustomer, curToy);
                    prizesToAward.add(curPrize);
                }
                i++;
            }
            drw.setPrizesToAward(prizesToAward);
            scanner.close();
        } catch (Exception ex) {
            System.out.println("Error with table of prizes to award.\n" + ex.toString());
            return false;
        }
        return true;
    }

    public boolean loadPrizesAwarded() {
        List<recieved> prizesAwarded = new LinkedList<>();
        try (FileReader fr = new FileReader(fnamePrizesAwarded)) {
            Scanner scanner = new Scanner(fr);
            int i = 0; 
            while (scanner.hasNextLine()) {
                String curRow = scanner.nextLine();
                if (i > 0) {
                    String[] fields = curRow.split("\\|");
                    if (fields.length != 4) {
                        throw new Exception("There is error in file in row " + i 
                                    + ". The number of fields is not equal 4.");
                    }
                    int curId = Integer.parseInt(fields[0].trim());
                    String[] buFields = fields[1].trim().split(";");
                    customer curCustomer = new customer(Integer.parseInt(buFields[0].trim()),
                                                buFields[1].trim(),
                                                buFields[2].trim(),
                                                buFields[3].trim());
                    String[] toFields = fields[2].trim().split(";");
                    toy curToy = new toy(Integer.parseInt(toFields[0].trim()),
                                        toFields[1].trim(),
                                        0,
                                        Float.parseFloat(toFields[2].trim()),
                                        0);
                    
                    LocalDateTime curDate = LocalDateTime.parse(fields[3].trim(), formatter);
                    recieved curPrize = new recieved(curId, curCustomer, curToy, curDate);
                    prizesAwarded.add(curPrize);
                }
                i++;
            }
            drw.setPrizesAwarded(prizesAwarded);
            scanner.close();
        } catch (Exception ex) {
            System.out.println("Error with table of prizes awarded.\n" + ex.toString());
            return false;
        }
        return true;
    }

    public void ShowTablePrizesToAward() {
        String s1 = "Table Prizes Roulette";
        System.out.println("\n" + s1 + "\n" + "-".repeat(s1.length()));
        for (prize item : drw.getPrizesToAward()) {
            System.out.println(item.toString());
        }
    }

    public void ShowTablePrizesAwarded() {
        String s1 = "Table Prizes Awarded";
        System.out.println("\n" + s1 + "\n" + "-".repeat(s1.length()));
        for (recieved item : drw.getPrizesAwarded()) {
            System.out.println(item.toString());
        }
    }    
    
    public boolean PrizeToAwardSetAsAwarded(prize curPrize, LocalDateTime curDate) {
        int curID = curPrize.getId();
        this.loadPrizesAwarded();
        List<recieved> prizesAwarded = drw.getPrizesAwarded();
        int newId = getPrizesAwardedNewId();

        recieved newPrizeAwarded = new recieved(newId,
                                                    curPrize.getCustomer(),
                                                    curPrize.getToy(),
                                                    curDate);

        if (!prizesAwarded.add(newPrizeAwarded)) {
            System.out.print("Error occured when you tried to add awarded prize.");
            return false;
        }

        List<prize> prizesToAward = drw.getPrizesToAward();
        for (prize item : prizesToAward) {
            if (item.getId() == curID) {
                prizesToAward.remove(item);
                break;
            }
        }

        drw.setPrizesAwarded(prizesAwarded);
        savePrizesAwarded();

        drw.setPrizesToAward(prizesToAward);
        savePrizesToAward();

        System.out.println("Prize was awarded. id of prize=" + newId + ".");    
        return true;
    }

    public boolean PrizesToAwardAddNew() {

        loadPrizesToAward();

        toysmodel toysmodel = new toysmodel();
        if (!toysmodel.load()) {
            return false;
        }

        toy RandomToy = toysmodel.getRandomToyByWeight();
        if (RandomToy == null) {
            System.out.println("Error. Toy was not chosen.");
            return false;
        }
        
        customersmodel customersmodel = new customersmodel();
        if (!customersmodel.load()) {
            return false;
        }

        List<customer> customersAll = customersmodel.getCustomersAll();
        List<customer> customersNotAwarded = new LinkedList<>();
        for (customer customer : customersAll) {
            boolean isPresent = false;
            for (prize prize : drw.getPrizesToAward()) {
                if (customer.getId() == prize.getCustomer().getId()) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                customersNotAwarded.add(customer);
            }
        }

        if (customersNotAwarded.size()==0) {
            System.out.println("Prize can not be chosen. There is no people without prizes.");
            return false;
        }

        int RndNumber = new Random().nextInt(customersNotAwarded.size());
        customer RandomCustomer = customersNotAwarded.get(RndNumber);      
        int NewId = getPrizesToAwardNewId();
        prize newPrize = new prize(NewId, RandomCustomer, RandomToy);
        drw.getPrizesToAward().add(newPrize);
        if (!savePrizesToAward()) {
            System.out.println("Error occured when you tried to save the roulette prizes.");
            return false;
        }
        RandomToy.setCount(RandomToy.getCount()-1);
        toysmodel.save();

        System.out.println("Succesfull roulette. id=" + NewId + ". Wath Prizes Roulette.");
        return true;
    }
    

    public boolean savePrizesToAward() {
        try {
            FileWriter fr1 = new FileWriter(fnamePrizesToAward);
            fr1.append("id|Customer=id;fullName;checkNumber;phone|Toy=id;name;price\n");
            for (prize item : drw.getPrizesToAward()) {
                fr1.append(item.getId() + "|" +
                        item.getCustomer().toSavePrize() + "|" +
                        item.getToy().toSavePrize() + "\n");
            }
            fr1.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean savePrizesAwarded() {
        try {
            FileWriter fr1 = new FileWriter(fnamePrizesAwarded);
            fr1.append("id\t\t\t|Customer=id;fullName;checkNumber;phone\t\t\t|Toy=id;name;price\t\t\t|dateAward\n");
            for (recieved item : drw.getPrizesAwarded()) {
                fr1.append(item.getId() + "|" +
                        item.getCustomer().toSavePrize() + "|" +
                        item.getToy().toSavePrize() + "|" +
                        item.getDateAwardString() + "\n");
            }
            fr1.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public int getPrizesToAwardNewId() {
        int maxId = -1;
        for (prize item : drw.getPrizesToAward()) {
            if (item.getId() > maxId)
                maxId = item.getId();
        }
        return maxId + 1;
    }

    public int getPrizesAwardedNewId() {
        int maxId = -1;
        for (prize item : drw.getPrizesAwarded()) {
            if (item.getId() > maxId)
                maxId = item.getId();
        }
        return maxId + 1;
    }

    public prize getPrizeToAwardById(int curPrizeId) {
        for (prize item : drw.getPrizesToAward()) {
            if (item.getId() == curPrizeId)
                return item;
        }
        return null;
    }
}
