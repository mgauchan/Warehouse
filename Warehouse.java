
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

//Class by Kevin Gruwell
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Warehouse warehouse;

    private ProductList productList;
    private SupplierList supplierList;
    private ClientList clientList;

    private Warehouse() {
        productList = ProductList.instance();
        supplierList = SupplierList.instance();
        clientList = ClientList.instance();
    }

    public static Warehouse instance() {
        if(warehouse == null) warehouse = new Warehouse();
        return warehouse;
    }

    public Product addProduct(String name, String id, float salesPrice, int stock) {
        Product prod = new Product(name, id, salesPrice, stock);
        if(productList.insertProduct(prod)) return prod;
        return null;
    }
    public Supplier addSupplier(String name, String address, String id) {
        Supplier supp = new Supplier(name, address, id);
        if(supplierList.insertSupplier(supp)) return supp;
        return null;
    }
    public Client addClient(String name, String id, float balance) {
        Client client = new Client(name, id, balance);
        if(clientList.insertClient(client)) return client;
        return null;
    }

    public Product getProduct(String id) { return productList.getProduct(id); }
    public Iterator<Product> getProducts() { return productList.getProducts(); }

    public Supplier getSupplier(String id) { return supplierList.getSupplier(id); }
    public Iterator<Supplier> getSuppliers() { return supplierList.getSuppliers(); }

    public Client getClient(String id) { return clientList.getClient(id); }
    public Iterator<Client> getClients() { return clientList.getClients(); }

    public boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("WarehouseData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(warehouse);

            file.close();
            output.close();
            return true;
        }catch(Exception e) { e.printStackTrace(); return false; }
    }

    public static Warehouse retrieve() {
        try {
            FileInputStream file = new FileInputStream("WarehouseData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();

            file.close();
            input.close();
            return warehouse;
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    private void writeObject(ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(warehouse);
        }catch(IOException e) { e.printStackTrace(); }
    }

    private void readObject(ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if(warehouse == null) warehouse = (Warehouse) input.readObject();
            else input.readObject();
        }catch(IOException e) { e.printStackTrace(); }
        catch(ClassNotFoundException e) { e.printStackTrace(); }
    }

    @Override
    public String toString() {
        return productList + "\n" + supplierList + "\n" + clientList;
    }

}
