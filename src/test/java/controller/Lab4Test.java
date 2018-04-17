package controller;

import model.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/17/2018.
 */
public class Lab4Test {

    private ClientController clientController;

    @Before
    public void setUp() throws Exception {
        clientController = new ClientController(true);
    }

    @Test
    public void testReqA() throws Exception{
        assertEquals("Success!", clientController.AddClient("name", "address", "id"));
    }

    @Test
    public void testReqB() throws Exception{
        Client c = new Client("name", "address", "id");
        clientController.AddClient("name", "address", "id");
        assertEquals("Success!", clientController.AddClientIndex(c,2018,10,10));
    }

    @Test
    public void testReqC() throws Exception{
        Client c = new Client("name", "address", "id");
        clientController.AddClient("name", "address", "id");
        clientController.AddClientIndex(c, 2010,10,10);
        assertEquals("Year: 2010, Month: 10, Penalty: 10\n", clientController.ListInvoice(c));
    }

    @Test
    public void bigBang() throws Exception{
        Client c = new Client("name", "address", "id");
        assertEquals("Success!",clientController.AddClient("name", "address", "id"));
        assertEquals("Success!", clientController.AddClientIndex(c, 2010,10,10));
        assertEquals("Year: 2010, Month: 10, Penalty: 10\n", clientController.ListInvoice(c));
    }

}
