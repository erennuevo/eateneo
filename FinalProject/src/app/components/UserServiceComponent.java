package app.components;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.User;
import app.repositories.UserRepository;
import app.components.TwilioRequests;
import app.dto.UserDto;


@Component
public class UserServiceComponent {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TwilioComponent twilioComponent;
	
	
	@PostConstruct
	public void init() {
		if (userRepo.count() == 0) {
            User user1 = new User();
            user1.setName("Hannah");
            user1.setIdNumber("123456");
            user1.setPhoneNumber("+639123456789");
            userRepo.save(user1);

            User user2 = new User();
            user2.setName("Eren");
            user2.setIdNumber("654321");
            user2.setPhoneNumber("+639987654321");
            userRepo.save(user2);
        }
	}
	
	 public List<User> getAllUsers() {
		 return userRepo.findAll();
	 }
	 
	 
	 public User getUserById(String id) {
		 return userRepo.findByIdNumber(id);
	 }
	 
	 public User addNewUser(String name, String idNumber, String phoneNumber) {
		 User user = new User();
	     user.setName(name);
	     user.setIdNumber(idNumber);
	     user.setPhoneNumber(phoneNumber);
	     return userRepo.save(user); 
	 }
	 
	 
	   public String notifyAllUsers(String message) {
	        List<User> users = userRepo.findAll();

	        if (users.isEmpty()) {
	            return "No users found to notify.";
	        }

	        for (User user : users) {
	            twilioComponent.testSMS(user.getPhoneNumber(), message); 
	        }

	        return "Notification sent to all users!";
	    }


	   public User addNewUser(UserDto userDto) {
		    User user = new User();
		    user.setName(userDto.getName());
		    user.setIdNumber(userDto.getIdNumber());
		    user.setPhoneNumber(userDto.getPhoneNumber());

		    return userRepo.save(user);
		}
	 
	
}
