package repository;

import model.Client;
import model.Invoice;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DataManager {
    private final static String fileClient = "client.txt";
    private final static String fileInvoice = "invoice.txt";

    public ArrayList<Client> clients;
    public ArrayList<Invoice> invoices;

    public DataManager(){
        clients = new ArrayList<>();
        invoices = new ArrayList<>();

        LoadClients();
        LoadInvoices();
    }

    public void addClient(Client c){
        this.clients.add(c);
        this.SaveChanges();
    }

    public void addInvoice(Invoice i){
        this.invoices.add(i);
        this.SaveChanges();
    }

    private void LoadClients(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileClient));
            String line;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                this.clients.add(new Client(tokens[0].trim(),
                        tokens[1].trim() + ", " + tokens[2].trim() + ", " + tokens[3].trim(),
                        tokens[4]));
            }
            br.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    private void LoadInvoices(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileInvoice));
            String line;
            Boolean b = true;
            Client client = null;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                if(b){
                    client = new Client(tokens[0].trim(),
                            tokens[1].trim() + ", " + tokens[2].trim() + ", " + tokens[3].trim(),
                            tokens[4]);
                    b = false;
                }else{
                    Invoice invoice = new Invoice(client,
                            Integer.parseInt(tokens[0].trim()),
                            Integer.parseInt(tokens[1].trim()),
                            Integer.parseInt(tokens[2].trim()),
                            Integer.parseInt(tokens[3].trim())
                    );
                    this.invoices.add(invoice);
                    b = true;
                }
            }
            br.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public void SaveChanges(){
        try{
            File fClient = new File(fileClient);
            File fInvoice = new File(fileInvoice);
            FileWriter pwClient = new FileWriter(fClient.getAbsolutePath());
            FileWriter pwInvoice = new FileWriter(fInvoice.getAbsolutePath());

            String s;
            try (BufferedWriter bwc = new BufferedWriter(pwClient)) {
                s = "";
                for(Iterator<Client> i = this.clients.iterator(); i.hasNext();){
                    Client c = i.next();
                    s += c.toString() + System.getProperty("line.separator");
                }
                bwc.write(s);
            }
            try (BufferedWriter bwi = new BufferedWriter(pwInvoice)) {
                s = "";
                for(Iterator<Invoice> i = this.invoices.iterator(); i.hasNext();){
                    Invoice iss = i.next();
                    s += iss.toString() + System.getProperty("line.separator");
                }
                bwi.write(s);
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

}
