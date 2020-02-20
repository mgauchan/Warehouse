
import java.io.Serializable;
import java.text.DecimalFormat;

// Class by Kevin Gruwell
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String id;
    private float salesPrice;
    private int stock;

    public Product(String name, String id, float salesPrice, int stock) {
        this.name = name;
        this.id = id;
        this.salesPrice = salesPrice;
        this.stock += stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public float getSalesPrice() { return salesPrice; }
    public int getStock() { return stock; }

    public void setSalesPrice(float salesPrice) { this.salesPrice = salesPrice; }

    public void changeStock(int stockMod) {
        stock += stockMod;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "{[Product] Name: " + name +
                " | ID: " + id +
                " | Sales Price: $" + df.format(salesPrice) +
                " | Stock: " + stock + "}";
    }

}