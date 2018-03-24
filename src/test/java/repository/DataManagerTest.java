package repository;

import model.Client;
import model.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataManagerTest {
    private DataManager dataManager;

    @Before
    public void setUp() throws Exception {
        dataManager = new DataManager(true);
    }

    @Test
    public void addClient() throws Exception {
        assertEquals(0, dataManager.clients.size());
        dataManager.addClient(new Client("Test", "Test", "Test"));
        assertEquals(1, dataManager.clients.size());
    }

    @Test
    public void addClientFail() throws Exception {
        dataManager.addClient(new Client("Test", "Test", "Test"));
        assertEquals(0, dataManager.clients.size());
    }

    @Test
    public void addInvoice() throws Exception {
        assertEquals(0, dataManager.invoices.size());
        Client c = new Client("Test", "Test", "Test");
        dataManager.addClient(c);
        dataManager.addInvoice(new Invoice(c, 2018,1,10,0));
        assertEquals(1, dataManager.invoices.size());
    }

    @Test
    public void getInvoices() throws Exception {
        Client c = new Client("Test", "Test", "Test");
        dataManager.addClient(c);
        assertEquals(0, dataManager.getInvoices(c).size());
        dataManager.addInvoice(new Invoice(c, 2018,1,10,0));
        dataManager.addInvoice(new Invoice(c, 2018,2,20,0));

        List<Invoice> invoices = dataManager.getInvoices(c);
        assertEquals(2, invoices.size());
    }

}