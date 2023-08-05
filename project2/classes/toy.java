package project2.classes;

public class toy {
    private int id;
    private String name;
    private int count;
    private float price;
    private int rarity;
    
    public toy(int id, String name, int count, float price, int rarity) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    public int getRarity() {
        return rarity;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String toString() {
        return "[id=" + id + ", name=" + name + ", count=" + count + 
                ", price=" + price + ", rarity=" + rarity + "]";
    }
   
    public String toStringAsPrize() {
        return "[id=" + id + ", name=" + name + ", price=" + price + "]";
    }

    public String toSavePrize() {
        return id + ";" + name + ";" + price;
    }
}
