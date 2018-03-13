package model;

import java.util.Objects;

public class Invoice {
    public Client client;
    public int year;
    public int month;
    public float toPay;
    public float paid;

    public Invoice(Client client, int year, int month, float toPay, float paid){
        this.client = client;
        this.year = year;
        this.month = month;
        this.toPay = toPay;
        this.paid = paid;
    }

    @Override
    public String toString(){
        return String.format("%s" + System.getProperty("line.separator") + "%d, %d, %2.0f, %2.0f",
                this.client.toString(), this.year, this.month, this.toPay, this.paid);
    }

    @Override
    public boolean equals(Object object){
        if(object == null) return false;
        if(!(object instanceof Invoice)) return false;
        Invoice i = (Invoice) object;
        if(i.client == null) return false;
        return (i.month == this.month) && (i.year == this.year) && (i.client.equals(this.client));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.client);
        hash = 71 * hash + this.year;
        hash = 71 * hash + this.month;
        hash = 71 * hash + Float.floatToIntBits(this.toPay);
        hash = 71 * hash + Float.floatToIntBits(this.paid);
        return hash;
    }
}
