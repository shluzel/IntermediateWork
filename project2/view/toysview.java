package project2.view;

import java.util.*;
import project2.classes.*;

public class toysview {

    List<toy> toys;
    
    public toysview(List<toy> toys) {
        this.toys = toys;
    }

    public void ShowTable() {
        System.out.println("\nToys Table\n---------------");
        for (toy item : toys) {
            System.out.println(item.toString());
        }
    }
}
