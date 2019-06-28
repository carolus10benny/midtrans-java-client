package midtrans.java.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Desktop;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lib.Snap;
import lib.apiConfiguration;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author benny.setiawan
 */
public class SnapMinimumRequest {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    String ChargeSnap(String json) throws IOException {
//      System.out.println(json); //Print All Body Request
        RequestBody body = RequestBody.create(JSON, json);
        String originalInput = "SB-Mid-server-PR6OZrsGpKzrxch9Uk5DnW7E" + ":";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        Request request = new Request.Builder()
                .url("https://app.stg.midtrans.com/snap/v1/transactions") //Sample Target Staging Environment
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + encodedString) //Sample AccountStaging OPDM095345
                .build();
        try (Response response = client.newCall(request).execute()) {
            return "responMidtrans: " + response.body().string();
        }
    }

    public static void main(String[] args) throws Exception {
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
        
        Snap snapLib = new Snap();
//        String responseMsg = snapLib.CreateTransaction(resultJson);
//        String responseMsg = new Snap().CreateTransaction(resultJson);
        Response responseMsg = snapLib.CreateTransaction(resultJson);
        System.out.println(responseMsg);
//        System.out.println("Nilai Token: " + snapLib.CreateTransactionToken(response)
//                + "Nilai URL Redirect" + snapLib.CreateTransactionRedirectUrl(response));
    }

    /* //Main untuk Method "ChargeSnap" 1 Class
    public static void main(String[] args) {
        SnapMinimumRequest testSnapMinimumRequest = new SnapMinimumRequest();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        int minimalAmount = 10000; //Dummy value of Amount
        
        HashMap transactionDetails = new HashMap();
        HashMap detailsTransaction = new HashMap();
        detailsTransaction.put("order_id", "Midtrans-"+timeStamp); //Dummy Value of orderId
        detailsTransaction.put("gross_amount", minimalAmount);
        transactionDetails.put("transaction_details", detailsTransaction);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionDetails);
        String response = testSnapMinimumRequest.ChargeSnap(resultJson);
        System.out.println(response);
    } */
}
