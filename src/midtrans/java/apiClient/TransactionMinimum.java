package midtrans.java.client;

/**
 *
 * @author benny.setiawan
 */
public class TransactionMinimum {
    private String order_id;
    private int gross_amount;

    public TransactionMinimum(String order_id, int gross_amount) {
        this.order_id = order_id;
        this.gross_amount = gross_amount;
    }

    /**
     * @return the order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the gross_amount
     */
    public int getGross_amount() {
        return gross_amount;
    }

    /**
     * @param gross_amount the gross_amount to set
     */
    public void setGross_amount(int gross_amount) {
        this.gross_amount = gross_amount;
    }
    
}