package lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author benny.setiawan
 */
public class Snap {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public  CreateTransaction(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        String originalInput = apiConfiguration.getServerKey() + ":";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        Request request = new Request.Builder()
                .url(apiConfiguration.getSnapApiBaseUrl())
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + encodedString)
                .build();
//        System.out.println(json+" --- "+encodedString+" --- "+request);
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
        Response responseMsg = client.newCall(request).execute();
        System.out.println(responseMsg);
        return responseMsg;
    }

    public String CreateTransactionToken(String json) throws IOException {
        Map map = objectMapper.readValue(json, new TypeReference<Map>(){});
        String token = map.get("token").toString();
        return token;
    }

    public String CreateTransactionRedirectUrl(String json) throws IOException {
        Map map = objectMapper.readValue(json, new TypeReference<Map>(){});
        String redirectUrl = map.get("redirect_url").toString();
        return redirectUrl;
    }
}
