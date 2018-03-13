package model;

import java.util.Objects;

public class Client {
    public String name;
    public String address;
    public String idClient;

    public Client(String name, String address, String idC){
        this.name = name;
        this.address = address;
        this.idClient = idC;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s", this.name, this.address, this.idClient);
    }

    @Override
    public boolean equals(Object object){
        if(object == null) return false;
        if(!(object instanceof Client)) return false;
        Client c = (Client)object;

        return (c.idClient.equals(this.idClient));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.address);
        return hash;
    }
}