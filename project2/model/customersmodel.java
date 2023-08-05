package project2.model;

import project2.classes.*;
import java.io.*;
import java.util.*;

public class customersmodel {
    private List<customer> customers; 
    private String fnameCustomers; 

    public customersmodel() {
        fnameCustomers = "./project2/info/customers.csv";
    }
    
    public void add(customer rec) {
        customers.add(rec);
    }
    
    public boolean deleteById(int curId) {
        for (customer item : customers) {
            if (item.getId() == curId) {
                customers.remove(item);
                System.out.println("Customer with id=" + curId + " deleted.");
                return true;
            }
        }
        System.out.println("Customer with id=" + curId + " not found.");
        return false;
    }

    public boolean save() {
        try {
            FileWriter fr1 = new FileWriter(fnameCustomers);
            fr1.append("id\t\t\t|fullName\t\t\t|checkNumber\t\t\t|phone\n");
            for (customer item : customers) {
                fr1.append(item.getId() + "\t\t\t|" +
                        item.getFullName() + "\t\t\t|" +
                        item.getCheckNumber() + "|\t\t\t" +
                        item.getPhone() + "\n");
            }
            fr1.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean load() {
        customers = new LinkedList<>();
        try (FileReader fr = new FileReader(fnameCustomers)) {
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
                    String curFullName = fields[1].trim();
                    String curCheckNumber = fields[2].trim();
                    String curPhone = fields[3].trim();

                    customer curCustomer = new customer(curId, curFullName, curCheckNumber,
                                                curPhone);
                    customers.add(curCustomer);
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

    public List<customer> getCustomersAll() {
        return customers;
    }

    public int getNewId() {
        int maxId = -1;
        for (customer item : customers) {
            if (item.getId() > maxId)
                maxId = item.getId();
        }
        return maxId + 1;
    }

    public customer getCustomerById(int curCustomerId) {
        for (customer item : customers) {
            if (item.getId() == curCustomerId)
                return item;
        }
        return null;
    }

    public String toString() {
        String res = "";
        for (customer item : customers) {
            res += item.toString();
        }
        return "Customers Table\n---------------------------------------------\n: " + res;
    }
}
