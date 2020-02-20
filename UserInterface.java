import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

//Class by Kevin Gruwell
public class UserInterface {
    {
            private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            private static UserInterface userInterface;
            private static Warehouse warehouse;

            private static final int EXIT = 0;
            private static final int SAVE = 1;
            private static final int HELP = 2;
            private static final int ADD_PRODUCT = 3;
            private static final int ADD_SUPPLIER = 4;
            private static final int ADD_CLIENT = 5;
            private static final int CHANGE_PRODUCT_SALESPRICE = 6;
            private static final int CHANGE_SUPPLIER_DATA = 7;
            private static final int CHANGE_CLIENT_DATA = 8;
            private static final int SHOW_PRODUCTS = 9;
            private static final int SHOW_SUPPLIERS = 10;
            private static final int SHOW_CLIENTS = 11;

    private UserInterface() {
            if (yesOrNo("Look for saved data and use it?")) retrieve();
            else warehouse = Warehouse.instance();
        }

            public static UserInterface instance () {
            if (userInterface == null) userInterface = new UserInterface();
            return userInterface;
        }

            public String getToken (String prompt){
            do {
                try {
                    System.out.println(prompt);
                    String str = reader.readLine();
                    StringTokenizer tokenizer = new StringTokenizer(str, "\n\r\f");
                    if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
                } catch (IOException e) {
                    System.exit(0);
                }
            } while (true);
        }

            private boolean yesOrNo (String prompt){
            String str = getToken(prompt + " (Y|y)[es] or anything else for no");
            if (str.charAt(0) != 'y' && str.charAt(0) != 'Y') return false;
            return true;
        }

            public void help () {
            System.out.println("Enter a number between 0 and 11 as explained below:");
            System.out.println(EXIT + " to exit the program.");
            System.out.println(SAVE + " to save the current warehouse.");
            System.out.println(HELP + " to display this menu.");
            System.out.println(ADD_PRODUCT + " to add a product.");
            System.out.println(ADD_SUPPLIER + " to add a supplier.");
            System.out.println(ADD_CLIENT + " to add a client.");
            System.out.println(CHANGE_PRODUCT_SALESPRICE + " to change product's sales price.");
            System.out.println(CHANGE_SUPPLIER_DATA + " to change supplier data.");
            System.out.println(CHANGE_CLIENT_DATA + " to change client data.");
            System.out.println(SHOW_PRODUCTS + " to show a list of products.");
            System.out.println(SHOW_SUPPLIERS + " to show a list of suppliers.");
            System.out.println(SHOW_CLIENTS + " to show a list of clients.");
        }

            public void addProducts () {
            Product result;
            do {
                String name = getToken("Enter name");
                String id = getToken("Enter ID");
                String salesPrice = getToken("Enter sales price");
                String stock = getToken("Enter stock");
                result = warehouse.addProduct(name, id, Float.parseFloat(salesPrice), Integer.parseInt(stock));

                if (result != null) System.out.println(result);
                else System.out.println("Product could not be added.");

                if (!yesOrNo("Add more products?")) break;
            } while (true);
        }

            public void addSuppliers () {
            Supplier result;
            do {
                String name = getToken("Enter name");
                String id = getToken("Enter ID");
                String address = getToken("Enter address");
                result = warehouse.addSupplier(name, address, id);

                if (result != null) System.out.println(result);
                else System.out.println("Supplier could not be added.");

                if (!yesOrNo("Add more suppliers?")) break;
            } while (true);
        }

            public void addClients () {
            Client result;
            do {
                String name = getToken("Enter name");
                String id = getToken("Enter ID");
                String balance = getToken("Enter balance");
                result = warehouse.addClient(name, id, Float.parseFloat(balance));

                if (result != null) System.out.println(result);
                else System.out.println("Client could not be added.");

                if (!yesOrNo("Add more clients?")) break;
            } while (true);
        }

            public void changeProductSalesprice () {
            do {
                String id = getToken("Enter product ID");
                Product p = warehouse.getProduct(id);
                if (p == null) System.out.println("Product with id " + id + " not found.");
                else {
                    float newPrice = Float.parseFloat(getToken("Enter new sales price"));
                    p.setSalesPrice(newPrice);
                    System.out.println("New sales price has been set.");
                }

                if (!yesOrNo("Change a different sales price?")) break;
            } while (true);
        }

            public void changeSupplierData () {
            System.out.println("Stub");
        }
            public void changeClientData () {
            System.out.println("Stub");
        }

            public void showProducts () {
            Iterator<Product> products = warehouse.getProducts();
            while (products.hasNext()) System.out.println(products.next().toString());
        }

            public void showClients () {
            Iterator<Client> clients = warehouse.getClients();
            while (clients.hasNext()) System.out.println(clients.next().toString());
        }

            public void showSuppliers () {
            Iterator<Supplier> suppliers = warehouse.getSuppliers();
            while (suppliers.hasNext()) System.out.println(suppliers.next().toString());
        }

            private void retrieve () {
            Warehouse temphouse = Warehouse.retrieve();
            if (temphouse != null) {
                System.out.println("The warehouse has been successfully retrieved from the file WarehouseData.");
                warehouse = temphouse;
            } else {
                System.out.println("File does not exist. Creating new warehouse...");
                warehouse = Warehouse.instance();
            }
        }

            private void save () {
            if (warehouse.save())
                System.out.println("The warehouse has been successfully saved to the file WarehouseData.");
            else System.out.println("There has been an error in saving.");
        }

            public int getCommand () {
            do {
                try {
                    int val = Integer.parseInt(getToken("Enter command: (" + HELP + " for help)"));
                    if (val >= EXIT && val <= SHOW_CLIENTS) return val;
                } catch (NumberFormatException e) {
                    System.out.println("Enter a number");
                }
            } while (true);
        }

            public void process () {
            int command;
            help();
            while ((command = getCommand()) != EXIT) {
                switch (command) {
                    case SAVE:
                        save();
                        break;
                    case HELP:
                        help();
                        break;
                    case ADD_PRODUCT:
                        addProducts();
                        break;
                    case ADD_SUPPLIER:
                        addSuppliers();
                        break;
                    case ADD_CLIENT:
                        addClients();
                        break;
                    case CHANGE_PRODUCT_SALESPRICE:
                        changeProductSalesprice();
                        break;
                    case CHANGE_SUPPLIER_DATA:
                        changeSupplierData();
                        break;
                    case CHANGE_CLIENT_DATA:
                        changeClientData();
                        break;
                    case SHOW_PRODUCTS:
                        showProducts();
                        break;
                    case SHOW_SUPPLIERS:
                        showSuppliers();
                        break;
                    case SHOW_CLIENTS:
                        showClients();
                        break;
                }
            }
        }

            public static void main (String[]args){
            UserInterface.instance().process();

        }

        }
    }

