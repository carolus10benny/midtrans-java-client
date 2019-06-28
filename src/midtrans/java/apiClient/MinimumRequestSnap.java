package midtrans.java.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author benny.setiawan
 */
public class MinimumRequestSnap {
    private TransactionMinimum transaction_details;

    public MinimumRequestSnap(TransactionMinimum transaction_details) {
        this.transaction_details = transaction_details;
    }

    /**
     * @return the transaction_details
     */
    public TransactionMinimum getTransaction_details() {
        return transaction_details;
    }

    /**
     * @param transaction_details the transaction_details to set
     */
    public void setTransaction_details(TransactionMinimum transaction_details) {
        this.transaction_details = transaction_details;
    }
    
}