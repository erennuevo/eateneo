package app.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import app.components.NotificationServiceComponent;

@Path("/eateneo")
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationServiceComponent notificationService;

//    @POST
//    @Path("/notifyAllUsers")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public NotificationResponse notifyAllUsers(NotificationRequest request) {
//        try {
//        	notificationService.notifyAllUsers(request.getMessage());
//            return new NotificationResponse(request.getMessage(), "Success");
//        } 
//        catch (Exception e) {
//        	logger.error("Error sending notifications", e);
//            return new NotificationResponse("Failed to send message", "Error");
//        }
//    }


    public static class NotificationRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class NotificationResponse {
        private String message;
        private String status;

        public NotificationResponse(String message, String status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public String getStatus() {
            return status;
        }
    }
}
