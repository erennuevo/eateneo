package app.components;

import java.util.Base64;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import org.springframework.stereotype.Component;

@Component
public class TwilioComponent {

    private final String acctId = "AC91673dc692b4d5485f46826384138880";      
    private final String creds = "263381c8faa84509783eeffbc9f4c9fc";            
    private final String msgsid = "MG67dc450d638a68e8dc14597514989b4b"; 
    private final String url = "https://api.twilio.com/2010-04-01/Accounts/AC91673dc692b4d5485f46826384138880/Messages.json";

    public String testSMS(String toNumber, String messageBody) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.twilio.com/")
                    .build();

            TwilioRequests req = retrofit.create(TwilioRequests.class);

            byte[] encodedAuth = Base64.getEncoder().encode((acctId + ":" + creds).getBytes());
            final String authorization = "Basic " + new String(encodedAuth);

            Call<ResponseBody> call = req.testSMS(toNumber, msgsid, messageBody, authorization, url);
            Response<ResponseBody> resp = call.execute();

            String body = resp.body() != null ? resp.body().string() : resp.errorBody().string();

            System.out.println(resp.code() + " Message sent to " + toNumber + ": " + messageBody);

            return "twilioReply: Working: " + resp.code() + " | Body: " + body;

        } catch (Exception e) {
            e.printStackTrace();
            return "twilioReply: error: " + e.getMessage();
        }
    }
}
