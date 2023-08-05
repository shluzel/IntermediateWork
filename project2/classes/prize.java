package project2.classes;

public class prize {
    private int id;
    private customer customer;
    private toy toy;
    
    public prize(int id, customer customer, toy toy) {
        this.id = id;
        this.customer = customer;
        this.toy = toy;
    }

    public int getId() {
        return id;
    }

    public customer getCustomer() {
        return customer;
    }

    public toy getToy() {
        return toy;
    }

    public void setBuyer(customer customer) {
        this.customer = customer;
    }

    public void setToy(toy toy) {
        this.toy = toy;
    }

    public String toString() {
        return "Prize: [id=" + id + ",\n" +
                "Customer=" + customer.toString() + ",\n" +
                "Toy=" + toy.toStringAsPrize() + "]\n***************";
    }

}
