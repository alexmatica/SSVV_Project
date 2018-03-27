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

}