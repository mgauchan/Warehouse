import java.io.*;

public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String id;

    public Supplier (String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getId() {
        return id;
    }

    public void setName(String newName) {
        name = newName;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public void setId(String newId) {
        id = newId;
    }

    public String toString() {
        String string = " Name:" + name + " address:" + address + "  ID:" + id +"  ";
        return string;
    }
}
