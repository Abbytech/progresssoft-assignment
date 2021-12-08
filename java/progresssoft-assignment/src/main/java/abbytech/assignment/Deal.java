package abbytech.assignment;

import java.sql.Date;
import java.util.Currency;

public class Deal {
    private long id;
    private final Currency orderingCurrencyISOCode;
    private final Currency toCurrencyISOCode;
    private final Date timestamp;
    private final float amount;

    public Deal(long id,
                String orderingCurrencyISOCode,
                String toCurrencyISOCode,
                long timestamp,
                float amount) throws IllegalArgumentException {
        if(amount<0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (timestamp<0){
            throw new IllegalArgumentException("Timestamp cannot be negative");
        }

        this.timestamp = new Date(timestamp);
        this.id = id;
        this.orderingCurrencyISOCode = Currency.getInstance(orderingCurrencyISOCode);
        this.toCurrencyISOCode = Currency.getInstance(toCurrencyISOCode);
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getOrderingCurrencyISOCode() {
        return orderingCurrencyISOCode.getCurrencyCode();
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode.getCurrencyCode();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", orderingCurrencyISOCode=" + orderingCurrencyISOCode +
                ", toCurrencyISOCode=" + toCurrencyISOCode +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
