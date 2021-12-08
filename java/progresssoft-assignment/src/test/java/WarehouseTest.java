import abbytech.assignment.Deal;
import abbytech.assignment.Warehouse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WarehouseTest {

    private Warehouse warehouse;

    @Before
    public void initWarehouse(){
        warehouse = new Warehouse("jdbc:sqlite::memory",
                null,null);

    }

    @Test
    public void acceptsDeal(){
        long id = Math.round(Math.random() * 10000);

        Deal deal = new Deal(id, "JOD",
                "JOD",
                System.currentTimeMillis()/1000, 100.5f);


        Exception exception = null;
        try {
            warehouse.acceptDeal(deal);
        } catch (SQLException e) {
            exception = e;
        }
        if (exception!=null){
            System.out.println(exception.getMessage());
        }

        Assert.assertNull(exception);
    }

    @Test
    public void rejectsDuplicateDeal(){
        long id = 0;

        Deal deal = new Deal(id, "JOD",
                "JOD",
                System.currentTimeMillis()/1000, 100.5f);


        Exception exception = null;
        try {
            warehouse.acceptDeal(deal);
            warehouse.acceptDeal(deal);
        } catch (SQLException e) {
            exception = e;
        }

        Assert.assertNotNull(exception);
        System.out.println(exception.getMessage());
    }
}
