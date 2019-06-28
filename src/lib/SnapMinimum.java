package lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author benny.setiawan
 */
public class SnapMinimum {
    public static void main(String[] args) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            int minimalAmount = 10000; //Dummy value of Amount
            
            HashMap transactionDetails = new HashMap();
            HashMap detailsTransaction = new HashMap();
            detailsTransaction.put("order_id", "Midtrans-" + timeStamp); //Dummy Value of orderId
            detailsTransaction.put("gross_amount", minimalAmount);
            transactionDetails.put("transaction_details", detailsTransaction);
            
            ObjectMapper objectMapper = new ObjectMapper();
            String resultJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionDetails);
            apiConfiguration.setIsProduction(false);
            apiConfiguration.setServerKey("SB-Mid-server-PR6OZrsGpKzrxch9Uk5DnW7E");
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SnapMinimum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
