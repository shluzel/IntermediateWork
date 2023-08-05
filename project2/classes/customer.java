package project2.classes;

public class customer {
    
    private int id;
    private String fullName;
    private String checkNumber;
    
    private String phone;
    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getCheckNumber() {
        return checkNumber;
    }
    public String getPhone() {
        return phone;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public customer(int id, String fullName, String checkNumber, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.checkNumber = checkNumber;
        this.phone = phone;
    }

    public String toString() {
        return "[id=" + id + ",fullName=" + fullName + ", checkNumber=" + 
                checkNumber + ", phone=" + phone + "]";
    }

    public String toSavePrize() {
        return id + ";" + fullName + ";" + checkNumber + ";" + phone;
    }
}