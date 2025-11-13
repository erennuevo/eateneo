package app.components;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.entities.Meal;
import app.entities.User;
import app.repositories.MealRepository;
import app.repositories.UserRepository;

@Component
public class NotificationServiceComponent {
//
//    private final String accountSid = "AC91673dc692b4d5485f46826384138880";
//    private final String authToken = "263381c8faa84509783eeffbc9f4c9fc";
//    private final String messageServiceSid = "MG67dc450d638a68e8dc14597514989b4b";
//    private final String baseUrl = "https://api.twilio.com/";
//    private final String endpointUrl = baseUrl + "2010-04-01/Accounts/" + accountSid + "/Messages.json";
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private MealRepository mealRepo;
//
//    // === Sends SMS to one user ===
//    public String sendSMS(String phoneNumber, String messageBody) {
//        try {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .build();
//
//            TwilioRequests req = retrofit.create(TwilioRequests.class);
//
//            String authorization = "Basic " + Base64.getEncoder()
//                    .encodeToString((accountSid + ":" + authToken).getBytes());
//
//            Call<ResponseBody> call = req.testSMS(
//                    phoneNumber,
//                    messageServiceSid,
//                    messageBody,
//                    authorization,
//                    endpointUrl
//            );
//
//            Response<ResponseBody> resp = call.execute();
//            String body = resp.isSuccessful()
//                    ? (resp.body() != null ? resp.body().string() : "")
//                    : (resp.errorBody() != null ? resp.errorBody().string() : "");
//
//            System.out.println("[" + resp.code() + "] Sent to " + phoneNumber + ": " + messageBody);
//            return "Twilio reply (" + resp.code() + "): " + body;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Twilio error: " + e.getMessage();
//        }
//    }
//
//    // === Runs every day at 12:00 NN ===
//    //@Scheduled(cron = "0 0 12 * * ?")
//    // CHANGED THIS FIRST TO EVERY 5 MINS
////    @Scheduled(cron = "0 */5 * * * ?") 
////    public void scheduleDailyRecommendation() {
////        List<User> users = userRepo.findAll();
////        List<Meal> meals = mealRepo.findAll();
////
////        if (users.isEmpty()) {
////            System.out.println("No users found for daily recommendation.");
////            return;
////        }
////        if (meals.isEmpty()) {
////            System.out.println("No meals found in the database!");
////            return;
////        }
////
////        // Pick a random meal from database
////        Meal randomMeal = meals.get(new Random().nextInt(meals.size()));
////        String message = "Lunch Recommendation: Try " + randomMeal.getName() +
////                (randomMeal.getDescription() != null ? " - (" + randomMeal.getDescription() : ")") + " today!";
////
////        System.out.println("Sending daily recommendation: " + message);
////
////        // Send SMS to all users
////        for (User user : users) {
////            sendSMS(user.getPhoneNumber(), message);
////        }
////    }
//    
//    public void notifyAllUsers(String message) {
//        List<User> users = userRepo.findAll();
//        for (User user : users) {
//            sendSMS(user.getPhoneNumber(), message);
//        }
//    }
}
