package project2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import project2.classes.*;
import project2.view.*;
import project2.model.*;

public class menu {
    private String prevPos;
    private String choice;
    private String newPos;
    private boolean ShowNewChoice;
    private Scanner sc;
    DateTimeFormatter formatter;

    public void run() {
        showProgramGreeting();
        sc = new Scanner(System.in);
        while (true) {
            ShowNewChoice = true;
            if (getChoice() != "") {
                if (getPrevPos() != "") {
                    setNewPos((getPrevPos() + "," +
                            getChoice()));
                } else {
                    setNewPos(getChoice());
                }
            } else {
                if (getPrevPos() != "") {
                    setNewPos(getPrevPos());
                } else {
                    setNewPos("");
                }
            }
            if (getChoice().equals("0")) {
                showMainMenu();
            } else if (getChoice().equals("q")) {
                showProgramExit();
                return;
            } else {
                switch (getNewPos()) {
                    case (""):
                        showMainMenu();
                        break;
                    case ("1"):
                        showCustomersMenu();
                        break;
                    case ("1,1"):
                        CustomersShowTableAll();
                        break;

                    case ("1,2"):
                        if (CustomerAddNew()) {
                            CustomersShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("1,3"):
                        if (CustomerEdit()) {
                            CustomersShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("1,4"):
                        if (CustomerDeleteById()) {
                            CustomersShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("2"):
                        showToysMenu();
                        break;

                    case ("2,1"):
                        ToysShowTableAll();
                        break;

                    case ("2,2"):
                        if (ToyAddNew() == true) {
                            ToysShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("2,3"):
                        if (ToyEdit()) {
                            ToysShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("2,4"):
                        if (ToyDeleteById()) {
                            ToysShowTableAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("3"):
                        showDrawingMenu();
                        break;

                    case ("3,1"):
                        PrizesToAwardShowAll();
                        showDrawingMenu();
                        break;

                    case ("3,2"):
                        if (PrizeAddNew()) {
                            PrizesToAwardShowAll();
                            showDrawingMenu();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("3,3"):
                        if (PrizeSetAsAwarded()) {
                            PrizesAwardedShowAll();
                        } else {
                            setChoice("");
                            ShowNewChoice = false;
                        }
                        break;

                    case ("3,4"):
                        PrizesAwardedShowAll();
                        break;

                    default:
                        showMenuItemNotFound();
                        setChoice("");
                        ShowNewChoice = false;
                        break;
                }
            }

            if (ShowNewChoice) {
                System.out.printf("Select menu item: ");
                try {
                    setChoice(sc.nextLine().trim());
                } catch (NoSuchElementException exception) {
                    System.out.println("Menu item was not chosen");
                    setChoice("");
                }
            }
        }
    }

    public void showProgramGreeting() {
        System.out.println();
        String s1 = "Roulette for Toy Store";
        System.out.println(s1);
        System.out.println("-".repeat(s1.length()));
    }

    public void showMainMenu() {
        String s1 = "Main Menu";
        System.out.println("\n" + s1 + "\n" + "-".repeat(s1.length()));
        System.out.println("1. Customers.");
        System.out.println("2. Toys.");
        System.out.println("3. Roulette.");
        System.out.println("q. Exit.");
        ResetMenuPos();
    }

    public void showCustomersMenu() {
        String s1 = "Menu - Customers Table";
        System.out.println("\n" + s1 + "\n" + "-".repeat(s1.length()));
        System.out.println("1. Show Customers Table.");
        System.out.println("2. Add Customer.");
        System.out.println("3. Edit Customer.");
        System.out.println("4. Delete Customer.");
        System.out.println("0. Back in Main Menu.");
        System.out.println("q. Exit.");
        setPrevPos(getNewPos());
    }

    public void showToysMenu() {
        String s1 = "Menu - Toys Table";
        System.out.println("\n" + s1 + "\n" + "-".repeat(s1.length()));
        System.out.println("1. Show Toy Table.");
        System.out.println("2. Add Toy.");
        System.out.println("3. Edit Toy.");
        System.out.println("4. Delete Toy.");
        System.out.println("0. Back in Main Menu.");
        System.out.println("q. Exit.");
        setPrevPos(getNewPos());
    }

    public void showDrawingMenu() {
        System.out.println("\nMenu - Roulette\n----------------");
        System.out.println("1. Show Roulette Table.");
        System.out.println("2. Play The Next Prize.");
        System.out.println("3. Check Roulette.");
        System.out.println("4. Show Table Awarded Prizes.");
        System.out.println("0. Back in Main Menu.");
        System.out.println("q. Exit.");
        setPrevPos(getNewPos());
    }

    public void showProgramExit() {
        System.out.println();
        System.out.println("Quitting");
    }

    public void showMenuItemNotFound() {
        System.out.println("There is no such item in menu.");
    }

    public void CustomersShowTableAll() {
        customersmodel customersmodel = new customersmodel();
        if (customersmodel.load()) {
            customersview customersview = new customersview(customersmodel.getCustomersAll());
            customersview.ShowTable();
        }
        ReturnToPrevPos();
        showCustomersMenu();
    }

    public boolean CustomerAddNew() {
        customersmodel customersmodel = new customersmodel();
        if (!customersmodel.load()) {
            System.out.println("\nAddin Customer was interrupted.");
            return false;
        }

        int curId = customersmodel.getNewId();
        System.out.println("\nAdding Customer. Enter info:");
        System.out.print("Name: ");
        try {
            String curFullName = sc.nextLine();
            System.out.print("Reciet number: ");
            String curCheckNumber = sc.nextLine();
            System.out.print("Phone number: ");
            String curPhone = sc.nextLine();
            customer curCustomer = new customer(curId, curFullName, curCheckNumber,
                    curPhone);
            customersmodel.add(curCustomer);
        } catch (Exception ex) {
            System.out.println("Error.\n" + ex.toString());
            return false;
        }

        if (customersmodel.save()) {
            System.out.println("Succesfully added new cuxtomer.");
        } else {
            System.out.println("Error with adding new customer.");
            return false;
        }
        return true;
    }

    public boolean CustomerDeleteById() {
        customersmodel customersmodel = new customersmodel();
        if (customersmodel.load()) {
            customersview customersview = new customersview(customersmodel.getCustomersAll());
            customersview.ShowTable();
        }
        System.out.print("\nEnter the id of customer you want to delete: ");
        try {
            int curId = Integer.parseInt(sc.nextLine());
            if (customersmodel.deleteById(curId)) {
                customersmodel.save();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error occured when customer with deleting customer.\n" + ex.toString());
            return false;
        }
        return false;
    }

    public boolean CustomerEdit() {
        customer editedCustomer;
        customersmodel customersmodel = new customersmodel();
        if (customersmodel.load()) {
            customersview customersview = new customersview(customersmodel.getCustomersAll());
            customersview.ShowTable();
        }
        System.out.print("\nEnter the id of customer you want to edit: ");
        try {
            int curId = Integer.parseInt(sc.nextLine());
            editedCustomer = customersmodel.getCustomerById(curId);
            String curValue;
            System.out.println("Enter new info (Press Enter to save with no changes).");
            System.out.print("Name (before): " +
                    editedCustomer.getFullName() +
                    "\nName: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedCustomer.setFullName(curValue);

            System.out.print("Reciet number (before): " +
                    editedCustomer.getCheckNumber() +
                    "\nReciet number: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedCustomer.setCheckNumber(curValue);

            System.out.print("Phone Number (before): " +
                    editedCustomer.getPhone() +
                    "\nPhone Number: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedCustomer.setPhone(curValue);

            if (customersmodel.save()) {
                System.out.println("Customer with id=" + curId + " was edited.");
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Error occured when you tried to edit customer.\n" + ex.toString());
            return false;
        }
        return false;
    }

    public void ToysShowTableAll() {
        toysmodel toysmodel = new toysmodel();
        if (toysmodel.load()) {
            toysview toysView = new toysview(toysmodel.getToysAll());
            toysView.ShowTable();
        }

        ReturnToPrevPos();
        showToysMenu();
    }

    public boolean ToyAddNew() {
        toysmodel toysmodel = new toysmodel();
        if (!toysmodel.load()) {
            System.out.println("\nAdding toy was interrupted.");
            return false;
        }

        int curId = toysmodel.getNewId();
        System.out.println("\nAdding New Toy. Enter info:");
        System.out.print("Name: ");
        try {
            String curName = sc.nextLine();
            System.out.print("Quantity: ");
            int curCount = Integer.parseInt(sc.nextLine());
            System.out.print("Price: ");
            float curPrice = Float.parseFloat(sc.nextLine());
            System.out.print("Rarity(drop frequency): ");
            int curRarity = Integer.parseInt(sc.nextLine());
            toy curToy = new toy(curId, curName, curCount,
                    curPrice, curRarity);
            toysmodel.add(curToy);
        } catch (Exception ex) {
            System.out.println("Error.\n" + ex.toString());
            return false;
        }

        if (toysmodel.save()) {
            System.out.println("New Toy was added.");
        } else {
            System.out.println("Error occured when you tried to add new toy.");
            return false;
        }
        return true;
    }

    public boolean ToyDeleteById() {
        toysmodel toysmodel = new toysmodel();
        if (toysmodel.load()) {
            toysview toysview = new toysview(toysmodel.getToysAll());
            toysview.ShowTable();
        }
        System.out.print("\nEnter the id of toy you want to delete: ");
        try {
            int curId = Integer.parseInt(sc.nextLine());
            if (toysmodel.deleteById(curId)) {
                toysmodel.save();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error occured when you tried to delete a toy.\n" + ex.toString());
            return false;
        }
        return false;
    }

    public boolean ToyEdit() {
        toy editedToy;
        toysmodel toysmodel = new toysmodel();
        if (toysmodel.load()) {
            toysview toysview = new toysview(toysmodel.getToysAll());
            toysview.ShowTable();
        }
        System.out.print("\nEnter id of toy you want to edit: ");
        try {
            int curId = Integer.parseInt(sc.nextLine());
            editedToy = toysmodel.getToyById(curId);
            String curValue;
            System.out.println("Enter new info (Press Enter to save with no changes).");
            System.out.print("Name (before): " +
                    editedToy.getName() +
                    "\nName: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedToy.setName(curValue);

            System.out.print("Quantity (before): " +
                    editedToy.getCount() +
                    "\nQuantity: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedToy.setCount(Integer.parseInt(curValue));

            System.out.print("Price (before): " +
                    editedToy.getPrice() +
                    "\nPrice: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedToy.setPrice(Float.parseFloat(curValue));

            System.out.print("Rarity (before): " +
                    editedToy.getRarity() +
                    "\nRarity: ");
            curValue = sc.nextLine();
            if (!curValue.equals(""))
                editedToy.setRarity(Integer.parseInt(curValue));

            if (toysmodel.save()) {
                System.out.println("Toy with id=" + curId + " was edited.");
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Error occured when you tried to edit a toy.\n" + ex.toString());
            return false;
        }
        return false;
    }

    public void PrizesToAwardShowAll() {
        roulettemodel roulettemodel = new roulettemodel();
        if (roulettemodel.loadPrizesToAward()) {
            roulettemodel.ShowTablePrizesToAward();
        }
        ReturnToPrevPos();
        
    }

    public boolean PrizeAddNew() {
        roulettemodel roulettemodel = new roulettemodel();
        if (roulettemodel.PrizesToAwardAddNew())
            return true;
        return false;
    }

    public void PrizesAwardedShowAll() {
        roulettemodel roulettemodel = new roulettemodel();
        if (roulettemodel.loadPrizesAwarded()) {
            roulettemodel.ShowTablePrizesAwarded();
        }
        ReturnToPrevPos();
        showDrawingMenu();
    }

    public boolean PrizeSetAsAwarded() {
            PrizesToAwardShowAll();
            System.out.print("Enter id of prize after roulette, the prize will be awarded: ");
            try {
                int curId = Integer.parseInt(sc.nextLine());

                System.out.print("Enter date and time (Press Enter to add current date): ");
                String curValue = sc.nextLine();
                LocalDateTime curDate;
                if (curValue.equals("")) {
                    curDate = LocalDateTime.now();
                } else {
                    curDate = LocalDateTime.parse(sc.nextLine(),formatter);
                }

                roulettemodel roulettemodel = new roulettemodel();
                if (!roulettemodel.loadPrizesToAward()) {
                    System.out.print("Error occured when you tried to load roulette");
                    return false;
                }
                prize curPrize = roulettemodel.getPrizeToAwardById(curId);
                if (roulettemodel.PrizeToAwardSetAsAwarded(curPrize, curDate)) {
                    
                    return true;
                }
            } catch (Exception ex) {
                System.out.println("Error occured when you tried to edit a toy.\n" + ex.toString());
                return false;
            }

        return false;
    }

    public String getPrevPos() {
        return prevPos;
    }

    public String getChoice() {
        return choice;
    }

    public String getNewPos() {
        return newPos;
    }

    public void setPrevPos(String prevPos) {
        this.prevPos = prevPos;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setNewPos(String newPos) {
        this.newPos = newPos;
    }

    public void ResetMenuPos() {
        prevPos = "";
        choice = "";
        newPos = "";
    }

    public void ReturnToPrevPos() {
        newPos = prevPos;
        choice = "";
    }

    public menu() {
        prevPos = "";
        choice = "";
        newPos = "";
        formatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy HH:mm");
    }
}
