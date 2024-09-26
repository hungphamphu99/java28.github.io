package ex_2.entities;

public class Reader {
    private int id;
    private static int nextId = 0;
    private String name;
    private String phoneNumber;
    private String address;

    public Reader( String name, String phoneNumber, String address) {
        this.id = ++nextId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Reader{id=" + id + ", name='" + name + "', phoneNumber='" + phoneNumber + "', address='" + address + "'}";
    }
}
