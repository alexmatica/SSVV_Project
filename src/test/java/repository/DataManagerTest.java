package repository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    private DataManager dataManager;

    @Before
    public void setUp() throws Exception {
        dataManager = new DataManager();

    }

    @org.junit.Test
    public void addClient() throws Exception {
        assertEquals(1,1);
    }

    @Test
    public void addInvoice() throws Exception {
        assertEquals(1,2);
    }

}