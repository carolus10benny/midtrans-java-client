package midtrans.java.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author benny.setiawan
 */
public class CoreApiMinimumRequest {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    String ChargeCoreApi(String json) throws IOException {
      System.out.println(json); //Print All Body Request
      RequestBody body = RequestBody.create(JSON, json);
      String originalInput = "SB-Mid-server-3UbtL1dY8bZewXYI2bVVCOIi"+":";
      String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
      Request request = new Request.Builder()
          .url("https://api.sandbox.midtrans.com/v2/charge") //Sample Target Staging Environment
          .post(body)
        .addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Basic "+encodedString) //Sample AccountSandbox G076156273
          .build();
      try (Response response = client.newCall(request).execute()) {
        return "responMidtrans: "+response.body().string();
      }
    }

    public static void main(String[] args) throws Exception{
        CoreApiMinimumRequest testCoreApiMinimumRequest = new CoreApiMinimumRequest();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        int minimalAmount = 10000; //Dummy value of Amount
        String paymentChannel = "bca_klikpay";
        
        HashMap transactionDetails = new HashMap();
        HashMap detailsTransaction = new HashMap();
        detailsTransaction.put("order_id", "Midtrans-"+timeStamp); //Dummy Value of orderId
        detailsTransaction.put("gross_amount", minimalAmount);
        transactionDetails.put("transaction_details", detailsTransaction);
        transactionDetails.put("payment_type", paymentChannel); //Sample paymentType Charge
        
        HashMap paymentType = new HashMap();
        paymentType.put("misc_fee", 5000);
        paymentType.put("description", "Testing Java Midtrans");
        transactionDetails.put(paymentChannel, paymentType);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionDetails);
        String response = testCoreApiMinimumRequest.ChargeCoreApi(resultJson);
        System.out.println(response);
    }
}
