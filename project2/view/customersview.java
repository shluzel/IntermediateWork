package project2.view;

import java.util.List;
import project2.classes.*;

public class customersview {

    List<customer> customers;

    public customersview(List<customer> customers) {
        this.customers = customers;
    }

    public void ShowTable() {
        System.out.println("\nCustomers Table\n---------------");
        for (customer item : customers) {
            System.out.println(item.toString());
        }
    }
}
