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
        return "[id=" + id + ",\t\t\t name=" + name + ",\t\t\t count=" + count + 
                ",\t\t\t price=" + price + ",\t\t\t rarity=" + rarity + "]";
    }
   
    public String toStringAsPrize() {
        return "[id=" + id + ",\t\t\t name=" + name + ",\t\t\t price=" + price + "]";
    }

    public String toSavePrize() {
        return id + ";\t\t\t" + name + ";\t\t\t" + price;
    }
}
