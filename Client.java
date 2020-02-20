import java.util.*;
import java.lang.*;
import java.io.*;

public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private float balance;

    public  Client (String name,String id, float balance)
    {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }
    public String getName() {return name;}
    public String getId() {return id;}
    public float getBalance() {return balance;}

    public void setName(String newName) {name = newName;}
    public void setId(String newId){id = newId;}
    public void addToBalance(float newBalance) {balance = newBalance;}

    public Boolean makePayment(float payment) {
        if(payment <= balance) {
            balance -= payment;
            return true;
        } else {
            return false;
        }
    }
    public String toString() {
        return "name " + name + " id " + id + " balance " + balance;
    }
}