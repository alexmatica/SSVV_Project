package controller;

import model.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 5/15/2018.
 */
public class Lab4HomeTest {

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
    public void testReqAandB() throws Exception{
        Client c = new Client("name", "address", "id");
        testReqA();
        assertEquals("Success!", clientController.AddClientIndex(c,2018,10,10));
    }

    @Test
    public void testReqABC() throws Exception{
        Client c = new Client("name", "address", "id");
        testReqAandB();
        assertEquals("Year: 2018, Month: 10, Penalty: 10\n", clientController.ListInvoice(c));
    }
}
