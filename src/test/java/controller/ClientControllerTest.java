package controller;

import model.Client;
import org.junit.Before;
import org.junit.Test;
import repository.DataManager;

import static org.junit.Assert.*;

/**
 * Created by Alex on 3/24/2018.
 */
public class ClientControllerTest {

    private ClientController clientController;

    @Before
    public void setUp() throws Exception {
        clientController = new ClientController(true);
    }

    @Test
    public void validateClientNameEmpty() throws Exception{
        Client c = new Client("","address", "unique");
        assertEquals("Name or address cannot be empty!",clientController.ValidateClient(c.name, c.address));
    }

    @Test
    public void validateClientNameLong() throws Exception{
        Client c = new Client("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "address", "unique");
        assertEquals("Name too long",clientController.ValidateClient(c.name, c.address));
    }

    @Test
    public void validateClientNameOk() throws Exception{
        Client c = new Client("nameok", "address", "unqiue");
        assertEquals(null, clientController.ValidateClient(c.name, c.address));
    }

    @Test
    public void validateClientAddressEmpty() throws Exception{
        Client c = new Client("nameok","", "unique");
        assertEquals("Name or address cannot be empty!", clientController.ValidateClient(c.name, c.address));
    }

    @Test
    public void validateClientAddressOk() throws Exception{
        Client c = new Client("nameok","address", "unique");
        assertEquals(null, clientController.ValidateClient(c.name, c.address));
    }

    @Test
    public void validateClientIdUnique() throws Exception{
        Client c = new Client("nameok","address", "unique");
        assertEquals("Success!", clientController.AddClient(c.name, c.address, c.idClient));
    }

    @Test
    public void validateClientIdNotUnique() throws Exception{
        Client c = new Client("nameok","address", "unique");
        clientController.AddClient(c.name, c.address, c.idClient);
        assertEquals("Client already exists!", clientController.AddClient(c.name, c.address, c.idClient));
    }

    @Test
    public void validateIndexInvalidYear() throws Exception{
        Client c = new Client("nameok","address", "unique");
        try {
            clientController.AddClientIndex(c, -1,1,1);
            assert false;
        } catch (NumberFormatException e) {
            assertEquals("Year can't be 0 or less!", e.getMessage());
        }
    }

    @Test
    public void validateIndexInvalidMonth() throws Exception{
        Client c = new Client("nameok","address", "unique");
        try {
            clientController.AddClientIndex(c, 2018,-1,1);
            assert false;
        } catch (NumberFormatException e) {
            assertEquals("Month must be between 0 and 12!", e.getMessage());
        }
    }

    @Test
    public void validateIndexInvalidToPay() throws Exception{
        Client c = new Client("nameok","address", "unique");
        try {
            clientController.AddClientIndex(c, 2018,1,-11);
            assert false;
        } catch (NumberFormatException e) {
            assertEquals("Amount to pay can't be 0 or less!", e.getMessage());
        }
    }

    @Test
    public void validateIndexValidInvoice() throws Exception{
        Client c = new Client("nameok","address", "unique");
        clientController.AddClient("nameok", "address", "unique");
        try {
            clientController.AddClientIndex(c, 10,10,10);
            assert true;
        } catch (Exception e){
            assert false;
        }
    }

    @Test
    public void validateIndexInvalidClient() throws Exception{
        Client c = new Client("","", "");
        assertEquals("Name or address cannot be empty!", clientController.AddClientIndex(c, 2000, 10, 10));
    }

    @Test
    public void validateIndexExistingInvoice() throws Exception{
        Client c = new Client("nameok","address", "unique");
        clientController.AddClient("nameok", "address", "unique");
        clientController.AddClientIndex(c, 10,10,10);
        assertEquals("Monthly index already exists!", clientController.AddClientIndex(c, 10,10,10));
    }

    @Test
    public void validateIndexValid() throws Exception{
        try{
            Client c = new Client("nameok","address", "unique");
            clientController.AddClient("nameok", "address", "unique");
            clientController.AddClientIndex(c, 10,10,10);
            assert true;
        } catch (Exception e){
            assert false;
        }
    }
}