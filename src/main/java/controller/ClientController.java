package controller;

import model.Client;
import model.Invoice;
import repository.DataManager;

public class ClientController {
    private DataManager _dataManager;

    public ClientController(){
        _dataManager = new DataManager();

        _dataManager.invoices.forEach(System.out::println);
        _dataManager.clients.forEach(System.out::println);
    }

    private String ValidateClient(String name, String address){
        if(!name.equals("") && !address.equals("") && !name.equals(" ")){
            for(int i=0;i<name.length();i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    return "Invalid character: " + name.charAt(i);
                }
            }
            return null;
        }else{
            return "Name or address cannot be empty!";
        }
    }

    public String AddClient(String name, String address, String id){
        //validation
        String valid;
        if((valid = ValidateClient(name, address)) != null){
            return valid;
        }
        Client c = new Client(name, address,id);
        //uniqueness
        for(int j=0; j<_dataManager.clients.size(); j++){
            if(_dataManager.clients.get(j).equals(c)){
                return "Client already exists!";
            }
        }
        this._dataManager.addClient(c);
        return "Success!";
    }

    public String AddClientIndex(Client c, int year, int month, float toPay) throws NumberFormatException{

        if (year <= 0)
            throw new NumberFormatException("Year can't be 0 or less!");
        if (month <= 0 || month >= 12)
            throw new NumberFormatException("Month must be between 0 and 12!");
        if (toPay <= 0)
            throw new NumberFormatException("Amount to pay can't be 0 or less!");

        String valid;
        if((valid = ValidateClient(c.name, c.address)) == null){
            //check if client exist
            Boolean exist = false;
            for(int i=0; i<_dataManager.clients.size(); i++){
                if(_dataManager.clients.get(i).equals(c)){
                    exist = true;
                    break;
                }
            }
            if(exist){
                Invoice i = new Invoice(c, year, month, toPay, 0);
                //uniqueness
                for(int j=0; j<_dataManager.invoices.size(); j++){
                    if(_dataManager.invoices.get(j).equals(i)){
                        return "Monthly index already exists!";
                    }
                }
                this._dataManager.addInvoice(i);
                return "Success!";
            }else{
                return "Client does not exist!";
            }
        }else{
            return valid;
        }
    }

    public String ListInvoice(Client c){
        String s = "";
        for(int i=0; i<_dataManager.invoices.size(); i++){
            if(_dataManager.invoices.get(i).client.equals(c)){
                Invoice crt = _dataManager.invoices.get(i);
                s += String.format("Year: %d, Month: %d, Penalty: %2.0f\n", crt.year, crt.month, crt.toPay);
            }
        }
        if (!s.equals(""))
            return s;
        return "No invoices!";
    }

}
