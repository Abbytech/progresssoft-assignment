import abbytech.assignment.Deal;
import org.junit.Assert;
import org.junit.Test;

public class DealTest {
    @Test
    public void throwsErrorOnInvalidArguments() {
        Exception exception = null;
        Deal deal = null;
        try {
            deal = new Deal(0L, "Invalid",
                    "Invalid",
                    System.currentTimeMillis(), 1);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        Assert.assertNotNull(exception.getMessage(),exception);
        Assert.assertNull("Deal should be null",deal);
    }

    @Test
    public void createsValidDeal(){
        Deal deal = new Deal(0L, "JOD",
                "JOD",
                System.currentTimeMillis()/1000, 1);

        Assert.assertNotNull(deal);
        System.out.println(deal.toString());
    }
}
