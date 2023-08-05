package project2.model;

import project2.classes.*;
import java.io.*;
import java.util.*;

public class toysmodel {
    private List<toy> toys;
    private String fnameToys;

    public toysmodel() {
        fnameToys = "./project2/info/toys.csv";
    }

    public void add(toy rec) {
        toys.add(rec);
    }

    public boolean deleteById(int curId) {
        for (toy item : toys) {
            if (item.getId() == curId) {
                toys.remove(item);
                System.out.println("Toy with id=" + curId + " was deleted.");
                return true;
            }
        }
        System.out.println("Toy with id=" + curId + " not found.");
        return false;
    }

    public boolean save() {
        try {
            FileWriter fr1 = new FileWriter(fnameToys);
            fr1.append("id|name|count|price|rarity\n");
            for (toy item : toys) {
                fr1.append(item.getId() + "|" +
                        item.getName() + "|" +
                        item.getCount() + "|" +
                        item.getPrice() + "|" +
                        item.getRarity() + "\n");
            }
            fr1.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean load() {
        toys = new LinkedList<>();
        try (FileReader fr = new FileReader(fnameToys)) {
            Scanner scanner = new Scanner(fr);
            int i = 0; 
            while (scanner.hasNextLine()) {
                String curRow = scanner.nextLine();
                if (i > 0) {
                    String[] fields = curRow.split("\\|");
                    if (fields.length != 5) {
                        throw new Exception("There is error in file in row " + i
                                + ". The number of fields is not equal 5.");
                    }
                    int curId = Integer.parseInt(fields[0].trim());
                    String curName = fields[1].trim();
                    int curCount = Integer.parseInt(fields[2].trim());
                    float curPrice = Float.parseFloat(fields[3].trim());
                    int curRarity = Integer.parseInt(fields[4].trim());

                    toy curToy = new toy(curId, curName, curCount,
                            curPrice, curRarity);
                    toys.add(curToy);
                }
                i++;
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public List<toy> getToysAll() {
        return toys;
    }

    public int getNewId() {
        int maxId = -1;
        for (toy item : toys) {
            if (item.getId() > maxId)
                maxId = item.getId();
        }
        return maxId + 1;
    }

    public toy getToyById(int curToyId) {
        for (toy item : toys) {
            if (item.getId() == curToyId)
                return item;
        }
        return null;
    }

    public toy getRandomToyByWeight() {
        if (toys.size() == 0) {
            System.out.println("There is no toys.");
            return null;
        }

        int SumRarity = 0;
        List<toy> selToys = new LinkedList<>();
        for (toy item : toys) {
            if (item.getCount() > 0) {
                selToys.add(item);
                SumRarity += item.getRarity();
            }
        }

        if (selToys.size() == 0) {
            System.out.println("We ran out of toys :(");
            return null;
        }

        int RndRarity = new Random().nextInt(SumRarity + 1);
        SumRarity=0;
        for (toy item : selToys) {
            SumRarity += item.getRarity();
            if (SumRarity >= RndRarity) {
                return item;
            }
        }
        return null;
    }

    public String toString() {
        String res = "";
        for (toy item : toys) {
            res += item.toString();
        }
        return "Toys Table\n---------------------------------------------\n: " + res;
    }

}
